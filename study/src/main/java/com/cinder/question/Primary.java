package com.cinder.question;

public class Primary {
    public static void main(String[] args) {
        System.out.println(fib(10));

    }

    public static String fib(int num){
        String dp[] = new String[num];
        dp[0] = "1";
        dp[1] = "1";
        for (int i = 2; i < num; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[num-1];
    }
}
