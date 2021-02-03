package com.embrace.springcloud.service.impl;

import com.embrace.springcloud.service.IMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;


import javax.annotation.Resource;
import java.util.UUID;

/**
 * @Author embrace
 * @Description
 * @Date create in  2020/12/2 21:12
 */
@EnableBinding(Source.class)
@Slf4j
public class MessageProviderService implements IMessageProvider {

    @Qualifier("errorChannel")
    @Resource
   private MessageChannel output;


    @Override
    public String sendMsg() {
        String serial = UUID.randomUUID().toString().replace("-","");
        output.send(MessageBuilder.withPayload(serial).build());
        return serial;
    }
}
