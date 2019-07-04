package cn.cghome.video.controller;

import cn.cghome.video.entity.Source;
import cn.cghome.video.task.AutoInitVideoTask;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.List;

/**
 * <p>
 *  系统管理控制器
 * </p>
 *
 * @author czl
 * @since 2019-07-04
 */
@RestController
@RequestMapping("/source")
public class SysController {

    @Autowired
    private AutoInitVideoTask autoInitVideoTask;


    /**
     * 手动初始化
     *
     * @return
     * @author: czl
     * @date: 2019/5/15
     */
    @ApiOperation(value = "手动初始化", httpMethod = "GET")
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/init", method = RequestMethod.POST)
    public Integer init(String videoId) {
        try {
            autoInitVideoTask.init();
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }
}
