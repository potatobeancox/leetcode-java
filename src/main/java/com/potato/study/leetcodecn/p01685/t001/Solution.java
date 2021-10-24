package com.potato.study.leetcodecn.p01685.t001;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 1685. 有序数组中差绝对值之和
 *
 * 给你一个 非递减 有序整数数组 nums 。
 *
 * 请你建立并返回一个整数数组 result，它跟 nums 长度相同，且result[i] 等于 nums[i] 与数组中所有其他元素差的绝对值之和。
 *
 * 换句话说， result[i] 等于 sum(|nums[i]-nums[j]|) ，其中 0 <= j < nums.length 且 j != i （下标从 0 开始）。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [2,3,5]
 * 输出：[4,3,5]
 * 解释：假设数组下标从 0 开始，那么
 * result[0] = |2-2| + |2-3| + |2-5| = 0 + 1 + 3 = 4，
 * result[1] = |3-2| + |3-3| + |3-5| = 1 + 0 + 2 = 3，
 * result[2] = |5-2| + |5-3| + |5-5| = 3 + 2 + 0 = 5。
 * 示例 2：
 *
 * 输入：nums = [1,4,6,8,10]
 * 输出：[24,15,13,15,21]
 *  
 *
 * 提示：
 *
 * 2 <= nums.length <= 105
 * 1 <= nums[i] <= nums[i + 1] <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-of-absolute-differences-in-a-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     *
     * @param nums
     * @return
     */
    public int[] getSumAbsoluteDifferences(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            left[i] = left[i-1] + nums[i];
        }
        right[n-1] = nums[n-1];
        for (int i = n-2; i >= 0; i--) {
            right[i] = right[i+1] + nums[i];
        }
        // get result
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                result[i] = right[i+1] - nums[i] * (n-i-1);
            } else if (i == n-1) {
                result[i] = nums[i] * i - left[i-1];
            } else {
                result[i] = nums[i] * i - left[i-1] + right[i+1] - nums[i] * (n-i-1);
            }
        }
        return result;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[] {
                2,3,5
        };
        // [4,3,5]
        int[] sumAbsoluteDifferences = solution.getSumAbsoluteDifferences(arr);
        System.out.println(Arrays.toString(sumAbsoluteDifferences));
    }

}
