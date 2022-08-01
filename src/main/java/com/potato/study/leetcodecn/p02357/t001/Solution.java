package com.potato.study.leetcodecn.p02357.t001;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 2357. 使数组中所有元素都等于零
 *
 * 给你一个非负整数数组 nums 。在一步操作中，你必须：

 选出一个正整数 x ，x 需要小于或等于 nums 中 最小 的 非零 元素。
 nums 中的每个正整数都减去 x。
 返回使 nums 中所有元素都等于 0 需要的 最少 操作数。

  

 示例 1：

 输入：nums = [1,5,0,3,5]
 输出：3
 解释：
 第一步操作：选出 x = 1 ，之后 nums = [0,4,0,2,4] 。
 第二步操作：选出 x = 2 ，之后 nums = [0,2,0,0,2] 。
 第三步操作：选出 x = 2 ，之后 nums = [0,0,0,0,0] 。
 示例 2：

 输入：nums = [0]
 输出：0
 解释：nums 中的每个元素都已经是 0 ，所以不需要执行任何操作。
  

 提示：

 1 <= nums.length <= 100
 0 <= nums[i] <= 100

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/make-array-zero-by-subtracting-equal-amounts
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int minimumOperations(int[] nums) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int num : nums) {
            if (num > 0) {
                priorityQueue.add(num);
            }
        }

        if (priorityQueue.size() == 0) {
            return 0;
        }
        int operationCount = 0;
        while (priorityQueue.size() > 0) {
            int min = priorityQueue.peek();
            PriorityQueue<Integer> newPriorityQueue = new PriorityQueue<>();
            while (!priorityQueue.isEmpty()) {
                int poll = priorityQueue.poll();
                if (poll > min) {
                    newPriorityQueue.add(poll - min);
                }
            }
            priorityQueue = newPriorityQueue;
            operationCount++;
        }
        return operationCount;
    }


}
