/**
 * Created by vicykie on 2016/5/23.
 */
public class Test {
    public static void main(String[] args) {
        String str = "7.00R15-12PR[CR907]";
        System.out.println(getSizeByGG(str));
//        String pattern = "R";//匹配单词
////        295/75R22.5
////        11.00R20
//        Pattern p = Pattern.compile(pattern);
//        Matcher matcher = p.matcher(str);
////        System.out.println(matcher.find());
//        int count = 0;
//        while (matcher.find()) {
//            count++;
////            System.out.println(matcher.start());
//            str = str.substring(matcher.end());
//            System.out.println(Double.valueOf(str));
////            System.out.println(matcher.end());
//        }
//        System.out.println(count);
    }


    private static String getSizeByGG(String gg) {

        String size = null;
        if (null == gg) {
            size = "0";
        }
        if (gg.contains("R") && gg.contains("-")) {
            size = gg.substring(gg.indexOf("R", 0) + 1, gg.lastIndexOf("-"));
        } else if (gg.contains("R")) {
            size = gg.substring(gg.lastIndexOf("R") + 1);
        } else if (gg.contains("-")) {
            size = gg.substring(gg.lastIndexOf("-") + 1);
        }
        if (size.contains("+"))
            size = size.replaceAll("\\+", "");
        if (size.contains("LT"))
            size = size.replaceAll("LT", "");
        if (16 <= Double.valueOf(size) && Double.valueOf(size) < 20) {
            size = "16";
        } else if (20 <= Double.valueOf(size)) {
            size = "20";
        }
        return size;
    }
}
