package com.cinder.mianshi;

/**
 * @author Cinder
 * @Description:
 * @Date create in 19:49 2020/6/4/004
 * @Modified By:
 */
public class Two {
    public static void main(String[] args) {
        System.out.println(fib(8));
    }

    public static int fib(int n){
        if(n <= 0){
            return -1;
        }
        if(n == 1 || n == 2) {
            return 1;
        }
        return fib(n-1)+fib(n-2);
    }
}
