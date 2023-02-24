package com.potato.study.leetcodecn.p02566.t001;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * 2566. 替换一个数字后的最大差值
 给你一个整数 num 。你知道 Danny Mittal 会偷偷将 0 到 9 中的一个数字 替换 成另一个数字。

 请你返回将 num 中 恰好一个 数字进行替换后，得到的最大值和最小值的差位多少。

 注意：

 当 Danny 将一个数字 d1 替换成另一个数字 d2 时，Danny 需要将 nums 中所有 d1 都替换成 d2 。
 Danny 可以将一个数字替换成它自己，也就是说 num 可以不变。
 Danny 可以将数字分别替换成两个不同的数字分别得到最大值和最小值。
 替换后得到的数字可以包含前导 0 。
 Danny Mittal 获得周赛 326 前 10 名，让我们恭喜他。
  

 示例 1：

 输入：num = 11891
 输出：99009
 解释：
 为了得到最大值，我们将数字 1 替换成数字 9 ，得到 99899 。
 为了得到最小值，我们将数字 1 替换成数字 0 ，得到 890 。
 两个数字的差值为 99009 。
 示例 2：

 输入：num = 90
 输出：99
 解释：
 可以得到的最大值是 99（将 0 替换成 9），最小值是 0（将 9 替换成 0）。
 所以我们得到 99 。
  

 提示：

 1 <= num <= 108

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/maximum-difference-by-remapping-a-digit
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 2566
    public int minMaxDifference(int num) {
        // 找到第一个不为9的换成9
        char[] chars = String.valueOf(num).toCharArray();
        StringBuilder max = new StringBuilder();
        StringBuilder min = new StringBuilder();
        Character replace1 = null;
        Character replace2 = null;
        for (int i = 0; i < chars.length; i++) {
            // replace1 对应max 把第一个不为9的变成9
            if (chars[i] != '9' && replace1 == null) {
                replace1 = chars[i];
            }
            if (chars[i] != '0' && replace2 == null) {
                replace2 = chars[i];
            }
            // 执行替换
            if (replace1 == null) {
                max.append(chars[i]);
            } else {
                if (replace1.charValue() == chars[i]) {
                    max.append('9');
                } else {
                    max.append(chars[i]);
                }
            }
            if (replace2 == null) {
                min.append(chars[i]);
            } else {
                if (replace2.charValue() == chars[i]) {
                    min.append('0');
                } else {
                    min.append(chars[i]);
                }
            }
        }
        // 计算 最大和最小值 中间差多少
        long l = Long.valueOf(max.toString()) - Long.valueOf(min.toString());
        return (int) l;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.minMaxDifference(11891);
        System.out.println(i);
        Assert.assertEquals(99009, i);


        i = solution.minMaxDifference(90);
        System.out.println(i);
        Assert.assertEquals(99, i);


    }

}
