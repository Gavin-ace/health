package com.bjpowernode.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bjpowernode.constant.MessageConstant;
import com.bjpowernode.constant.RedisConstant;
import com.bjpowernode.entity.PageResult;
import com.bjpowernode.entity.QueryPageBean;
import com.bjpowernode.entity.Result;
import com.bjpowernode.pojo.OrderSetting;
import com.bjpowernode.pojo.Setmeal;
import com.bjpowernode.service.OrderSettingService;
import com.bjpowernode.service.SetmealService;
import com.bjpowernode.utils.POIUtils;
import com.bjpowernode.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.util.*;

@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {

    @Reference
    private OrderSettingService orderSettingService;

    @RequestMapping("/upload")
    public Result upload(MultipartFile excelFile){
        try {
            //1、读取Excel
            List<String[]> stringList = POIUtils.readExcel(excelFile);
            List<OrderSetting> orderSettingList = new ArrayList<>();
            for (String[] string : stringList) {
                String orderDate = string[0];
                String number = string[1];
                OrderSetting orderSetting = new OrderSetting();
                orderSetting.setOrderDate(new Date(orderDate));
                orderSetting.setNumber(Integer.parseInt(number));
                orderSettingList.add(orderSetting);
            }

            //2、插入到数据库
            orderSettingService.addOrderSetting(orderSettingList);
            return new Result(true,MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }
    }

    /**
     * 根据日期查询预约设置信息
     * @return
     */
    @RequestMapping("/getOrderSettingByMonth")
    public Result getOrderSettingByMonth(String date) {
        try {
            List<Map<String,Object>> mapList = orderSettingService.getOrderSettingByMonth(date);
            return new Result(true, MessageConstant.GET_ORDERSETTING_SUCCESS,mapList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_ORDERSETTING_FAIL);
        }
    }

    @RequestMapping("/editNumberByDate")
    public Result editNumberByDate(@RequestBody OrderSetting orderSetting) {
        try {
            orderSettingService.editNumberByDate(orderSetting);
            return new Result(true, MessageConstant.ORDERSETTING_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ORDERSETTING_FAIL);
        }
    }
}
