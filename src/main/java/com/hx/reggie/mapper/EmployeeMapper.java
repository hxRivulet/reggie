package com.hx.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hx.reggie.domain.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description
 * @Author 229hexi
 * @Create 2022-09-29 16:32
 * @Version 1.0
 **/
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

}
