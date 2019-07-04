package cn.cghome.video.controller;


import cn.cghome.video.entity.Source;
import cn.cghome.video.service.ISourceService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
    })
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public List<Source> list(String videoId) {
        QueryWrapper<Source> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("video_id",videoId);
        return sourceService.list(queryWrapper);
    }

}
