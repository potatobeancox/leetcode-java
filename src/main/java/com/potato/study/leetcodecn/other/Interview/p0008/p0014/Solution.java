package com.potato.study.leetcodecn.other.Interview.p0008.p0014;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 面试题 08.14. 布尔运算
 *
 * 给定一个布尔表达式和一个期望的布尔结果 result，布尔表达式由 0 (false)、1 (true)、& (AND)、 | (OR) 和 ^ (XOR) 符号组成。实现一个函数，算出有几种可使该表达式得出 result 值的括号方法。
 *
 * 示例 1:
 *
 * 输入: s = "1^0|0|1", result = 0
 *
 * 输出: 2
 * 解释: 两种可能的括号方法是
 * 1^(0|(0|1))
 * 1^((0|0)|1)
 * 示例 2:
 *
 * 输入: s = "0&0&0&1^1|0", result = 1
 *
 * 输出: 10
 * 提示：
 *
 * 运算符的数量不超过 19 个
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/boolean-evaluation-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 08.14
    public int countEval(String s, int result) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            int current = s.charAt(0) - '0';
            if (current == result) {
                return 1;
            } else {
                return 0;
            }
        }
        // 最终结果等于 result 的种类数量
        int n = s.length();
        int[][][] dp = new int[n][n][2];
        // 遍历每个字母 设置个数
        for (int i = 0; i < n; i++) {
            if (!Character.isDigit(s.charAt(i))) {
                continue;
            }
            int num = s.charAt(i) - '0';
            dp[i][i][num] = 1;
        }
        // dp ijk 从i到j 包含两端 结果是 0or1的种类数量
        for (int len = 2; len < s.length(); len += 2) {
            // 枚举开始位置
            for (int i = 0; i < s.length(); i+=2) {
                int start = i;
                int end = i + len - 1;
                // 尝试计算 分割点 按照 符号位置 搞
                for (int j = start + 1; j <= end; j+=2) {
                    // 当前是什么符号
                    char op = s.charAt(i);
                    //  0 (false)、1 (true)、& (AND)、 | (OR) 和 ^ (XOR)
                    if (op == '&') {
                        // 任意是0 就行
                        dp[start][end][0] = dp[start][i-1][0] * dp[i+1][end][0] + dp[start][i-1][0] * dp[i+1][end][1]
                                + dp[start][i-1][1] * dp[i+1][end][0];
                        // 两个都是1
                        dp[start][end][1] = dp[start][i-1][1] * dp[i+1][end][1];
                    } else if (op == '|') {
                        // 任意是0 就行
                        dp[start][end][0] = dp[start][i-1][0] * dp[i+1][end][0];
                        // 两个都是1
                        dp[start][end][1] = dp[start][i-1][1] * dp[i+1][end][1] + dp[start][i-1][0] * dp[i+1][end][1]
                                + dp[start][i-1][1] * dp[i+1][end][0];
                    } else if (op == '^') {
                        // 任意是0 就行 异或
                        dp[start][end][0] = dp[start][i-1][0] * dp[i+1][end][0] + dp[start][i-1][0] * dp[i+1][end][1]
                                + dp[start][i-1][1] * dp[i+1][end][0];
                        // 两个都是1
                        dp[start][end][1] = dp[start][i-1][1] * dp[i+1][end][1];
                    }
                }

            }
        }
        return dp[0][n-1][result];
    }




}
