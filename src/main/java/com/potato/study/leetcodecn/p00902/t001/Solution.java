package com.potato.study.leetcodecn.p00902.t001;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 902. 最大为 N 的数字组合
 *
 * 给定一个按 非递减顺序 排列的数字数组 digits 。你可以用任意次数 digits[i] 来写的数字。例如，如果 digits = ['1','3','5']，我们可以写数字，如 '13', '551', 和 '1351315'。

 返回 可以生成的小于或等于给定整数 n 的正整数的个数 。

  

 示例 1：

 输入：digits = ["1","3","5","7"], n = 100
 输出：20
 解释：
 可写出的 20 个数字是：
 1, 3, 5, 7, 11, 13, 15, 17, 31, 33, 35, 37, 51, 53, 55, 57, 71, 73, 75, 77.
 示例 2：

 输入：digits = ["1","4","9"], n = 1000000000
 输出：29523
 解释：
 我们可以写 3 个一位数字，9 个两位数字，27 个三位数字，
 81 个四位数字，243 个五位数字，729 个六位数字，
 2187 个七位数字，6561 个八位数字和 19683 个九位数字。
 总共，可以使用D中的数字写出 29523 个整数。
 示例 3:

 输入：digits = ["7"], n = 8
 输出：1
  

 提示：

 1 <= digits.length <= 9
 digits[i].length == 1
 digits[i] 是从 '1' 到 '9' 的数
 digits 中的所有值都 不同 
 digits 按 非递减顺序 排列
 1 <= n <= 109

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/numbers-at-most-n-given-digit-set
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode.cn/problems/numbers-at-most-n-given-digit-set/solution/zui-da-wei-n-de-shu-zi-zu-he-by-leetcode-f3yi/
     * @param digits
     * @param n
     * @return
     */
    public int atMostNGivenDigitSet(String[] digits, int n) {
        String numStr = String.valueOf(n);
        int len = numStr.length() + 1;
        // dp i 0/1 使用 n的前i个串，0为小于的个数 1是等于的个数
        int[][] dp = new int[len][2];
        // 初始状态 比0还小肯定没有 因为所有 digit都是大于0的
        dp[0][0] = 0;
        // 根0 相等的方法 只有一个空集合
        dp[0][1] = 1;

        // 遍历 num 的每个位置 j 对于每个位置 从前往后 遍历 digit 的每个位置
        for (int i = 1; i < len; i++) {
            int numChar = numStr.charAt(i-1) - '0';
            // for 循环处理 前缀一样的情况
            for (int j = 0; j < digits.length; j++) {
                // 如果当前j等于i位置的值 改一下相等状态
                int digit = digits[j].charAt(0) - '0';
                if (digit == numChar) {
                    // 这种才只能存在相等
                    dp[i][1] = dp[i-1][1];
                } else if (digit < numChar) {
                    // j位置小于 i位置字母 改一下 小的情况
                    dp[i][0] += dp[i-1][1];
                } else {
                    // 当前数字比当前的 i要大了 说明 不管了
                    break;
                }
            }
            // 比当前小的情况 加上 前i-1小作为前缀 加上最后的 digits.length 个的情况乘法的由来
            if (i > 1) {
                // 前i-1已经笑了 那么将他们做成前缀 锁边找个 digit都行，另外就是完全没有前缀的时候
                dp[i][0] += dp[i-1][0] * digits.length + digits.length;
            }
        }
        // dp i 1 = dp i-1 1 +
        return dp[len-1][0] + dp[len-1][1];
    }

}
