package com.potato.study.leetcodecn.other.Interview.p0008.p0008;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 面试题 08.08. 有重复字符串的排列组合
 *
 * 有重复字符串的排列组合。编写一种方法，计算某字符串的所有排列组合。
 *
 * 示例1:
 *
 *  输入：S = "qqe"
 *  输出：["eqq","qeq","qqe"]
 * 示例2:
 *
 *  输入：S = "ab"
 *  输出：["ab", "ba"]
 * 提示:
 *
 * 字符都是英文字母。
 * 字符串长度在[1, 9]之间。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutation-ii-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public String[] permutation(String s) {
        Set<String> result = new HashSet<>();
        char[] chars = s.toCharArray();
        // 按照升序排序
        Arrays.sort(chars);
        boolean[] visit = new boolean[s.length()];
        StringBuilder builder = new StringBuilder();
        dfs(result, chars, visit, builder);
        String[] res = new String[result.size()];
        int index = 0;
        for (String element : result) {
            res[index] = element;
            index++;
        }
        return res;
    }

    /**
     *
     * @param result
     * @param chars
     * @param visit
     */
    private void dfs(Set<String> result, char[] chars, boolean[] visit, StringBuilder builder) {
        if (builder.length() == visit.length) {
            // 找到单词
            result.add(builder.toString());
        }
        int n = visit.length;
        // 遍历 visit 找到第一个没用的字符 使用
        for (int i = 0; i < n; i++) {
            if (visit[i]) {
                continue;
            }
            // 使用这个字母 dfs
            visit[i] = true;
            builder.append(chars[i]);
            dfs(result, chars, visit, builder);
            builder.deleteCharAt(builder.length() - 1);
            visit[i] = false;
        }
    }

}
