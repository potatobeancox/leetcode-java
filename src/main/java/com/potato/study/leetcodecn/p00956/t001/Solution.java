package com.potato.study.leetcodecn.p00956.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.Arrays;

/**
 * 956. 最高的广告牌
 *
 * 你正在安装一个广告牌，并希望它高度最大。这块广告牌将有两个钢制支架，两边各一个。每个钢支架的高度必须相等。

 你有一堆可以焊接在一起的钢筋 rods。举个例子，如果钢筋的长度为 1、2 和 3，则可以将它们焊接在一起形成长度为 6 的支架。

 返回 广告牌的最大可能安装高度 。如果没法安装广告牌，请返回 0 。

  

 示例 1：

 输入：[1,2,3,6]
 输出：6
 解释：我们有两个不相交的子集 {1,2,3} 和 {6}，它们具有相同的和 sum = 6。
 示例 2：

 输入：[1,2,3,4,5,6]
 输出：10
 解释：我们有两个不相交的子集 {2,3,5} 和 {4,6}，它们具有相同的和 sum = 10。
 示例 3：

 输入：[1,2]
 输出：0
 解释：没法安装广告牌，所以返回 0。
  

 提示：

 0 <= rods.length <= 20
 1 <= rods[i] <= 1000
 sum(rods[i]) <= 5000

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/tallest-billboard
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int tallestBillboard(int[] rods) {
        // 求 rods sum
        int sum = 0;
        for (int rod : rods) {
            sum += rod;
        }
        int[] dp = new int[sum+1];
        // -1 就是 当前 i 状态还没有办法达到
        Arrays.fill(dp, -1);
        dp[0] = 0;
        // 对于 每个 rod 枚举每个 状态
        for (int rod : rods) {
            int[] clone = dp.clone();
            for (int i = 0; i < dp.length; i++) {
                // 之前为-1 就是i就是没办法达到 直接con
                if (clone[i] == -1) {
                    continue;
                }
                // i 可以达到了 要看 放在那边 假设放在 多的那边 可能是没有变 dp i 说明 差距i的最大值 加在最大的时候max 肯定不变
                if (i+rod < dp.length) {
                    dp[i+rod] = Math.max(dp[i+rod], clone[i]+rod);
                }
                // 放在少的一遍 要看 i 和 rod， 哪个大
                if (i >= rod) {
                    // 放在低的那一边而且 差距要比之前的大
                    dp[i-rod] = Math.max(dp[i-rod], clone[i]);
                    // 高的 clone i 低的 clone i - i
                } else {
                    // i < rod 高的都被用了
                    dp[rod-i] = Math.max(dp[rod-i], clone[i] - i + rod);
                }
            }
        }
        // dp i 左右两边差距为 i 情况下 最长的长度为多少
        // 过程中间记录两边 相同的最大值
        return dp[0];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] rods = new int[] {
                1,2,3,6
        };
        int i = solution.tallestBillboard(rods);
        System.out.println(i);
        Assert.assertEquals(6, i);


        rods = new int[] {
                3,4,3,3,2
        };
        i = solution.tallestBillboard(rods);
        System.out.println(i);
        Assert.assertEquals(6, i);

        rods = new int[] {
                61,45,43,54,40,53,55,47,51,59,42
        };
        i = solution.tallestBillboard(rods);
        System.out.println(i);
        Assert.assertEquals(275, i);
    }
}
