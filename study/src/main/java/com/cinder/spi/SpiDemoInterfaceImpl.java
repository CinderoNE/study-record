package com.cinder.spi;

/**
 * @author Cinder
 * @Description:
 * @Date create in 2:08 2020/7/26/026
 * @Modified By:
 */
public class SpiDemoInterfaceImpl implements SpiDemoInterface {
    @Override
    public void run() {
        System.out.println("SpiDemoInterfaceImpl.run");
    }
}
