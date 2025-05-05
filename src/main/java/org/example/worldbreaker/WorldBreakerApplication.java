package org.example.worldbreaker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = {"org.example.worldbreaker.*"})
@Slf4j
public class WorldBreakerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorldBreakerApplication.class, args);
    }

}
