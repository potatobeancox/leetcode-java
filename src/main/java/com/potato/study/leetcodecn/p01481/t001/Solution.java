package com.potato.study.leetcodecn.p01481.t001;

import org.junit.Assert;

import java.util.*;

/**
 * 1481. 不同整数的最少数目
 *
 * 给你一个整数数组 arr 和一个整数 k 。现需要从数组中恰好移除 k 个元素，请找出移除后数组中不同整数的最少数目。

  

 示例 1：

 输入：arr = [5,5,4], k = 1
 输出：1
 解释：移除 1 个 4 ，数组中只剩下 5 一种整数。
 示例 2：

 输入：arr = [4,3,1,1,3,3,2], k = 3
 输出：2
 解释：先移除 4、2 ，然后再移除两个 1 中的任意 1 个或者三个 3 中的任意 1 个，最后剩下 1 和 3 两种整数。
  

 提示：

 1 <= arr.length <= 10^5
 1 <= arr[i] <= 10^9
 0 <= k <= arr.length

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/least-number-of-unique-integers-after-k-removals
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            Integer count = countMap.getOrDefault(arr[i], 0);
            count++;
            countMap.put(arr[i], count);
        }
        // 排序
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(countMap.getOrDefault(o1, 0), countMap.getOrDefault(o2, 0));
            }
        });
        for (int key : countMap.keySet()) {
            priorityQueue.add(key);
        }
        int remind = k;
        while (remind > 0 && !priorityQueue.isEmpty()) {
            int peek = priorityQueue.peek();
            if (countMap.get(peek) <= remind) {
                remind -= countMap.get(peek);
                priorityQueue.poll();
            } else {
                // 已经尽力了
                remind = 0;
            }

        }
        return priorityQueue.size();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[] {
                5,5,4
        };
        int k = 1;
        int leastNumOfUniqueInts = solution.findLeastNumOfUniqueInts(arr, k);
        System.out.println(leastNumOfUniqueInts);
        Assert.assertEquals(1, leastNumOfUniqueInts);
    }
}
