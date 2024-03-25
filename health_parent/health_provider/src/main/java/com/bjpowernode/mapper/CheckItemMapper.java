package com.bjpowernode.mapper;

import com.bjpowernode.entity.QueryPageBean;
import com.bjpowernode.pojo.CheckItem;

import java.util.List;

public interface CheckItemMapper {
    void add(CheckItem checkItem);

    List<CheckItem> findPage(QueryPageBean queryPageBean);

    void delete(Integer id);

    List<CheckItem> findAll();
}
