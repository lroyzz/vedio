package cn.cghome.video.util;

public class StringUtil {

    public static String subString(String source, String begin, String end){
        return source.substring(source.indexOf(begin)+begin.length(),source.indexOf(end));
    }
}
