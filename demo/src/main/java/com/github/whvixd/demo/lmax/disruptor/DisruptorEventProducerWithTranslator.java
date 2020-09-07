package com.github.whvixd.demo.lmax.disruptor;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * Created by wangzhixiang on 2020/9/7.
 */
public class DisruptorEventProducerWithTranslator {
    private final RingBuffer<DisruptorEvent> ringBuffer;

    public DisruptorEventProducerWithTranslator(RingBuffer<DisruptorEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    private static final EventTranslatorOneArg<DisruptorEvent, ByteBuffer> TRANSLATOR =
            (event, sequence, bb) -> event.setData(bb.getLong(0) + "");

    public void onData(ByteBuffer bb) {
        ringBuffer.publishEvent(TRANSLATOR, bb);
    }
}
