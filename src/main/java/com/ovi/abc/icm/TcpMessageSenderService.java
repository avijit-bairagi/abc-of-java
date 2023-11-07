package com.ovi.abc.icm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TcpMessageSenderService {

    @Autowired
    private TcpGateway tcpGateway;

    public void sendMessage() {
        InputMessage inputMessage = new InputMessage();
        inputMessage.setId(1);
        inputMessage.setName("John");

        tcpGateway.sendTcpMessage(inputMessage);
    }
}
