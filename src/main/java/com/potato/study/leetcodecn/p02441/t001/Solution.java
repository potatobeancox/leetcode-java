package com.potato.study.leetcodecn.p02441.t001;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 2441. 与对应负数同时存在的最大正整数
 *
 * 给你一个 不包含 任何零的整数数组 nums ，找出自身与对应的负数都在数组中存在的最大正整数 k 。
 *
 * 返回正整数 k ，如果不存在这样的整数，返回 -1 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [-1,2,-3,3]
 * 输出：3
 * 解释：3 是数组中唯一一个满足题目要求的 k 。
 * 示例 2：
 *
 * 输入：nums = [-1,10,6,7,-7,1]
 * 输出：7
 * 解释：数组中存在 1 和 7 对应的负数，7 的值更大。
 * 示例 3：
 *
 * 输入：nums = [-10,8,6,7,-2,-3]
 * 输出：-1
 * 解释：不存在满足题目要求的 k ，返回 -1 。
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 1000
 * -1000 <= nums[i] <= 1000
 * nums[i] != 0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/largest-positive-integer-that-exists-with-its-negative
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int findMaxK(int[] nums) {
        Arrays.sort(nums);
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (num > 0) {
                break;
            }
            set.add(num);
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] < 0) {
                return -1;
            }
            if (set.contains(nums[i] * -1)) {
                return nums[i];
            }
        }
        return -1;
    }


}
