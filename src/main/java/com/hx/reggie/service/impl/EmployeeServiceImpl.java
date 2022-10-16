package com.hx.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hx.reggie.domain.Employee;
import com.hx.reggie.mapper.EmployeeMapper;
import com.hx.reggie.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author 229hexi
 * @Create 2022-09-29 16:35
 * @Version 1.0
 **/
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
