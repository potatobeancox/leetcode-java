package com.potato.study.leetcodecn.p02524.t001;

import org.junit.Assert;

import java.util.*;

/**
 * 2524. 子数组的最大频率分数
 *
 * 给定一个整数数组 nums 和一个 正 整数 k 。

 数组的 频率得分 是数组中 不同 值的 幂次 之和，并将和对 109 + 7 取模。

 例如，数组 [5,4,5,7,4,4] 的频率得分为 (43 + 52 + 71) modulo (109 + 7) = 96 。

 返回 nums 中长度为 k 的 子数组 的 最大 频率得分。你需要返回取模后的最大值，而不是实际值。

 子数组 是一个数组的连续部分。

  

 示例 1 ：

 输入：nums = [1,1,1,2,1,2], k = 3
 输出：5
 解释：子数组 [2,1,2] 的频率分数等于 5。可以证明这是我们可以获得的最大频率分数。
 示例 2 ：

 输入：nums = [1,1,1,1,1,1], k = 4
 输出：1
 解释：所有长度为 4 的子数组的频率得分都等于 1。
  

 提示：

 1 <= k <= nums.length <= 105
 1 <= nums[i] <= 106

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/maximum-frequency-score-of-a-subarray
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int maxFrequencyScore(int[] nums, int k) {
        // 用一个 map key是 窗口内部的值 value是 deque 当栈用 存储 窗口内部 当前计算结果
        Map<Integer, Deque<Long>> windowMap = new HashMap<>();
        int mod = 1_000_000_000 + 7;
        long maxScore = Long.MIN_VALUE;
        long currentScore = 0;
        for (int i = 0; i < nums.length; i++) {
            // 对window 内部进行计数
            int target = nums[i];
            if (!windowMap.containsKey(target)) {
                Deque<Long> deque = new LinkedList<>();
                // target 1次幂
                deque.addLast((long)target);
                currentScore += target;
                currentScore %= mod;

                windowMap.put(target, deque);
            } else {
                Deque<Long> deque = windowMap.get(target);
                Long lastScore = deque.peekLast();
                long thisScore = (lastScore * target) % mod;
                deque.addLast(thisScore);
                currentScore += (thisScore - lastScore);
            }

            // window 超过了 k 缩小window
            if (i >= k-1) {
                // 先结算 分数
                maxScore = Math.max(maxScore, (currentScore % mod + mod) % mod);
                // 修改窗口
                int removeKey = nums[i-k+1];

                Deque<Long> deque = windowMap.get(removeKey);
                Long lastScore = deque.peekLast();
                currentScore -= lastScore;
                if (deque.size() == 1) {
                    windowMap.remove(removeKey);
                } else {
                    deque.pollLast();
                    currentScore += deque.peekLast();
                }
            }
        }
        return (int) maxScore;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {
                1,1,1,2,1,2
        };
        int k = 3;
        int i = solution.maxFrequencyScore(nums, k);
        System.out.println(i);
        Assert.assertEquals(5, i);
    }


}
