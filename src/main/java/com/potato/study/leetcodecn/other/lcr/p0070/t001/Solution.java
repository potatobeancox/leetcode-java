package com.potato.study.leetcodecn.other.lcr.p0070.t001;

/**
 * 剑指 Offer II 070. 排序数组中只出现一次的数字
 *
 * 给定一个只包含整数的有序数组 nums ，每个元素都会出现两次，唯有一个数只会出现一次，请找出这个唯一的数字。

  

 示例 1:

 输入: nums = [1,1,2,3,3,4,4,8,8]
 输出: 2
 示例 2:

 输入: nums =  [3,3,7,7,10,11,11]
 输出: 10
  

  

 提示:

 1 <= nums.length <= 105
 0 <= nums[i] <= 105
  

 进阶: 采用的方案可以在 O(log n) 时间复杂度和 O(1) 空间复杂度中运行吗？



 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/skFtm2
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int singleNonDuplicate(int[] nums) {
        int bit = nums[0];
        for (int i = 1; i < nums.length; i++) {
            bit ^= nums[i];
        }
        return bit;
    }
}
