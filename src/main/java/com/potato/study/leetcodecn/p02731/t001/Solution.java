package com.potato.study.leetcodecn.p02731.t001;


import org.junit.Assert;

import java.util.Arrays;

/**
 *
 * 2731. 移动机器人
 *
 * 有一些机器人分布在一条无限长的数轴上，他们初始坐标用一个下标从 0 开始的整数数组 nums 表示。当你给机器人下达命令时，它们以每秒钟一单位的速度开始移动。

 给你一个字符串 s ，每个字符按顺序分别表示每个机器人移动的方向。'L' 表示机器人往左或者数轴的负方向移动，'R' 表示机器人往右或者数轴的正方向移动。

 当两个机器人相撞时，它们开始沿着原本相反的方向移动。

 请你返回指令重复执行 d 秒后，所有机器人之间两两距离之和。由于答案可能很大，请你将答案对 109 + 7 取余后返回。

 注意：

 对于坐标在 i 和 j 的两个机器人，(i,j) 和 (j,i) 视为相同的坐标对。也就是说，机器人视为无差别的。
 当机器人相撞时，它们 立即改变 它们的前进时间，这个过程不消耗任何时间。
 当两个机器人在同一时刻占据相同的位置时，就会相撞。

 例如，如果一个机器人位于位置 0 并往右移动，另一个机器人位于位置 2 并往左移动，下一秒，它们都将占据位置 1，并改变方向。再下一秒钟后，第一个机器人位于位置 0 并往左移动，而另一个机器人位于位置 2 并往右移动。

 例如，如果一个机器人位于位置 0 并往右移动，另一个机器人位于位置 1 并往左移动，下一秒，第一个机器人位于位置 0 并往左行驶，而另一个机器人位于位置 1 并往右移动。

  

 示例 1：

 输入：nums = [-2,0,2], s = "RLL", d = 3
 输出：8
 解释：
 1 秒后，机器人的位置为 [-1,-1,1] 。现在下标为 0 的机器人开始往左移动，下标为 1 的机器人开始往右移动。
 2 秒后，机器人的位置为 [-2,0,0] 。现在下标为 1 的机器人开始往左移动，下标为 2 的机器人开始往右移动。
 3 秒后，机器人的位置为 [-3,-1,1] 。
 下标为 0 和 1 的机器人之间距离为 abs(-3 - (-1)) = 2 。
 下标为 0 和 2 的机器人之间的距离为 abs(-3 - 1) = 4 。
 下标为 1 和 2 的机器人之间的距离为 abs(-1 - 1) = 2 。
 所有机器人对之间的总距离为 2 + 4 + 2 = 8 。
 示例 2：

 输入：nums = [1,0], s = "RL", d = 2
 输出：5
 解释：
 1 秒后，机器人的位置为 [2,-1] 。
 2 秒后，机器人的位置为 [3,-2] 。
 两个机器人的距离为 abs(-2 - 3) = 5 。
  

 提示：

 2 <= nums.length <= 105
 -2 * 109 <= nums[i] <= 2 * 109
 0 <= d <= 109
 nums.length == s.length 
 s 只包含 'L' 和 'R' 。
 nums[i] 互不相同。


 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/movement-of-robots
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode.cn/problems/movement-of-robots/solution/nao-jin-ji-zhuan-wan-pai-xu-tong-ji-pyth-we55/
     * @param nums
     * @param s
     * @param d
     * @return
     */
    public int sumDistance(int[] nums, String s, int d) {
        // 看了 题解 简单说就是 撞击了 是可以按照原来方向走的 所以 可以直接遍历数组计算 最终位置
        long[] finalPosition = new long[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (s.charAt(i) == 'R') {
                // x 轴正方向
                finalPosition[i] = nums[i] + d;
            } else {
                finalPosition[i] = nums[i] - d;
            }
        }
        // 计算之后进行排序 遍历每个位置 计算 当前点距离 之前点的距离和 就是之前点个数 * 当前位置 减去 之前的sum
        Arrays.sort(finalPosition);
        long sum = 0;
        int mod = 1_000_000_000 + 7;
        long prefixSum = finalPosition[0];
        for (int i = 1; i < nums.length; i++) {
            // 计算 当前点i 到之前每个点位置的距离和
            long currentDistance = finalPosition[i] * i - prefixSum;
            sum += currentDistance;
            // 注意mod
            sum %= mod;

            prefixSum += finalPosition[i];
        }
        return (int) sum;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {1, 0};
        String s = "RL";
        int d = 2;
        int i = solution.sumDistance(nums, s, d);
        System.out.println(i);
        Assert.assertEquals(5, i);
    }

}
