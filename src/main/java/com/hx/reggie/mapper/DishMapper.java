package com.hx.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hx.reggie.domain.Dish;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description
 * @Author 229hexi
 * @Create 2022-10-02 16:30
 * @Version 1.0
 **/
@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
