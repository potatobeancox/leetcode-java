package com.potato.study.leetcodecn.p00658.t001;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 658. 找到 K 个最接近的元素
 *
 * 给定一个排序好的数组 arr ，两个整数 k 和 x ，从数组中找到最靠近 x（两数之差最小）的 k 个数。返回的结果必须要是按升序排好的。

 整数 a 比整数 b 更接近 x 需要满足：

 |a - x| < |b - x| 或者
 |a - x| == |b - x| 且 a < b
  

 示例 1：

 输入：arr = [1,2,3,4,5], k = 4, x = 3
 输出：[1,2,3,4]
 示例 2：

 输入：arr = [1,2,3,4,5], k = 4, x = -1
 输出：[1,2,3,4]
  

 提示：

 1 <= k <= arr.length
 1 <= arr.length <= 104
 数组里的每个元素与 x 的绝对值不超过 104

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/find-k-closest-elements
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 658
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        // 升序
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(
                Comparator.comparingInt((Integer a) -> Math.abs(a - x)).thenComparingInt(a -> a)
        );
        for (int num: arr) {
            priorityQueue.add(num);
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            list.add(priorityQueue.poll());
        }
        Collections.sort(list);
        return list;
    }
}
