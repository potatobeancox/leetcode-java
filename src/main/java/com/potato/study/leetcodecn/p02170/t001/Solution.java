package com.potato.study.leetcodecn.p02170.t001;


import org.junit.Assert;

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


    // 2170
    public int minimumOperations(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }
        // 遍历 一遍 nums 统计 偶数位置 最多出现次数和数字，次多的出现次数和数字
        Map<Integer, Integer> countMap1 = new HashMap<>();
        Map<Integer, Integer> countMap2 = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                countMap1.put(nums[i], countMap1.getOrDefault(nums[i], 0) + 1);
            } else {
                countMap2.put(nums[i], countMap2.getOrDefault(nums[i], 0) + 1);
            }
        }
        // 奇数位置 最多的出现次数和数字 堆处理 小根堆
        PriorityQueue<Integer> priorityQueue1 = getPriorityQueue(countMap1);

        PriorityQueue<Integer> priorityQueue2 = getPriorityQueue(countMap2);
        // 如果 最多的 不一样 那么 就是 nums。le 减去
        if (priorityQueue1.size() == 1 && priorityQueue2.size() == 1) {
            int max1 = priorityQueue1.peek();
            int max2 = priorityQueue2.peek();
            if (max1 != max2) {
                return 0;
            }
            return nums.length - Math.max(countMap1.get(max1), countMap2.get(max2));
        } else if (priorityQueue1.size() == 1) {
            int max1 = priorityQueue1.poll();
            int secondMax2 = priorityQueue2.poll();
            int max2 = priorityQueue2.poll();
            if (max1 != max2) {
                return nums.length - (countMap1.get(max1) + countMap2.get(max2));
            } else {
                return nums.length - (countMap1.get(max1) + countMap2.get(secondMax2));
            }
        } else if (priorityQueue2.size() == 1) {
            int max2 = priorityQueue2.poll();
            int secondMax1 = priorityQueue1.poll();
            int max1 = priorityQueue1.poll();
            if (max1 != max2) {
                return nums.length - (countMap1.get(max1) + countMap2.get(max2));
            } else {
                return nums.length - (countMap1.get(secondMax1) + countMap2.get(max2));
            }
        } else {
            // 都是2
            int secondMax1 = priorityQueue1.poll();
            int max1 = priorityQueue1.poll();

            int secondMax2 =  priorityQueue2.poll();
            int max2 =  priorityQueue2.poll();

            if (max1 != max2) {
                return nums.length - (countMap1.get(max1) + countMap2.get(max2));
            }
            return nums.length - Math.max(countMap1.get(max1) + countMap2.get(secondMax2),
                    countMap1.get(secondMax1) + countMap2.get(max2));
        }
    }

    private PriorityQueue<Integer> getPriorityQueue(Map<Integer, Integer> countMap) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((i1, i2) -> Integer.compare(
                countMap.getOrDefault(i1, 0), countMap.getOrDefault(i2, 0)
        ));
        for (int key : countMap.keySet()) {
            if (priorityQueue.isEmpty()
                    || priorityQueue.size() < 2
                    || countMap.get(priorityQueue.peek()) < countMap.get(key)) {
                priorityQueue.add(key);
            }
            if (priorityQueue.size() > 2) {
                priorityQueue.poll();
            }
        }
        return priorityQueue;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {
                3,1,3,2,4,3
        };
        int i = solution.minimumOperations(nums);
        System.out.println(i);
        Assert.assertEquals(3, i);

        nums = new int[] {
                1,2,2,2,2
        };
        i = solution.minimumOperations(nums);
        System.out.println(i);
        Assert.assertEquals(2, i);


        nums = new int[] {
                42,2,97,78,8,12,98,7,98,48,56,80,54,51,61,23,2,64,87,24,25,10,55,25,12,71,82,67,21,54,29,78,11,29,65,24,56,3,55,67,70,97,40,14,92,82,42,37,7,91,63,47,42,4,100,47,75,76,43,90,28,14,69,4,90,8,52,89,68,51,5,52,1,83,6,87,79,56,93,59,11,42,77,68,79,58,72,66,28,12,59,75,87,99,47
        };
        i = solution.minimumOperations(nums);
        System.out.println(i);
        Assert.assertEquals(90, i);
    }
}
