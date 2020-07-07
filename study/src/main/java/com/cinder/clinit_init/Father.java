package com.cinder.clinit_init;

public class Father {

    protected int a = test();
    private static int b = method();


    static {
        System.out.println("(1)");
    }
    {
        System.out.println("(2)");
    }

    public int test(){
        System.out.println("(3)");
        return 1;
    }

    public static int method(){
        System.out.println("(4)");
        return 2;
    }

    Father(){
        System.out.println("(5)");
    }
}
