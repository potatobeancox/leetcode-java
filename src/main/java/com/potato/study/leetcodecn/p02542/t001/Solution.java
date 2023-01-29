package com.potato.study.leetcodecn.p02542.t001;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * 2542. 最大子序列的分数
 *
 * 给你两个下标从 0 开始的整数数组 nums1 和 nums2 ，两者长度都是 n ，再给你一个正整数 k 。你必须从 nums1 中选一个长度为 k 的 子序列 对应的下标。

 对于选择的下标 i0 ，i1 ，...， ik - 1 ，你的 分数 定义如下：

 nums1 中下标对应元素求和，乘以 nums2 中下标对应元素的 最小值 。
 用公示表示： (nums1[i0] + nums1[i1] +...+ nums1[ik - 1]) * min(nums2[i0] , nums2[i1], ... ,nums2[ik - 1]) 。
 请你返回 最大 可能的分数。

 一个数组的 子序列 下标是集合 {0, 1, ..., n-1} 中删除若干元素得到的剩余集合，也可以不删除任何元素。

  

 示例 1：

 输入：nums1 = [1,3,3,2], nums2 = [2,1,3,4], k = 3
 输出：12
 解释：
 四个可能的子序列分数为：
 - 选择下标 0 ，1 和 2 ，得到分数 (1+3+3) * min(2,1,3) = 7 。
 - 选择下标 0 ，1 和 3 ，得到分数 (1+3+2) * min(2,1,4) = 6 。
 - 选择下标 0 ，2 和 3 ，得到分数 (1+3+2) * min(2,3,4) = 12 。
 - 选择下标 1 ，2 和 3 ，得到分数 (3+3+2) * min(1,3,4) = 8 。
 所以最大分数为 12 。
 示例 2：

 输入：nums1 = [4,2,3,1,1], nums2 = [7,5,10,9,6], k = 1
 输出：30
 解释：
 选择下标 2 最优：nums1[2] * nums2[2] = 3 * 10 = 30 是最大可能分数。
  

 提示：

 n == nums1.length == nums2.length
 1 <= n <= 105
 0 <= nums1[i], nums2[j] <= 105
 1 <= k <= n

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/maximum-subsequence-score
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public long maxScore(int[] nums1, int[] nums2, int k) {
        // 小根堆 记录当前窗口的值 （模拟子序列），对 nums2 进行降序排序最开始 k-1 入栈作为基底
        List<NumPair> numPairList = new ArrayList<>();
        int n = nums1.length;
        for (int i = 0; i < n; i++) {
            NumPair numPair = new NumPair();
            numPair.num1 = nums1[i];
            numPair.num2 = nums2[i];

            numPairList.add(numPair);
        }
        // 排序 按照 num2 倒序
        Collections.sort(numPairList, (o1, o2) -> Integer.compare(o2.num2, o1.num2));

        // 每次计算一个最大的值 并维护和
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        long sum = 0;
        long maxScore = 0;
        for (int i = 0; i < n; i++) {
            // 判断是否需要减去 前面那个
            if (i >= k) {
                // k+1个了
                int removeVal = priorityQueue.poll();
                sum -= removeVal;
            }
            // 累加
            NumPair numPair = numPairList.get(i);
            sum += numPair.num1;
            priorityQueue.add(numPair.num1);

            // 窗口乘以 最小值
            if (i >= k-1) {
                maxScore = Math.max(maxScore, sum * numPair.num2);
            }
        }
        return maxScore;
    }

    class NumPair {
        public int num1;
        public int num2;
    }



}
