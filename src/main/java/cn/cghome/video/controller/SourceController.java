package cn.cghome.video.controller;


import cn.cghome.video.entity.Source;
import cn.cghome.video.service.ISourceService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author czl
 * @since 2019-07-04
 */
@RestController
@RequestMapping("/source")
@Api(value = "播放源管理", tags = "source", description = "播放源源管理")
public class SourceController {

    @Autowired
    private ISourceService sourceService;

    /**
     * 查询播放源
     *
     * @return
     * @author: czl
     * @date: 2019/5/15
     */
    @ApiOperation(value = "查询播放源", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "videoId", value = "视频资源id", required = true, dataType = "String", paramType = "query"),
    })
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Source> list(String videoId) {
        QueryWrapper<Source> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("video_id",videoId);
        return sourceService.list(queryWrapper);
    }

}
