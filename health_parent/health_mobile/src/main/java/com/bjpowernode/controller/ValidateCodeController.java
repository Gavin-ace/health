package com.bjpowernode.controller;

import com.bjpowernode.constant.MessageConstant;
import com.bjpowernode.constant.RedisMessageConstant;
import com.bjpowernode.entity.Result;
import com.bjpowernode.utils.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@RestController
@RequestMapping("/validateCode")
public class ValidateCodeController{

    @Autowired
    private JedisPool jedisPool;

    /**
     * 发送验证码
     * @param telephone
     * @return
     */
    @RequestMapping("/send4Order")
    public Result send4Order(String telephone){
        try {
            Integer validateCode = ValidateCodeUtils.generateValidateCode(4);
            System.out.println("往"+telephone+"手机号发送短信："+validateCode);
            Jedis jedis = jedisPool.getResource();
            jedis.set(telephone+"_"+RedisMessageConstant.SENDTYPE_ORDER,validateCode.toString());
            jedis.expire(telephone+"_"+RedisMessageConstant.SENDTYPE_ORDER,60);
            return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
    }
}
