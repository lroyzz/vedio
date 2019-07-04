package cn.cghome.video.task;

import cn.cghome.video.entity.Source;
import cn.cghome.video.entity.Video;
import cn.cghome.video.entity.VideoTypeEnum;
import cn.cghome.video.service.ISourceService;
import cn.cghome.video.service.IVideoService;
import cn.cghome.video.util.EscapeUtil;
import cn.cghome.video.util.StringUtil;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;

@Component
public class AutoInitVideoTask {

    private static String SERVER_HOST = "http://www.zhumimi.cn";

    private static Integer PAGE_FIRST = 1;

    @Autowired
    private IVideoService videoService;

    @Autowired
    private ISourceService sourceService;

    /**
     * @param type    1:电影,2:电视剧,3:综艺,20:动漫
     * @param pageStr 从1开始
     * @return
     */
    public static String getPageUrl(VideoTypeEnum type, Integer pageStr) {
        return "/?m=vod-type-id-" + type.val() + "-pg-" + pageStr + ".html";
    }

    /**
     * 获取document元素
     * @return
     */
    public static Document getDoc(String url) {
        Connection con = Jsoup
                .connect(url);// 获取连接
        con.header("User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0");// 配置模拟浏览器
        Connection.Response rs = null;// 获取响应
        try {
            rs = con.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Document doc = Jsoup.parse(rs.body());// 转换为Dom树
        return doc;
    }

    /**
     * 获取分页document元素
     * @param videoTypeEnum
     * @param pageIndex
     * @return
     */
    public static Document getPageDoc(VideoTypeEnum videoTypeEnum, int pageIndex) {
        return getDoc(SERVER_HOST + getPageUrl(videoTypeEnum, pageIndex));
    }

    /**
     * 获取document元素
     * @param detailUrl
     * @return
     */
    public static Document getOneDoc(String detailUrl) {
        return getDoc(SERVER_HOST + detailUrl);
    }

    /**
     * 获取页数
     *
     * @param videoTypeEnum
     * @return
     */
    public static int getPageCount(VideoTypeEnum videoTypeEnum) {
        Document doc = getPageDoc(VideoTypeEnum.MOVIE, PAGE_FIRST);

        Element pageEle = doc.selectFirst(".mod_pagenav");
        String pageText = pageEle.text();
        String pageStr = pageText.substring(pageText.indexOf("/") + 1, pageText.indexOf("页"));
        int pageCount = Integer.parseInt(pageStr);
        return pageCount;
    }

    /**
     * 获取分页记录
     *
     * @param videoTypeEnum
     * @param pageIndex
     * @return
     */
    public void initPageData(VideoTypeEnum videoTypeEnum, int pageIndex) throws UnsupportedEncodingException, MalformedURLException {
        Document doc = getPageDoc( videoTypeEnum, pageIndex);

        Elements elements = doc.select(".figures_list .list_item");
        List<Video> videoList = new ArrayList<>();
        for (Element element : elements) {
            Video video = new Video();
            Element figure = element.selectFirst(".figure");
            Element mask_txt = element.selectFirst(".mask_txt");
            Element figureTitle = element.selectFirst(".figure_title");
            String style = figure.attr("style");

            String href = figure.attr("href");
            String name = figureTitle.text();
            String img = style.substring(style.indexOf("url(") + 4, style.indexOf(")"));
            String maskTxt = mask_txt.text();

            String id = href.substring(href.indexOf("-id-") + 4, href.indexOf(".html"));
            video.setId(id);
            video.setName(name);
            video.setType( videoTypeEnum.val());
            video.setImg(img);
            video.setMaskTxt(maskTxt);
            System.out.println(video);
            videoList.add(video);

            Document detailDoc = getOneDoc(href);
            Elements detailSources = detailDoc.select(".play_list_num li a");

            List<Source> sourceList = new ArrayList<>();
            if (!CollectionUtils.isEmpty(detailSources)){
                sourceService.remove(new QueryWrapper<Source>().eq("video_id",id));
                for (Element detailSource : detailSources) {
                    // 删除淘宝优惠券链接
                    if (detailSource.text().contains("淘宝优惠券")){
                        continue;
                    }
                    String sourceName = detailSource.text();
                    String playHref = detailSource.attr("href");
                    Document oneDoc = getOneDoc(playHref);
                    Element macPlayer = oneDoc.selectFirst(".MacPlayer");
                    Element script = macPlayer.selectFirst("script");
                    String scriptStr = script.toString();
                    String palyUrl = StringUtil.subString(scriptStr, "mac_url=unescape('", "')");


                    palyUrl = EscapeUtil.unescape(palyUrl);
                    String[] splits = palyUrl.split("#");
                    if (splits.length > 1){
                        Map<String ,String> nameUrlMap = new HashMap<>();
                        for (String split : splits) {
                            String[] nameAndUrl = split.split("\\$");
                            nameUrlMap.put(nameAndUrl[0],nameAndUrl[1]);
                        }
                        palyUrl = nameUrlMap.get(sourceName);
                    } else{
                        palyUrl = palyUrl.split("\\$")[1];
                    }

                    Source source = new Source();
                    source.setVideoId(id);
                    source.setName(sourceName);
                    source.setUrl(palyUrl);
                    sourceList.add(source);
                }
                sourceService.saveBatch(sourceList);
            }
        }
        videoService.saveOrUpdateBatch(videoList);
    }

    /**
     * 初始化数据
     * @throws UnsupportedEncodingException
     * @throws MalformedURLException
     */
    public void init() throws UnsupportedEncodingException, MalformedURLException {
        List<VideoTypeEnum> videoTypeEnums = Arrays.asList(VideoTypeEnum.TELEPLAY);

        for (VideoTypeEnum videoTypeEnum : videoTypeEnums) {
            int pageCount = getPageCount(videoTypeEnum);
            int pageIndex = 1;

            while (pageIndex<= pageCount){
                initPageData(videoTypeEnum, pageIndex);
                pageIndex++;
                break;
            }
        }
    }
}
