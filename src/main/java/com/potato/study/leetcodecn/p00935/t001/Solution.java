package com.potato.study.leetcodecn.p00935.t001;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.junit.Assert;

import com.potato.study.leetcode.util.LeetcodeInputUtils;

/**
 * 935. 骑士拨号器
 *
 * 象棋骑士有一个独特的移动方式，它可以垂直移动两个方格，水平移动一个方格，或者水平移动两个方格，垂直移动一个方格(两者都形成一个 L 的形状)。
 *
 * 象棋骑士可能的移动方式如下图所示:
 *
 *
 *
 * 我们有一个象棋骑士和一个电话垫，如下所示，骑士只能站在一个数字单元格上(即蓝色单元格)。
 *
 *
 *
 * 给定一个整数 n，返回我们可以拨多少个长度为 n 的不同电话号码。
 *
 * 你可以将骑士放置在任何数字单元格上，然后你应该执行 n - 1 次移动来获得长度为 n 的号码。所有的跳跃应该是有效的骑士跳跃。
 *
 * 因为答案可能很大，所以输出答案模 109 + 7.
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 1
 * 输出：10
 * 解释：我们需要拨一个长度为1的数字，所以把骑士放在10个单元格中的任何一个数字单元格上都能满足条件。
 * 示例 2：
 *
 * 输入：n = 2
 * 输出：20
 * 解释：我们可以拨打的所有有效号码为[04, 06, 16, 18, 27, 29, 34, 38, 40, 43, 49, 60, 61, 67, 72, 76, 81, 83, 92, 94]
 * 示例 3：
 *
 * 输入：n = 3131
 * 输出：136006598
 * 解释：注意取模
 *  
 *
 * 提示：
 *
 * 1 <= n <= 5000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/knight-dialer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int knightDialer(int n) {
        // dp time num 第time 跳完 到大 num 的走法数量
        long[][] dp = new long[n][10];
        // 初始 time = 0 是都只有 一种走法 就是 停在这里
        for (int i = 0; i < 10; i++) {
            dp[0][i] = 1;
        }
        long mod = 1_000_000_000 + 7;
        // 对于每个 位置 找到 相邻的位置 事先知道 转移
        int[][] dir = new int[][] {
                {4, 6},
                {6, 8},
                {7, 9},
                {4, 8},
                {0, 3, 9},
                {},
                {0, 1, 7},
                {2, 6},
                {1, 3},
                {2, 4}
        };
        for (int time = 1; time < n; time++) {
            for (int i = 0; i < 10; i++) {
                int[] step = dir[i];
                for (int j = 0; j < step.length; j++) {
                    dp[time][i] = (dp[time][i] + dp[time-1][step[j]]) % mod;
                }
            }
        }

        long result = 0;
        for (int i = 0; i < 10; i++) {
            result += dp[n-1][i];
            result %= mod;
        }

        return (int) result;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 1;
        int i = solution.knightDialer(n);
        System.out.println(i);
        Assert.assertEquals(10, i);


        n = 3131;
        i = solution.knightDialer(n);
        System.out.println(i);
        Assert.assertEquals(136006598, i);
    }
}
