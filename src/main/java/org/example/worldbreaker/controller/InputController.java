package org.example.worldbreaker.controller;

import org.example.worldbreaker.configuration.ConsoleConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/world")
public class InputController {

    @Autowired
    public ConsoleConfig consoleConfig;

    @GetMapping("/break")
    public void HSBC(String testStr) {
        consoleConfig.execute(testStr);
    }
}
