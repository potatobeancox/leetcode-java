package com.potato.study.leetcodecn.p02767.t001;


import java.util.List;

/**
 *
 * 2767. 将字符串分割为最少的美丽子字符串
 *
 * 给你一个二进制字符串 s ，你需要将字符串分割成一个或者多个 子字符串  ，使每个子字符串都是 美丽 的。
 *
 * 如果一个字符串满足以下条件，我们称它是 美丽 的：
 *
 * 它不包含前导 0 。
 * 它是 5 的幂的 二进制 表示。
 * 请你返回分割后的子字符串的 最少 数目。如果无法将字符串 s 分割成美丽子字符串，请你返回 -1 。
 *
 * 子字符串是一个字符串中一段连续的字符序列。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "1011"
 * 输出：2
 * 解释：我们可以将输入字符串分成 ["101", "1"] 。
 * - 字符串 "101" 不包含前导 0 ，且它是整数 51 = 5 的二进制表示。
 * - 字符串 "1" 不包含前导 0 ，且它是整数 50 = 1 的二进制表示。
 * 最少可以将 s 分成 2 个美丽子字符串。
 * 示例 2：
 *
 * 输入：s = "111"
 * 输出：3
 * 解释：我们可以将输入字符串分成 ["1", "1", "1"] 。
 * - 字符串 "1" 不包含前导 0 ，且它是整数 50 = 1 的二进制表示。
 * 最少可以将 s 分成 3 个美丽子字符串。
 * 示例 3：
 *
 * 输入：s = "0"
 * 输出：-1
 * 解释：无法将给定字符串分成任何美丽子字符串。
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 15
 * s[i] 要么是 '0' 要么是 '1' 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/partition-string-into-minimum-beautiful-substrings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int minimumBeautifulSubstrings(String s) {
        // 1 <= s.length <= 15 生成位于这中间的 5的倍数的二进制值，使用记忆优化 记录当前s 能最少分成多少个字符传
        int length = s.length();
        // dp ij  【i，j】 之间字符传可以组成 5的幂 的 最小个数 不能有前导入 0

        // 最外层枚举 区间长度 从1 到length 内部计算开始终点位置

        return -1;
    }
}
