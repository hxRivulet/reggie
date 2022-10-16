package com.hx.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hx.reggie.domain.Category;

/**
 * @Description
 * @Author 229hexi
 * @Create 2022-10-02 15:22
 * @Version 1.0
 **/
public interface CategoryService extends IService<Category> {

    void remove(Long id);
}
