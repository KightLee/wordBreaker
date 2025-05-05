package org.example.worldbreaker.configuration;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.worldbreaker.algorithm.CoreOperation;
import org.example.worldbreaker.pojo.DictionaryMetaData;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

@Configuration
@Slf4j
public class ConsoleConfig {

    @Resource
    private CoreOperation coreOperation;

    private HashMap<Integer, List<List<String>>> cacheMap = new HashMap<>();


    @PostConstruct
    public void init() {
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                synchronized (CoreOperation.class) {
                    try {
                        System.out.println("> ");
                        String input = scanner.nextLine().trim();
                        if (input.contains(" ") || input.isEmpty()) {
                            System.out.println("do nothing ! ~~~ input world has space, check you world");
                            return;
                        }

                        DictionaryMetaData.WORLD = input;
                        List<String> recursion = coreOperation.recursion(DictionaryMetaData.dictionarySet, DictionaryMetaData.WORLD, cacheMap);
                        System.out.println("input:" + DictionaryMetaData.WORLD);
                        System.out.println("\n=====================================\n");
                        recursion.forEach(info -> System.out.println("output:" + info));
                    } catch (Exception e) {
                        log.error(e.getMessage());
                    } finally {
                        cacheMap.clear();
                    }
                }

            }
        }).start();
    }


}
