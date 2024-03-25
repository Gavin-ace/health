package com.bjpowernode.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.bjpowernode.mapper.OrderSettingMapper;
import com.bjpowernode.pojo.OrderSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {

    @Autowired
    private OrderSettingMapper orderSettingMapper;

    @Override
    public void addOrderSetting(List<OrderSetting> orderSettingList) {
        for (OrderSetting orderSetting : orderSettingList) {
            setOrderSetting(orderSetting);
        }
    }

    private void setOrderSetting(OrderSetting orderSetting) {
        //判断当前日期是否已经进行了预约设置
        long count = orderSettingMapper.getOrderSettingCountByOrderDate(orderSetting.getOrderDate());
        //已经进行了预约设置，执行更新操作
        if(count >0){
            orderSettingMapper.updateOrderSettingByOrderDate(orderSetting);
        }else {
            //没有进行预约设置，执行插入操作
            orderSettingMapper.addOrderSettingByOrderDate(orderSetting);
        }
    }

    @Override
    public List<Map<String, Object>> getOrderSettingByMonth(String date) {
        String beginTime = date+"-1";
        String endTime = date+"-31";
        List<OrderSetting> orderSettingList = orderSettingMapper.getOrderSettingByMonth(beginTime,endTime);
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (OrderSetting orderSetting : orderSettingList) {
            Map<String, Object> map = new HashMap<>();
            map.put("date",orderSetting.getOrderDate().getDate());
            map.put("number",orderSetting.getNumber());
            map.put("reservations",orderSetting.getReservations());
            mapList.add(map);
        }
        return mapList;
    }

    @Override
    public void editNumberByDate(OrderSetting orderSetting) {
        setOrderSetting(orderSetting);
    }
}
