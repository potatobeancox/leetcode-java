package com.potato.study.leetcodecn.p02815.t001;


import java.util.PriorityQueue;

import org.junit.Assert;

/**
 *
 * 2815. 数组中的最大数对和
 *
 * 给你一个下标从 0 开始的整数数组 nums 。请你从 nums 中找出和 最大 的一对数，且这两个数数位上最大的数字相等。
 *
 * 返回最大和，如果不存在满足题意的数字对，返回 -1 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [51,71,17,24,42]
 * 输出：88
 * 解释：
 * i = 1 和 j = 2 ，nums[i] 和 nums[j] 数位上最大的数字相等，且这一对的总和 71 + 17 = 88 。
 * i = 3 和 j = 4 ，nums[i] 和 nums[j] 数位上最大的数字相等，且这一对的总和 24 + 42 = 66 。
 * 可以证明不存在其他数对满足数位上最大的数字相等，所以答案是 88 。
 * 示例 2：
 *
 * 输入：nums = [1,2,3,4]
 * 输出：-1
 * 解释：不存在数对满足数位上最大的数字相等。
 *
 *
 * 提示：
 *
 * 2 <= nums.length <= 100
 * 1 <= nums[i] <= 104
 *
 */
public class Solution {


    public int maxSum(int[] nums) {
        // 采集从0-9的每个数字的最大值
        PriorityQueue<Integer>[] queues = new PriorityQueue[10];
        // init
        for (int i = 0; i <= 9; i++) {
            // 小根堆
            queues[i] = new PriorityQueue<>();
        }
        for (int num : nums) {
            // 找到每个数字的位置
            int target = num;
            int maxBit = -1;
            while (target != 0) {
                int bit = target % 10;
                maxBit = Math.max(maxBit, bit);

                target /= 10;
            }

            if (maxBit < 0) {
                continue;
            }
            queues[maxBit].add(num);
            // 超过限制 弹出超过
            if (queues[maxBit].size() > 2) {
                queues[maxBit].poll();
            }
        }
        // 找最大
        int max = -1;
        for (int i = 0; i <= 9; i++) {
            if (queues[i].size() != 2) {
                continue;
            }
            max = Math.max(max, queues[i].poll() + queues[i].poll());
        }
        return max;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {
                51,71,17,24,42
        };
        int i = solution.maxSum(nums);
        System.out.println(i);
        Assert.assertEquals(88, i);
    }


}
