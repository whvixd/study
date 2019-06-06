package com.github.whvixd.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * Created by wangzhx on 2019/1/26.
 */
@Slf4j
public class SimpleTriggerTest {
    public static void main(String[] args) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(JobTest.class).build();

        log.info("NOW:{}", new Date(System.currentTimeMillis()));
        Date date = new Date();
        date.setTime(date.getTime() + 4000L);
        SimpleTrigger simpleTrigger = (SimpleTrigger) TriggerBuilder.newTrigger()
                .startAt(date)
                .forJob(jobDetail)
                //每隔两次无线执行
//                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForTotalCount(2).withRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY))
                .build();

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();
        scheduler.scheduleJob(jobDetail, simpleTrigger);

        scheduler.shutdown();

    }
}
