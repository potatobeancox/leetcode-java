package com.potato.study.leetcodecn.p00786.t001;

import org.junit.Assert;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 786. 第 K 个最小的素数分数
 *
 * 给你一个按递增顺序排序的数组 arr 和一个整数 k 。数组 arr 由 1 和若干 素数  组成，且其中所有整数互不相同。

 对于每对满足 0 <= i < j < arr.length 的 i 和 j ，可以得到分数 arr[i] / arr[j] 。

 那么第 k 个最小的分数是多少呢?  以长度为 2 的整数数组返回你的答案, 这里 answer[0] == arr[i] 且 answer[1] == arr[j] 。

  
 示例 1：

 输入：arr = [1,2,3,5], k = 3
 输出：[2,5]
 解释：已构造好的分数,排序后如下所示:
 1/5, 1/3, 2/5, 1/2, 3/5, 2/3
 很明显第三个最小的分数是 2/5
 示例 2：

 输入：arr = [1,7], k = 1
 输出：[1,7]
  

 提示：

 2 <= arr.length <= 1000
 1 <= arr[i] <= 3 * 104
 arr[0] == 1
 arr[i] 是一个 素数 ，i > 0
 arr 中的所有数字 互不相同 ，且按 严格递增 排序
 1 <= k <= arr.length * (arr.length - 1) / 2

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/k-th-smallest-prime-fraction
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        // 因为 arr 是升序的数组 1到 len -1 作为分母 0开始作为分子
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(arr[o1[0]] * arr[o2[1]], arr[o1[1]] * arr[o2[0]]);
            }
        });
        for (int i = 1; i < arr.length; i++) {
            priorityQueue.add(new int[] {0, i});
        }
        int count = 0;
        int[] poll = null;
        while (count < k) {
            poll = priorityQueue.poll();
            int index1 = poll[0];
            int index2 = poll[1];
            if (index1 + 1 < index2) {
                priorityQueue.add(new int[] {index1 + 1, index2});
            }
            count++;
        }

        return new int[]{arr[poll[0]], arr[poll[1]]};
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[]{1,2,3,5};
        int k = 3;
        int[] ints = solution.kthSmallestPrimeFraction(arr, k);
        System.out.println(Arrays.toString(ints));
        Assert.assertArrayEquals(new int[] {2,5}, ints);
    }


}
