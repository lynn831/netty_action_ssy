package com.netty.example;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;


/**
 * @author:lynn
 * @since 2018/11/10 23:28
 */
public class ServerInitializer extends ChannelInitializer<SocketChannel> {
    public ServerInitializer() {
        super();
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        //new的对象不要用处理成多实例的
        ChannelPipeline pipeline=ch.pipeline();
        pipeline.addLast("HttpServer",new HttpServerCodec());
        pipeline.addLast("HttpServerHandler",new HttpServerHandler());

    }
}
