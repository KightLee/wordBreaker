package org.example.worldbreaker.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ThreadPause {

    @Scheduled(fixedDelay = 50000)
    public void pause() {
        log.debug("Thread Paused replace web application pom, that is so big!");
    }
}
