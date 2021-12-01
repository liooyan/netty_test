package com.test.tcp;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.http.HttpServerCodec;

public class TestSereverInitlalizer extends ChannelInitializer<SocketChannel> {


    EventLoopGroup workerGroup = new NioEventLoopGroup();

    @Override
    protected void initChannel(SocketChannel ch)  {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast( new DelimiterBasedFrameDecoder(19045, Unpooled.copiedBuffer("\n".getBytes())));

        pipeline.addLast(workerGroup,"testHttpSereverHandle2", new TestTcpServerHandler2());//把前面设置的handler加到最后
        pipeline.addLast("testHttpSereverHandle", new TestTcpServerHandler());//把前面设置的handler加到最后
        pipeline.addLast("TestTcpServerOutHandler", new TestTcpServerOutHandler());//把前面设置的handler加到最后

    }
}