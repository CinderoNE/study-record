package com.cinder.question;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("ac");
        list.add("abc");
        list.add("dd");
        list.add("yy");
        list.add("xx");
        System.out.println(list);
        ArrayList<Boolean> a = list.stream().map(s -> s.contains("a")).collect(Collectors.toCollection(ArrayList::new));
        System.out.println(a);



    }
}
