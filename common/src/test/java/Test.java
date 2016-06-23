import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by vicykie on 2016/5/23.
 */
public class Test {
    public static void main(String[] args) {
        String str = "295/75R22.5-18,,";
        StringBuffer sb = new StringBuffer(str);
        System.out.println(sb.deleteCharAt(sb.lastIndexOf(",")));
        str = str.substring(str.lastIndexOf("R") + 1, str.lastIndexOf("-"));
        System.out.println(str);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Random random = new Random();
        int s = random.nextInt(999) % (999 - 100 + 1) + 100;
        String sn = format.format(new Date()) + s;
        System.out.println(sn);
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
}
