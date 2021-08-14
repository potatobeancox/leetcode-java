package com.potato.study.leetcodecn.other.swordoffer.p0053.p2.t001;

import org.junit.Assert;

/**
 * 剑指 Offer 53 - II. 0～n-1中缺失的数字
 *
 * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。

  

 示例 1:

 输入: [0,1,3]
 输出: 2
 示例 2:

 输入: [0,1,2,3,4,5,6,7,9]
 输出: 8
  

 限制：

 1 <= 数组长度 <= 10000

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/que-shi-de-shu-zi-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 二分查找
     * left 0 right len-1 mid = left + right / 2
     *
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == mid) {
                // 往右边找
                left = mid + 1;
            } else {
                if (mid > 0 && nums[mid-1] == mid - 1) {
                    return mid;
                }
                right = mid - 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {
                0,1,3
        };
        int i = solution.missingNumber(nums);
        System.out.println(i);
        Assert.assertEquals(2, i);

        nums = new int[] {
                0,1,2,3,4,5,6,7,9
        };
        i = solution.missingNumber(nums);
        System.out.println(i);
        Assert.assertEquals(8, i);
    }

}
