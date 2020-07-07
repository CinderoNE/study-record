package com.cinder.mybatis.test;

import com.cinder.mybatis.bean.Department;
import com.cinder.mybatis.bean.Employee;
import com.cinder.mybatis.mapper.EmployeeMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MybatisTest01 {

    ApplicationContext ioc = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");


    @Test
    public void test(){
        EmployeeMapper bean = ioc.getBean(EmployeeMapper.class);
        Employee employee = new Employee();
        employee.setLastName("bat");
        employee.setSex(1);
        employee.setEmail("a@b.com");
        Department department = new Department();
        department.setId(1);
        employee.setDepartment(department);
        int i = bean.insertEmployee(employee);
        System.out.println("i = " + i);
        String[] beanDefinitionNames = ioc.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }
}
