package com.bjpowernode.service;

import com.bjpowernode.entity.PageResult;
import com.bjpowernode.entity.QueryPageBean;
import com.bjpowernode.pojo.CheckGroup;

import java.util.List;

public interface CheckGroupService {
    void add(Integer[] checkitemIds, CheckGroup checkGroup);

    PageResult findPage(QueryPageBean queryPageBean);

    CheckGroup findById(Integer id);

    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    void edit(Integer[] checkitemIds, CheckGroup checkGroup);

    List<CheckGroup> findAll();
}
