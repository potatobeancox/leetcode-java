package com.potato.study.leetcodecn.other.Interview.p0016.p0017;


import org.junit.Assert;

/**
 * 面试题 16.17. 连续数列
 *
 * 给定一个整数数组，找出总和最大的连续数列，并返回总和。

 示例：

 输入： [-2,1,-3,4,-1,2,1,-5,4]
 输出： 6
 解释： 连续子数组 [4,-1,2,1] 的和最大，为 6。
 进阶：

 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。

 通过次数16,542提交次数27,847

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/contiguous-sequence-lcci
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * maxValue i 代表 以i结尾的max value
     * maxValue 0 = nums 0
     * maxValue i = max {nums[i], nums[i] + maxValue[i-1]}
     * 生成过程中 记录最大值 用于返回
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int result = 0;
        if (null == nums || nums.length == 0) {
            return result;
        }
        int[] maxVal = new int[nums.length];
        maxVal[0] = nums[0];
        result = nums[0];

        for (int i = 1; i < nums.length; i++) {
            maxVal[i] = Math.max(nums[i], nums[i] + maxVal[i-1]);
            result = Math.max(result, maxVal[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        int res = solution.maxSubArray(nums);
        System.out.println(res);
        Assert.assertEquals(6, res);

        nums = new int[]{1};
        res = solution.maxSubArray(nums);
        System.out.println(res);
        Assert.assertEquals(1, res);
    }
}
