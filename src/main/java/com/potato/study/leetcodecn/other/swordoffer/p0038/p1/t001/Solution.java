package com.potato.study.leetcodecn.other.swordoffer.p0038.p1.t001;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 剑指 Offer 38. 字符串的排列
 *
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 *
 *  
 *
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 *
 *  
 *
 * 示例:
 *
 * 输入：s = "abc"
 * 输出：["abc","acb","bac","bca","cab","cba"]
 *  
 *
 * 限制：
 *
 * 1 <= s 的长度 <= 8
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {
    /**
     * 递归生成
     * @param s
     * @return
     */
    public String[] permutation(String s) {
        boolean[] visit = new boolean[s.length()];
        Set<String> resultSet = new HashSet<>();
        getPermutation(s, "", visit, resultSet);
        String[] res = new String[resultSet.size()];
        int i = 0;
        for (String word : resultSet) {
            res[i] = word;
            i++;
        }
        return res;
    }


    private void getPermutation(String s, String builder, boolean[] visit, Set<String> resultSet) {
        // 终止条件
        if (s.length() == builder.length()) {
            resultSet.add(builder);
        }
        // 遍历 visit 获取 字母 填入 build 并修改visitt
        for (int i = 0; i < s.length(); i++) {
            if (visit[i]) {
                continue;
            }
            char ch = s.charAt(i);
            visit[i] = true;
            getPermutation(s, builder + ch, visit, resultSet);
            visit[i] = false;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "abc";
        String[] permutation = solution.permutation(s);
        System.out.println(Arrays.toString(permutation));
    }
}
