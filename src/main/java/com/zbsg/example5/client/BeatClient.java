package com.zbsg.example5.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by yaoyuanliang on 2017/9/22.
 */
public class BeatClient {
    public static void main(String[] args) throws InterruptedException, IOException {
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();         //客户端启动类
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                    .handler(new BeatClientInitializer());

            ChannelFuture channelFuture = bootstrap.connect("localhost", 8899).sync();

            Channel channel = channelFuture.channel();
            //标准输入
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            while (true){
               channel.writeAndFlush(bufferedReader.readLine() + "\r\n");
            }

        } finally {
            eventLoopGroup.shutdownGracefully();
        }


    }
}
