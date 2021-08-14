package com.potato.study.leetcodecn.other.swordoffer.p0042.p1.t001;

import org.junit.Assert;

/**
 * 剑指 Offer 42. 连续子数组的最大和
 *
 * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。

 要求时间复杂度为O(n)。

  

 示例1:

 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
 输出: 6
 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
  

 提示：

 1 <= arr.length <= 10^5
 -100 <= arr[i] <= 100
 注意：本题与主站 53 题相同：https://leetcode-cn.com/problems/maximum-subarray/

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 正常需要先n*n 遍历 开始和结束位置 然后求加和 也就是3次方
     *
     * 先求加和 然后进行遍历相减 得到最大值
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }
        int index = 0;
        int max = Integer.MIN_VALUE;
        while (index < nums.length && nums[index] <= 0) {
            max = Math.max(max, nums[index]);
            index++;
        }
        int sum = 0;
        for (int i = index; i < nums.length; i++) {
            sum += nums[i];
            if (sum >= 0) {
                max = Math.max(max, sum);
            } else {
                // sum < 0
                sum = 0;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        int i = solution.maxSubArray(nums);
        System.out.println(i);
        Assert.assertEquals(6, i);

//        nums = {-2,1,-3,4,-1,2,1,-5,4};
//        int i = solution.maxSubArray(nums);
//        System.out.println(i);
//        Assert.assertEquals(6, i);
    }

}
