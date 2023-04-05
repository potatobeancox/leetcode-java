package com.potato.study.leetcodecn.other.swordoffer.p0044.p1.t001;

import org.junit.Assert;

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
        if (n < 10) {
            return n;
        }
        long temp = n - 9;
        // 记录当前 是几位数
        long bitCount = 2;
        long startNum = 10;
        long endNum = 99;

        while (temp > (endNum - startNum + 1) * bitCount) {

            temp -= ((endNum - startNum + 1) * bitCount);
            // 更新 位数
            bitCount++;
            startNum *= 10;
            endNum *= 10;
            endNum += 9;

            // 找到了位置
        }
        // 看一下在哪个数字里边
        long bitNumIndex = (temp-1) / bitCount;
        long targetNum = startNum + bitNumIndex;
        String s = String.valueOf(targetNum);
        long targetIndex = (temp - 1) % bitCount;
        return s.charAt((int)targetIndex) - '0';
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int nthDigit = solution.findNthDigit(11);
        System.out.println(nthDigit);
        Assert.assertEquals(0, nthDigit);


        nthDigit = solution.findNthDigit(1000);
        System.out.println(nthDigit);
        Assert.assertEquals(3, nthDigit);


        nthDigit = solution.findNthDigit(1000000000);
        System.out.println(nthDigit);
        Assert.assertEquals(1, nthDigit);

    }

}
