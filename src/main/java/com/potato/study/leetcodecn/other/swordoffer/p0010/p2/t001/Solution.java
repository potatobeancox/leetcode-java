package com.potato.study.leetcodecn.other.swordoffer.p0010.p2.t001;

import org.junit.Assert;

/**
 * 剑指 Offer 10- II. 青蛙跳台阶问题
 *
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。

 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。

 示例 1：

 输入：n = 2
 输出：2
 示例 2：

 输入：n = 7
 输出：21
 示例 3：

 输入：n = 0
 输出：1
 提示：

 0 <= n <= 100
 注意：本题与主站 70 题相同：https://leetcode-cn.com/problems/climbing-stairs/

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     *
     * @param n
     * @return
     */
    public int numWays(int n) {
        if (n <= 1) {
            return 1;
        }
        int step1 = 1;
        int step2 = 1;
        int step = 0;
        for (int i = 2; i <= n; i++) {
            step = step1 % 1000000007 + step2 % 1000000007;
            step = step % 1000000007;
            step2 = step1;
            step1 = step;
        }
        return step;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 2;
        int i = solution.numWays(n);
        System.out.println(i);
        Assert.assertEquals(2, i);

        n = 7;
        i = solution.numWays(n);
        System.out.println(i);
        Assert.assertEquals(21, i);

        n = 44;
        i = solution.numWays(n);
        System.out.println(i);
        Assert.assertEquals(134903163, i);
    }

}
