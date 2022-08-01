package com.potato.study.leetcodecn.p00325.t001;


import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * 325. 和等于 k 的最长子数组长度
 *
 * 给定一个数组 nums 和一个目标值 k，找到和等于 k 的最长连续子数组长度。如果不存在任意一个符合要求的子数组，则返回 0。

  

 示例 1:

 输入: nums = [1,-1,5,-2,3], k = 3
 输出: 4
 解释: 子数组 [1, -1, 5, -2] 和等于 3，且长度最长。
 示例 2:

 输入: nums = [-2,-1,2,1], k = 1
 输出: 2
 解释: 子数组 [-1, 2] 和等于 1，且长度最长。
  

 提示：

 1 <= nums.length <= 2 * 105
 -104 <= nums[i] <= 104
 -109 <= k <= 109


 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/maximum-size-subarray-sum-equals-k
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     *
     * @param nums
     * @param k
     * @return
     */
    public int maxSubArrayLen(int[] nums, int k) {
        // 题目保证数组长度大于0
        int prefixSum = 0;
        Map<Integer, Integer> valueFirstIndexMap = new HashMap<>();
        // 处理全部数组满足条件的情况
        valueFirstIndexMap.put(0, -1);
        // 遍历 nums 计算 prefixSum 并找到第一个 出现数字index 的位置 prefixSum2 - k = prefixSum1
        int maxLength = 0;
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            int target = prefixSum - k;
            if (valueFirstIndexMap.containsKey(target)) {
                maxLength = Math.max(maxLength, i - valueFirstIndexMap.get(target));
            }
            // 记录 prefix sum
            if (!valueFirstIndexMap.containsKey(prefixSum)) {
                valueFirstIndexMap.put(prefixSum, i);
            }

        }
        // 找到了 计算子数组长度
        return maxLength;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        int i = solution.maxSubArrayLen();
//        System.out.println(i);
//        Assert.assertArrayEquals(3, i);
    }
}
