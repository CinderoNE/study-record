package com.cinder.bio;

import java.util.ArrayList;

public class Test {
    static int i ;
    public static  String CINDER = "cinder";
    static{
        System.out.println("11");

    }


     Test(){
        this(3);
    }

    Test(int a){
        i = a;
    }


    public static void main(String[] args) {
        int f;
        System.out.println(4 & 7);
        System.out.println(Math.cos(Math.toRadians(42)));
        CINDER += '1';
        double d = 0.9233d;
        int[] dp = new int[11];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < 11; i++) {
            dp[i] = dp[i-1]+dp[i-2];
        }
        System.out.println(dp[8]);
        System.out.println(dp[10]);
        System.out.println("is" + (100 + 5));
        Object o = new Object(){
            public boolean equals(Object obj) {
                return true;
            }
        };
        System.out.println(o.equals("ss"));
        byte a = 127;
        byte b = 127;
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(12);
        integers.remove(1);
        integers.stream().forEach(System.out::println);
        float fl = 5.1f;
        long in = (long) fl;
        System.out.println("/101");
        long ll = 1;
        String s = CINDER.toUpperCase();
        System.out.println(s);
        System.out.println(CINDER);
        String s1 = "hello";
        String s2 = "hello";
        System.out.println(s1.equals(s2));
    }

    void main(){

    }

    public final void f(){
        final int a;
    }
}
