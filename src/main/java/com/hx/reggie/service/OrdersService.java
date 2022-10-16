package com.hx.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hx.reggie.domain.Orders;

/**
 * @Description
 * @Author 229hexi
 * @Create 2022-10-08 16:58
 * @Version 1.0
 **/
public interface OrdersService extends IService<Orders> {
    /**
     * 用户下单
     * @param orders
     */
    void submit(Orders orders);
}
