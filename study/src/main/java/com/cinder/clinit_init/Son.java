package com.cinder.clinit_init;

public class Son extends Father {
    private int a = test();
    private static int b = method();


    static {
        System.out.println("(6)");
    }
    {
        System.out.println("(7)");
    }

    public int test(){
        System.out.println("(8)");
        return 1;
    }

    public static int method(){
        System.out.println("(9)");
        return 2;
    }

    Son(){
        System.out.println("(10)");
    }

    public static void main(String[] args) {
        new Son();
    }
}
