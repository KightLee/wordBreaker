package org.example.worldbreaker.configuration;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.worldbreaker.algorithm.CoreOperation;
import org.example.worldbreaker.pojo.DictionaryMetaData;
import org.example.worldbreaker.validation.WordValidator;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

//@Configuration
@Service
@Slf4j
public class ConsoleConfig {

    @Resource
    private CoreOperation coreOperation;

    // cache Map
    private final HashMap<Integer, List<List<String>>> cacheMap = new HashMap<>();


//    @PostConstruct
    public void init() {
        // access user inout and output the answer
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                synchronized (CoreOperation.class) {
                    try {
                        System.out.println("> ");
                        String input = scanner.nextLine().trim();
                        execute(input);
                    } catch (Exception e) {
                        log.error("execute err, reason: {}", e.getLocalizedMessage());
                    } finally {
                        cacheMap.clear();
                    }
                }

            }
        }).start();
    }

    public void execute(String input) {
        if (input.isBlank() || !WordValidator.isValid(input)) {
            System.out.println("do nothing ! ~~~ input world has error, check you world");
            return;
        }

        DictionaryMetaData.WORLD = input;
        List<String> recursion = coreOperation.recursion(DictionaryMetaData.dictionarySet, DictionaryMetaData.WORLD, cacheMap);
        System.out.println("input:" + DictionaryMetaData.WORLD);
        System.out.println("\n=====================================\n");
        recursion.forEach(info -> System.out.println("output:" + info));
    }

}
