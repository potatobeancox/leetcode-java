package com.potato.study.leetcodecn.p02099.t001;

import com.potato.study.leetcode.domain.ListNode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 2099. 找到和最大的长度为 K 的子序列
 *
 * 给你一个整数数组 nums 和一个整数 k 。你需要找到 nums 中长度为 k 的 子序列 ，且这个子序列的 和最大 。

 请你返回 任意 一个长度为 k 的整数子序列。

 子序列 定义为从一个数组里删除一些元素后，不改变剩下元素的顺序得到的数组。

  

 示例 1：

 输入：nums = [2,1,3,3], k = 2
 输出：[3,3]
 解释：
 子序列有最大和：3 + 3 = 6 。
 示例 2：

 输入：nums = [-1,-2,3,4], k = 3
 输出：[-1,3,4]
 解释：
 子序列有最大和：-1 + 3 + 4 = 6 。
 示例 3：

 输入：nums = [3,4,3,3], k = 2
 输出：[3,4]
 解释：
 子序列有最大和：3 + 4 = 7 。
 另一个可行的子序列为 [4, 3] 。
  

 提示：

 1 <= nums.length <= 1000
 -105 <= nums[i] <= 105
 1 <= k <= nums.length

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/find-subsequence-of-length-k-with-the-largest-sum
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 2099
    public int[] maxSubsequence(int[] nums, int k) {
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o2[0], o1[0]);
            }
        });
        for (int i = 0; i < nums.length; i++) {
            priorityQueue.add(new int[] {nums[i], i});
        }
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = priorityQueue.poll()[1];
        }
        Arrays.sort(result);
        for (int i = 0; i < k; i++) {
            result[i] = nums[result[i]];
        }
        return result;
    }
}
