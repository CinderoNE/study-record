package com.cinder.mianshi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Cinder
 * @Description:
 * @Date create in 19:35 2020/6/4/004
 * @Modified By:
 */
public class One extends Thread{
    public static void main(String[] args) {
        Integer[] nums = {10,7,2,4,7,62,3,4,2,1,8,9,19};
        List<Integer> collect = Stream.of(nums).collect(Collectors.toList());
        Collections.sort(collect);
        sort(nums);
        Arrays.stream(nums).forEach(System.out::println);
        One one = new One();
    }
    public static void sort(Integer[] arr){
        boolean swap = false;
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length - i-1; j++) {
                if(arr[j] > arr[j+1]){
                    int t = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = t;
                    swap = true;
                }
            }
            if(!swap) {
                return;
            }
        }
    }
}
