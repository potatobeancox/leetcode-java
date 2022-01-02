package com.potato.study.leetcodecn.p00233.t001;

import org.junit.Assert;

import java.util.Stack;

/**
 * 233. 数字 1 的个数
 *
 * 给定一个整数 n，计算所有小于等于 n 的非负整数中数字 1 出现的个数。

  

 示例 1：

 输入：n = 13
 输出：6
 示例 2：

 输入：n = 0
 输出：0
  

 提示：

 0 <= n <= 109

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/number-of-digit-one
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {
    public int countDigitOne(int n) {
        // 从n的个位开始计数，然后一次判定有多少个1 求和
        int count = 0;
        int bit = 10;
        while (n * 10 >= bit) {
            count += (n / bit);
            int remid = n % bit;
            int max = bit / 10 * 2;
            int min = bit / 10;
            if (remid >= max) {
                count += min;
            } else if (remid >= min) {
                count += (remid - min + 1);
            }
            bit *= 10; // 100 1000
        }
        return count;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.countDigitOne(13);
        System.out.println(i);
        Assert.assertEquals(6, i);


        i = solution.countDigitOne(0);
        System.out.println(i);
        Assert.assertEquals(0, i);

        i = solution.countDigitOne(1);
        System.out.println(i);
        Assert.assertEquals(1, i);


        i = solution.countDigitOne(100);
        System.out.println(i);
        Assert.assertEquals(21, i);
    }
}
