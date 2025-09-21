package me.mourjo.flowcrux;

import io.camunda.zeebe.spring.client.annotation.Deployment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Deployment(resources = {"classpath:*.bpmn"})
@SpringBootApplication
public class FlowcruxApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowcruxApplication.class, args);
    }

}
