import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by vicykie on 2016/5/23.
 */
public class Test {
    public static void main(String[] args) {
        String str = "295/75R22.5";
        String pattern = "R";//匹配单词
//        295/75R22.5
//        11.00R20
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(str);
//        System.out.println(matcher.find());
        int count = 0;
        while (matcher.find()){
            count++;
            System.out.println(matcher.start());
            System.out.println(str.substring(matcher.end()));
            System.out.println(matcher.end());
        }
        System.out.println(count);
    }
}
