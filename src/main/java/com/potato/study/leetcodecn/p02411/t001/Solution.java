package com.potato.study.leetcodecn.p02411.t001;

import java.util.Arrays;

/**
 * 2411. 按位或最大的最小子数组长度
 *
 * 给你一个长度为 n 下标从 0 开始的数组 nums ，数组中所有数字均为非负整数。对于 0 到 n - 1 之间的每一个下标 i ，你需要找出 nums 中一个 最小 非空子数组，它的起始位置为 i （包含这个位置），同时有 最大 的 按位或运算值 。
 *
 * 换言之，令 Bij 表示子数组 nums[i...j] 的按位或运算的结果，你需要找到一个起始位置为 i 的最小子数组，这个子数组的按位或运算的结果等于 max(Bik) ，其中 i <= k <= n - 1 。
 * 一个数组的按位或运算值是这个数组里所有数字按位或运算的结果。
 *
 * 请你返回一个大小为 n 的整数数组 answer，其中 answer[i]是开始位置为 i ，按位或运算结果最大，且 最短 子数组的长度。
 *
 * 子数组 是数组里一段连续非空元素组成的序列。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,0,2,1,3]
 * 输出：[3,3,2,2,1]
 * 解释：
 * 任何位置开始，最大按位或运算的结果都是 3 。
 * - 下标 0 处，能得到结果 3 的最短子数组是 [1,0,2] 。
 * - 下标 1 处，能得到结果 3 的最短子数组是 [0,2,1] 。
 * - 下标 2 处，能得到结果 3 的最短子数组是 [2,1] 。
 * - 下标 3 处，能得到结果 3 的最短子数组是 [1,3] 。
 * - 下标 4 处，能得到结果 3 的最短子数组是 [3] 。
 * 所以我们返回 [3,3,2,2,1] 。
 * 示例 2：
 *
 * 输入：nums = [1,2]
 * 输出：[2,1]
 * 解释：
 * 下标 0 处，能得到最大按位或运算值的最短子数组长度为 2 。
 * 下标 1 处，能得到最大按位或运算值的最短子数组长度为 1 。
 * 所以我们返回 [2,1] 。
 *  
 *
 * 提示：
 *
 * n == nums.length
 * 1 <= n <= 105
 * 0 <= nums[i] <= 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/smallest-subarrays-with-maximum-bitwise-or
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode.cn/problems/smallest-subarrays-with-maximum-bitwise-or/solution/by-endlesscheng-zai1/
     * @param nums
     * @return
     */
    public int[] smallestSubarrays(int[] nums) {
        // 遍历nums 每个位置 i
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            // 从这个位置开始往前遍历，看看 |上i 之后是不是变大了，是的话更改 新的j的num和 结果个数
            // 至少有一个数字
            res[i] = 1;
            for (int j = i-1; j >= 0; j--) {
                // 说明 num j 要跟i联合 一下得到的才是最大
                if ((nums[i] | nums[j]) != nums[j]) {
                    nums[j] |= nums[i];
                    res[j] = i - j + 1;
                } else {
                    // 当前j已经达到最大了可以不从j开始算了
                    break;
                }
            }
        }
        return res;
    }

}
