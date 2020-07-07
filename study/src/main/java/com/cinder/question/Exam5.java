package com.cinder.question;

public class Exam5 {
    int i;
    int j;
    static int s;

    {
        int i = 1;
        i++;
        j++;
        s++;
    }

    public void test(int j){
        j++;
        i++;
        s++;
    }
    public static void main(String[] args) {
        Exam5 e1 = new Exam5();
        Exam5 e2 = new Exam5();
        e1.test(10);
        e1.test(20);
        e2.test(30);
        System.out.println("e1.i = " + e1.i);
        System.out.println("e1.j = " + e1.j);
        System.out.println("e1.s = " + e1.s);
        System.out.println("e2.i = " + e2.i);
        System.out.println("e2.j = " + e2.j);
        System.out.println("e2.s = " + e2.s);
    }
}


