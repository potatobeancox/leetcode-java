package com.potato.study.leetcodecn.p01012.t001;

import org.junit.Assert;

/**
 * 1012. 至少有 1 位重复的数字
 *
 * 给定正整数 n，返回在 [1, n] 范围内具有 至少 1 位 重复数字的正整数的个数。

  

 示例 1：

 输入：n = 20
 输出：1
 解释：具有至少 1 位重复数字的正数（<= 20）只有 11 。
 示例 2：

 输入：n = 100
 输出：10
 解释：具有至少 1 位重复数字的正数（<= 100）有 11，22，33，44，55，66，77，88，99 和 100 。
 示例 3：

 输入：n = 1000
 输出：262
  

 提示：

 1 <= n <= 109

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/numbers-with-repeated-digits
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int numDupDigitsAtMostN(int n) {
        // n+1 - 没有正整数重复的情况 

        return -1;
    }
}
