package com.hx.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hx.reggie.common.CustomException;
import com.hx.reggie.domain.Category;
import com.hx.reggie.domain.Dish;
import com.hx.reggie.domain.Setmeal;
import com.hx.reggie.mapper.CategoryMapper;
import com.hx.reggie.service.CategoryService;
import com.hx.reggie.service.DishService;
import com.hx.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author 229hexi
 * @Create 2022-10-02 15:23
 * @Version 1.0
 **/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private DishService dishService;
    @Autowired
    private SetmealService setmealService;

    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId,id);
        int discount = dishService.count(dishLambdaQueryWrapper);


        //判断当前分类是否包含菜品，包含则抛出异常
        if(discount > 0){
            throw  new CustomException("当前分类包含了菜品，不能删除");
        }
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
        int setCount = setmealService.count(setmealLambdaQueryWrapper);
        //判断当前分类是否包含套餐，包含则抛出异常
        if(setCount > 0){
            throw new CustomException("当前分类包含了菜品，不能删除");
        }
        //正常删除
        categoryMapper.deleteById(id);

    }
}
