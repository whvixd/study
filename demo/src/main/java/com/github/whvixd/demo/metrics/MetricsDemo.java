package com.github.whvixd.demo.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import com.github.whvixd.demo.Entity;

import java.util.concurrent.TimeUnit;

/**
 * Created by wangzhx on 2018/8/25.
 */
public class MetricsDemo {

    public static <T> void recordTimeLog(T value) throws InterruptedException {

//        System.out.println(new Type(){}.getTypeName());
        MetricRegistry metricRegistry = new MetricRegistry();

        metricRegistry.register(value.getClass().getCanonicalName(), (Gauge) () -> value);

        ConsoleReporter.forRegistry(metricRegistry).build().start(1, TimeUnit.SECONDS);

        Thread.sleep(5000);

    }

    public static void main(String[] args) throws InterruptedException {
        Entity.Student student = new Entity.Student();
        student.setName("jams");
        MetricsDemo.recordTimeLog(student);
    }

}
