package cn.cghome.video.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author czl
 * @since 2019-07-04
 */
@TableName("video")
@ApiModel(value="Video对象", description="")
public class Video extends Model<Video> {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String type;

    private String img;

    private String maskTxt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    public String getMaskTxt() {
        return maskTxt;
    }

    public void setMaskTxt(String maskTxt) {
        this.maskTxt = maskTxt;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Video{" +
        "id=" + id +
        ", name=" + name +
        ", type=" + type +
        ", img=" + img +
        ", maskTxt=" + maskTxt +
        "}";
    }
}
