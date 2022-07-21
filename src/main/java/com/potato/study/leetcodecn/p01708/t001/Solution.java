package com.potato.study.leetcodecn.p01708.t001;

import java.util.Arrays;
import java.util.Stack;

import org.junit.Assert;

import com.potato.study.leetcode.util.LeetcodeInputUtils;

/**
 * 1708. 长度为 K 的最大子数组
 *
 * 在数组 A 和数组 B 中，对于第一个满足 A[i] != B[i] 的索引 i ，当 A[i] > B[i] 时，数组 A 大于数组 B。
 *
 * 例如，对于索引从 0 开始的数组：
 *
 * [1,3,2,4] > [1,2,2,4] ，因为在索引 1 上， 3 > 2。
 * [1,4,4,4] < [2,1,1,1] ，因为在索引 0 上， 1 < 2。
 * 一个数组的子数组是原数组上的一个连续子序列。
 *
 * 给定一个包含不同整数的整数类型数组 nums ，返回 nums 中长度为 k 的最大子数组。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: nums = [1,4,5,2,3], k = 3
 * 输出: [5,2,3]
 * 解释: 长度为 3 的子数组有： [1,4,5]、 [4,5,2] 和 [5,2,3]。
 * 在这些数组中， [5,2,3] 是最大的。
 * Example 2:
 *
 * 输入: nums = [1,4,5,2,3], k = 4
 * 输出: [4,5,2,3]
 * 解释: 长度为 4 的子数组有： [1,4,5,2] 和 [4,5,2,3]。
 * 在这些数组中， [4,5,2,3] 是最大的。
 * 示例 3:
 *
 * 输入: nums = [1,4,5,2,3], k = 1
 * 输出: [5]
 *  
 *
 * 提示：
 *
 * 1 <= k <= nums.length <= 105
 * 1 <= nums[i] <= 109
 * nums 中的所有整数都是不同的。
 *  
 *
 * 进阶：如果允许 nums 中存在相同元素，你该如何解决该问题？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/largest-subarray-length-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * 注意子数组和 子序列区别
     * @param nums
     * @param k
     * @return
     */
    public int[] largestSubarray(int[] nums, int k) {
        int maxIndex = 0;
        for (int i = 1; i < nums.length - k + 1; i++) {
            if (isBigger(nums, i, maxIndex, k)) {
                maxIndex = i;
            }
        }
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = nums[maxIndex++];
        }
        return result;
    }

    private boolean isBigger(int[] nums, int i, int maxIndex, int k) {
        for (int j = 0; j < k; j++) {
            if (nums[i + j] > nums[maxIndex + j]) {
                return true;
            } else if (nums[i + j] < nums[maxIndex + j]) {
                return false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "[1,4,5,2,3]";
        int[] nums = LeetcodeInputUtils.inputString2IntArray(input);
        int k = 3;
        int[] ints = solution.largestSubarray(nums, k);
        System.out.println(Arrays.toString(ints));
        Assert.assertArrayEquals(new int[] {
                5,2,3
        }, ints);

        input = "[1,4,5,2,3]";
        nums = LeetcodeInputUtils.inputString2IntArray(input);
        k = 1;
        ints = solution.largestSubarray(nums, k);
        System.out.println(Arrays.toString(ints));
        Assert.assertArrayEquals(new int[] {
                5
        }, ints);


        input = "[5,31,62,21,40,23,93,69,7,81,20,11,77,52,89]";
        nums = LeetcodeInputUtils.inputString2IntArray(input);
        k = 5;
        ints = solution.largestSubarray(nums, k);
        System.out.println(Arrays.toString(ints));
        Assert.assertArrayEquals(new int[] {
                93,69,7,81,20
        }, ints);
    }


}
