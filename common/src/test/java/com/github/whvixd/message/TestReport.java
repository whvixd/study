package com.github.whvixd.message;

import org.junit.Test;

import java.io.IOException;
import java.util.stream.IntStream;

/**
 * Created by wangzhx on 2019/7/10.
 */
public class TestReport {
    @Test
    public void testAgent() throws IOException {
        Agent agent = new Agent();

        IntStream.range(0,100).forEach(e->{
            agent.register(new SubscriberOne());
        });
        agent.push(new Report("I come!"));
        agent.push(new Report(21));

    }


    @Test
    public void testAsyncAgent() throws InterruptedException, IOException {
        Agent agent = new Agent(true);

        IntStream.range(0,100).forEach(e->{
            agent.register(new SubscriberOne());
        });
        agent.register(new SubscriberTwo());

        agent.push(new Report("I come!"));
        agent.push(new Report(21));

        System.in.read();
    }

}
