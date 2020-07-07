package com.cinder.mybatis.bean;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Employee {
    private int id;
    private String lastName;
    private String email;
    private int sex;  //0 female male
    private Department department;
}
