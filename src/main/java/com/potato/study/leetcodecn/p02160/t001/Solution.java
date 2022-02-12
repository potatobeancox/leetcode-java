package com.potato.study.leetcodecn.p02160.t001;

import java.util.Arrays;

/**
 * 2160. 拆分数位后四位数字的最小和
 *
 * 给你一个四位 正 整数 num 。请你使用 num 中的 数位 ，将 num 拆成两个新的整数 new1 和 new2 。new1 和 new2 中可以有 前导 0 ，且 num 中 所有 数位都必须使用。
 *
 * 比方说，给你 num = 2932 ，你拥有的数位包括：两个 2 ，一个 9 和一个 3 。一些可能的 [new1, new2] 数对为 [22, 93]，[23, 92]，[223, 9] 和 [2, 329] 。
 * 请你返回可以得到的 new1 和 new2 的 最小 和。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：num = 2932
 * 输出：52
 * 解释：可行的 [new1, new2] 数对为 [29, 23] ，[223, 9] 等等。
 * 最小和为数对 [29, 23] 的和：29 + 23 = 52 。
 * 示例 2：
 *
 * 输入：num = 4009
 * 输出：13
 * 解释：可行的 [new1, new2] 数对为 [0, 49] ，[490, 0] 等等。
 * 最小和为数对 [4, 9] 的和：4 + 9 = 13 。
 *  
 *
 * 提示：
 *
 * 1000 <= num <= 9999
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-sum-of-four-digit-number-after-splitting-digits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int minimumSum(int num) {
        // 统计非0数位 一共多少个数字
        char[] chars = String.valueOf(num).toCharArray();
        Arrays.sort(chars);
        int num1 = 0;
        int num2 = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '0') {
                continue;
            }
            // 遍历通过记过 从小达到 一次往两个数字上加 知道 消耗 完
            if (i % 2 == 0) {
                num1 *= 10;
                num1 += (chars[i] - '0');
            } else {
                num2 *= 10;
                num2 += (chars[i] - '0');
            }
        }
        // 计算生成的 数字之和
        return num1 + num2;
    }
}
