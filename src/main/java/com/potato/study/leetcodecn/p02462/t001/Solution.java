package com.potato.study.leetcodecn.p02462.t001;

import org.junit.Assert;

import java.util.PriorityQueue;

/**
 * 2462. 雇佣 K 位工人的总代价
 *
 * 给你一个下标从 0 开始的整数数组 costs ，其中 costs[i] 是雇佣第 i 位工人的代价。
 *
 * 同时给你两个整数 k 和 candidates 。我们想根据以下规则恰好雇佣 k 位工人：
 *
 * 总共进行 k 轮雇佣，且每一轮恰好雇佣一位工人。
 * 在每一轮雇佣中，从最前面 candidates 和最后面 candidates 人中选出代价最小的一位工人，如果有多位代价相同且最小的工人，选择下标更小的一位工人。
 * 比方说，costs = [3,2,7,7,1,2] 且 candidates = 2 ，第一轮雇佣中，我们选择第 4 位工人，因为他的代价最小 [3,2,7,7,1,2] 。
 * 第二轮雇佣，我们选择第 1 位工人，因为他们的代价与第 4 位工人一样都是最小代价，而且下标更小，[3,2,7,7,2] 。注意每一轮雇佣后，剩余工人的下标可能会发生变化。
 * 如果剩余员工数目不足 candidates 人，那么下一轮雇佣他们中代价最小的一人，如果有多位代价相同且最小的工人，选择下标更小的一位工人。
 * 一位工人只能被选择一次。
 * 返回雇佣恰好 k 位工人的总代价。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：costs = [17,12,10,2,7,2,11,20,8], k = 3, candidates = 4
 * 输出：11
 * 解释：我们总共雇佣 3 位工人。总代价一开始为 0 。
 * - 第一轮雇佣，我们从 [17,12,10,2,7,2,11,20,8] 中选择。最小代价是 2 ，有两位工人，我们选择下标更小的一位工人，即第 3 位工人。总代价是 0 + 2 = 2 。
 * - 第二轮雇佣，我们从 [17,12,10,7,2,11,20,8] 中选择。最小代价是 2 ，下标为 4 ，总代价是 2 + 2 = 4 。
 * - 第三轮雇佣，我们从 [17,12,10,7,11,20,8] 中选择，最小代价是 7 ，下标为 3 ，总代价是 4 + 7 = 11 。注意下标为 3 的工人同时在最前面和最后面 4 位工人中。
 * 总雇佣代价是 11 。
 * 示例 2：
 *
 * 输入：costs = [1,2,4,1], k = 3, candidates = 3
 * 输出：4
 * 解释：我们总共雇佣 3 位工人。总代价一开始为 0 。
 * - 第一轮雇佣，我们从 [1,2,4,1] 中选择。最小代价为 1 ，有两位工人，我们选择下标更小的一位工人，即第 0 位工人，总代价是 0 + 1 = 1 。注意，下标为 1 和 2 的工人同时在最前面和最后面 3 位工人中。
 * - 第二轮雇佣，我们从 [2,4,1] 中选择。最小代价为 1 ，下标为 2 ，总代价是 1 + 1 = 2 。
 * - 第三轮雇佣，少于 3 位工人，我们从剩余工人 [2,4] 中选择。最小代价是 2 ，下标为 0 。总代价为 2 + 2 = 4 。
 * 总雇佣代价是 4 。
 *  
 *
 * 提示：
 *
 * 1 <= costs.length <= 105
 * 1 <= costs[i] <= 105
 * 1 <= k, candidates <= costs.length
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/total-cost-to-hire-k-workers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public long totalCost(int[] costs, int k, int candidates) {
        // 小跟堆 存下标 分别从前后 获取 记录 前后获取到的index
        PriorityQueue<Integer> indexPriorityQueue = new PriorityQueue<>((o1, o2) -> {
            return Integer.compare(costs[o1], costs[o2]);
        });
        int index1;
        for (index1 = 0; index1 < candidates && index1 < costs.length; index1++) {
           indexPriorityQueue.add(index1);
        }
        PriorityQueue<Integer> lastIndexPriorityQueue = new PriorityQueue<>((o1, o2) -> {
            return Integer.compare(costs[o1], costs[o2]);
        });
        int index2;
        for (index2 = costs.length - 1; index2 >= Math.max(index1, 0) && lastIndexPriorityQueue.size() < candidates ; index2--) {
            lastIndexPriorityQueue.add(index2);
        }
        // 判断一下目前剩余的是否已经到了可以使用一个堆的时候了
        long totalCost = 0;
        for (int i = 0; i < k; i++) {
            // 两种只有一个的情况
            if (indexPriorityQueue.isEmpty()) {
                Integer poll = lastIndexPriorityQueue.poll();
                totalCost += costs[poll];
                if (index2 >= index1) {
                    lastIndexPriorityQueue.add(index2);
                    index2--;
                }
            } else if (lastIndexPriorityQueue.isEmpty()) {
                Integer poll = indexPriorityQueue.poll();
                // 补充
                totalCost += costs[poll];
                if (index1 <= index2) {
                    indexPriorityQueue.add(index1);
                    index1++;
                }
            } else {
                // 两边都有值 判断下 两边目前哪个大
                if (indexPriorityQueue.peek() == lastIndexPriorityQueue.peek()) {
                    Integer poll = indexPriorityQueue.poll();
                    lastIndexPriorityQueue.poll();
                    totalCost += costs[poll];
                    // 补充
                    if (index1 <= index2) {
                        indexPriorityQueue.add(index1);
                        index1++;
                    }
                    if (index2 >= index1) {
                        lastIndexPriorityQueue.add(index2);
                        index2--;
                    }
                } else if (costs[indexPriorityQueue.peek()] > costs[lastIndexPriorityQueue.peek()]) {
                    Integer poll = lastIndexPriorityQueue.poll();
                    totalCost += costs[poll];
                    if (index2 >= index1) {
                        lastIndexPriorityQueue.add(index2);
                        index2--;
                    }
                } else if (costs[indexPriorityQueue.peek()] < costs[lastIndexPriorityQueue.peek()]) {
                    Integer poll = indexPriorityQueue.poll();
                    // 补充
                    totalCost += costs[poll];
                    if (index1 <= index2) {
                        indexPriorityQueue.add(index1);
                        index1++;
                    }
                } else {
                    // 相等出一个下标小的
                    if (indexPriorityQueue.peek() < lastIndexPriorityQueue.peek()) {
                        Integer poll = indexPriorityQueue.poll();
                        // 补充
                        totalCost += costs[poll];
                        if (index1 <= index2) {
                            indexPriorityQueue.add(index1);
                            index1++;
                        }
                    } else {
                        Integer poll = lastIndexPriorityQueue.poll();
                        totalCost += costs[poll];
                        if (index2 >= index1) {
                            lastIndexPriorityQueue.add(index2);
                            index2--;
                        }
                    }
                }
            }
        }
        // 如果当前前后的个数 都已经到了极限 那么只从一个 堆里边pop即可
        return totalCost;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] costs = new int[] {
                17,12,10,2,7,2,11,20,8
        };
        int k = 3;
        int candidates = 4;
        long l = solution.totalCost(costs, k, candidates);
        System.out.println(l);
        Assert.assertEquals(11, l);


        costs = new int[] {
                1,2,4,1
        };
        k = 3;
        candidates = 3;
        l = solution.totalCost(costs, k, candidates);
        System.out.println(l);
        Assert.assertEquals(4, l);
    }
}
