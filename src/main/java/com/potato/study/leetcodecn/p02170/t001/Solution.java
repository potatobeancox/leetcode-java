package com.potato.study.leetcodecn.p02170.t001;


import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 2170. 使数组变成交替数组的最少操作数
 *
 * 给你一个下标从 0 开始的数组 nums ，该数组由 n 个正整数组成。

 如果满足下述条件，则数组 nums 是一个 交替数组 ：

 nums[i - 2] == nums[i] ，其中 2 <= i <= n - 1 。
 nums[i - 1] != nums[i] ，其中 1 <= i <= n - 1 。
 在一步 操作 中，你可以选择下标 i 并将 nums[i] 更改 为 任一 正整数。

 返回使数组变成交替数组的 最少操作数 。

  

 示例 1：

 输入：nums = [3,1,3,2,4,3]
 输出：3
 解释：
 使数组变成交替数组的方法之一是将该数组转换为 [3,1,3,1,3,1] 。
 在这种情况下，操作数为 3 。
 可以证明，操作数少于 3 的情况下，无法使数组变成交替数组。
 示例 2：

 输入：nums = [1,2,2,2,2]
 输出：2
 解释：
 使数组变成交替数组的方法之一是将该数组转换为 [1,2,1,2,1].
 在这种情况下，操作数为 2 。
 注意，数组不能转换成 [2,2,2,2,2] 。因为在这种情况下，nums[0] == nums[1]，不满足交替数组的条件。
  

 提示：

 1 <= nums.length <= 105
 1 <= nums[i] <= 105

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/minimum-operations-to-make-the-array-alternating
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int minimumOperations(int[] nums) {
        // 记录 odd index 和 even index 的 map count
        Map<Integer, Integer> oddMap = new HashMap<>();
        Map<Integer, Integer> evenMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                evenMap.put(nums[i], evenMap.getOrDefault(nums[i], 0) + 1);
            } else {
                oddMap.put(nums[i], oddMap.getOrDefault(nums[i], 0) + 1);
            }
        }
        // 找到 odd 最大 第二大的key
        PriorityQueue<Integer> evenPriorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(evenMap.get(o1), evenMap.get(o2));
            }
        });
        // 找到 even 最大和 次大的key
        for (int key : evenMap.keySet()) {
            if (evenPriorityQueue.isEmpty()
                    || evenPriorityQueue.size() < 2
                    || evenMap.get(evenPriorityQueue.peek()) < evenMap.get(key)) {
                evenPriorityQueue.add(key);
            }
            if (evenPriorityQueue.size() > 2) {
                evenPriorityQueue.poll();
            }
        }


        PriorityQueue<Integer> oddPriorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(evenMap.get(o1), evenMap.get(o2));
            }
        });
        for (int key : oddMap.keySet()) {
            if (oddPriorityQueue.isEmpty()
                    || oddPriorityQueue.size() < 2
                    || oddMap.get(oddPriorityQueue.peek()) < oddMap.get(key)) {
                oddPriorityQueue.add(key);
            }
            if (oddPriorityQueue.size() > 2) {
                oddPriorityQueue.poll();
            }
        }
        return -1;
    }
}
