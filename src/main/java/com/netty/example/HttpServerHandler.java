package com.netty.example;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author:lynn
 * @since 2018/11/10 23:41
 */
@Slf4j
public class HttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        //否则要做异常处理
        if(msg instanceof HttpRequest){

            ByteBuf content= Unpooled.copiedBuffer("Hi, lynn", CharsetUtil.UTF_8);
            FullHttpResponse response=new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());
            ctx.writeAndFlush(response);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //模拟显式的抛出异常
        
        System.out.println("HttpServerHandler.exceptionCaught()");
        ctx.fireExceptionCaught(cause);
    }
}
