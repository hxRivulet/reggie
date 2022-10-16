package com.hx.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hx.reggie.domain.OrderDetail;
import com.hx.reggie.mapper.OrderDetailMapper;
import com.hx.reggie.service.OrderDetailService;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author 229hexi
 * @Create 2022-10-08 17:04
 * @Version 1.0
 **/
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}
