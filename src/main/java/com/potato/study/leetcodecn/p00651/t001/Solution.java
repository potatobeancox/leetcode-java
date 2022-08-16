package com.potato.study.leetcodecn.p00651.t001;

import org.junit.Assert;

/**
 * 651. 4键键盘
 *
 * 假设你有一个特殊的键盘包含下面的按键：
 *
 * A：在屏幕上打印一个 'A'。
 * Ctrl-A：选中整个屏幕。
 * Ctrl-C：复制选中区域到缓冲区。
 * Ctrl-V：将缓冲区内容输出到上次输入的结束位置，并显示在屏幕上。
 * 现在，你可以 最多 按键 n 次（使用上述四种按键），返回屏幕上最多可以显示 'A' 的个数 。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: n = 3
 * 输出: 3
 * 解释:
 * 我们最多可以在屏幕上显示三个'A'通过如下顺序按键：
 * A, A, A
 * 示例 2:
 *
 * 输入: n = 7
 * 输出: 9
 * 解释:
 * 我们最多可以在屏幕上显示九个'A'通过如下顺序按键：
 * A, A, A, Ctrl A, Ctrl C, Ctrl V, Ctrl V
 *  
 *
 * 提示:
 *
 * 1 <= n <= 50
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/4-keys-keyboard
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int maxA(int n) {
        // 执行 n 步最多能获得多少个a
        int[] dp = new int[n+1];
        // 两种转移 要么 是 dp i-1 + 1 要么是 dp i-2-j * j 【1， i-2】
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i-1] + 1;
            // 按了多少次 ctrilv
            for (int j = 1; j <= i-2; j++) {
                dp[i] = Math.max(dp[i], j * dp[i-2-j] + dp[i-2-j]);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.maxA(7);
        System.out.println(i);
        Assert.assertEquals(9, i);
    }


}
