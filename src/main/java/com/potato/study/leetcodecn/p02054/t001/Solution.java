package com.potato.study.leetcodecn.p02054.t001;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import org.junit.Assert;

/**
 * 2054. 两个最好的不重叠活动
 *
 * 给你一个下标从 0 开始的二维整数数组 events ，其中 events[i] = [startTimei, endTimei,
 * valuei] 。第 i 个活动开始于 startTimei ，结束于 endTimei ，如果你参加这个活动，那么你可以得到价值 valuei 。你 最多 可以参加 两个时间不重叠 活动，使得它们的价值之和 最大 。
 *
 * 请你返回价值之和的 最大值 。
 *
 * 注意，活动的开始时间和结束时间是 包括 在活动时间内的，也就是说，你不能参加两个活动且它们之一的开始时间等于另一个活动的结束时间。更具体的，如果你参加一个活动，且结束时间为 t ，那么下一个活动必须在 t + 1 或之后的时间开始。
 *
 *  
 *
 * 示例 1:
 *
 *
 *
 * 输入：events = [[1,3,2],[4,5,2],[2,4,3]]
 * 输出：4
 * 解释：选择绿色的活动 0 和 1 ，价值之和为 2 + 2 = 4 。
 * 示例 2：
 *
 *
 *
 * 输入：events = [[1,3,2],[4,5,2],[1,5,5]]
 * 输出：5
 * 解释：选择活动 2 ，价值和为 5 。
 * 示例 3：
 *
 *
 *
 * 输入：events = [[1,5,3],[1,5,1],[6,6,5]]
 * 输出：8
 * 解释：选择活动 0 和 2 ，价值之和为 3 + 5 = 8 。
 *  
 *
 * 提示：
 *
 * 2 <= events.length <= 105
 * events[i].length == 3
 * 1 <= startTimei <= endTimei <= 109
 * 1 <= valuei <= 106
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/two-best-non-overlapping-events
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    //  2054
    public int maxTwoEvents(int[][] events) {
        // 将 events 按照开始 时间升序排序
        Arrays.sort(events, (e1, e2) -> {
            int compare = Integer.compare(e1[0], e2[0]);
            if (compare != 0) {
                return compare;
            }
            compare = Integer.compare(e1[1], e2[1]);
            return compare;
        });
        // 用一个 优先级队列 存 还没有完成的 event 0- 结束时间 1价格
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });
        // 用 max 记录之前完事的 事务的最大价值
        int maxEverValue = 0;
        int maxTwo = 0;
        for (int i = 0; i < events.length; i++) {
            int[] event = events[i];
            int start = event[0];
            int end = event[1];
            int value = event[2];
            // priorityQueue 开始时间之前的券poll
            while (!priorityQueue.isEmpty() && priorityQueue.peek()[0] < start) {
                int[] poll = priorityQueue.poll();
                maxEverValue = Math.max(maxEverValue, poll[1]);
            }
            if (maxEverValue != 0) {
                maxTwo = Math.max(maxTwo, maxEverValue + value);
            }
            priorityQueue.add(new int[] {
                    end, value
            });
        }
        // 遍历 排序 后的 events 计算 当前 结束 的最大值 和 当前时间的value 和 max的和记录
        return maxTwo;
    }




}
