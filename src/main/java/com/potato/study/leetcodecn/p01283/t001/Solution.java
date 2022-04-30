package com.potato.study.leetcodecn.p01283.t001;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import org.junit.Assert;

/**
 * 1283. 使结果不超过阈值的最小除数
 *
 * 给你一个整数数组 nums 和一个正整数 threshold  ，你需要选择一个正整数作为除数，然后将数组里每个数都除以它，并对除法结果求和。
 *
 * 请你找出能够使上述结果小于等于阈值 threshold 的除数中 最小 的那个。
 *
 * 每个数除以除数后都向上取整，比方说 7/3 = 3 ， 10/2 = 5 。
 *
 * 题目保证一定有解。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,5,9], threshold = 6
 * 输出：5
 * 解释：如果除数为 1 ，我们可以得到和为 17 （1+2+5+9）。
 * 如果除数为 4 ，我们可以得到和为 7 (1+1+2+3) 。如果除数为 5 ，和为 5 (1+1+1+2)。
 * 示例 2：
 *
 * 输入：nums = [2,3,5,7,11], threshold = 11
 * 输出：3
 * 示例 3：
 *
 * 输入：nums = [19], threshold = 5
 * 输出：4
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 5 * 10^4
 * 1 <= nums[i] <= 10^6
 * nums.length <= threshold <= 10^6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-the-smallest-divisor-given-a-threshold
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int smallestDivisor(int[] nums, int threshold) {
        // 二分法 先求  1 - 10^6 作为
        int left = 1;
        int right = 1_000_000;
        int res = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            boolean result = canDivisor(nums, threshold, mid);
            if (result) {
                // 目前这个能整除 还能小么
                res = mid;
                right = mid - 1;
            } else {
                // 除法之后 数字太大了
                left = mid + 1;
            }
        }
        return res;


    }


    private boolean canDivisor(int[] nums, int threshold, int divisor) {
        int total = 0;
        for (int num : nums) {
            total += ((num - 1) / divisor + 1);
        }
        return total <= threshold;
    }


}
