package com.cinder.question;

import java.util.Arrays;

public class Exam4 {


    public static void change(int j,String s,Integer b,int[] array,MyData my){
        j += 1;
        s += "world";
        b += 1;
        array[0] += 1;
        my.a += 1;
    }

    public static void main(String[] args) {
        int i = 1;
        String str = "hello";
        Integer a = 100;
        int[] arr = {1,2,3,4,5};
        MyData m  = new MyData();
        change(i,str,a,arr,m);

        System.out.println("i = " + i);
        System.out.println("str = " + str);
        System.out.println("a = " + a);
        System.out.println("arr = " + Arrays.toString(arr));
        System.out.println("m.a = " + m.a);
    }

}


class MyData{
    int a = 10;
}