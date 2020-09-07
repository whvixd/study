package com.github.whvixd.demo.lmax.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.nio.ByteBuffer;

/**
 * Created by wangzhixiang on 2020/9/7.
 */
public class DisruptorDemo {
    public static void main(String[] args) throws InterruptedException {
        // The factory for the event
        DisruptorEventFactory factory = new DisruptorEventFactory();



        // Specify the size of the ring buffer, must be power of 2.
        int bufferSize = 1024;

        // Construct the Disruptor
//        Disruptor<DisruptorEvent> disruptor = new Disruptor<>(factory, bufferSize, DaemonThreadFactory.INSTANCE);

        // 堵塞策略
        Disruptor<DisruptorEvent> disruptor = new Disruptor<>(
                factory, bufferSize, DaemonThreadFactory.INSTANCE, ProducerType.SINGLE, new BlockingWaitStrategy());

        // Connect the handler
        disruptor.handleEventsWith(new DisruptorEventHandler());

        // Start the Disruptor, starts all threads running
        disruptor.start();

        // Get the ring buffer from the Disruptor to be used for publishing.
        RingBuffer<DisruptorEvent> ringBuffer = disruptor.getRingBuffer();

        DisruptorEventProducerWithTranslator producer = new DisruptorEventProducerWithTranslator(ringBuffer);

        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long l = 0; true; l++)
        {
            bb.putLong(0, l);
            producer.onData(bb);
            Thread.sleep(1000);
        }
    }
}
