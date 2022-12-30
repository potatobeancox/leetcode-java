package com.potato.study.leetcodecn.other.swordoffer.p0044.p1.t001;

import java.util.PriorityQueue;

/**
 * 剑指 Offer 44. 数字序列中某一位的数字
 *
 * 数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。

 请写一个函数，求任意第n位对应的数字。

  

 示例 1：

 输入：n = 3
 输出：3
 示例 2：

 输入：n = 11
 输出：0
  

 限制：

 0 <= n < 2^31
 注意：本题与主站 400 题相同：https://leetcode-cn.com/problems/nth-digit/

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/shu-zi-xu-lie-zhong-mou-yi-wei-de-shu-zi-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 剑指 offer 44
    public int findNthDigit(int n) {
        // 记录当前 是几位数
        int bitCount = 1;
        // 当前 bitcount 有多少个数字
        int bitCountNum = 9;
        // 判断当前 n在 多少个 bitCount里边
        while (n > bitCount * bitCountNum) {
            n -= (bitCount * bitCountNum);

            bitCount++;
            bitCountNum *= 10;
        }
        // 定位这个n在第几个数字里边
        int index = n-1;
        // 当前bit 位数字的开始数字
        int startNum = (int) Math.pow(10, bitCount - 1);
        // 当前数字
        int currentNum = startNum + index / bitCount;
        int digitIndex = index % bitCount;
        String s = String.valueOf(currentNum);
        return s.charAt(digitIndex) - '0';
    }

}
