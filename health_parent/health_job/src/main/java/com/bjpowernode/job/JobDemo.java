package com.bjpowernode.job;

import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JobDemo {
    public void printTime(){
        System.out.println("现在时刻："+new Date());
    }


    //1、求差集：sdiff SETMEAL_PIC_RESOURCE SETMEAL_PIC_DB_RESOURCE-->1
    //2、QiNiuUtil.deletePic(1)
    //3、jedis.srem(1),避免重复删除垃圾图片
}
