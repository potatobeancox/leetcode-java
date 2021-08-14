package com.potato.study.leetcodecn.other.swordoffer.p0053.p1.t001;

import org.junit.Assert;

/**
 * 剑指 Offer 53 - I. 在排序数组中查找数字 I
 *
 * 统计一个数字在排序数组中出现的次数。

  

 示例 1:

 输入: nums = [5,7,7,8,8,10], target = 8
 输出: 2
 示例 2:

 输入: nums = [5,7,7,8,8,10], target = 6
 输出: 0
  

 限制：

 0 <= 数组长度 <= 50000

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 查找第一个 等于这个数字 在数组中出现的位置   index1
     * 查找最后一个 等于这个数组 在数据中 出现为位置 index2
     * 做差 + 1
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        // 使用 -1 代表没有出现过
        int firstIndex = -1;

        while (left <= right) {
            // 使用右边移动 代替 除法
            int mid = left + ((right - left) >> 1);
            if (nums[mid] < target) {
                // mid 小 说明 tar 在mid 右边
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                // 等于 还需要继续判断 如果前一个数字 不是tar 说明 找到了第一个数字
                if (mid == 0 || nums[mid - 1] != target) {
                    firstIndex = mid;
                    break;
                } else {
                    // 找到了相等的位置 但不是第一个 还需要往前找
                    right = mid - 1;
                }
            }
        }

        if (-1 == firstIndex) {
            return 0;
        }
        // 找 最后一个节点
        left = 0;
        right = nums.length - 1;
        int lastIndex = -1;

        while (left <= right) {
            // 使用右边移动 代替 除法
            int mid = left + ((right - left) >> 1);
            if (nums[mid] < target) {
                // mid 小 说明 tar 在mid 右边
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                // 等于 还需要继续判断 如果前一个数字 不是tar 说明 找到了第一个数字
                if (mid == nums.length - 1 || nums[mid + 1] != target) {
                    lastIndex = mid;
                    break;
                } else {
                    // 找到了相等的位置 但不是第一个 还需要往前找
                    left = mid + 1;
                }
            }
        }
        if (-1 == lastIndex) {
            return 0;
        }
        return lastIndex - firstIndex + 1;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] nums = new int[]{5,7,7,8,8,10};
        int target = 8;
        int num = solution.search(nums, target);
        System.out.println(num);
        Assert.assertEquals(2, num);

        nums = new int[]{5,7,7,8,8,10};
        target = 6;
        num = solution.search(nums, target);
        System.out.println(num);
        Assert.assertEquals(0, num);
    }

}
