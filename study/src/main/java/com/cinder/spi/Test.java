package com.cinder.spi;

import java.util.ServiceLoader;

/**
 * @author Cinder
 * @Description: SPI服务发现机制
 * @Date create in 2:08 2020/7/26/026
 * @Modified By:
 */
public class Test {
    public static void main(String[] args) {
        ServiceLoader<SpiDemoInterface> loader = ServiceLoader.load(SpiDemoInterface.class);
        loader.forEach(SpiDemoInterface::run);
    }
}
