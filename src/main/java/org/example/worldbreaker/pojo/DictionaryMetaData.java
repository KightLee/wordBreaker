package org.example.worldbreaker.pojo;

import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class DictionaryMetaData {

    // dictionary set
    public static HashSet<String> dictionarySet = new HashSet<>();

    // word content
    public static String WORLD;

}
