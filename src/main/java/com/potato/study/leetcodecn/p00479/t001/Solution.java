package com.potato.study.leetcodecn.p00479.t001;

import org.junit.Assert;

import java.util.Random;

/**
 * 479. 最大回文数乘积
 *
 * 给定一个整数 n ，返回 可表示为两个 n 位整数乘积的 最大回文整数 。因为答案可能非常大，所以返回它对 1337 取余 。

  

 示例 1:

 输入：n = 2
 输出：987
 解释：99 x 91 = 9009, 9009 % 1337 = 987
 示例 2:

 输入： n = 1
 输出： 9
  

 提示:

 1 <= n <= 8


 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/largest-palindrome-product
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     *
     * @param n
     * @return
     */
    public int largestPalindrome(int n) {
        if (n == 1) {
            return 9;
        }
        // 枚举 左边 n位数 生成 逐步判断是否可以整除
        long leftMax = (long) Math.pow(10, n) - 1;
        for (long i = leftMax; i >= 0; i--) {
            long num = getPalindromeNum(i);
            // 枚举因数 枚举大的
            for (long j = leftMax; j * j >= num; j--) {
                if (num % j == 0 && num / j <= j) {
                    return (int) (num % 1337);
                }
            }
        }
        return -1;
    }

    private long getPalindromeNum(long left) {
        StringBuilder builder = new StringBuilder(String.valueOf(left));
        String reverse = builder.reverse().toString();


        builder = new StringBuilder(String.valueOf(left));
        builder.append(reverse);

        return Long.parseLong(builder.toString());
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int palindromeNum = solution.largestPalindrome(2);
        System.out.println(palindromeNum);
        Assert.assertEquals(987, palindromeNum);

    }
}
