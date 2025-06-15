package org.example.worldbreaker.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

//@Component
@Slf4j
public class ThreadPause {

    // should be 50000, but 5000 for test
    @Scheduled(fixedDelay = 50000)
    public void pause() {
        log.debug("Thread Paused replace web application pom, that is so big!");
    }
}
