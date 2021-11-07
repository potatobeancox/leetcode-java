package com.potato.study.leetcodecn.other.swordoffer2.p0086.t001;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 剑指 Offer II 086. 分割回文子字符串
 *
 * 给定一个字符串 s ，请将 s 分割成一些子串，使每个子串都是 回文串 ，返回 s 所有可能的分割方案。
 *
 * 回文串 是正着读和反着读都一样的字符串。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "google"
 * 输出：[["g","o","o","g","l","e"],["g","oo","g","l","e"],["goog","l","e"]]
 * 示例 2：
 *
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 * 示例 3：
 *
 * 输入：s = "a"
 * 输出：[["a"] 
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 16
 * s 仅由小写英文字母组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/M99OJA
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public String[][] partition(String s) {
        // 预处理 dp ij ij之间字符串是否是 回文
        boolean[][] isPalindrome = new boolean[s.length()][s.length()];
        // 如果 ij位置等 那么 && 里边的
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (i == j) {
                    isPalindrome[i][j] = true;
                    continue;
                }
                if (s.charAt(i) != s.charAt(j)) {
                    isPalindrome[i][j] = false;
                    continue;
                }
                // 字母相同
                if (i+1 <= j-1) {
                    isPalindrome[i][j] = isPalindrome[i+1][j-1];
                } else {
                    isPalindrome[i][j] = true;
                }
            }
        }

        // dfs 获取结果

        List<List<String>> resultList = new ArrayList<>();
        dfs(s, 0, isPalindrome, resultList, new ArrayList<>());

        String[][] result = new String[resultList.size()][];
        for (int i = 0; i < result.length; i++) {
            result[i] = new String[resultList.get(i).size()];
            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = resultList.get(i).get(j);
            }
        }

        return result;
    }


    private void dfs(String s, int index, boolean[][] isPalindrome, List<List<String>> resultList, List<String> current) {
        // 终止条件 到头了
        if (index == s.length()) {
            if (current.size() != 0) {
                resultList.add(current);
            }
            return;
        }
        // 遍历下一个位置 
        for (int i = index; i < s.length(); i++) {
            // 不是回文 continue
            if (!isPalindrome[index][i]) {
                continue;
            }
            // 是 回文
            String substring = s.substring(index, i + 1);
            List<String> list = new ArrayList<>(current);
            list.add(substring);
            dfs(s, i + 1, isPalindrome, resultList, list);
        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "google";
        String[][] partition = solution.partition(input);
        System.out.println(Arrays.deepToString(partition));
    }

}
