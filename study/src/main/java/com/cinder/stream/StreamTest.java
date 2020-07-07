package com.cinder.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;


/**
 * 偶数ID且年龄大于24岁，用户名转为大写且用户名字倒序，只输出一个用户名字
 */
public class StreamTest {

    public static void main(String[] args) {
        User user1 = new User();
        System.out.println("user1.b = " + user1.id);
        System.out.println("User.a = " + User.a);
        User user = new User(11, "a", 23);
        User user2 = new User(12, "b", 24);
        User user3 = new User(13, "c", 22);
        User user4 = new User(14, "d", 28);
        User user5 = new User(16, "e", 26);
        List<User> users = Arrays.asList(user, user2, user3, user4, user5);
        Optional<String> first = users.stream().filter(u -> (u.getId() & 1) == 0).filter(u -> u.getAge() > 24).
                map(u -> u.getUserName().toUpperCase()).sorted(Comparator.reverseOrder()).limit(1).findFirst();
        System.out.println(first.get());
    }
}


@AllArgsConstructor
@Data
@NoArgsConstructor
class User {
    public int id;
    private String userName;
    private int age;

    public static int a;

}
