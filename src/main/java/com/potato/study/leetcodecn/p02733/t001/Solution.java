package com.potato.study.leetcodecn.p02733.t001;


/**
 *
 * 2733. 既不是最小值也不是最大值
 *
 * 给你一个整数数组 nums ，数组由 不同正整数 组成，请你找出并返回数组中 任一 既不是 最小值 也不是 最大值 的数字，如果不存在这样的数字，返回 -1 。
 *
 * 返回所选整数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [3,2,1,4]
 * 输出：2
 * 解释：在这个示例中，最小值是 1 ，最大值是 4 。因此，2 或 3 都是有效答案。
 * 示例 2：
 *
 * 输入：nums = [1,2]
 * 输出：-1
 * 解释：由于不存在既不是最大值也不是最小值的数字，我们无法选出满足题目给定条件的数字。因此，不存在答案，返回 -1 。
 * 示例 3：
 *
 * 输入：nums = [2,1,3]
 * 输出：2
 * 解释：2 既不是最小值，也不是最大值，这个示例只有这一个有效答案。
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 * nums 中的所有数字互不相同
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/neither-minimum-nor-maximum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int findNonMinOrMax(int[] nums) {
        // 遍历一遍记录最大值和最小值 如果当前值
        int minIndex = -1;
        int maxIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            if (minIndex == -1 || maxIndex == -1) {
                minIndex = i;
                maxIndex = i;
                continue;
            }
            if (nums[minIndex] == nums[i] || nums[maxIndex] == nums[i]) {
                continue;
            }
            // 如果当前值位于 min 和 max 之间那么直接返回
            if (nums[minIndex] < nums[i] && nums[i] < nums[maxIndex]) {
                return nums[i];
            }
            // 否则 如果当前值小于min 更新 min
            if (nums[i] < nums[minIndex]) {
                if (nums[minIndex] != nums[maxIndex]) {
                    return nums[minIndex];
                }
                minIndex = i;
            }
            // 如果当前值大于 max 更新max
            if (nums[i] > nums[maxIndex]) {
                if (nums[minIndex] != nums[maxIndex]) {
                    return nums[maxIndex];
                }
                maxIndex = i;
            }
        }
        return -1;
    }

}
