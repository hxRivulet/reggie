package com.hx.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hx.reggie.common.CustomException;
import com.hx.reggie.domain.Dish;
import com.hx.reggie.domain.DishFlavor;
import com.hx.reggie.domain.Setmeal;
import com.hx.reggie.domain.SetmealDish;
import com.hx.reggie.dto.DishDto;
import com.hx.reggie.dto.SetmealDto;
import com.hx.reggie.mapper.SetmealMapper;
import com.hx.reggie.service.SetmealDishService;
import com.hx.reggie.service.SetmealService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description
 * @Author 229hexi
 * @Create 2022-10-02 16:32
 * @Version 1.0
 **/
@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

    @Autowired
    private SetmealDishService setmealDishService;
    @Override
    @Transactional
    public void saveWithDish(SetmealDto setmealDto) {
        //保存基本套餐信息
        this.save(setmealDto);

        //保存菜品关联信息
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        for (SetmealDish setmealDish : setmealDishes){
            setmealDish.setSetmealId(setmealDto.getId());
        }
        setmealDishService.saveBatch(setmealDishes);

    }

    @Transactional
    public void deleteWithDish(List<Long> ids) {
        //查询当前套餐状态
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Setmeal::getId,ids);
        queryWrapper.eq(Setmeal::getStatus,1);
        int count = this.count(queryWrapper);
        if(count > 0){
            //起售状态，不可删除
            throw new CustomException("当前套餐正在售卖中，不可删除");

        }
        //停售状态可以删除
        this.removeByIds(ids);

        LambdaQueryWrapper<SetmealDish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(SetmealDish::getSetmealId,ids);
        setmealDishService.remove(lambdaQueryWrapper);
    }

    @Override
    public void status(int status, List<Long> ids) {
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Setmeal::getId,ids);
        List<Setmeal> setmeals = this.list(queryWrapper);

        for (Setmeal setmeal : setmeals){
           setmeal.setStatus(status);
        }

        this.updateBatchById(setmeals);
    }

    @Override
    public SetmealDto getByIdWithFlavor(Long id) {
        SetmealDto setmealDto = new SetmealDto();
        //查询基本套餐信息
        Setmeal setmeal = this.getById(id);
        //数据拷贝
        BeanUtils.copyProperties(setmeal,setmealDto);
        //查询菜品信息
        LambdaQueryWrapper<SetmealDish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SetmealDish::getSetmealId,setmealDto.getId());
        List<SetmealDish> setmealDishes = setmealDishService.list(queryWrapper);

        setmealDto.setSetmealDishes(setmealDishes);

        return setmealDto;
    }

    @Override
    public void updateWithFlavor(SetmealDto setmealDto) {
        //保存基本的套餐信息
        this.updateById(setmealDto);
        //删除原有套餐的菜品
        LambdaQueryWrapper<SetmealDish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SetmealDish::getSetmealId,setmealDto.getId());
        setmealDishService.remove(queryWrapper);
        //添加新的菜品信息
        List<SetmealDish> flavors = setmealDto.getSetmealDishes();
        for (SetmealDish setmealDish : flavors){
            setmealDish.setSetmealId(setmealDto.getId());

        }
        setmealDishService.saveBatch(flavors);
    }


}

