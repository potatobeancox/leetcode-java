package com.potato.study.leetcodecn.p02730.t001;


/**
 *
 * 2730. 找到最长的半重复子字符串
 *
 * 给你一个下标从 0 开始的字符串 s ，这个字符串只包含 0 到 9 的数字字符。

 如果一个字符串 t 中至多有一对相邻字符是相等的，那么称这个字符串是 半重复的 。

 请你返回 s 中最长 半重复 子字符串的长度。

 一个 子字符串 是一个字符串中一段连续 非空 的字符。

  

 示例 1：

 输入：s = "52233"
 输出：4
 解释：最长半重复子字符串是 "5223" ，子字符串从 i = 0 开始，在 j = 3 处结束。
 示例 2：

 输入：s = "5494"
 输出：4
 解释：s 就是一个半重复字符串，所以答案为 4 。
 示例 3：

 输入：s = "1111111"
 输出：2
 解释：最长半重复子字符串是 "11" ，子字符串从 i = 0 开始，在 j = 1 处结束。
  

 提示：

 1 <= s.length <= 50
 '0' <= s[i] <= '9'

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/find-the-longest-semi-repetitive-substring
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode.cn/problems/find-the-longest-semi-repetitive-substring/solution/shuang-zhi-zhen-hua-chuang-pythonjavacgo-nurf/
     * @param s
     * @return
     */
    public int longestSemiRepetitiveSubstring(String s) {
        // 滑动窗口 记录左边位置
        int left = 0;
        int sameCount = 0;
        // 枚举右边位置 并记录same的个数
        int max = 1;
        for (int i = 1; i < s.length(); i++) {
            // 比较当前与之前的是不是重复了
            if (s.charAt(i) == s.charAt(i-1)) {
                sameCount++;
            }
            // 一旦大于1 需要移动左边
            while (sameCount > 1) {
                left++;
                if (s.charAt(left) == s.charAt(left-1)) {
                    sameCount--;
                }
            }
            // 计算大小
            max = Math.max(max, i - left + 1);
        }
        return max;
    }

}
