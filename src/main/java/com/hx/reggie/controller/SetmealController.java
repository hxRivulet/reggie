package com.hx.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hx.reggie.common.Result;
import com.hx.reggie.domain.Category;
import com.hx.reggie.domain.Setmeal;
import com.hx.reggie.dto.DishDto;
import com.hx.reggie.dto.SetmealDto;
import com.hx.reggie.service.CategoryService;
import com.hx.reggie.service.SetmealDishService;
import com.hx.reggie.service.SetmealService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author 229hexi
 * @Create 2022-10-04 15:38
 * @Version 1.0
 **/
@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Autowired
    private SetmealDishService setmealDishService;
    @Autowired
    private SetmealService setmealService;
    @Autowired
    private CategoryService categoryService;

    /**
     * 新增套餐
     * @return
     */
    @PostMapping
    public Result<String> save(@RequestBody SetmealDto setmealDto){

        setmealService.saveWithDish(setmealDto);
        return Result.success("添加成功");
    }

    /**
     * 套餐信息分页展示
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("page")
    public Result<Page> page(int page,int pageSize,String name){
        Page<Setmeal> pageInfo = new Page<>(page,pageSize);
        Page<SetmealDto> setmealDtoPage = new Page<>();

        BeanUtils.copyProperties(pageInfo,setmealDtoPage,"records");
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotEmpty(name),Setmeal::getName,name);
        queryWrapper.orderByDesc(Setmeal::getUpdateTime);
        setmealService.page(pageInfo);

        List<Setmeal> setmeals = pageInfo.getRecords();
        List<SetmealDto> setmealDtos = new ArrayList<>();

        for (Setmeal setmeal : setmeals){
            SetmealDto setmealDto = new SetmealDto();
            BeanUtils.copyProperties(setmeal,setmealDto);
            Category category = categoryService.getById(setmeal.getCategoryId());
            if(category != null){
                String categoryName = category.getName();
                setmealDto.setCategoryName(categoryName);
            }
            setmealDtos.add(setmealDto);
        }
        setmealDtoPage.setRecords(setmealDtos);
        return Result.success(setmealDtoPage);
    }

    /**
     * 删除套餐和对应的菜品信息
     * @param ids
     * @return
     */
    @DeleteMapping
    public Result<String> delete(@RequestParam List<Long> ids){
        setmealService.deleteWithDish(ids);

        return Result.success("删除成功");
    }

    /**
     * 起售，停售
     * @param status
     * @param ids
     * @return
     */
    @PostMapping("status/{status}")
    public Result<String> status(@PathVariable int status,@RequestParam List<Long> ids){

        setmealService.status(status,ids);
        return Result.success("修改成功");
    }

    /**
     * 根据id查询套餐和菜品信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<SetmealDto> get(@PathVariable Long id){
        SetmealDto setmealDto = setmealService.getByIdWithFlavor(id);

        return Result.success(setmealDto);
    }


    /**
     * 修改套餐和菜品信息
     * @param setmealDto
     * @return
     */
    @PutMapping
    public Result<String> update(@RequestBody SetmealDto setmealDto){

        setmealService.updateWithFlavor(setmealDto);
        return Result.success("修改成功");
    }

    /**
     * 根据条件查询分类数据
     * @param setmeal
     * @return
     */
    @GetMapping("/list")
    public Result<List<Setmeal>> list(Setmeal setmeal){
        //添加条件查询
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Setmeal::getStatus,1);
        queryWrapper.eq(setmeal.getCategoryId() != null,Setmeal::getCategoryId,setmeal.getCategoryId());
        //添加排序条件
        queryWrapper.orderByDesc(Setmeal::getUpdateTime);

        List<Setmeal> list = setmealService.list(queryWrapper);

        return Result.success(list);
    }
}
