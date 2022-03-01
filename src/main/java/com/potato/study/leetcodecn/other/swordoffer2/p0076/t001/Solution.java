package com.potato.study.leetcodecn.other.swordoffer2.p0076.t001;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 剑指 Offer II 076. 数组中的第 k 大的数字
 *
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 *
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 *
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 *  
 *
 * 提示：
 *
 * 1 <= k <= nums.length <= 104
 * -104 <= nums[i] <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/xx4gT2
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // offer II 076 数组中 第k大的数字
    public int findKthLargest(int[] nums, int k) {
        // k 容量的小根堆
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(
                Integer::compare
        );
        for (int num : nums) {
            if (priorityQueue.isEmpty()
                    || priorityQueue.size() < k
                    || priorityQueue.peek() < num) {
                priorityQueue.add(num);
            }
            // 大小
            if (priorityQueue.size() > k) {
                priorityQueue.poll();
            }
        }
        return priorityQueue.peek();
    }
}
