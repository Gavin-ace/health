package com.bjpowernode.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.bjpowernode.entity.PageResult;
import com.bjpowernode.entity.QueryPageBean;
import com.bjpowernode.mapper.SetmealMapper;
import com.bjpowernode.pojo.CheckGroup;
import com.bjpowernode.pojo.Setmeal;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealMapper setmealMapper;

    @Override
    public void add(Integer[] checkgroupIds, Setmeal setmeal) {
        //1、保存检查组信息--->返回自增id
        setmealMapper.addSetmeal(setmeal);
        //2、保存关联表信息
        Integer setmealId = setmeal.getId();
        //shift+alt+m
        addSetmealCheckGroup(checkgroupIds, setmealId);
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        //分页条件
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());

        List<Setmeal> setmealList = setmealMapper.findPage(queryPageBean);

        //获取分页信息
        PageInfo<Setmeal> pageInfo = new PageInfo<>(setmealList);

        return new PageResult(pageInfo.getTotal(), pageInfo.getList());
    }

    @Override
    public List<Setmeal> getAllSetmeal() {
        return setmealMapper.getAllSetmeal();
    }

    private void addSetmealCheckGroup(Integer[] checkgroupIds, Integer setmealId) {
        for (Integer checkgroupId : checkgroupIds) {
            setmealMapper.addSetmealCheckGroup(checkgroupId,setmealId);
        }
    }

    @Override
    public Setmeal findById(Integer id) {
        return setmealMapper.findById(id);
    }
}
