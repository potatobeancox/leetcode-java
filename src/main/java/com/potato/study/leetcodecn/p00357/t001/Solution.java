package com.potato.study.leetcodecn.p00357.t001;

import org.junit.Assert;

/**
 * 357. 计算各个位数不同的数字个数
 *
 * 给定一个非负整数 n，计算各位数字都不同的数字 x 的个数，其中 0 ≤ x < 10n 。

 示例:

 输入: 2
 输出: 91
 解释: 答案应为除去 11,22,33,44,55,66,77,88,99 外，在 [0,100) 区间内的所有数字。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/count-numbers-with-unique-digits
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     *
     * @param n
     * @return
     */
    public int countNumbersWithUniqueDigits(int n) {
        if (0 == n) {
            return 1;
        }
        // 1. 计算 没有0的情况 dp i = dp i-1 * （9-i） 初始化 计算dp 0 = 9
        int[] dp = new int[n];
        dp[0] = 9;
        int total = 9;
        for (int i = 1; i < n; i++) {
            dp[i] = dp[i-1] * (9-i);
            total += dp[i];
        }
        // 只有一个位置 0
        total++;
        // 2. 计算有0 的情况 k个数字 其中一个 是 0 也就是 有 k-1 放置方法 num = k-1 * dp【k-2】
        for (int i = 2; i <= n; i++) {
            total += (i-1) * dp[i-2];
        }
        // 3. 遍历计算综和
        return total;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 2;
        int i = solution.countNumbersWithUniqueDigits(n);
        System.out.println(i);
        Assert.assertEquals(91, i);
    }
}
