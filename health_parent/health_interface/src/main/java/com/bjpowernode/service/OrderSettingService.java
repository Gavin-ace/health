package com.bjpowernode.service;

import com.bjpowernode.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

public interface OrderSettingService {
    void addOrderSetting(List<OrderSetting> orderSettingList);

    List<Map<String, Object>> getOrderSettingByMonth(String date);

    void editNumberByDate(OrderSetting orderSetting);
}
