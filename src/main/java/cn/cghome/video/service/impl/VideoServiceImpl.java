package cn.cghome.video.service.impl;

import cn.cghome.video.entity.Video;
import cn.cghome.video.mapper.VideoMapper;
import cn.cghome.video.service.IVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author czl
 * @since 2019-07-04
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements IVideoService {

}
