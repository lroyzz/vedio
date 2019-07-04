package cn.cghome.video.util;

public class EscapeUtil {

    public static String escape(String src) {
        int i;
        char j;
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length() * 6);
        for (i = 0; i < src.length(); i++) {
            j = src.charAt(i);
            if (Character.isDigit(j) || Character.isLowerCase(j)
                    || Character.isUpperCase(j))
                tmp.append(j);
            else if (j < 256) {
                tmp.append("%");
                if (j < 16)
                    tmp.append("0");
                tmp.append(Integer.toString(j, 16));
            } else {
                tmp.append("%u");
                tmp.append(Integer.toString(j, 16));
            }
        }
        return tmp.toString();
    }

    public static String unescape(String src) {
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length());
        int lastPos = 0, pos = 0;
        char ch;
        while (lastPos < src.length()) {
            pos = src.indexOf("%", lastPos);
            if (pos == lastPos) {
                if (src.charAt(pos + 1) == 'u') {
                    ch = (char) Integer.parseInt(src
                            .substring(pos + 2, pos + 6), 16);
                    tmp.append(ch);
                    lastPos = pos + 6;
                } else {
                    ch = (char) Integer.parseInt(src
                            .substring(pos + 1, pos + 3), 16);
                    tmp.append(ch);
                    lastPos = pos + 3;
                }
            } else {
                if (pos == -1) {
                    tmp.append(src.substring(lastPos));
                    lastPos = src.length();
                } else {
                    tmp.append(src.substring(lastPos, pos));
                    lastPos = pos;
                }
            }
        }
        return tmp.toString();
    }

    public static void main(String[] args) {
        //String s = "%u7b2c01%u96c6%24http%3A%2F%2Fwww.le.com%2Fptv%2Fvplay%2F27305812.html%23%u7b2c02%u96c6%24http%3A%2F%2Fwww.le.com%2Fptv%2Fvplay%2F27305890.html%23%u7b2c03%u96c6%24http%3A%2F%2Fwww.le.com%2Fptv%2Fvplay%2F27305988.html%23%u7b2c04%u96c6%24http%3A%2F%2Fwww.le.com%2Fptv%2Fvplay%2F27306058.html%23%u7b2c05%u96c6%24http%3A%2F%2Fwww.le.com%2Fptv%2Fvplay%2F27306158.html%23%u7b2c06%u96c6%24http%3A%2F%2Fwww.le.com%2Fptv%2Fvplay%2F27306248.html%23%u7b2c07%u96c6%24http%3A%2F%2Fwww.le.com%2Fptv%2Fvplay%2F27306366.html%23%u7b2c08%u96c6%24http%3A%2F%2Fwww.le.com%2Fptv%2Fvplay%2F27306484.html%23%u7b2c09%u96c6%24http%3A%2F%2Fwww.le.com%2Fptv%2Fvplay%2F27306585.html%23%u7b2c10%u96c6%24http%3A%2F%2Fwww.le.com%2Fptv%2Fvplay%2F27306702.html%23%u7b2c11%u96c6%24http%3A%2F%2Fwww.le.com%2Fptv%2Fvplay%2F27306792.html%23%u7b2c12%u96c6%24http%3A%2F%2Fwww.le.com%2Fptv%2Fvplay%2F27307079.html%23%u7b2c13%u96c6%24http%3A%2F%2Fwww.le.com%2Fptv%2Fvplay%2F27307164.html%23%u7b2c14%u96c6%24http%3A%2F%2Fwww.le.com%2Fptv%2Fvplay%2F27307275.html%23%u7b2c15%u96c6%24http%3A%2F%2Fwww.le.com%2Fptv%2Fvplay%2F27307410.html%23%u7b2c16%u96c6%24http%3A%2F%2Fwww.le.com%2Fptv%2Fvplay%2F27307561.html%23%u7b2c17%u96c6%24http%3A%2F%2Fwww.le.com%2Fptv%2Fvplay%2F27307714.html%23%u7b2c18%u96c6%24http%3A%2F%2Fwww.le.com%2Fptv%2Fvplay%2F27307856.html%23%u7b2c19%u96c6%24http%3A%2F%2Fwww.le.com%2Fptv%2Fvplay%2F27308023.html%23%u7b2c20%u96c6%24http%3A%2F%2Fwww.le.com%2Fptv%2Fvplay%2F27308190.html%23%u7b2c21%u96c6%24http%3A%2F%2Fwww.le.com%2Fptv%2Fvplay%2F27308308.html%23%u7b2c22%u96c6%24http%3A%2F%2Fwww.le.com%2Fptv%2Fvplay%2F27308417.html%23%u7b2c23%u96c6%24http%3A%2F%2Fwww.le.com%2Fptv%2Fvplay%2F27308558.html%23%u7b2c24%u96c6%24http%3A%2F%2Fwww.le.com%2Fptv%2Fvplay%2F27308677.html%23%u7b2c25%u96c6%24http%3A%2F%2Fwww.le.com%2Fptv%2Fvplay%2F27308757.html%23%u7b2c26%u96c6%24http%3A%2F%2Fwww.le.com%2Fptv%2Fvplay%2F27308837.html%23%u7b2c27%u96c6%24http%3A%2F%2Fwww.le.com%2Fptv%2Fvplay%2F27308903.html%23%u7b2c28%u96c6%24http%3A%2F%2Fwww.le.com%2Fptv%2Fvplay%2F27308969.html%23%u7b2c29%u96c6%24http%3A%2F%2Fwww.le.com%2Fptv%2Fvplay%2F27309028.html%23%u7b2c30%u96c6%24http%3A%2F%2Fwww.le.com%2Fptv%2Fvplay%2F27309109.html%23%u7b2c31%u96c6%24http%3A%2F%2Fwww.le.com%2Fptv%2Fvplay%2F27309169.html%23%u7b2c32%u96c6%24http%3A%2F%2Fwww.le.com%2Fptv%2Fvplay%2F27309220.html%23%u7b2c33%u96c6%24http%3A%2F%2Fwww.le.com%2Fptv%2Fvplay%2F27309281.html%23%u7b2c34%u96c6%24http%3A%2F%2Fwww.le.com%2Fptv%2Fvplay%2F27309355.html%23%u7b2c35%u96c6%24http%3A%2F%2Fwww.le.com%2Fptv%2Fvplay%2F27309435.html%23%u7b2c36%u96c6%24http%3A%2F%2Fwww.le.com%2Fptv%2Fvplay%2F27309517.html%23%u7b2c37%u96c6%24http%3A%2F%2Fwww.le.com%2Fptv%2Fvplay%2F27309616.html%23%u7b2c38%u96c6%24http%3A%2F%2Fwww.le.com%2Fptv%2Fvplay%2F27309694.html%23%u7b2c39%u96c6%24http%3A%2F%2Fwww.le.com%2Fptv%2Fvplay%2F27309786.html%23%u7b2c40%u96c6%24http%3A%2F%2Fwww.le.com%2Fptv%2Fvplay%2F27309852.html%23%u7b2c41%u96c6%24http%3A%2F%2Fwww.le.com%2Fptv%2Fvplay%2F27309921.html%23%u7b2c42%u96c6%24http%3A%2F%2Fwww.le.com%2Fptv%2Fvplay%2F27309968.html%23%u7b2c43%u96c6%24http%3A%2F%2Fwww.le.com%2Fptv%2Fvplay%2F27310020.html%23%u7b2c44%u96c6%24http%3A%2F%2Fwww.le.com%2Fptv%2Fvplay%2F27310061.html";
        String s = "%u9ad8%u6e05%24https%3A%2F%2Fvod.300hu.com%2F4c1f7a6atransbjngwcloud1oss%2F371492d6196326325064577025%2Fv.f30.mp4";
        System.out.println(unescape(s));
    }
}
