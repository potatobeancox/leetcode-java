package com.potato.study.leetcodecn.p01664.t001;

import java.util.Arrays;

/**
 * 1664. 生成平衡数组的方案数
 *
 * 你一个整数数组 nums 。你需要选择 恰好 一个下标（下标从 0 开始）并删除对应的元素。请注意剩下元素的下标可能会因为删除操作而发生改变。
 *
 * 比方说，如果 nums = [6,1,7,4,1] ，那么：
 *
 * 选择删除下标 1 ，剩下的数组为 nums = [6,7,4,1] 。
 * 选择删除下标 2 ，剩下的数组为 nums = [6,1,4,1] 。
 * 选择删除下标 4 ，剩下的数组为 nums = [6,1,7,4] 。
 * 如果一个数组满足奇数下标元素的和与偶数下标元素的和相等，该数组就是一个 平衡数组 。
 *
 * 请你返回删除操作后，剩下的数组 nums 是 平衡数组 的 方案数 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [2,1,6,4]
 * 输出：1
 * 解释：
 * 删除下标 0 ：[1,6,4] -> 偶数元素下标为：1 + 4 = 5 。奇数元素下标为：6 。不平衡。
 * 删除下标 1 ：[2,6,4] -> 偶数元素下标为：2 + 4 = 6 。奇数元素下标为：6 。平衡。
 * 删除下标 2 ：[2,1,4] -> 偶数元素下标为：2 + 4 = 6 。奇数元素下标为：1 。不平衡。
 * 删除下标 3 ：[2,1,6] -> 偶数元素下标为：2 + 6 = 8 。奇数元素下标为：1 。不平衡。
 * 只有一种让剩余数组成为平衡数组的方案。
 * 示例 2：
 *
 * 输入：nums = [1,1,1]
 * 输出：3
 * 解释：你可以删除任意元素，剩余数组都是平衡数组。
 * 示例 3：
 *
 * 输入：nums = [1,2,3]
 * 输出：0
 * 解释：不管删除哪个元素，剩下数组都不是平衡数组。
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ways-to-make-a-fair-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 1664
    public int waysToMakeFair(int[] nums) {
        // 从某个位置开始 往前 的间隔1的和 和从每个位置开始 往后到最后的 和
        int[] from = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (i - 2 < 0) {
                from[i] = nums[i];
            } else {
                from[i] = nums[i] + from[i-2];
            }
        }
        int[] to = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + 2 >= nums.length) {
                to[i] = nums[i];
            } else {
                to[i] = nums[i] + to[i+2];
            }
        }
        int wayCount = 0;
        // 遍历 nums 判定 深处i 是够相同
        for (int i = 0; i < nums.length; i++) {
            // i位置之前
            int target1 = from[i] - nums[i];
            if (i + 1 < nums.length) {
                target1 += to[i + 1];
            }
            // i-1 位置
            int target2 = 0;
            if (i >= 1) {
                target2 += from[i - 1];
            }
            if (i + 2 < nums.length) {
                target2 += to[i + 2];
            }
            if (target1 == target2) {
                wayCount++;
            }
        }
        return wayCount;
    }
}
