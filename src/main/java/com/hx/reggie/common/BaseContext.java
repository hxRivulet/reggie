package com.hx.reggie.common;

/**
 * @Description 基于ThreadLocal封装的工具类，用于保存和获取当前用户的Id
 * @Author 229hexi
 * @Create 2022-10-01 16:39
 * @Version 1.0
 **/
public class BaseContext {
    public static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    /**
     * 设置id
     * @param id
     */
    public static void setId(Long id){
        threadLocal.set(id);
    }

    /**
     * 获取Id
     * @return
     */
    public static Long getId(){
       return threadLocal.get();
    }
}
