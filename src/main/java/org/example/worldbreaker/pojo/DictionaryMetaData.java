package org.example.worldbreaker.pojo;

import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class DictionaryMetaData {

    public static HashSet<String> dictionarySet = new HashSet<>();

    public static final String DICTIONARY_PATH = "classpath:dictionary/customer.txt";

    public static String WORLD;
}
