package com.cinder.proxy;

public class UserManagerImpl implements UserManager{

    @Override
    public void addUser(String username, String password) {
        System.out.println("UserManagerImpl.addUser");
    }
}
