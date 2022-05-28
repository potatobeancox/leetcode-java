package com.potato.study.leetcodecn.p01546.t001;


import java.util.HashSet;
import java.util.Set;

/**
 * 1546. 和为目标值且不重叠的非空子数组的最大数目
 *
 * 给你一个数组 nums 和一个整数 target 。
 *
 * 请你返回 非空不重叠 子数组的最大数目，且每个子数组中数字和都为 target 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,1,1,1], target = 2
 * 输出：2
 * 解释：总共有 2 个不重叠子数组（加粗数字表示） [1,1,1,1,1] ，它们的和为目标值 2 。
 * 示例 2：
 *
 * 输入：nums = [-1,3,5,1,4,2,-9], target = 6
 * 输出：2
 * 解释：总共有 3 个子数组和为 6 。
 * ([5,1], [4,2], [3,5,1,4,2,-9]) 但只有前 2 个是不重叠的。
 * 示例 3：
 *
 * 输入：nums = [-2,6,6,3,5,4,1,2,8], target = 10
 * 输出：3
 * 示例 4：
 *
 * 输入：nums = [0,0,0], target = 0
 * 输出：3
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 0 <= target <= 10^6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-number-of-non-overlapping-subarrays-with-sum-equals-target
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode.cn/problems/maximum-number-of-non-overlapping-subarrays-with-sum-equals-target/solution/he-wei-mu-biao-zhi-de-zui-da-shu-mu-bu-zhong-die-f/
     * @param nums
     * @param target
     * @return
     */
    public int maxNonOverlapping(int[] nums, int target) {
        int index = 0;
        int maxCount = 0;
        while (index < nums.length) {
            // 从前往后 每次新创建一个 set 记录还没有用到的前缀，
            Set<Integer> appearSet = new HashSet<>();
            int sum = 0;
            appearSet.add(sum);
            while (index < nums.length) {
                // 遍历过程中，计算sum，如果 sum - target 之前set里边有，说明已经找到一个不重叠，贪心思想 找到第一个满足 找到之后
                sum += nums[index];
                if (appearSet.contains(sum - target)) {
                    maxCount++;
                    index++;
                    break;
                } else {
                    appearSet.add(sum);
                    index++;
                }
            }
        }
        return maxCount;
    }
}

