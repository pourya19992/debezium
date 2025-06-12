package org.example.debezium.services;

public class ConsoleMessageSender implements MessageSender {
    @Override
    public void sendMessage(String message) {
        System.out.println("Message: " + message);
    }
}
