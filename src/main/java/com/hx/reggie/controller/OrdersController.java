package com.hx.reggie.controller;

import com.hx.reggie.common.Result;
import com.hx.reggie.domain.Orders;
import com.hx.reggie.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author 229hexi
 * @Create 2022-10-08 17:02
 * @Version 1.0
 **/
@RestController
@RequestMapping("/order")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    /**
     * 用户下单
     * @param orders
     * @return
     */
    @PostMapping("/submit")
    public Result<String> submit(@RequestBody Orders orders){

        ordersService.submit(orders);
        return Result.success("下单成功");

    }

}
