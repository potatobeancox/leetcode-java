package com.potato.study.leetcodecn.p00713.t001;

import org.junit.Assert;

/**
 * 713. 乘积小于K的子数组
 *
 * 给定一个正整数数组 nums和整数 k 。

 请找出该数组内乘积小于 k 的连续的子数组的个数。

  

 示例 1:

 输入: nums = [10,5,2,6], k = 100
 输出: 8
 解释: 8个乘积小于100的子数组分别为: [10], [5], [2], [6], [10,5], [5,2], [2,6], [5,2,6]。
 需要注意的是 [10,5,2] 并不是乘积小于100的子数组。
 示例 2:

 输入: nums = [1,2,3], k = 0
 输出: 0
  

 提示: 

 1 <= nums.length <= 3 * 104
 1 <= nums[i] <= 1000
 0 <= k <= 106

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/subarray-product-less-than-k
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        // 枚举每个 right 位置
        long tmp = 1;
        int totalNum = 0;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            // 对于每个 right位置 对left 进行移动直到 到了 小于 k的位置， 确定 区间有多少个元素 right - left + 1 就是几个子数组
            if (nums[right] >= k) {
                left = right + 1;
                tmp = 1;
                continue;
            }
            tmp *= nums[right];
            while (tmp >= k && left <= right) {
                tmp /= nums[left];
                left++;
            }
            if (tmp < k) {
                totalNum += (right - left + 1);
            }
        }
        return totalNum;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{
                57,44,92,28,66,60,37,33,52,38,29,76,8,75,22
        };
        int k = 18;
        int i = solution.numSubarrayProductLessThanK(nums, k);
        System.out.println(i);
        Assert.assertEquals(1, i);


        nums = new int[]{
                100,2,3,4,100,5,6,7,100
        };
        k = 100;
        i = solution.numSubarrayProductLessThanK(nums, k);
        System.out.println(i);
        Assert.assertEquals(11, i);
    }
}
