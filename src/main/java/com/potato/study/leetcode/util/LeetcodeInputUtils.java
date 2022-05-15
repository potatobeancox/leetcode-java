package com.potato.study.leetcode.util;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * leetcode 题目中输入生成的工具类
 */
public class LeetcodeInputUtils {


    /**
     * deadends = ["0201","0101","0102","1212","2002"]
     * 测试用例
     * []
     * null
     * 返回 string 数组
     * @param input
     * @return
     */
    public static String[] inputString2StringArray(String input) {
        if (input == null) {
            return null;
        }
        if ("".equals(input) || "[]".equals(input)) {
            return new String[0];
        }
        // 去掉"[]"
        String substring = input.substring(1, input.length() - 1);
        // ，拆分
        String[] split = substring.split(",");
        // 去掉引号
        String[] result = new String[split.length];
        for (int i = 0; i < split.length; i++) {
            String word = split[i].trim();
            word = word.substring(1, word.length() - 1);
            result[i] = word;
        }
        // 返回数组
        return result;
    }


    public static List<String> inputString2StringList(String input) {
        if (StringUtils.isBlank(input)) {
            return new ArrayList<>();
        }
        if ("".equals(input) || "[]".equals(input)) {
            return new ArrayList<>();
        }
        // 去掉"[]"
        String substring = input.substring(1, input.length() - 1);
        // ，拆分
        String[] split = substring.split(",");
        // 去掉引号
        List<String> resultList = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            String word = split[i].trim();
            word = word.substring(1, word.length() - 1);
            resultList.add(word);
        }
        // 返回数组
        return resultList;
    }


    public static List<Boolean> inputString2BooleanList(String input) {
        if (StringUtils.isBlank(input)) {
            return new ArrayList<>();
        }
        if ("".equals(input) || "[]".equals(input)) {
            return new ArrayList<>();
        }
        // 去掉"[]"
        String substring = input.substring(1, input.length() - 1);
        // ，拆分
        String[] split = substring.split(",");
        List<Boolean> resultList = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            String word = split[i].trim();
            resultList.add(Boolean.parseBoolean(word));
        }
        // 返回数组
        return resultList;
    }


    public static List<List<String>> inputString2StringListList(String input) {
        if (StringUtils.isBlank(input)) {
            return new ArrayList<>();
        }
        if ("".equals(input) || "[]".equals(input)) {
            return new ArrayList<>();
        }
        // 去掉"[]"
        String substring = input.substring(2, input.length() - 2);
        // ，拆分
        String[] split = substring.split("\\],\\[");
        // 去掉引号
        List<List<String>> resultList = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            resultList.add(inputString2StringList(split[i]));
        }
        // 返回数组
        return resultList;
    }


    @Test
    public void testInputString2StringArray() {
        String input = "[\"0201\",\"0101\",\"0102\",\"1212\",\"2002\"]";
        String[] array = inputString2StringArray(input);
        System.out.println(Arrays.toString(array));
    }


    /**
     * rectangles = [[2,3],[3,7],[4,3],[3,7]]
     * 测试用例
     * []
     * null
     * 返回 int 2维 数组
     * @param input
     * @return
     */
    public static int[][] inputString2IntArrayTwoDimensional(String input) {
        if (input == null) {
            return null;
        }
        if ("".equals(input) || "[]".equals(input)) {
            return new int[0][0];
        }
        // 去掉"[2,3],[3,7],[4,3],[3,7] 或者 [1,2]"
        String substring = input.substring(1, input.length() - 1);
        // ，拆分
        String[] split = substring.split("]");
//        String[] result = new String[split.length];
        int[][] result = new int[split.length][];
        for (int i = 0; i < split.length; i++) {
            // 去掉引号 [2,3 或者 ,[3,7
            String word = split[i].trim();
            if ("".equals(word)) {
                continue;
            }
            // [2 或者 3 或者 ""
            String[] numSplit = word.split(",");
            List<Integer> list = new ArrayList<>();
            for (String numStr : numSplit) {
                if (numStr.startsWith("[")) {
                    numStr = numStr.substring(1);
                }
                if ("".equals(numStr)) {
                    continue;
                }
                list.add(Integer.parseInt(numStr.trim()));
            }
            int[] arr = new int[list.size()];
            for (int j = 0; j < list.size(); j++) {
                arr[j] = list.get(j);
            }
            result[i] = arr;
        }
        // 返回数组
        return result;
    }


    /**
     * rectangles = [[2,3],[3,7],[4,3],[3,7]]
     * 测试用例
     * []
     * null
     * 返回 int 2维 数组
     * @param input
     * @return
     */
    public static List<List<Integer>> inputString2IntListTwoDimensional(String input) {
        if (input == null) {
            return new ArrayList<>();
        }
        if ("".equals(input) || "[]".equals(input)) {
            return new ArrayList<>();
        }
        // 去掉"[2,3],[3,7],[4,3],[3,7] 或者 [1,2]"
        String substring = input.substring(1, input.length() - 1);
        // ，拆分
        String[] split = substring.split("]");
//        String[] result = new String[split.length];
//        int[][] result = new int[split.length][];
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            // 去掉引号 [2,3 或者 ,[3,7
            String word = split[i].trim();
            if ("".equals(word)) {
                continue;
            }
            // [2 或者 3 或者 ""
            String[] numSplit = word.split(",");
            List<Integer> list = new ArrayList<>();
            for (String numStr : numSplit) {
                if (numStr.startsWith("[")) {
                    numStr = numStr.substring(1);
                }
                if ("".equals(numStr)) {
                    continue;
                }
                list.add(Integer.parseInt(numStr.trim()));
            }
            result.add(list);
        }
        // 返回数组
        return result;
    }



    @Test
    public void testInputString2IntArrayTwoDimensional() {
        String input = "[[2,3],[3,7],[4,3],[3,7]]";
        int[][] array = inputString2IntArrayTwoDimensional(input);
        System.out.println(Arrays.deepToString(array));
    }


    /**
     * rectangles =height = [0,1,0,2,1,0,1,3,2,1,2,1]
     * 测试用例
     * []
     * null
     * 返回 int  数组
     * @param input
     * @return
     */
    public static int[] inputString2IntArray(String input) {
        return ArrayUtil.string2ArrayiInt(input);
    }



}
