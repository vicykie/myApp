import org.vicykie.framework.myApp.common.entity.authority.User;
import org.vicykie.framework.myApp.common.enums.Status;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by vicykie on 2016/6/6.
 */
public class TestLambda {
    public static void main(String[] args) {

        List<User> vicyList = new ArrayList<User>() {
            {
                add(new User("vi", "vi33cy", Status.ENABLE, 12, 28));
                add(new User("vi1", "viscy", Status.ENABLE, 122, 20));
                add(new User("vi21", "vic1y", Status.ENABLE, 42, 16));
                add(new User("vi23", "vicyk", Status.ENABLE, 32, 14));
                add(new User("vi33", "v1", Status.ENABLE, 14, 21));
                add(new User("vi33", "v2", Status.ENABLE, 13, 21));
                add(new User("vi33", "vi3y", Status.ENABLE, 14, 15));
            }
        };

        //遍历所有
        vicyList.forEach(user -> System.out.printf("username:%s  name:%s\n", user.getUsername(), user.getName()));
        Predicate<User> nameEqual = user -> user.getName().equals("vicyk");
        //显示name==vicyk的
        vicyList.stream().filter(nameEqual).forEach(user -> System.out.println(user.getName()));
        //age <20 ;
        Predicate<User> ageLess = user -> user.getAge() < 20;
        //age 降序
        vicyList.stream().filter(ageLess).sorted((p, p2) ->
                p2.getAge() - p.getAge()  //降序
        ).forEachOrdered(user -> System.out.println(user.getAge()));
        //年龄最大的
        User u = vicyList.stream().max((p1, p2) -> p1.getAge() - p2.getAge()).get();
        System.out.println(u.getName() + ": " + u.getAge());
        //age<25 && score >20
        List<User> userList = vicyList.stream().filter(user -> user.getAge() < 25 && user.getScore() > 20).collect(Collectors.toList());
        userList.stream().forEach(user -> System.out.println(user.getName()));
        //批量设置对象值
        userList.stream().forEach(user -> user.setName("aaaa"));
        userList.stream().forEach(user -> System.out.println(user.getName()));
        //统计  分数之和
        int scoreSum = vicyList.parallelStream().mapToInt(user -> user.getScore()).sum();
        System.out.println(scoreSum);
        //统计
        IntSummaryStatistics statics = vicyList.stream().mapToInt(user -> user.getAge()).summaryStatistics();
        System.out.println("max " + statics.getMax());
        System.out.println("min " + statics.getMin());
        System.out.println("count " + statics.getCount());
        System.out.println("average " + statics.getAverage());
        System.out.println("sum " + statics.getSum());
    }

}
