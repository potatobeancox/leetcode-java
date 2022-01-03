package com.potato.study.leetcodecn.p00795.t001;

import org.junit.Assert;

/**
 * 795. 区间子数组个数
 *
 * 给你一个整数数组 nums 和两个整数：left 及 right 。找出 nums 中连续、非空且其中最大元素在范围 [left, right] 内的子数组，并返回满足条件的子数组的个数。

 生成的测试用例保证结果符合 32-bit 整数范围。

  

 示例 1：

 输入：nums = [2,1,4,3], left = 2, right = 3
 输出：3
 解释：满足条件的三个子数组：[2], [2, 1], [3]
 示例 2：

 输入：nums = [2,9,2,5,6], left = 2, right = 8
 输出：7
  

 提示：

 1 <= nums.length <= 105
 0 <= nums[i] <= 109
 0 <= left <= right <= 109

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/number-of-subarrays-with-bounded-maximum
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        return getBoundcount(nums, right) - getBoundcount(nums, left - 1);
    }


    /**
     * 获取最大值不超过max 的区间个数
     * @param nums
     * @param max
     * @return
     */
    private int getBoundcount(int[] nums, int max) {
        // 从前往后遍历 找到每个区间大小 计算总的个数
        int boundCount = 0;
        int total = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= max) {
                boundCount++;
            } else {
                // 计数
                boundCount = 0;
            }
            total += boundCount;
        }
        return total;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{2,1,4,3};
        int left = 2;
        int right = 3;
        int i = solution.numSubarrayBoundedMax(nums, left, right);
        System.out.println(i);
        Assert.assertEquals(3, i);


        nums = new int[]{2,9,2,5,6};
        left = 2;
        right = 8;
        i = solution.numSubarrayBoundedMax(nums, left, right);
        System.out.println(i);
        Assert.assertEquals(7, i);
    }
}
