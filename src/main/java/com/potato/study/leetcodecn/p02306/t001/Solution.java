package com.potato.study.leetcodecn.p02306.t001;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 2306. 公司命名
 *
 * 给你一个字符串数组 ideas 表示在公司命名过程中使用的名字列表。公司命名流程如下：
 *
 * 从 ideas 中选择 2 个 不同 名字，称为 ideaA 和 ideaB 。
 * 交换 ideaA 和 ideaB 的首字母。
 * 如果得到的两个新名字 都 不在 ideas 中，那么 ideaA ideaB（串联 ideaA 和 ideaB ，中间用一个空格分隔）是一个有效的公司名字。
 * 否则，不是一个有效的名字。
 * 返回 不同 且有效的公司名字的数目。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：ideas = ["coffee","donuts","time","toffee"]
 * 输出：6
 * 解释：下面列出一些有效的选择方案：
 * - ("coffee", "donuts")：对应的公司名字是 "doffee conuts" 。
 * - ("donuts", "coffee")：对应的公司名字是 "conuts doffee" 。
 * - ("donuts", "time")：对应的公司名字是 "tonuts dime" 。
 * - ("donuts", "toffee")：对应的公司名字是 "tonuts doffee" 。
 * - ("time", "donuts")：对应的公司名字是 "dime tonuts" 。
 * - ("toffee", "donuts")：对应的公司名字是 "doffee tonuts" 。
 * 因此，总共有 6 个不同的公司名字。
 *
 * 下面列出一些无效的选择方案：
 * - ("coffee", "time")：在原数组中存在交换后形成的名字 "toffee" 。
 * - ("time", "toffee")：在原数组中存在交换后形成的两个名字。
 * - ("coffee", "toffee")：在原数组中存在交换后形成的两个名字。
 * 示例 2：
 *
 * 输入：ideas = ["lack","back"]
 * 输出：0
 * 解释：不存在有效的选择方案。因此，返回 0 。
 *  
 *
 * 提示：
 *
 * 2 <= ideas.length <= 5 * 104
 * 1 <= ideas[i].length <= 10
 * ideas[i] 由小写英文字母组成
 * ideas 中的所有字符串 互不相同
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/naming-a-company
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {




    /**
     * 2306
     * @param ideas
     * @return
     */
    public long distinctNames(String[] ideas) {
        // 使用一个 二维数组 记录 将idea 从 某个字母换成 另一个字母 的可能数量
        Set<String> set = new HashSet<>();
        for (String idea :ideas) {
            set.add(idea);
        }
        long[][] count = new long[26][26];
        for (String idea : ideas) {
            char fromChar = idea.charAt(0);
            // 从每个点开始 换 换了之后 看看 在不在 set里边
            for (int i = 0; i < 26; i++) {
                char replaceChar = (char) ('a' + i);
                if (replaceChar == fromChar) {
                    continue;
                }
                // 没存在 可以换
                String replaceWord = String.valueOf(replaceChar);
                if (idea.length() > 1) {
                    replaceWord += idea.substring(1);
                }
                if (!set.contains(replaceWord)) {
                    count[fromChar - 'a'][replaceChar - 'a']++;
                }
            }
        }
        // 遍历 ideas 计算 从另外字符 到这个 字符 有多少个 对个数
        long totalResult = 0;
        for (String idea : ideas) {
            char toChar = idea.charAt(0);
            // 从每个点开始 换 换了之后 看看 在不在 set里边
            for (int i = 0; i < 26; i++) {
                char fromChar = (char) ('a' + i);
                if (fromChar == fromChar) {
                    continue;
                }
                // 没存在 可以换
                String replaceWord = String.valueOf(fromChar);
                if (idea.length() > 1) {
                    replaceWord += idea.substring(1);
                }
                if (!set.contains(replaceWord)) {
                    totalResult += count[fromChar - 'a'][toChar - 'a'];
                }
            }
        }
        return totalResult;
    }

}
