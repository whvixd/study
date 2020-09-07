package com.github.whvixd.demo.lmax.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * Created by wangzhixiang on 2020/9/7.
 */
public class DisruptorEventFactory implements EventFactory<DisruptorEvent> {
    @Override
    public DisruptorEvent newInstance() {
        return new DisruptorEvent();
    }
}
