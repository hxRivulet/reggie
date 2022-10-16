package com.hx.reggie.dto;

import com.hx.reggie.domain.Setmeal;
import com.hx.reggie.domain.SetmealDish;
import lombok.Data;

import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
