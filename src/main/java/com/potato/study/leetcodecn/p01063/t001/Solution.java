package com.potato.study.leetcodecn.p01063.t001;

/**
 * 1063. 有效子数组的数目
 *
 * 给定一个整数数组 nums ，返回满足下面条件的 非空、连续 子数组的数目：
 *
 * 子数组 是数组的 连续 部分。
 * 子数组最左边的元素不大于子数组中的其他元素 。
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,4,2,5,3]
 * 输出：11
 * 解释：有 11 个有效子数组，分别是：[1],[4],[2],[5],[3],[1,4],[2,5],[1,4,2],[2,5,3],[1,4,2,5],[1,4,2,5,3] 。
 * 示例 2：
 *
 * 输入：nums = [3,2,1]
 * 输出：3
 * 解释：有 3 个有效子数组，分别是：[3],[2],[1] 。
 * 示例 3：
 *
 * 输入：nums = [2,2,2]
 * 输出：6
 * 解释：有 6 个有效子数组，分别为是：[2],[2],[2],[2,2],[2,2],[2,2,2] 。
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 5 * 104
 * 0 <= nums[i] <= 105
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/number-of-valid-subarrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int validSubarrays(int[] nums) {
        // 枚举每个开始 位置 看最长能有多少个数组
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int len = 0;
            for (int j = i; j < nums.length ; j++) {
                if (nums[i] <= nums[j]) {
                    len++;
                } else {
                    break;
                }
            }
            count += len;
        }
        return count;
    }
}
