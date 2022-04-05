package com.potato.study.leetcodecn.other.Interview.p0016.p0005;


import java.util.HashMap;
import java.util.Map;

/**
 * 面试题 16.05. 阶乘尾数
 *
 * 设计一个算法，算出 n 阶乘有多少个尾随零。

 示例 1:

 输入: 3
 输出: 0
 解释: 3! = 6, 尾数中没有零。
 示例 2:

 输入: 5
 输出: 1
 解释: 5! = 120, 尾数中有 1 个零.
 说明: 你算法的时间复杂度应为 O(log n) 。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/factorial-zeros-lcci
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int trailingZeroes(int n) {
        long totalCount = 0;
        long num = 5;
        while (num <= n) {
            totalCount += (n / num);
            num *= 5;
        }
        return (int) totalCount;
    }
}
