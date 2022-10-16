package com.hx.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hx.reggie.domain.ShoppingCart;
import com.hx.reggie.mapper.ShoppingCartMapper;
import com.hx.reggie.service.ShoppingCartService;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author 229hexi
 * @Create 2022-10-06 17:22
 * @Version 1.0
 **/
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
}
