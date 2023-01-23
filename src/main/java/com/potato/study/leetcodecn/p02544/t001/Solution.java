package com.potato.study.leetcodecn.p02544.t001;

/**
 * 2544. 交替数字和
 *
 * 给你一个正整数 n 。n 中的每一位数字都会按下述规则分配一个符号：
 *
 * 最高有效位 上的数字分配到 正 号。
 * 剩余每位上数字的符号都与其相邻数字相反。
 * 返回所有数字及其对应符号的和。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 521
 * 输出：4
 * 解释：(+5) + (-2) + (+1) = 4
 * 示例 2：
 *
 * 输入：n = 111
 * 输出：1
 * 解释：(+1) + (-1) + (+1) = 1
 * 示例 3：
 *
 * 输入：n = 886996
 * 输出：0
 * 解释：(+8) + (-8) + (+6) + (-9) + (+9) + (-6) = 0
 *  
 *
 * 提示：
 *
 * 1 <= n <= 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/alternating-digit-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int alternateDigitSum(int n) {
        String s = String.valueOf(n);
        int sum = 0;
        int op = 1;
        for (char ch : s.toCharArray()) {
            sum += (ch - '0') * op;
            op *= -1;
        }
        return sum;
    }



}
