package com.cinder.mybatis.mapper;

import com.cinder.mybatis.bean.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeMapper {

    Employee findById(int id);

    int insertEmployee(Employee employee);
}
