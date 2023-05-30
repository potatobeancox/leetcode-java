package com.potato.study.leetcodecn.p02708.t001;

import org.junit.Assert;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 *
 * 2708. 一个小组的最大实力值
 *
 * 给你一个下标从 0 开始的整数数组 nums ，它表示一个班级中所有学生在一次考试中的成绩。老师想选出一部分同学组成一个 非空 小组，且这个小组的 实力值 最大，如果这个小组里的学生下标为 i0, i1, i2, ... , ik ，那么这个小组的实力值定义为 nums[i0] * nums[i1] * nums[i2] * ... * nums[ik​] 。
 *
 * 请你返回老师创建的小组能得到的最大实力值为多少。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [3,-1,-5,2,5,-9]
 * 输出：1350
 * 解释：一种构成最大实力值小组的方案是选择下标为 [0,2,3,4,5] 的学生。实力值为 3 * (-5) * 2 * 5 * (-9) = 1350 ，这是可以得到的最大实力值。
 * 示例 2：
 *
 * 输入：nums = [-4,-5,-4]
 * 输出：20
 * 解释：选择下标为 [0, 1] 的学生。得到的实力值为 20 。我们没法得到更大的实力值。
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 13
 * -9 <= nums[i] <= 9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-strength-of-a-group
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {




    public long maxStrength(int[] nums) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        long res = Long.MIN_VALUE;
        long min = Long.MIN_VALUE;
        for (int n : nums) {
            if (n < 0) {
                priorityQueue.add(n);
            } else if (n > 0) {
                if (res == Long.MIN_VALUE) {
                    res = n;
                } else {
                    res *= n;
                }
            } else {
                min = 0;
            }
        }
        if (!priorityQueue.isEmpty()) {
            min = Math.max(min, priorityQueue.peek());
        }
        while (priorityQueue.size() >= 2) {
            if (res == Long.MIN_VALUE) {
                res = priorityQueue.poll();
            } else {
                res *= priorityQueue.poll();
            }
            res *= priorityQueue.poll();
        }
        return Math.max(res, min);
    }

    // [-4,-5,-4]
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {
                -4,-5,-4
        };
        long l = solution.maxStrength(nums);
        System.out.println(l);
        Assert.assertEquals(20, l);

        // [-2,-3,8,9] 432
        nums = new int[] {
                -2,-3,8,9
        };
        l = solution.maxStrength(nums);
        System.out.println(l);
        Assert.assertEquals(432, l);
    }

}
