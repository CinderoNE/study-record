package com.cinder.spring.test;


import com.cinder.spring.beans.Book;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

    ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

    @Test
    public void bookTest(){
        Book b1 = ac.getBean(Book.class);
        Book b2 = ac.getBean(Book.class);
        System.out.println(b1 == b2);
        ac.getBean("cinder");



    }





}
