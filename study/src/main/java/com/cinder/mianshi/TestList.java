package com.cinder.mianshi;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Cinder
 * @Description:
 * @Date create in 17:46 2020/6/13/013
 * @Modified By:
 */
public class TestList {
    public static void main(String[] args) {
        Stream<String> stringStream = Stream.of("1", "2", "3");
        List<String> collect = stringStream.collect(Collectors.toList());
        collect.removeIf(s -> s.equals("1"));
        System.out.println(collect);
    }
}
