package com.bjpowernode.mapper;

import com.bjpowernode.pojo.OrderSetting;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface OrderSettingMapper {
    void updateOrderSettingByOrderDate(OrderSetting orderSetting);

    void addOrderSettingByOrderDate(OrderSetting orderSetting);

    List<OrderSetting> getOrderSettingByMonth(@Param("beginTime") String beginTime, @Param("endTime") String endTime);

    long getOrderSettingCountByOrderDate(Date date);

    OrderSetting getOrderSettingByOrderDate(Date date);
}
