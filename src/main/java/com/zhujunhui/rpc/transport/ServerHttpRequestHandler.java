package com.zhujunhui.rpc.transport;

import com.zhujunhui.rpc.Dispatcher;
import com.zhujunhui.rpc.protocol.RpcContent;
import com.zhujunhui.util.SerDerUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * http协议的 request handler
 * @author zhujunhui
 * @date 2023/7/10
 */
public class ServerHttpRequestHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpRequest req = (FullHttpRequest) msg;
        System.out.println(req.toString());

        ByteBuf buf = req.content();
        byte[] data = new byte[buf.readableBytes()];

        try (ByteArrayInputStream bis = new ByteArrayInputStream(data);
             ObjectInputStream obs = new ObjectInputStream(bis)) {
            RpcContent content = (RpcContent) obs.readObject();
            String serviceName = content.getInterfaceName();
            String methodName = content.getMethodName();
            Object service = Dispatcher.getInstance().get(serviceName);
            Class<?> clazz = service.getClass();
            Object res = null;
            try {
                Method method = clazz.getMethod(methodName, content.getParameterTypes());
                res = method.invoke(service, content.getArgs());
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            RpcContent.RpcContentBuilder rpcContentBuilder = new RpcContent.RpcContentBuilder();
            RpcContent responseContent = rpcContentBuilder.response(res).build();
            byte[] ser = SerDerUtil.ser(responseContent);
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_0,
                    HttpResponseStatus.OK,
                    Unpooled.copiedBuffer(ser));
            ctx.writeAndFlush(response);
        }

    }
}
