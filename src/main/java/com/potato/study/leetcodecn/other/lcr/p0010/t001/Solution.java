package com.potato.study.leetcodecn.other.lcr.p0010.t001;

import java.util.HashMap;
import java.util.Map;

/**
 * LCR 010. 和为 K 的子数组
 *
 * 给定一个整数数组和一个整数 k ，请找到该数组中和为 k 的连续子数组的个数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2
 * 解释: 此题 [1,1] 与 [1,1] 为两种不同的情况
 * 示例 2：
 *
 * 输入:nums = [1,2,3], k = 3
 * 输出: 2
 *  
 *
 * 提示:
 *
 * 1 <= nums.length <= 2 * 104
 * -1000 <= nums[i] <= 1000
 * -107 <= k <= 107
 *
 *  
 *
 * 注意：本题与主站 560 题相同： https://leetcode-cn.com/problems/subarray-sum-equals-k/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/QTMn0o
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        // n1 + n2 == k =>  n2 == k - n1
        // 便利 nums 记录 当前 的和 和之前的前缀和 和前缀和的出现次数 然后从次数里边找一共有多少个
        long sum = 0;
        Map<Long, Integer> sumCountMap = new HashMap<>();
        // 0出现过一次 处理 所有数组都是字串的情况
        sumCountMap.put(0L, 1);
        int totalCount = 0;
        for (int num : nums) {
            sum += num;
            // sum - target = k;
            long target = sum - k;
            int targetCount = sumCountMap.getOrDefault(target, 0);
            totalCount += targetCount;
            // 往里边插入 当前sum
            sumCountMap.put(sum, sumCountMap.getOrDefault(sum, 0) + 1);
        }
        return totalCount;
    }
}
