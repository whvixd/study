package com.github.whvixd.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * Created by wangzhx on 2019/1/26.
 * <p>
 * corn: [秒] [分] [时] [日] [月] [周] [年]
 * 特殊字符: , 或 - 至  ? 不使用  * 每  / 每隔  #第  L 最
 */
@Slf4j
public class CronTriggerTest {

    public static void main(String[] args) throws SchedulerException, InterruptedException {
        JobDetail jobDetail = JobBuilder.newJob(JobTest.class).build();

        Trigger trigger = TriggerBuilder.newTrigger().startNow()
                // 每秒执行
//                .withSchedule(CronScheduleBuilder.cronSchedule("* * * ? * *"))
                // 2019年 每月的第四周的周六的2,5,7,20秒执行
//                .withSchedule(CronScheduleBuilder.cronSchedule("2,5,7,20 * * ? * 7#4 2019"))
                // 2019年1月或2月的周六11点至13点每秒执行
//                .withSchedule(CronScheduleBuilder.cronSchedule("* * 11-13 ? 1,2 SAT 2019"))
                //2019年1月或2月的周六11点至13点每分钟的2秒后开始后每隔5秒执行
//                .withSchedule(CronScheduleBuilder.cronSchedule("2/5 * 11-13 ? 1,2 SAT 2019"))
                //LW表示这个月最后一周的工作日
//                .withSchedule(CronScheduleBuilder.cronSchedule("* * * ? * LW"))
                // 每月最后一天执行
//                .withSchedule(CronScheduleBuilder.cronSchedule("* * * L * ?"))
//                 每秒执行
                .withSchedule(CronScheduleBuilder.cronSchedule("/15 * 10-20 * * ? "))

                .build();

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();
        //最近执行的时间
        Date date = scheduler.scheduleJob(jobDetail, trigger);
        log.info("scheduler time:{}", date);
        //执行两秒后挂起
//        Thread.sleep(2000L);
//        scheduler.standby();

        //挂起两秒后执重新执行
//        Thread.sleep(2000L);
//        scheduler.start();

        //再执行两秒后关闭程序
//        Thread.sleep(2000L);
//        scheduler.shutdown();//直接关闭，scheduler.shutdown(false)一样
//        scheduler.shutdown(true);//执行完所有的job关闭,让job睡10s，之后才会关闭

    }
}
