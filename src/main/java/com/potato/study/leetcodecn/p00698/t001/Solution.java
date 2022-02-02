package com.potato.study.leetcodecn.p00698.t001;


import org.junit.Assert;

import java.util.*;

/**
 * 698. 划分为k个相等的子集
 *
 * 给定一个整数数组  nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。

 示例 1：

 输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 输出： True
 说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。
  

 提示：

 1 <= k <= len(nums) <= 16
 0 < nums[i] < 10000


 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/partition-to-k-equal-sum-subsets
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public boolean canPartitionKSubsets(int[] nums, int k) {
        // 计算sum 求是否能被k 整除
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int tmp = nums[left];
            nums[left] = nums[right];
            nums[right] = tmp;

            left++;
            right--;

        }

        int sum = Arrays.stream(nums).sum();
        int target = sum / k;
        // 回溯找到是否可以形成k个相等子集合
        if (target * k != sum) {
            return false;
        }
        if (nums.length < k) {
            return false;
        }
        if (nums[nums.length-1] > target) {
            return false;
        }
        // 用负数代表是否遍历过
        int currentSum = 0;
        int currentCount = 0;
        return canPartitionKSubsetsEach(nums, target, k, currentSum, currentCount);
    }


    /**
     * 回溯
     * @param nums
     * @param target
     * @param targetCount
     * @param currentSum
     * @param currentCount
     * @return
     */
    private boolean canPartitionKSubsetsEach(int[] nums, int target, int targetCount, int currentSum, int currentCount) {
        // 找到 targetCount个了
        if (currentCount == targetCount) {
            return true;
        }
        // 还没找到 targetCount 个
        if (currentSum > target) {
            return false;
        }
        // 遍历 nums 取一个
        for (int i = 0; i < nums.length; i++) {
            // 之前已经遍历过
            if (nums[i] < 0) {
                continue;
            }
            // 使用这个节点
            int currentValue = nums[i];
            if (currentSum + currentValue > target) {
                return false;
            }
            nums[i] *= -1;
            boolean flag;
            if (currentSum + currentValue == target) {
                flag = canPartitionKSubsetsEach(nums, target, targetCount, 0, currentCount+1);
            } else {
                flag = canPartitionKSubsetsEach(nums, target, targetCount, currentSum + currentValue, currentCount);
            }
            if (flag) {
                return true;
            }
            nums[i] *= -1;
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{4,3,2,3,5,2,1};
        int k = 4;
        boolean b = solution.canPartitionKSubsets(nums, k);
        System.out.println(b);
        Assert.assertEquals(true, b);




        nums = new int[]{129,17,74,57,1421,99,92,285,1276,218,1588,215,369,117,153,22};
        k = 3;
        b = solution.canPartitionKSubsets(nums, k);
        System.out.println(b);
//        Assert.assertEquals(true, b);
    }

}
