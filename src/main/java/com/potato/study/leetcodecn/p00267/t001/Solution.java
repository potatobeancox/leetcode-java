package com.potato.study.leetcodecn.p00267.t001;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 267. 回文排列 II

 *
 * 给定一个字符串 s ，返回 其重新排列组合后可能构成的所有回文字符串，并去除重复的组合 。

 你可以按 任意顺序 返回答案。如果 s 不能形成任何回文排列时，则返回一个空列表。

  

 示例 1：

 输入: s = "aabb"
 输出: ["abba", "baab"]
 示例 2：

 输入: s = "abc"
 输出: []
  

 提示：

 1 <= s.length <= 16
 s 仅由小写英文字母组成

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/palindrome-permutation-ii
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     *
     * https://leetcode.cn/problems/palindrome-permutation-ii/solution/jing-dian-hui-su-dfs-ke-zhuan-hua-wei-qu-h2o1/
     * @param s
     * @return
     */
    public List<String> generatePalindromes(String s) {
        // 遍历 s 统计一共有多少个 字符 按照 字符数字/2 计算出现次数 统计一下单独出现的次数
        Map<Character, Integer> appearCountMap = new HashMap<>();
        for (char ch : s.toCharArray()) {
            int count = appearCountMap.getOrDefault(ch, 0);
            count++;
            appearCountMap.put(ch, count);
        }
        // 统计单独出现次数
        int oddCount = 0;
        Character oddCh = null;
        int size = 0;
        for (char ch : appearCountMap.keySet()) {
            int count = appearCountMap.get(ch);
            size += count / 2;
            if (count % 2 == 0) {
                continue;
            }
            oddCount++;
            oddCh = ch;
        }
        // 奇数个数超过 1 不行
        if (oddCount > 1) {
            return new ArrayList<>();
        }
        // 使用哪些字符
        // 按照顺序生成字符和 visit 数组
        char[] chars = new char[size];
        int index = 0;
        for (char ch : appearCountMap.keySet()) {
            int count = appearCountMap.get(ch);
            for (int i = 0; i < count / 2; i++) {
                chars[index++] = ch;
            }
        }
        boolean[] used = new boolean[size];
        List<String> result = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        // dfs 生成结果
        dfs(chars, used, result, builder, oddCh);
        return result;
    }

    /**
     *
     * @param chars
     * @param used
     * @param result
     * @param currentWordBuilder
     */
    private void dfs(char[] chars, boolean[] used, List<String> result, StringBuilder
            currentWordBuilder, Character oddCh) {
        int size = chars.length;
        // 当所有字符都使用完了 之后 生成最终答案
        if (size == currentWordBuilder.length()) {

            if (oddCh == null) {
                // 没有奇数次数的单词
                result.add(currentWordBuilder.toString()
                        + currentWordBuilder.reverse().toString());


                currentWordBuilder.reverse();

            } else {
                result.add(currentWordBuilder.toString()
                        + oddCh
                        + currentWordBuilder.reverse().toString());


                currentWordBuilder.reverse();
            }
            return;
        }
        // 没有使用完了 从0 开始 遍历 字符数组 ，选择一个 字符进行递归遍历
        for (int i = 0; i < chars.length; i++) {
            // 用过了 用下一个
            if (used[i]) {
                continue;
            }
            // 如果当前字幕和上一个字幕一致 且上一个也没有用过 那就不用了 剪枝
            if (i > 0 && chars[i] == chars[i-1] && !used[i-1]) {
                continue;
            }
            // 用了当前这个 字母
            used[i] = true;
            currentWordBuilder.append(chars[i]);
            // 递归
            dfs(chars, used, result, currentWordBuilder, oddCh);
            used[i] = false;
            currentWordBuilder.deleteCharAt(currentWordBuilder.length() - 1);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "aabbcc";
        List<String> strings = solution.generatePalindromes(s);
        System.out.println(strings);
    }


}
