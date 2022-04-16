package com.potato.study.leetcodecn.p01438.t001;

import org.junit.Assert;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 1438. 绝对差不超过限制的最长连续子数组
 *
 * 给你一个整数数组 nums ，和一个表示限制的整数 limit，请你返回最长连续子数组的长度，该子数组中的任意两个元素之间的绝对差必须小于或者等于 limit 。

 如果不存在满足条件的子数组，则返回 0 。

  

 示例 1：

 输入：nums = [8,2,4,7], limit = 4
 输出：2
 解释：所有子数组如下：
 [8] 最大绝对差 |8-8| = 0 <= 4.
 [8,2] 最大绝对差 |8-2| = 6 > 4.
 [8,2,4] 最大绝对差 |8-2| = 6 > 4.
 [8,2,4,7] 最大绝对差 |8-2| = 6 > 4.
 [2] 最大绝对差 |2-2| = 0 <= 4.
 [2,4] 最大绝对差 |2-4| = 2 <= 4.
 [2,4,7] 最大绝对差 |2-7| = 5 > 4.
 [4] 最大绝对差 |4-4| = 0 <= 4.
 [4,7] 最大绝对差 |4-7| = 3 <= 4.
 [7] 最大绝对差 |7-7| = 0 <= 4.
 因此，满足题意的最长子数组的长度为 2 。
 示例 2：

 输入：nums = [10,1,2,4,7,2], limit = 5
 输出：4
 解释：满足题意的最长子数组是 [2,4,7,2]，其最大绝对差 |2-7| = 5 <= 5 。
 示例 3：

 输入：nums = [4,2,2,2,4,4,2,2], limit = 0
 输出：3
  

 提示：

 1 <= nums.length <= 10^5
 1 <= nums[i] <= 10^9
 0 <= limit <= 10^9

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode-cn.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/solution/xiang-jie-er-fen-hua-dong-chuang-kou-dan-41g1/
     * 分别用两个 双端队列 维护最大值和最小值
     * @param nums
     * @param limit
     * @return
     */
    public int longestSubarray(int[] nums, int limit) {

        Deque<Integer> maxDeque = new LinkedList<>();
        Deque<Integer> minDeque = new LinkedList<>();
        int maxLength = 0;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            // right 更新 双端队列 如果当前值 比尾巴 大或者小 那么可以循环出战
            while (!maxDeque.isEmpty() && maxDeque.peekLast() < nums[right]) {
                maxDeque.pollLast();
            }
            maxDeque.addLast(nums[right]);
            while (!minDeque.isEmpty() && minDeque.peekLast() > nums[right]) {
                minDeque.pollLast();
            }
            minDeque.addLast(nums[right]);
            // 判断此时是不是满足 limit
            while (!maxDeque.isEmpty() && !minDeque.isEmpty()
                    && maxDeque.peekFirst() - minDeque.peekFirst() > limit) {
                // 超了 移动左边
                if (maxDeque.peekFirst() == nums[left]) {
                    maxDeque.pollFirst();
                }
                if (minDeque.peekFirst() == nums[left]) {
                    minDeque.pollFirst();
                }
                left++;
            }
            maxLength = Math.max(maxLength, right - left + 1);

        }
        return maxLength;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {8,2,4,7};
        int k = 4;
        int res = solution.longestSubarray(nums, k);
        System.out.println(res);
        Assert.assertEquals(2, res);


        nums = new int[] {10,1,2,4,7,2};
        k = 5;
        res = solution.longestSubarray(nums, k);
        System.out.println(res);
        Assert.assertEquals(4, res);


    }
}
