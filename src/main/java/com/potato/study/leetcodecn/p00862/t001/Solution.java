package com.potato.study.leetcodecn.p00862.t001;

import java.util.Deque;
import java.util.LinkedList;

import org.junit.Assert;

import com.potato.study.leetcode.util.LeetcodeInputUtils;

/**
 * 862. 和至少为 K 的最短子数组
 *
 * 给你一个整数数组 nums 和一个整数 k ，找出 nums 中和至少为 k 的 最短非空子数组 ，并返回该子数组的长度。如果不存在这样的 子数组 ，返回 -1 。
 *
 * 子数组 是数组中 连续 的一部分。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1], k = 1
 * 输出：1
 * 示例 2：
 *
 * 输入：nums = [1,2], k = 4
 * 输出：-1
 * 示例 3：
 *
 * 输入：nums = [2,-1,2], k = 3
 * 输出：3
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * -105 <= nums[i] <= 105
 * 1 <= k <= 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shortest-subarray-with-sum-at-least-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int shortestSubarray(int[] inputNums, int k) {
        // 求一下前缀和
        long[] nums = new long[inputNums.length];
        nums[0] = inputNums[0];
        for (int i = 1; i < nums.length; i++) {
            nums[i] = nums[i-1] + inputNums[i];
        }
        // 双端队列 内部单调增
        Deque<Integer> indexDeque = new LinkedList<>();
        int shortestSubarrayLen = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= k) {
                shortestSubarrayLen = Math.min(i+1, shortestSubarrayLen);
            }
            if (indexDeque.isEmpty()) {
                indexDeque.addLast(i);
                continue;
            }
            // num index1 >= num index2 那么说明 到 k  只能停止到 index 2 处
            while (!indexDeque.isEmpty() && nums[indexDeque.peekLast()] >= nums[i]) {
                indexDeque.pollLast();
            }

            while  (!indexDeque.isEmpty() && nums[i] - nums[indexDeque.peekFirst()] >= k) {
                Integer firstIndex = indexDeque.pollFirst();
                shortestSubarrayLen = Math.min(shortestSubarrayLen, i - firstIndex);
            }

            indexDeque.addLast(i);
        }
        return shortestSubarrayLen == Integer.MAX_VALUE ? -1 : shortestSubarrayLen;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {
            1
        };
        int k = 1;
        int i = solution.shortestSubarray(nums, k);
        System.out.println(i);
        Assert.assertEquals(1, i);


        nums = new int[] {
                1, 2
        };
        k = 4;
        i = solution.shortestSubarray(nums, k);
        System.out.println(i);
        Assert.assertEquals(-1, i);


        nums = new int[] {
                2, -1, 2
        };
        k = 3;
        i = solution.shortestSubarray(nums, k);
        System.out.println(i);
        Assert.assertEquals(3, i);


        nums = new int[] {
                17,85,93,-45,-21
        };
        k = 150;
        i = solution.shortestSubarray(nums, k);
        System.out.println(i);
        Assert.assertEquals(2, i);


        nums = new int[] {
                84,-37,32,40,95
        };
        k = 167;
        i = solution.shortestSubarray(nums, k);
        System.out.println(i);
        Assert.assertEquals(3, i);


    }
}
