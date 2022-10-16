package com.hx.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hx.reggie.common.BaseContext;
import com.hx.reggie.common.Result;
import com.hx.reggie.domain.Dish;
import com.hx.reggie.domain.ShoppingCart;
import com.hx.reggie.dto.DishDto;
import com.hx.reggie.dto.SetmealDto;
import com.hx.reggie.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description
 * @Author 229hexi
 * @Create 2022-10-06 17:23
 * @Version 1.0
 **/
@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * 添加购物车
     * @param shoppingCart
     * @return
     */
    @PostMapping("/add")
    public Result<ShoppingCart> add(@RequestBody ShoppingCart shoppingCart){
        //设置当前用户id
        Long id = BaseContext.getId();
        shoppingCart.setUserId(id);
        Long dishId = shoppingCart.getDishId();

        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId,id);

        if(dishId != null){
            //当前添加的是菜品
            queryWrapper.eq(ShoppingCart::getDishId,dishId);
        }else {
            //当前添加的是套餐
            queryWrapper.eq(ShoppingCart::getSetmealId,shoppingCart.getSetmealId());
        }
        //判断当前菜品或套餐是否在购物车中
        ShoppingCart cartServiceOne = shoppingCartService.getOne(queryWrapper);

        if(cartServiceOne != null){
            //购物车中存在，number+1
            Integer number = cartServiceOne.getNumber();
            cartServiceOne.setNumber(number +1);
            shoppingCartService.updateById(cartServiceOne);
        }else {
            //购物车中不存在，添加购物车，number默认为1
            shoppingCart.setNumber(1);
            shoppingCart.setCreateTime(LocalDateTime.now());
            shoppingCartService.save(shoppingCart);
            cartServiceOne = shoppingCart;
        }

        return Result.success(cartServiceOne);

    }

    /**
     * 减少购物车
     * @param shoppingCart
     * @return
     */
    @PostMapping("/sub")
    public Result<ShoppingCart> sub(@RequestBody ShoppingCart shoppingCart){
        //获取当前用户id
        Long userId = BaseContext.getId();
        Long dishId = shoppingCart.getDishId();

        //获取当前用户购物车
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId,userId);

        if(dishId != null){
            //当前选中的是菜品
            queryWrapper.eq(ShoppingCart::getDishId,dishId);
        }else {
            //当前选中的是套餐
            queryWrapper.eq(ShoppingCart::getSetmealId,shoppingCart.getSetmealId());
        }
        ShoppingCart cartServiceOne = shoppingCartService.getOne(queryWrapper);

        //获取购物车中当前菜品数量
        Integer number = cartServiceOne.getNumber();
        //如果>1，则数量减1
        if(number > 1){
            cartServiceOne.setNumber(number - 1);
            shoppingCartService.updateById(cartServiceOne);
        }else {
            //如果为1，则删除该菜品
            shoppingCartService.remove(queryWrapper);
        }

        return Result.success(cartServiceOne);
    }

    /**
     * 查看购物车
     * @return
     */
    @GetMapping("/list")
    public Result<List<ShoppingCart>> list(){

        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId,BaseContext.getId());

        List<ShoppingCart> list = shoppingCartService.list(queryWrapper);
        return Result.success(list);

    }

    /**
     * 清空购物车
     * @return
     */
    @DeleteMapping("/clean")
    public Result<String> clean(){

        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId,BaseContext.getId());

        shoppingCartService.remove(queryWrapper);
        return Result.success("清空购物车成功");

    }
}
