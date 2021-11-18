package com.test;

import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.ScheduledFuture;

import java.util.concurrent.TimeUnit;

/**
 * {@link EventLoopTest}
 *
 * @author com.lioyan
 * @date 2021/11/18  15:55
 */
public class EventLoopTest {
    public static void main(String[] args) {

        NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup(1);


        ScheduledFuture<?> schedule = nioEventLoopGroup.schedule(() -> {
            System.out.println(123);
        }, 3, TimeUnit.SECONDS);
        schedule.addListener(future -> System.out.println("qqqqq"));
        boolean success = schedule.isDone();
        System.out.println(success);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        nioEventLoopGroup.execute(() ->{
            System.out.println(22222);
        });


    }
}
