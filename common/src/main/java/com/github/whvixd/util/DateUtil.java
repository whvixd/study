package com.github.whvixd.util;

import com.google.common.collect.Lists;
import lombok.experimental.UtilityClass;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by wangzhx on 2020/4/13.
 */
@UtilityClass
public class DateUtil {
    public List<Date> getPeriodDates(Date beginDate, Date endDate) {
        if (beginDate == null || endDate == null || beginDate.after(endDate)) {
            return Lists.newArrayList();
        }
        List<Date> periodDates = Lists.newArrayList(beginDate);
        Calendar beginCalendar = Calendar.getInstance();
        beginCalendar.setTime(beginDate);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);
        while (endDate.after(beginCalendar.getTime())) {
            beginCalendar.add(Calendar.DAY_OF_MONTH, 1);
            periodDates.add(beginCalendar.getTime());
        }
        return periodDates;
    }

}
