package cn.cghome.video.entity;

import org.apache.commons.codec.StringDecoder;
import org.apache.commons.text.translate.UnicodeEscaper;
import org.apache.commons.text.translate.UnicodeUnescaper;

public enum VideoTypeEnum {

    MOVIE("1"),
    TELEPLAY("2"),
    VARIETY("3"),
    ANIME("20");


    private String value = "0";

    private VideoTypeEnum(String value) {     //必须是private的，否则编译错误
        this.value = value;
    }

    public String val() {
        return this.value;
    }

}
