package com.github.whvixd.message;

import org.junit.Test;

/**
 * Created by wangzhx on 2019/7/10.
 */
public class TestReport {
    @Test
    public void test(){
        Agent agent = new Agent();
        agent.register(new Subscriber());
        agent.register(new SubscriberTwo());
        agent.push(new Report("I come!"));
        agent.push(new Report(21));
    }
}
