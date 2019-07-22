package com.github.whvixd.message;

import org.junit.Test;

import java.io.IOException;

/**
 * Created by wangzhx on 2019/7/10.
 */
public class TestReport {
    @Test
    public void test() throws IOException {
        Agent agent = new Agent();

        agent.register(new Subscriber());
        agent.register(new SubscriberTwo());
        agent.push(new Report("I come!"));
        agent.push(new Report(21));

//        System.in.read();
    }
}
