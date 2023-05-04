package com.potato.study.leetcodecn.p01788.t001;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * 1788. 最大化花园的美观度
 *
 * 有一个花园，有 n 朵花，这些花都有一个用整数表示的美观度。这些花被种在一条线上。给定一个长度为 n 的整数类型数组 flowers ，每一个 flowers[i] 表示第 i 朵花的美观度。
 *
 * 一个花园满足下列条件时，该花园是有效的。
 *
 * 花园中至少包含两朵花。
 * 第一朵花和最后一朵花的美观度相同。
 * 作为一个被钦定的园丁，你可以从花园中去除任意朵花（也可以不去除任意一朵）。你想要通过一种方法移除某些花朵，使得剩下的花园变得有效。花园的美观度是其中所有剩余的花朵美观度之和。
 *
 * 返回你去除了任意朵花（也可以不去除任意一朵）之后形成的有效花园中最大可能的美观度。
 *
 *  
 *
 * 示例 1：
 *
 * 输入: flowers = [1,2,3,1,2]
 * 输出: 8
 * 解释: 你可以修整为有效花园 [2,3,1,2] 来达到总美观度 2 + 3 + 1 + 2 = 8。
 * 示例 2：
 *
 * 输入: flowers = [100,1,1,-3,1]
 * 输出: 3
 * 解释: 你可以修整为有效花园 [1,1,1] 来达到总美观度 1 + 1 + 1 = 3。
 * 示例 3：
 *
 * 输入: flowers = [-1,-2,0,-1]
 * 输出: -2
 * 解释: 你可以修整为有效花园 [-1,-1] 来达到总美观度 -1 + -1 = -2。
 *  
 *
 * 提示：
 *
 * 2 <= flowers.length <= 105
 * -104 <= flowers[i] <= 104
 * 去除一些花朵（可能没有）后，是有可能形成一个有效花园的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximize-the-beauty-of-the-garden
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {



    // 1788
    public int maximumBeauty(int[] flowers) {
        // map 记录每种花 第一次出现的位置 index
        Map<Integer, Integer> firstAppearIndexMap = new HashMap<>();
        // 前缀和数组 记录 到当前位置 include 之前的 正整数的和
        int length = flowers.length;
        long[] prefixSum = new long[length];
        long max = Long.MIN_VALUE;
        // 遍历 flowers 对于每个位置 i 找到前面存在 的index 计算 包含i和 index 在内的正数的和
        for (int i = 0; i < flowers.length; i++) {
            if (flowers[i] > 0) {
                prefixSum[i] = flowers[i];
            }
            if (i > 0) {
                prefixSum[i] += prefixSum[i-1];
            }
            // 求之前的和
            Integer beforeIndex = firstAppearIndexMap.get(flowers[i]);
            if (beforeIndex != null) {
                long sum = prefixSum[i];
                if (beforeIndex > 0) {
                    sum -= prefixSum[beforeIndex-1];
                }
                // 如果当前是 负数还要加上两端 计算之后 维护最大值
                if (flowers[i] < 0) {
                    sum += 2 * flowers[i];
                }
                // 计算max
                max = Math.max(max, sum);
            }
            if (!firstAppearIndexMap.containsKey(flowers[i])) {
                firstAppearIndexMap.put(flowers[i], i);
            }
        }
        return (int) max;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        // [1,2,3,1,2]
        int[] flowers = new int[] {
                1,2,3,1,2
        };
        int i = solution.maximumBeauty(flowers);
        System.out.println(i);
        Assert.assertEquals(8, i);
    }
}
