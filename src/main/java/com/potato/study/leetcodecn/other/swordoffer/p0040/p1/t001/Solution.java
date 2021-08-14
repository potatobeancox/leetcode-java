package com.potato.study.leetcodecn.other.swordoffer.p0040.p1.t001;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 剑指 Offer 40. 最小的k个数
 *
 * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。

  

 示例 1：

 输入：arr = [3,2,1], k = 2
 输出：[1,2] 或者 [2,1]
 示例 2：

 输入：arr = [0,1,2,1], k = 1
 输出：[0]
  

 限制：

 0 <= k <= arr.length <= 10000
 0 <= arr[i] <= 10000

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 大根堆 小于 根就加到堆中 堆超了就删除 堆顶
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int i = 0; i < arr.length; i++) {
            // 小于 根就加到堆中 堆超了就删除 堆顶
            if (priorityQueue.isEmpty() || priorityQueue.size() < k || priorityQueue.peek() > arr[i]) {
                priorityQueue.add(arr[i]);
            }
            // 超了大小需要修剪对
            while (!priorityQueue.isEmpty() && priorityQueue.size() > k) {
                priorityQueue.poll();
            }
        }
        // 将堆转换成数字
        int[] result = new int[priorityQueue.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = priorityQueue.poll();
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[] {3,2,1};
        int k = 2;
        int[] leastNumbers = solution.getLeastNumbers(arr, k);
        System.out.println(Arrays.toString(leastNumbers));

        arr = new int[] {0,1,2,1};
        k = 1;
        leastNumbers = solution.getLeastNumbers(arr, k);
        System.out.println(Arrays.toString(leastNumbers));


        arr = new int[] {0,0,0,2,0,5};
        k = 0;
        leastNumbers = solution.getLeastNumbers(arr, k);
        System.out.println(Arrays.toString(leastNumbers));
    }

}
