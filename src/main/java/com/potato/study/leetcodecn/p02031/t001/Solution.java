package com.potato.study.leetcodecn.p02031.t001;

import java.util.*;

/**
 * 2031. 1 比 0 多的子数组个数
 *
 * 给你一个只包含 0 和 1 的数组 nums，请返回 1 的数量 大于 0 的数量的子数组的个数。由于答案可能很大，请返回答案对 109 + 7 取余 的结果。
 *
 * 一个 子数组 指的是原数组中连续的一个子序列。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: nums = [0,1,1,0,1]
 * 输出: 9
 * 解释:
 * 长度为 1 的、1 的数量大于 0 的数量的子数组有: [1], [1], [1]
 * 长度为 2 的、1 的数量大于 0 的数量的子数组有: [1,1]
 * 长度为 3 的、1 的数量大于 0 的数量的子数组有: [0,1,1], [1,1,0], [1,0,1]
 * 长度为 4 的、1 的数量大于 0 的数量的子数组有: [1,1,0,1]
 * 长度为 5 的、1 的数量大于 0 的数量的子数组有: [0,1,1,0,1]
 * 示例 2:
 *
 * 输入: nums = [0]
 * 输出: 0
 * 解释:
 * 没有子数组的 1 的数量大于 0 的数量。
 * 示例 3:
 *
 * 输入: nums = [1]
 * 输出: 1
 * 解释:
 * 长度为 1 的、1 的数量大于 0 的数量的子数组有: [1]
 *  
 *
 * 提示:
 *
 * 1 <= nums.length <= 105
 * 0 <= nums[i] <= 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/count-subarrays-with-more-ones-than-zeros
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int subarraysWithMoreZerosThanOnes(int[] nums) {
        // 将nums 中 0 看成是 -1
        Map<Integer, Integer> statusCountMap = new HashMap<>();
        // 空数组有一种
        statusCountMap.put(0, 1);
        // 遍历 nums 使用 map 计数 维护一个 状态值，并维护一个比当前小的值 每次状态值 如果当前的-1 要减去之前的值 否则要加上值
        int status = 0;
        int totalCount = 0;
        int belowCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                // 更新小于 status的值
                belowCount += statusCountMap.getOrDefault(status, 0);
                status++;
            } else {
                // 假设当前是 2 需要-- below记录的是1数量，需要吧1的减去
                status--;
                belowCount -= statusCountMap.getOrDefault(status, 0);
            }
            statusCountMap.put(status, statusCountMap.getOrDefault(status, 0) + 1);
            // 已当前为结尾 大于 0的数值 将小于当前值的子数组数量加上
            totalCount += belowCount;
            totalCount %= 1_000_000_007;
        }
        return totalCount;
    }


}
