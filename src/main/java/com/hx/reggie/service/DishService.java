package com.hx.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hx.reggie.domain.Dish;
import com.hx.reggie.dto.DishDto;

import java.util.List;

/**
 * @Description
 * @Author 229hexi
 * @Create 2022-10-02 16:31
 * @Version 1.0
 **/
public interface DishService extends IService<Dish> {
    /**
     * 新增菜品，同时插入菜品对应的口味数据，需要操作两张表：dish、dish_flavor
     * @param dishDto
     */
    void saveWithFlavor(DishDto dishDto);

    /**
     *根据id查询菜品信息和对应的口味信息
     * @param id
     */
    DishDto getByIdWithFlavor(Long id);

    void updateWithFlavor(DishDto dishDto);

    /**
     * 起售，停售
     * @param status
     * @param ids
     */
    void status(int status, List<Long> ids);
}
