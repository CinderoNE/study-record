package com.cinder.mianshi;

/**
 * @author Cinder
 * @Description:
 * @Date create in 16:03 2020/6/5/005
 * @Modified By:
 */
public class TestA {
    public void a(){
        System.out.println("TestA.a");
    }

    public void b(){
        System.out.println("TestA.b");
    }
    public static void main(String[] args) {
        TestB testB = new TestB();
        testB.aa();
    }
}

class TestB extends TestA{

    @Override
    public void a(){
        System.out.println("TestB.a");
    }

    public void aa(){
        super.a();
        a();
        super.b();
        b();
    }
}
