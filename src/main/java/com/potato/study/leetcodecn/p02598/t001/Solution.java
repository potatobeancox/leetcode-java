package com.potato.study.leetcodecn.p02598.t001;

import org.junit.Assert;

/**
 * 2598. 执行操作后的最大 MEX
 * 给你一个下标从 0 开始的整数数组 nums 和一个整数 value 。
 *
 * 在一步操作中，你可以对 nums 中的任一元素加上或减去 value 。
 *
 * 例如，如果 nums = [1,2,3] 且 value = 2 ，你可以选择 nums[0] 减去 value ，得到 nums = [-1,2,3] 。
 * 数组的 MEX (minimum excluded) 是指其中数组中缺失的最小非负整数。
 *
 * 例如，[-1,2,3] 的 MEX 是 0 ，而 [1,0,3] 的 MEX 是 2 。
 * 返回在执行上述操作 任意次 后，nums 的最大 MEX 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,-10,7,13,6,8], value = 5
 * 输出：4
 * 解释：执行下述操作可以得到这一结果：
 * - nums[1] 加上 value 两次，nums = [1,0,7,13,6,8]
 * - nums[2] 减去 value 一次，nums = [1,0,2,13,6,8]
 * - nums[3] 减去 value 两次，nums = [1,0,2,3,6,8]
 * nums 的 MEX 是 4 。可以证明 4 是可以取到的最大 MEX 。
 * 示例 2：
 *
 * 输入：nums = [1,-10,7,13,6,8], value = 7
 * 输出：2
 * 解释：执行下述操作可以得到这一结果：
 * - nums[2] 减去 value 一次，nums = [1,-10,0,13,6,8]
 * nums 的 MEX 是 2 。可以证明 2 是可以取到的最大 MEX 。
 *  
 *
 * 提示：
 *
 * 1 <= nums.length, value <= 105
 * -109 <= nums[i] <= 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/smallest-missing-non-negative-integer-after-operations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 2598 遍历 nums 计算%之后的余数 看看 能有多少
    public int findSmallestInteger(int[] nums, int value) {
        if (value == 1) {
            // 1个给0
            return nums.length;
        }
        int[] modCounts = new int[value];
        for (int num : nums) {
            if (num < 0) {
                int k = num * -1 / value;
                num += (k+1) * value;
            }
            int res = num % value;
            modCounts[res]++;
        }
        // 找到count的最小值
        int minIndex = 0;
        for (int i = 0; i < value; i++) {
            if (modCounts[i] < modCounts[minIndex]) {
                minIndex = i;
            }
        }
        if (modCounts[minIndex] == 0) {
            return minIndex;
        }
        int times = modCounts[minIndex];

        int res = value * times + minIndex;
        if (res > nums.length) {
            return nums.length;
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {3,0,3,2,4,2,1,1,0,4};
        int value = 5;
        int smallestInteger = solution.findSmallestInteger(nums, value);
        System.out.println(smallestInteger);
        Assert.assertEquals(10, smallestInteger);
    }

}
