package com.github.whvixd.quartz;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wangzhx on 2019/1/27.
 */
@Slf4j
public class SpringJobTest {

    public void execute() {
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        log.info("NOW:{}", simpleDateFormat.format(date));


    }
}
