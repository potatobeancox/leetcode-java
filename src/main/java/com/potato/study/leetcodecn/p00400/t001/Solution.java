package com.potato.study.leetcodecn.p00400.t001;

import java.util.Deque;
import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

/**
 * 400. 第 N 位数字
 *
 * 在无限的整数序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...中找到第 n 位数字。
 *
 *  
 *
 * 注意：n 是正数且在 32 位整数范围内（n < 231）。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：3
 * 输出：3
 * 示例 2：
 *
 * 输入：11
 * 输出：0
 * 解释：第 11 位数字在序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... 里是 0 ，它是 10 的一部分。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/nth-digit
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     *
     * @param n
     * @return
     */
    public int findNthDigit(int n) {
        // 确定 n 在几位数里边
        int bitLen = 1;
        while (n > 9 * Math.pow(10, bitLen - 1) * bitLen) {
            n -= 9 * Math.pow(10, bitLen - 1) * bitLen;
            bitLen++;
        }
        // 确定n 在哪个数字里边
        int start = (int) Math.pow(10, bitLen - 1);
        int num = n / bitLen;
        int remind = (n % bitLen);
        int target;
        if (remind != 0) {
            target = start + num;
            return String.valueOf(target).charAt(remind - 1) - '0';
        } else {
            target = start + num - 1;
            return String.valueOf(target).charAt(bitLen - 1) - '0';
        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 3;
        int res = solution.findNthDigit(n);
        System.out.println(res);
        Assert.assertEquals(3, res);

        n = 11;
        res = solution.findNthDigit(n);
        System.out.println(res);
        Assert.assertEquals(0, res);

        n = 10;
        res = solution.findNthDigit(n);
        System.out.println(res);
        Assert.assertEquals(1, res);

        n = 100;
        res = solution.findNthDigit(n);
        System.out.println(res);
        Assert.assertEquals(5, res);
    }



}
