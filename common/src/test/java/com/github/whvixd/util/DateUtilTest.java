package com.github.whvixd.util;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by wangzhx on 2020/4/13.
 */
public class DateUtilTest {

    @Test
    public void test() throws ParseException {
        Date beginDate = DateUtils.parseDateStrictly("2020-05-11", "yyyy-MM-dd");
        Date endDate = DateUtils.parseDateStrictly("2020-04-15", "yyyy-MM-dd");
        DateUtil.getPeriodDates(beginDate, endDate).forEach(System.out::println);

        Date date = DateUtils.parseDateStrictly("2020-02-11", "yyyy-MM-dd");
        System.out.println(DateUtils.isSameDay(beginDate,date));
    }
}
