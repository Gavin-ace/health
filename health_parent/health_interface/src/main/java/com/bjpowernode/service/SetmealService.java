package com.bjpowernode.service;

import com.bjpowernode.entity.PageResult;
import com.bjpowernode.entity.QueryPageBean;
import com.bjpowernode.pojo.Setmeal;

import java.util.List;

public interface SetmealService {
    void add(Integer[] checkgroupIds, Setmeal setmeal);

    PageResult findPage(QueryPageBean queryPageBean);

    List<Setmeal> getAllSetmeal();

    Setmeal findById(Integer id);
}
