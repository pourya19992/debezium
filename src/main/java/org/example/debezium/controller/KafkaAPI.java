package org.example.debezium.controller;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
public class KafkaAPI {
    private final KafkaTemplate<String, String> template;

    public KafkaAPI(KafkaTemplate<String, String> template) {
        this.template = template;
    }

    @GetMapping("/send")
    public String send() throws ExecutionException, InterruptedException {
        template.send("j2os", "Hi1").get();//blocking
        //none blocking
        return "OK";
    }

    @GetMapping("/asyncSend")
    public String asyncSend() {
        CompletableFuture<SendResult<String, String>> future = template.send("j2os", "Hi2");
        future.whenComplete((result, exception) -> {
            if (Objects.isNull(exception))
                System.out.println(result);
            else
                exception.printStackTrace();
        });
        return "OK";
    }

    @KafkaListener(topics = "j2os", groupId = "service")
    public void consume(String message) {
        System.out.println(String.format("Message received -> %s", message));
    }
}
