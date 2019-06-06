package com.github.whvixd.quartz;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * Created by wangzhx on 2019/1/12.
 * <p>
 * Job每次执行execute方法会生成实例，执行完会被垃圾回收
 * <p>
 * JobDetail 将绑定的Job封装了一层:name,group,jobClass,jobDataMap
 * 若没有初始化group，则使用默认DEFAULT
 */
@Slf4j
public class SchedulerTest {

    public static void main(String[] args) throws SchedulerException {
        Date startDate = new Date();
        Date endDate = new Date();
        //startDate after 3s
        startDate.setTime(startDate.getTime() + 3000);
        //endDate after 9s
        endDate.setTime(endDate.getTime() + 9000);
        /**
         * 1. 创建JobDetail实例，绑定Job
         * 2. 创建Trigger实例，定义Job触发的时间
         * 3. 创建Schedule实例，执行任务
         */
        JobDetail jobDetail = JobBuilder.newJob(JobTest.class).
                withIdentity("job-test", "one").
                usingJobData("jobDetailKey", "jobDetailValue").build();
        log.info("JobDetail.name:{},group:{},jobClass:{}", jobDetail.getKey().getName(), jobDetail.getKey().getGroup(), jobDetail.getJobClass().getSimpleName());

        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger-test", "one")
//                .startNow()
                .startAt(startDate)
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever())
                .usingJobData("triggerKey", "triggerValue")
                .endAt(endDate)
                .build();

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.shutdown();
    }
}
