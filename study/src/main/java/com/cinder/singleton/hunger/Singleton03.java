package com.cinder.singleton.hunger;

import java.io.IOException;
import java.util.Properties;

/**
 * 静态代码块
 */
public class Singleton03 {

    private Singleton03(String info){
        this.info = info;
    }

    private String info;

    public static final Singleton03 INSTANCE;



    static {
        Properties properties = new Properties();
        try {
            properties.load(Singleton03.class.getResourceAsStream("/singleton.properties"));
            INSTANCE = new Singleton03(properties.getProperty("info"));
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }



    @Override
    public String toString() {
        return "Singleton03{" +
                "info='" + info + '\'' +
                '}';
    }
}
