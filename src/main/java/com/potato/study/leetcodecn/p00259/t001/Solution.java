package com.potato.study.leetcodecn.p00259.t001;

import org.junit.Assert;

import java.util.Arrays;

/**
 * 259. 较小的三数之和
 *
 * 给定一个长度为 n 的整数数组和一个目标值 target ，寻找能够使条件 nums[i] + nums[j] + nums[k] < target 成立的三元组  i, j, k 个数（0 <= i < j < k < n）。

  

 示例 1：

 输入: nums = [-2,0,1,3], target = 2
 输出: 2
 解释: 因为一共有两个三元组满足累加和小于 2:
      [-2,0,1]
 [-2,0,3]
 示例 2：

 输入: nums = [], target = 0
 输出: 0
 示例 3：

 输入: nums = [0], target = 0
 输出: 0
  

 提示:

 n == nums.length
 0 <= n <= 3500
 -100 <= nums[i] <= 100
 -100 <= target <= 100

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/3sum-smaller
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        // 先固定i
        int count = 0;
        for (int i = 0; i < nums.length-2; i++) {
            // left 和 right
            int k = nums.length - 1;
            // j 固定找 k
            for (int j = i + 1; j < nums.length-1; j++) {
                while (k > j && nums[j] + nums[k] + nums[i] >= target) {
                    k--;
                }
                if (k <= j) {
                    continue;
                }
                // 计算 有多少种k
                count += (k - j);
            }
        }
        return count;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{-2,0,1,3};
        int target = 2;
        int i = solution.threeSumSmaller(nums, target);
        System.out.println(i);
        Assert.assertEquals(2, i);



//        [-1,1,-1,-1]
//        -1
        nums = new int[]{-1,1,-1,-1};
        target = -1;
        i = solution.threeSumSmaller(nums, target);
        System.out.println(i);
        Assert.assertEquals(1, i);
    }


}
