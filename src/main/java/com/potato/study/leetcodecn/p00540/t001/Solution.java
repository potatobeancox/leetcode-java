package com.potato.study.leetcodecn.p00540.t001;


import org.junit.Assert;

/**
 * 540. 有序数组中的单一元素
 *
 * 给定一个只包含整数的有序数组，每个元素都会出现两次，唯有一个数只会出现一次，找出这个数。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: nums = [1,1,2,3,3,4,4,8,8]
 * 输出: 2
 * 示例 2:
 *
 * 输入: nums =  [3,3,7,7,10,11,11]
 * 输出: 10
 *  
 *
 * 提示:
 *
 * 1 <= nums.length <= 105
 * 0 <= nums[i] <= 105
 *  
 *
 * 进阶: 采用的方案可以在 O(log n) 时间复杂度和 O(1) 空间复杂度中运行吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/single-element-in-a-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 540
    public int singleNonDuplicate(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            // mid 分奇数偶数进行判定 奇数 的话 判定 mid和mid-1状态，偶数的话 判定mid mid+1状态
            if (mid % 2 == 0) {
                // 如果此时mid + 1不存在 那么直接返回mid
                if (mid + 1 > right) {
                    return nums[mid];
                }
                // mid + 1 和mid 相等 说明 往后找
                if (nums[mid+1] == nums[mid]) {
                    left = mid + 2;
                } else {
                    right = mid;
                }
            } else {
                // 如果此时mid01不存在 直接返回mid
                if (mid - 1 < left) {
                    return nums[mid];
                }
                // mid - 1 与mid相等 说明往后找
                if (nums[mid-1] == nums[mid]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
        }
        return nums[right];
    }



}
