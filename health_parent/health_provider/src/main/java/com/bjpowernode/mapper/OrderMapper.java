package com.bjpowernode.mapper;

import com.bjpowernode.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface OrderMapper {

    Order getOrderCondition(@Param("memberId") Integer memberId, @Param("orderDate") String orderDate, @Param("setmealId") Integer setmealId);

    void addOrder(Order order);

    Map<String, Object> findById(Integer id);
}
