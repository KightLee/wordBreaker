package org.example.worldbreaker.file;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.worldbreaker.pojo.DictionaryMetaData;
import org.example.worldbreaker.pojo.StageEnum;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class DictionaryReader implements InitializingBean {

    @Value("${world_stage.phase:1}")
    private Integer stage;

    @Value("${world_stage.name:customer.txt}")
    private String diyName;

    @Value("${world_stage.default_name:customer.txt}")
    private String defaultName;

    @Resource
    private ResourceLoader resourceLoader;

    private static final String DICTIONARY_PATH = "classpath:dictionary/";

    // load properties and choose the dictionary use switch achieve strategy
    public void loadDictionary() {
        StageEnum stageEnum = StageEnum.getStage(stage);
        switch (stageEnum) {
            case Stage1 -> add(DICTIONARY_PATH + defaultName);
            case Stage2 -> add(DICTIONARY_PATH + diyName);
            case Stage3 -> add();
            default -> log.error("stage is not valid");
        }
    }

    public void add(File file) {
        try {
            String originString = Files.readString(file.toPath());
            String trim = originString.substring(1, originString.length() - 1).trim();
            List<String> split = List.of(trim.split(","));
            split.stream().map(String::trim).forEach(info -> {
                if (info.contains(" ")) {
                    String[] space = info.split(" ");
                    DictionaryMetaData.dictionarySet.addAll(Arrays.asList(space));
                } else {
                    DictionaryMetaData.dictionarySet.add(info);
                }
            });

        } catch (RuntimeException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(String path) {
        try {
            File file = ResourceUtils.getFile(path);
            add(file);
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void add() {
        try {
            org.springframework.core.io.Resource[] resources = ((ResourcePatternResolver) resourceLoader)
                    .getResources(DICTIONARY_PATH + "/**/*.txt");
            for (org.springframework.core.io.Resource resource : resources) {
                if (resource.isReadable()) {
                    add(resource.getFile());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void afterPropertiesSet() {
        loadDictionary();
    }
}
