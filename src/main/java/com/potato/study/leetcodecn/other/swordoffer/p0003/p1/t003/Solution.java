package com.potato.study.leetcodecn.other.swordoffer.p0003.p1.t003;

import org.junit.Assert;

/**
 * 剑指 Offer 03. 数组中重复的数字
 *
 * 找出数组中重复的数字。


 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。

 示例 1：

 输入：
 [2, 3, 1, 0, 2, 5, 3]
 输出：2 或 3
  

 限制：

 2 <= n <= 100000

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     *
     * 二分法计数 Olgn
     * 每次计算 0 - mid 个数， 如果大于个数 说明 在这个区间里存在多余的数字
     * @param nums
     * @return
     */
    public int findRepeatNumber(int[] nums) {
        // 记录当前比较的位置
        int index = 0;
        while (index < nums.length) {
            if (nums[index] == index) {
                index++;
            } else {
                // 交换 的那个位置已经有了合适的元素
                if (nums[nums[index]] == nums[index]) {
                    return nums[index];
                }
                int tmp = nums[index];
                int swapIndex = nums[index];
                nums[index] = nums[swapIndex];
                nums[swapIndex] = tmp;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] nums = new int[]{2, 3, 1, 0, 2, 5, 3};
        int num = solution.findRepeatNumber(nums);
        System.out.println(num);
        Assert.assertTrue( num == 2 || num == 3);


    }

}
