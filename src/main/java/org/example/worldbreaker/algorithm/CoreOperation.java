package org.example.worldbreaker.algorithm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
public class CoreOperation {

    /**
     * generate handle method
     * @param set
     * @param str2
     * @param map
     * @return
     */
    public List<String> recursion(Set<String> set, String str2, HashMap<Integer, List<List<String>>> map) {
        // char change
        List<String> head = charRecursion(set, str2);
        // String change
        List<List<String>> resultList = backtrack(0, set, head, map);
        // exchange the result from back up
        List<String> result = new ArrayList<>(resultList.size());
        if (!resultList.isEmpty()) {
            result.addAll(resultList.stream()
                    .peek(Collections::reverse)
                    .map(this::ListToFinalString)
                    .toList());
        }
        return result;
    }

    /**
     * char change
     * @param set
     * @param str2
     * @return
     */

    public List<String> charRecursion(Set<String> set, String str2) {
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


    /**
     * backtrack: world arr merge dictionary
     * @param start
     * @param set
     * @param arr
     * @param map
     * @return
     */
    public List<List<String>> backtrack(int start, Set<String> set, List<String> arr, HashMap<Integer, List<List<String>>> map) {
        // optimize code ， using map cache store kv
        if (map.containsKey(start)) {
            return map.get(start);
        }

        List<List<String>> resultArr = new ArrayList<>();
        if (start == arr.size()) {
            // end
            resultArr.add(new ArrayList<>());
            return resultArr;
        } else {
            // recursion
            for (int i = start; i < arr.size(); i++) {
                String tmpWorld = mergeWorld(start, i, arr);
                if (set.contains(tmpWorld)) {
                    int param = i + 1;
                    List<List<String>> lists = backtrack(param, set, arr, map);
                    for (List<String> list : lists) {
                        List<String> subList = new ArrayList<>(list);
                        subList.add(tmpWorld);
                        resultArr.add(subList);
                    }
                }
                log.debug("currentWord: " + resultArr);
                map.put(start, resultArr);
            }

            return resultArr;
        }
    }


    /**
     * List to String
     * @param recursion
     * @return
     */
    private String ListToFinalString(List<String> recursion) {
        StringBuilder output = new StringBuilder();
        for (String s : recursion) {
            output.append(s).append("\u0020");
        }
        String callbackWord = output.toString();
        log.debug("turn ######### {}", callbackWord);
        return callbackWord;
    }

    /**
     * merge word from List
     * @param from
     * @param to
     * @param arr
     * @return
     */
    public String mergeWorld(Integer from, Integer to, List<String> arr) {
        StringBuilder sb = new StringBuilder();
        arr.subList(from, to + 1).stream().filter(Objects::nonNull).forEach(sb::append);
        return sb.toString();
    }

    /**
     * merge word from String
     * @param from
     * @param to
     * @param arr
     * @return
     */
    public String mergeWorld(Integer from, Integer to, String arr) {
        if (from < 0 || to < 0 || to < from) {
            throw new RuntimeException("err point");
        }
        return arr.substring(from, to);
    }
}
