package com.potato.study.leetcodecn.p02743.t001;


import java.util.HashSet;
import java.util.Set;

/**
 *
 * 2743. 计算没有重复字符的子字符串数量
 *
 * 给定你一个只包含小写英文字母的字符串 s 。如果一个子字符串不包含任何字符至少出现两次（换句话说，它不包含重复字符），则称其为 特殊 子字符串。你的任务是计算 特殊 子字符串的数量。例如，在字符串 "pop" 中，子串 "po" 是一个特殊子字符串，然而 "pop" 不是 特殊 子字符串（因为 'p' 出现了两次）。
 *
 * 返回 特殊 子字符串的数量。
 *
 * 子字符串 是指字符串中连续的字符序列。例如，"abc" 是 "abcd" 的一个子字符串，但 "acd" 不是。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "abcd"
 * 输出：10
 * 解释：由于每个字符只出现一次，每个子串都是特殊子串。长度为 1 的子串有 4 个，长度为 2 的有 3 个，长度为 3 的有 2 个，长度为 4 的有 1 个。所以一共有 4 + 3 + 2 + 1 = 10 个特殊子串。
 * 示例 2：
 *
 * 输入：s = "ooo"
 * 输出：3
 * 解释：任何长度至少为 2 的子串都包含重复字符。所以我们要计算长度为 1 的子串的数量，即 3 个。
 * 示例 3：
 *
 * 输入：s = "abab"
 * 输出：7
 * 解释：特殊子串如下（按起始位置排序）：
 * 长度为 1 的特殊子串："a", "b", "a", "b"
 * 长度为 2 的特殊子串："ab", "ba", "ab"
 * 并且可以证明没有长度至少为 3 的特殊子串。所以答案是4 + 3 = 7。
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 105
 * s 只包含小写英文字母。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/count-substrings-without-repeating-character
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     *
     * @param s
     * @return
     */
    public int numberOfSpecialSubstrings(String s) {
        // 一个 数组 26个位置 记录每个字母出现的个数
        int[] count = new int[26];
        // 遍历 s 对于每个位置 作为最终位置记录当前 window 个数
        int left = 0;
        // 如果当前位置 没有超过 一个 记录当前 window 的长度 通过 左右 端点 计算
        int totalCount = 0;
        for (int i = 0; i < s.length(); i++) {
            // 只包含小写字母 其他这就不处理了
            int index = s.charAt(i) - 'a';
            count[index]++;
            while (count[index] > 1) {
                // 移动做边节点
                count[s.charAt(left) - 'a']--;
                left++;
            }
            // 以i作为最后一个字母的特殊子串的个数
            int thisCount = i - left + 1;
            totalCount += thisCount;
        }
        return totalCount;
    }

}
