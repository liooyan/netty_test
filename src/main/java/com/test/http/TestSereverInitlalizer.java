package com.test.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class TestSereverInitlalizer extends ChannelInitializer<SocketChannel> {


    TestHttpServerHandler testHttpSereverHandle =  new TestHttpServerHandler();
    EventLoopGroup bossGroup  = new NioEventLoopGroup();
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(bossGroup,"httpServerCodec", new HttpServerCodec());//http里用的
        pipeline.addLast("testHttpSereverHandle", testHttpSereverHandle);//把前面设置的handler加到最后

    }
}