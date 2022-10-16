package com.hx.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hx.reggie.domain.Orders;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description
 * @Author 229hexi
 * @Create 2022-10-08 16:57
 * @Version 1.0
 **/
@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {
}
