package com.hx.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hx.reggie.domain.Setmeal;
import com.hx.reggie.dto.SetmealDto;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Description
 * @Author 229hexi
 * @Create 2022-10-02 16:33
 * @Version 1.0
 **/
public interface SetmealService extends IService<Setmeal> {
    /**
     * 新增套餐,并保存套餐和菜品的关联
     * @param setmealDto
     */
    void saveWithDish(SetmealDto setmealDto);

    /**
     * 删除套餐和对应菜品信息
     * @param ids
     */
    void deleteWithDish(List<Long> ids);

    /**
     * 起售，停售
     * @param status
     * @param ids
     */
    void status(int status,List<Long> ids);

    SetmealDto getByIdWithFlavor(Long id);

    void updateWithFlavor(SetmealDto setmealDto);
}
