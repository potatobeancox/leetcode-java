package com.potato.study.leetcodecn.other.swordoffer.p0046.p1.t001;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

/**
 * 剑指 Offer 46. 把数字翻译成字符串
 *
 * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: 12258
 * 输出: 5
 * 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * dp dp i 代表 i-1 位置的可能性 （contain i-1 位置）
     * dp 0 = 0
     * dp 1 = 1
     *
     * dp i = dp i-1 + （dp i- 2）如果 i i-1 在26 以内
     * @param num
     * @return
     */
    public int translateNum(int num) {
        // 边界
        if (num == 0) {
            return 1;
        } else if (num < 0) {
            throw new RuntimeException("input param invalid");
        }
        // num -> list
        List<Long> numList = this.getNumList(num);
        // dp
        int[] dp = new int[numList.size() + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = dp[i-1];
            // 如果 i位置 数字和 i-1 能组装10-26 之间的数字就行
            Long num1 = numList.get(i-1);
            Long num2 = numList.get(i-2);
            long target = num2 * 10 + num1;
            if (10 <= target && target <= 25) {
                dp[i] += dp[i-2];
            }
        }
        // retuen result
        return dp[numList.size()];
    }

    /**
     * 生成 一个数组组成的list 每个元素 只有一位数字
     * @param num
     * @return
     */
    private List<Long> getNumList (int num) {
        List<Long> list = new ArrayList<>();
        while (num > 0) {
            long bit = num % 10;
            num /= 10;
            list.add(0, bit);
        }
        return list;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int num = 12258;
        int kk = solution.translateNum(num);
        System.out.println(kk);
        Assert.assertEquals(5, kk);


        num = 26;
        kk = solution.translateNum(num);
        System.out.println(kk);
        Assert.assertEquals(1, kk);

    }

}
