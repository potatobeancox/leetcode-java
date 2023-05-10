package com.potato.study.leetcodecn.p00466.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 466. 统计重复个数
 *
 * 定义 str = [s, n] 表示 str 由 n 个字符串 s 连接构成。
 *
 * 例如，str == ["abc", 3] =="abcabcabc" 。
 * 如果可以从 s2 中删除某些字符使其变为 s1，则称字符串 s1 可以从字符串 s2 获得。
 *
 * 例如，根据定义，s1 = "abc" 可以从 s2 = "abdbec" 获得，仅需要删除加粗且用斜体标识的字符。
 * 现在给你两个字符串 s1 和 s2 和两个整数 n1 和 n2 。由此构造得到两个字符串，其中 str1 = [s1, n1]、str2 = [s2, n2] 。
 *
 * 请你找出一个最大整数 m ，以满足 str = [str2, m] 可以从 str1 获得。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s1 = "acb", n1 = 4, s2 = "ab", n2 = 2
 * 输出：2
 * 示例 2：
 *
 * 输入：s1 = "acb", n1 = 1, s2 = "acb", n2 = 1
 * 输出：1
 *  
 *
 * 提示：
 *
 * 1 <= s1.length, s2.length <= 100
 * s1 和 s2 由小写英文字母组成
 * 1 <= n1, n2 <= 106
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/count-the-repetitions
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 466
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        // 现在给你两个字符串 s1 和 s2 和两个整数 n1 和 n2 。由此构造得到两个字符串，其中 str1 = [s1, n1]、str2 = [s2, n2] 。
        int[] dp = new int[s2.length()];
        // 请你找出一个最大整数 m ，以满足 str = [str2, m] 可以从 str1 获得。
        // 用一个 dp i 数组长度 s2 i位置 从 s1 头开始匹配 看看最多能匹配多少个 s2位置的字符串
        for (int i = 0; i < s2.length(); i++) {
            // 从 s1 0开始找看能匹配多少个位置
            int k = i;
            // 循环找 看从 某额点开始找
            for (int j = 0; j < s1.length(); j++) {
                if (s2.charAt(k) == s1.charAt(j)) {
                    dp[i]++;
                    k++;
                    k %= s2.length();
                }
            }
        }
        // 遍历 n1 次 记录当前位置 p 每次找到能最长前进的s2位置 累加 最后 处以 n2 和 s2的长度
        long matchCount = 0;
        int matchIndex = 0;
        for (int i = 0; i < n1; i++) {
            // 主要是用来计数
            int eachMatchCount = dp[matchIndex];
            matchIndex += eachMatchCount;
            matchIndex %= s2.length();

            matchCount += eachMatchCount;
        }
        return (int) (matchCount / s2.length() / n2);
    }
}
