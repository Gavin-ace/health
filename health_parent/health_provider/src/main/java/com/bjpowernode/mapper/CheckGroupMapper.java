package com.bjpowernode.mapper;

import com.bjpowernode.entity.QueryPageBean;
import com.bjpowernode.pojo.CheckGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CheckGroupMapper {
    void addCheckGroup(CheckGroup checkGroup);

    /**
     * 1、map
     * 2、pojo
     * 3、
     * @param checkGroupId
     * @param checkitemId
     */
    void addCheckGroupCheckItem(@Param("checkGroupId") Integer checkGroupId, @Param("checkItemId") Integer checkitemId);

    List<CheckGroup> findPage(QueryPageBean queryPageBean);

    CheckGroup findById(Integer id);

    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    void edit(CheckGroup checkGroup);

    void deleteCheckGroupCheckItemByCheckGroupId(Integer checkGroupId);

    List<CheckGroup> findAll();
}
