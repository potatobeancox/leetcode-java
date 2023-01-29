package com.potato.study.leetcodecn.p01918.t001;


import java.util.HashMap;
import java.util.Map;

/**
 * 1918. 第 K 小的子数组和·
 *
 * 给你一个 长度为 n 的整型数组 nums 和一个数值 k ，返回 第 k 小的子数组和。

 子数组 是指数组中一个 非空 且不间断的子序列。  子数组和 则指子数组中所有元素的和。

  

 示例 1:

 输入: nums = [2,1,3], k = 4
 输出: 3
 解释: [2,1,3] 的子数组为：
 - [2] 和为 2
 - [1] 和为 1
 - [3] 和为 3
 - [2,1] 和为 3
 - [1,3] 和为 4
 - [2,1,3] 和为 6
 最小子数组和的升序排序为 1, 2, 3, 3, 4, 6。 第 4 小的子数组和为 3 。
 示例 2：
 输入：nums = [3,3,5,5], k = 7
 输出：10
 解释：[3,3,5,5] 的子数组为：
 - [3] 和为 3
 - [3] 和为 3
 - [5] 和为 5
 - [5] 和为 5
 - [3,3] 和为 6
 - [3,5] 和为 8
 - [5,5] 和为 10
 - [3,3,5], 和为 11
 - [3,5,5] 和为 13
 - [3,3,5,5] 和为 16
 最小子数组和的升序排序为 3, 3, 5, 5, 6, 8, 10, 11, 13, 16。第 7 小的子数组和为 10 。
  

 提示:

 n == nums.length
 1 <= n <= 2 * 104
 1 <= nums[i] <= 5 * 104
 1 <= k <= n * (n + 1) / 2

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/kth-smallest-subarray-sum
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int kthSmallestSubarraySum(int[] nums, int k) {
        // 先求前缀和
        int n = nums.length;
        long[] prefix = new long[n+1];
        for (int i = 0; i < n; i++) {
            prefix[i+1] = nums[i] + prefix[i];
        }
        // 二分法 求 小于等于 target 子树组 的数量 左边是 前缀和 0 后面是前缀和 最大值
        long left = 0;
        long right = prefix[n];
        // 对于每一个 mid 求小于等于 mid的子数组的数量
        int res = -1;
        while (left <= right) {
            long mid = (left + right) / 2;
            // 小于等于 mid的 子数组数量
            int count = countSubArray(prefix, mid);
            if (count >= k) {
                res = (int)mid;
                right = mid - 1;
            } else {
                // 少了 需要=扩展一些
                left = mid + 1;
            }
        }
        return res;
    }

    /**
     * 小于等于 target的子数组数量
     * @param prefix
     * @param target
     * @return
     */
    private int countSubArray(long[] prefix, long target) {
        // right 从1 开始找到最后 left从 0 开始
        int left = 0;
        int count = 0;
        for (int i = 1; i < prefix.length; i++) {
            // 针对每个 位置的 right 求数量
            int right = i;
            // 如果当前 right 的前缀和 减去 left的前缀和 大于 target 说明 left还需要右移动
            while (left < right && prefix[right] - prefix[left] > target) {
                left++;
            }
            // 中间的子数组 和小于等于 target
            if (left < right) {
                count += (right - left);
            }
        }
        return count;
    }

}
