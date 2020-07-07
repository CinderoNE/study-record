package com.cinder.question;

import java.util.Arrays;
import java.util.List;

public class Exam6 {
    public static void main(String[] args) {
        String[] flags = {"1","2","3"};
        List<String> list = Arrays.asList(flags);
        list.add("4");
        list.forEach(s -> System.out.println(s));
    }
}

