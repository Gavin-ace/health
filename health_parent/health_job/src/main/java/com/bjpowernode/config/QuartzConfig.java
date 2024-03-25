package com.bjpowernode.config;

import com.bjpowernode.job.ClearImgJob;
import com.bjpowernode.job.JobDemo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzConfig {

    //1、job：做什么事
    @Bean
    public MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean(ClearImgJob clearImgJob){
        MethodInvokingJobDetailFactoryBean jobDetailFactoryBean = new MethodInvokingJobDetailFactoryBean();
        jobDetailFactoryBean.setTargetObject(clearImgJob);//要调用的类
        jobDetailFactoryBean.setTargetMethod("clearImg");//要调用的方法
        return jobDetailFactoryBean;
    }

    //2、trigger：什么时候做
    @Bean
    public CronTriggerFactoryBean cronTriggerFactoryBean(MethodInvokingJobDetailFactoryBean jobDetailFactoryBean){
        CronTriggerFactoryBean triggerFactoryBean = new CronTriggerFactoryBean();
        triggerFactoryBean.setCronExpression("*/5 * * * * ?");
        triggerFactoryBean.setJobDetail(jobDetailFactoryBean.getObject());
        return triggerFactoryBean;
    }

    //3、scheduled：什么时候做什么事
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(CronTriggerFactoryBean triggerFactoryBean){
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setTriggers(triggerFactoryBean.getObject());
        return schedulerFactoryBean;
    }
}
