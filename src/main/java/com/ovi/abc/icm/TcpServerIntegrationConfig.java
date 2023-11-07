package com.ovi.abc.icm;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.ip.dsl.Tcp;
import org.springframework.integration.ip.tcp.connection.AbstractServerConnectionFactory;
import org.springframework.integration.ip.tcp.serializer.ByteArrayLfSerializer;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.MessageBuilder;

@Configuration
public class TcpServerIntegrationConfig {

    @Bean
    public AbstractServerConnectionFactory serverConnectionFactory() {
        return Tcp.netServer(12345)
                .id("server")
                .serializer(new ByteArrayLfSerializer())
                .deserializer(new ByteArrayLfSerializer())
                .soTimeout(10000)
                .get();
    }

    @Bean
    public SubscribableChannel tcpInputChannel() {
        return MessageChannels.direct().get();
    }

    @Bean
    public SubscribableChannel tcpOutputChannel() {
        return MessageChannels.direct().get();
    }

    @Bean
    public IntegrationFlow inboundFlow(AbstractServerConnectionFactory serverConnectionFactory) {
        return IntegrationFlows.from(Tcp.inboundAdapter(serverConnectionFactory))
                .channel(tcpInputChannel())
                .get();
    }

    @Bean
    @ServiceActivator(inputChannel = "tcpInputChannel")
    public MessageHandler tcpMessageHandler() {
        return message -> {
            InputMessage inputMessage = (InputMessage) message.getPayload();
            
            // Process the InputMessage and convert it to ReceivedMessage
            ReceivedMessage receivedMessage = new ReceivedMessage();
            receivedMessage.setCode(inputMessage.getId());
            receivedMessage.setMsg("Received: " + inputMessage.getName());
            
            // Send the ReceivedMessage back to the client
            tcpOutputChannel().send(MessageBuilder.withPayload(receivedMessage).build());
        };
    }
}
