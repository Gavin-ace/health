package com.bjpowernode.job;

import com.bjpowernode.constant.RedisConstant;
import com.bjpowernode.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

@Component
public class ClearImgJob {

    @Autowired
    private JedisPool jedisPool;

    public void clearImg(){

        //sdiff SETMELA_PIC_RESOURCE  SETMELA_PIC_RESOURCE--->1
        Jedis jedis = jedisPool.getResource();
        Set<String> set = jedis.sdiff(RedisConstant.SETMEAL_PIC_RESOURCES, RedisConstant.SETMEAL_PIC_DB_RESOURCES);
        for (String imgName : set) {
            //QiNiuUtil.deletePic(1);
            QiniuUtils.deleteFileFromQiniu(imgName);
            //jedis.srem(1);//若不删除，下次定时任务会重复删除垃圾图片
            jedis.srem(RedisConstant.SETMEAL_PIC_RESOURCES,imgName);
            System.out.println("自定义任务执行，清理垃圾图片:" + imgName);
        }
    }
}
