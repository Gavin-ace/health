package com.bjpowernode.mapper;

import com.bjpowernode.entity.QueryPageBean;
import com.bjpowernode.pojo.Setmeal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SetmealMapper {
    void addSetmeal(Setmeal setmeal);

    void addSetmealCheckGroup(@Param("checkgroupId") Integer checkgroupId, @Param("setmealId") Integer setmealId);

    List<Setmeal> findPage(QueryPageBean queryPageBean);

    List<Setmeal> getAllSetmeal();

    Setmeal findById(Integer id);
}
