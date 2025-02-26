package org.example.debezium;

public class ConsoleMessageSender implements MessageSender {
    @Override
    public void sendMessage(String message) {
        System.out.println("Message: " + message);
    }
}
