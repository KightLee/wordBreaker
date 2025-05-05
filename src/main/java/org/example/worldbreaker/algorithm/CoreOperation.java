package org.example.worldbreaker.algorithm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
public class CoreOperation {

    public List<String> recursion(Set<String> set, String str2, HashMap<Integer, List<List<String>>> map) {
        // char change
        List<String> head = recursionV1(set, str2);
        // String change
        List<List<String>> lists = recursionV3(0, set, head, map);

        List<String> result = new ArrayList<>();
        if (!lists.isEmpty()) {
            result.addAll(lists.stream()
                    .peek(Collections::reverse)
                    .map(this::ListToFinalString)
                    .toList());
        }
        return result;
    }


    public List<String> recursionV1(Set<String> set, String str2) {
        List<String> outputArr = new ArrayList<>();
        int flag = 0;
        char[] target = str2.toCharArray();
        for (int i = 1; i <= target.length; i++) {
            for (int j = flag; j < i; j++) {
                String substring = mergeWorld(flag, i, str2);
                if (set.contains(substring)) {
                    outputArr.add(substring);
                    flag = i;
                    break;
                }
            }
        }

        return outputArr;
    }


    public List<List<String>> recursionV3(int start, Set<String> set, List<String> arr, HashMap<Integer, List<List<String>>> map) {
        if (map.containsKey(start)) {
            return map.get(start);
        }

        List<List<String>> resultArr = new ArrayList<>();
        if (start == arr.size()) {
            // 终点
//                List<String> str = new ArrayList<>();
//                str.add(arr.get(start));
            resultArr.add(new ArrayList<>());
            return resultArr;
        } else {
            // 递归
            for (int i = start; i < arr.size(); i++) {
                String tmpWorld = mergeWorld(start, i, arr);
                if (set.contains(tmpWorld)) {
                    int param = i + 1;
                    List<List<String>> lists = recursionV3(param, set, arr, map);
                    for (List<String> list : lists) {
                        List<String> subList = new ArrayList<>(list);
                        subList.add(tmpWorld);
                        resultArr.add(subList);
                    }
                }
                log.info("currentWord: " + resultArr);
                map.put(start, resultArr);
            }

            return resultArr;
        }
    }


    private String ListToFinalString(List<String> recursion) {
        StringBuilder output = new StringBuilder();
        for (String s : recursion) {
            output.append(s).append("\u0020");
        }
        String callbackWord = output.toString();
        log.debug("turn ######### {}", callbackWord);
        return callbackWord;
    }

    public String mergeWorld(Integer from, Integer to, List<String> arr) {
        StringBuilder sb = new StringBuilder();
        arr.subList(from, to + 1).stream().filter(Objects::nonNull).forEach(sb::append);
        return sb.toString();
    }

    public String mergeWorld(Integer from, Integer to, String arr) {
        if (from < 0 || to < 0 || to < from) {
            throw new RuntimeException("err point");
        }
        return arr.substring(from, to);
    }
}
