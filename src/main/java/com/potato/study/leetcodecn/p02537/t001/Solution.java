package com.potato.study.leetcodecn.p02537.t001;


import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * 2537. 统计好子数组的数目
 *
 * 给你一个整数数组 nums 和一个整数 k ，请你返回 nums 中 好 子数组的数目。
 *
 * 一个子数组 arr 如果有 至少 k 对下标 (i, j) 满足 i < j 且 arr[i] == arr[j] ，那么称它是一个 好 子数组。
 *
 * 子数组 是原数组中一段连续 非空 的元素序列。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,1,1,1], k = 10
 * 输出：1
 * 解释：唯一的好子数组是这个数组本身。
 * 示例 2：
 *
 * 输入：nums = [3,1,4,3,2,2,4], k = 2
 * 输出：4
 * 解释：总共有 4 个不同的好子数组：
 * - [3,1,4,3,2,2] 有 2 对。
 * - [3,1,4,3,2,2,4] 有 3 对。
 * - [1,4,3,2,2,4] 有 2 对。
 * - [4,3,2,2,4] 有 2 对。
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i], k <= 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/count-the-number-of-good-subarrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public long countGood(int[] nums, int k) {
        // pairs 记录到当前位置 一共有多少个 相同的对数量
        Map<Integer, Long> countMap = new HashMap<>();
        // key 出现的数字 value 已经出现的次数
        int left = 0;
        // 枚举每个右边 计算一共有多少个 pair 是一样的 同时 不停指定左边界移动
        long totalPairCount = 0;
        long goodCount = 0;
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            long appearCount = countMap.getOrDefault(val, 0L);
            // 判断当前有多少个 pair
            totalPairCount += appearCount;
            // 将left 往右移动知道 pair 数量少于 k
            countMap.put(val, appearCount + 1);
            while (left <= i && (totalPairCount - countMap.get(nums[left]) + 1) >= k) {
                countMap.put(nums[left], countMap.get(nums[left]) - 1);
                totalPairCount -= countMap.get(nums[left]);
                left++;
            }
            if (totalPairCount >= k) {
                goodCount += (left + 1);
            }
        }
        return goodCount;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {
                1,1,1,1,1
        };
        int k = 10;
        long l = solution.countGood(nums, k);
        System.out.println(l);
        Assert.assertEquals(1, l);


        nums = new int[] {
                3,1,4,3,2,2,4
        };
        k = 2;
        l = solution.countGood(nums, k);
        System.out.println(l);
        Assert.assertEquals(4, l);
    }


}
