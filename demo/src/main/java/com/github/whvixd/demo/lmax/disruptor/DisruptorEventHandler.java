package com.github.whvixd.demo.lmax.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * Created by wangzhixiang on 2020/9/7.
 */
public class DisruptorEventHandler implements EventHandler<DisruptorEvent> {
    @Override
    public void onEvent(DisruptorEvent disruptorEvent, long l, boolean b) throws Exception {
        System.out.println("disruptorEvent:"+disruptorEvent);
    }
}
