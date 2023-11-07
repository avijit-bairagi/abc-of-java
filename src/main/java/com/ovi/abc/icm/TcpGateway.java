package com.ovi.abc.icm;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface TcpGateway {
    @Gateway(requestChannel = "tcpOutputChannel")
    void sendTcpMessage(InputMessage inputMessage);
}