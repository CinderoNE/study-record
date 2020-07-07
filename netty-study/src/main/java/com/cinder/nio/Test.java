package com.cinder.nio;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public abstract class Test implements AB{
    static int a;
    public class son{
        son(){
            System.out.println("ss");
        }
    }
    public Test(int x) {
    }

    protected abstract void cc();

    public Test(){

    }

    static void qq(){
        System.out.println("qq");
    }


     public static void main(String[] args) {
         System.out.println(new B().getValue());
         System.out.println(a);
         Byte b = 127;
         System.out.println(++b);
         List<Integer> integers = Arrays.asList(1, 3, 2);
         System.out.println(integers);
         Test test = null;
         test.qq();
         aa.fun();
     }



         public static class A  {
         protected int value;
         public A (int v) {
             setValue(v);
         }
         public void setValue(int value) {
             this.value= value;
         }
         public A a(){
             return new A(1);
         }
         public int getValue() {
             try {
                 value ++;
                 return value;
             } finally {
                 this.setValue(value);
                 System.out.println(value);
             }
         }
     }
     static class B extends A {
         public B () {
             super(5);
             setValue(getValue()- 3);
         }
         @Override
         public void setValue(int value) {
             super.setValue(2 * value);
         }
         @Override
         public B a(){
             return new B();
         }
     }
 }



 interface AB {
    public  void fun(int a);
    final static  int a = 1;





}

class ss{
    public int a(){
        return 1;
    }



}


abstract class aa implements AB{

    static void fun(){
        System.out.println("aa.fun");
    }
    aa(){}
}



