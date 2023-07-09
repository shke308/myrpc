package com.zhujunhui;

import com.zhujunhui.exception.InterfaceExistedException;
import com.zhujunhui.rpc.Dispatcher;
import com.zhujunhui.rpc.transport.ServerHttpRequestHandler;
import com.zhujunhui.service.Car;
import com.zhujunhui.service.MayCar;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * 服务器
 * @author zhujunhui
 * @date 2023/7/10
 */
public class RpcService {

    private static volatile RpcService INSTANCE;

    private Dispatcher dispatcher;

    private RpcService () {
        this.dispatcher = Dispatcher.getInstance();
        try {
            this.dispatcher.register(Car.class.getName(), new MayCar());
        } catch (InterfaceExistedException e) {
            e.printStackTrace();
        }
    }

    public static RpcService getInstance() {
        if (INSTANCE == null) {
            synchronized (RpcService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RpcService();
                }
            }
        }
        return INSTANCE;
    }

    public void startServer() {
        NioEventLoopGroup group = new NioEventLoopGroup(1);
        NioEventLoopGroup work = new NioEventLoopGroup(3);
        ServerBootstrap server = new ServerBootstrap();
        ChannelFuture bind = server.group(group, work)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioServerSocketChannel>() {
                    @Override
                    protected void initChannel(NioServerSocketChannel ch) throws Exception {
                        System.out.println("server accept client port: " + ch.remoteAddress().getPort());
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new HttpServerCodec())
                                .addLast(new HttpObjectAggregator(1024 * 512))
                                .addLast(new ServerHttpRequestHandler());

                    }
                })
                .bind(9090);
        try {
            bind.sync().channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
