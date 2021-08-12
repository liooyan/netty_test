package com.test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @ClassName:      TestServe
 * @Description:     描述  主要作用是写一个可以支持 http 请求的伪服务器。可以看到请求的头和数据 ，也可以自己构建response
 * @CreateDate:     2019/7/3 23:08
 * @Version:        1.0
 */
public class TestServer {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGroup  = new NioEventLoopGroup();//线程，用来接收  事件循环组  死循环
        EventLoopGroup workerGroup = new NioEventLoopGroup();//线程，用来处理  事件循环组  死循环

        try{
            //启动器
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)//添加设置两个线程组
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new TestSereverInitlalizer());//这里去设置初始化类

            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();//阻塞，等待

            channelFuture.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();//优雅关闭
            workerGroup.shutdownGracefully();
        }

    }
}