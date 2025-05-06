package org.example.worldbreaker;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.worldbreaker.algorithm.CoreOperation;
import org.example.worldbreaker.file.DictionaryReader;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@SpringBootTest
@Slf4j
class WorldBreakerApplicationTests {

    private static final String testStrList = "i, like, sam, sung, samsung, mobile, ice, cream, man, go, gmo, ilikesam, sungmobile";

    private static final String testStr = "ilikesamsungmobile";

    private static final HashSet<String> set = new HashSet<>();

    private static final List<String> outputArr = new ArrayList<>();

    @Resource
    private CoreOperation coreOperation;

    @Resource
    private DictionaryReader dictionaryReader;

    static {
        // mock dictionary set
        List<String> strArr = Arrays.stream(testStrList.split(",")).map(String::trim).toList();
        set.addAll(strArr);
    }

    /**
     * filter and valid the dic data what user input
     */
    @Test
    void StringSplitTest() {
        List<String> strArr = Arrays.stream(testStrList.split(",")).map(String::trim).toList();
        strArr.forEach(log::info);
    }

    /**
     * testing char merge algorithm
     */
    @Test
    void coreAlgorithmTest() {
        int flag = 0;
        char[] target = testStr.toCharArray();
        for (int i = 1; i <= target.length; i++) {
            for (int j = flag; j < i; j++) {
                String substring = testStr.substring(flag, i);
                if (set.contains(substring)) {
                    System.out.println(substring);
                    outputArr.add(substring);
                    flag = i;
                    break;
                }
            }
        }


        StringBuilder output = new StringBuilder();
        for (String s : outputArr) {
            output.append(s).append("\u0020");
        }
        log.info(output.toString());

    }

    /**
     * testing generate handle method
     */
    @Test
    void coreAlgorithm_Version2_Test() {
        List<String> recursion = coreOperation.recursion(set, testStr, null);
        log.info(recursion.toString());


//        List<String> head = coreOperation.recursionV1(set, testStr);
//        HashMap<Integer, List<List<String>>> map = new HashMap<>();
//        List<List<String>> lists = coreOperation.func3(0, set, head, map);
//        lists.forEach(list -> System.out.println(list+ "\n"));
//        System.out.println(" ---- ");

    }


    /**
     * merge world testing
     */
    @Test
    void getWorld_Test() {
        ArrayList<String> list = new ArrayList<>(set);
        log.info("merge #########   " + coreOperation.mergeWorld(0, 1, list));
    }

    /**
     * file testing
     */
    @Test
    void fileWorld_Test() {
        dictionaryReader.loadDictionary();
    }


}
