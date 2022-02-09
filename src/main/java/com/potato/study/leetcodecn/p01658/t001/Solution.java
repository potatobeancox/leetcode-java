package com.potato.study.leetcodecn.p01658.t001;

import org.junit.Assert;

/**
 * 1658. 将 x 减到 0 的最小操作数
 *
 * 给你一个整数数组 nums 和一个整数 x 。每一次操作时，你应当移除数组 nums 最左边或最右边的元素，然后从 x 中减去该元素的值。请注意，需要 修改 数组以供接下来的操作使用。

 如果可以将 x 恰好 减到 0 ，返回 最小操作数 ；否则，返回 -1 。

  

 示例 1：

 输入：nums = [1,1,4,2,3], x = 5
 输出：2
 解释：最佳解决方案是移除后两个元素，将 x 减到 0 。
 示例 2：

 输入：nums = [5,6,7,8,9], x = 4
 输出：-1
 示例 3：

 输入：nums = [3,2,20,1,1,3], x = 10
 输出：5
 解释：最佳解决方案是移除后三个元素和前两个元素（总共 5 次操作），将 x 减到 0 。
  

 提示：

 1 <= nums.length <= 105
 1 <= nums[i] <= 104
 1 <= x <= 109

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/minimum-operations-to-reduce-x-to-zero
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int minOperations(int[] nums, int x) {
        // 统计 nums sum
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int target = sum - x;
        if (target < 0) {
            return -1;
        }
        if (target == 0) {
            return nums.length;
        }
        // 滑动窗口 判断 如果遇到窗口内和 大于 sum - k 循环移动左边，否则移动右边
        int left = 0;
        int window = 0;
        int minLen = nums.length;
        for (int right = 0; right < nums.length; right++) {
            window += nums[right];
            while (window > target && left <= right) {
                window -= nums[left];
                left++;
            }
            if (window == target) {
                minLen = Math.min(minLen, nums.length - (right - left + 1));
            }
        }
        if (minLen == nums.length) {
            return -1;
        }
        return minLen;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[] {
                1,1,4,2,3
        };
        int x = 5;
        int i = solution.minOperations(arr, x);
        System.out.println(i);
        Assert.assertEquals(2, i);




        arr = new int[] {
                5,2,3,1,1
        };
        x = 5;
        i = solution.minOperations(arr, x);
        System.out.println(i);
        Assert.assertEquals(1, i);


        arr = new int[] {
                8828,9581,49,9818,9974,9869,9991,10000,10000,10000,9999,9993,9904,8819,1231,6309
        };
        x = 134365;
        i = solution.minOperations(arr, x);
        System.out.println(i);
        Assert.assertEquals(16, i);
    }
}
