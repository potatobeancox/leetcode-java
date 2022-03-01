package com.potato.study.leetcodecn.other.swordoffer.p0020.p1.t001;

/**
 * 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
 *
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。

  

 示例：

 输入：nums = [1,2,3,4]
 输出：[1,3,2,4]
 注：[3,1,2,4] 也是正确的答案之一。
  

 提示：

 0 <= nums.length <= 50000
 1 <= nums[i] <= 10000

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
     * @param nums
     * @return
     */
    public int[] exchange(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            // left 从左往右找偶数break 直到找到right
            while (left <= right && nums[left] % 2 == 1) {
                left++;
            }
            // right 从右往左找奇数 break 直到找到left
            while (left <= right && nums[right] % 2 == 0) {
                right--;
            }
            // 如果还合法 就交换
            if (left < right) {
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;

                left++;
                right--;
            }
        }
        return nums;
    }
}
