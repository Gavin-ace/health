package com.bjpowernode.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.bjpowernode.constant.MessageConstant;
import com.bjpowernode.constant.RedisMessageConstant;
import com.bjpowernode.entity.Result;
import com.bjpowernode.mapper.MemberMapper;
import com.bjpowernode.mapper.OrderMapper;
import com.bjpowernode.mapper.OrderSettingMapper;
import com.bjpowernode.pojo.Member;
import com.bjpowernode.pojo.Order;
import com.bjpowernode.pojo.OrderSetting;
import com.bjpowernode.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.util.Date;
import java.util.Map;
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private JedisPool jedisPool;
    @Autowired
    private OrderSettingMapper orderSettingMapper;
    @Autowired
    private MemberMapper memberMapper;

    @Override
    public Result submit(Map<String, Object> map) throws Exception {
        //0、判断验证码是否一致
        String telephone = (String) map.get("telephone");
        String validateCodeRedis = jedisPool.getResource().get(telephone + "_" + RedisMessageConstant.SENDTYPE_ORDER);
        String validateCode = (String) map.get("validateCode");
        if(!validateCode.equals(validateCodeRedis)){
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }
        //1、检查用户所选择的预约日期是否已经提前进行了预约设置，如果没有设置则无法进行预约
        String orderDate = (String) map.get("orderDate");

        OrderSetting orderSetting = orderSettingMapper.getOrderSettingByOrderDate(DateUtils.parseString2Date(orderDate));
        if(orderSetting == null){
            return new Result(false, MessageConstant.SELECTED_DATE_CANNOT_ORDER);
        }
        //2、检查用户所选择的预约日期是否已经约满，如果已经约满则无法预约
        if(orderSetting.getNumber() == orderSetting.getReservations()){
            return new Result(false, MessageConstant.ORDER_FULL);
        }
        //4、检查当前用户是否为会员，如果是会员则直接完成预约，如果不是会员则自动完成注册并进行预约
        Member member = memberMapper.getMemberByTelephone(telephone);
        if(member == null){
            member = new Member();
            member.setName((String) map.get("name"));
            member.setSex((String) map.get("sex"));
            member.setPhoneNumber(telephone);
            member.setRegTime(new Date());
            member.setIdCard((String) map.get("idCard"));
            //主键回填
            memberMapper.addMember(member);
        }
        //3、检查用户是否重复预约（同一个用户在同一天预约了同一个套餐），如果是重复预约 则无法完成再次预约
        Integer setmealId = Integer.parseInt((String) map.get("setmealId"));
        Order order = orderMapper.getOrderCondition(member.getId(),orderDate,setmealId);
        if(order != null){
            return new Result(false, MessageConstant.HAS_ORDERED);
        }
        //5、保存预约信息，
        order = new Order(member.getId(), DateUtils.parseString2Date(orderDate), Order.ORDERTYPE_WEIXIN,
                Order.ORDERSTATUS_NO, setmealId);
        orderMapper.addOrder(order);
        //6、更新当日的已预约人数
        orderSetting.setReservations(orderSetting.getReservations()+1);
        orderSettingMapper.updateOrderSettingByOrderDate(orderSetting);
        return new Result(true, MessageConstant.ORDER_SUCCESS, order.getId());
    }

    @Override
    public Map<String, Object> findById(Integer id) {
        return orderMapper.findById(id);
    }
}
