package com.ovi.abc.icm;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.ip.dsl.Tcp;
import org.springframework.integration.ip.tcp.connection.AbstractClientConnectionFactory;
import org.springframework.integration.ip.tcp.serializer.ByteArrayLfSerializer;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.SubscribableChannel;

@Configuration
public class TcpClientIntegrationConfig {

    @Bean
    public AbstractClientConnectionFactory clientConnectionFactory() {
        return Tcp.netClient("localhost", 12345)
                .id("client")
                .serializer(new ByteArrayLfSerializer())
                .deserializer(new ByteArrayLfSerializer())
                .soTimeout(10000)
                .get();
    }

    @Bean
    public SubscribableChannel tcpOutputChannel() {
        return MessageChannels.direct().get();
    }

    @Bean
    public MessageHandler tcpOutboundAdapter(AbstractClientConnectionFactory clientConnectionFactory) {
        return Tcp.outboundAdapter(clientConnectionFactory).get();
    }


    @Bean
    public IntegrationFlow clientOutboundFlow(AbstractClientConnectionFactory clientConnectionFactory) {
        return IntegrationFlows.from("clientOutboundChannel")
                .handle(Tcp.outboundAdapter(clientConnectionFactory))
                .get();
    }
}
