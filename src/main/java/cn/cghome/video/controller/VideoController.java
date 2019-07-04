package cn.cghome.video.controller;


import cn.cghome.video.entity.Video;
import cn.cghome.video.service.IVideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author czl
 * @since 2019-07-04
 */
@RestController
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private IVideoService videoService;

    /**
     * 查询首页推荐视频资源
     *
     * @return
     * @author: czl
     * @date: 2019/5/15
     */
    @ApiOperation(value = "查询首页推荐视频资源", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页数 从1开始", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "视频类型 1:电影,2:电视剧,3:综艺,20:动漫", required = true, dataType = "String", paramType = "type")
    })
    @RequestMapping(value = "/listFavorite", method = RequestMethod.POST)
    public IPage listFavorite(Page page, String type) {
        QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", type);
        queryWrapper.orderByDesc("favorite_order");
        return videoService.page(page, queryWrapper);
    }

    /**
     * 查询视频资源
     *
     * @return
     * @author: czl
     * @date: 2019/5/15
     */
    @ApiOperation(value = "查询视频资源", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页数 从1开始", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "视频类型 1:电影,2:电视剧,3:综艺,20:动漫", required = true, dataType = "String", paramType = "type"),
            @ApiImplicitParam(name = "name", value = "视频名称", required = false, dataType = "String", paramType = "type")
    })
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public IPage list(Page page, String type, String name) {
        QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", type);
        queryWrapper.like("name", name);
        return videoService.page(page, queryWrapper);
    }

}
