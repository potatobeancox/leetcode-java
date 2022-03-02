package com.potato.study.leetcodecn.other.swordoffer2.p0060.t001;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 剑指 Offer II 060. 出现频率最高的 k 个数字
 *
 * 给定一个整数数组 nums 和一个整数 k ，请返回其中出现频率前 k 高的元素。可以按 任意顺序 返回答案。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 *
 * 输入: nums = [1], k = 1
 * 输出: [1]
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * k 的取值范围是 [1, 数组中不相同的元素的个数]
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
 *  
 *
 * 进阶：所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。
 *
 *  
 *
 * 注意：本题与主站 347 题相同：https://leetcode-cn.com/problems/top-k-frequent-elements/
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/g5c51o
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        // 小根堆
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(
                (i1, i2) -> {
                    return Integer.compare(countMap.get(i1), countMap.get(i2));
                }
        );
        for (int key : countMap.keySet()) {
            if (priorityQueue.isEmpty()
                    || priorityQueue.size() < k
                    || countMap.get(priorityQueue.peek()) < countMap.get(key)) {
                priorityQueue.add(key);
            }
            if (priorityQueue.size() > k) {
                priorityQueue.poll();
            }
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = priorityQueue.poll();
        }
        return res;
    }
}
