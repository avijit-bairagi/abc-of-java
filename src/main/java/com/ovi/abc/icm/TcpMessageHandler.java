package com.ovi.abc.icm;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class TcpMessageHandler {

    @ServiceActivator(inputChannel = "tcpInputChannel")
    public void handleMessage(Message<ReceivedMessage> message) {
        ReceivedMessage receivedMessage = message.getPayload();

        // You can now access the fields of the OutputMessage
        int code = receivedMessage.getCode();
        String msg = receivedMessage.getMsg();

        // Add your logic to process the received OutputMessage here.
    }
}
