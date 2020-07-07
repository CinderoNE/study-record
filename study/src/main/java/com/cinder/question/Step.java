package com.cinder.question;

public class Step {
    public static void main(String[] args) {
         System.out.println(step(40));

    }

    public static int step(int n){
        if(n<1){
            throw new IllegalArgumentException("n不能小于1");
        }
        if(n<=2){
            return n;
        }
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for(int i=2;i<n;i++){
            dp[i] = dp[i-1]+dp[i-2];
        }
        return dp[n-1];

    }
}
