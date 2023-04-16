package com.potato.study.leetcodecn.p01235.t001;


import org.junit.Assert;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * 1235. 规划兼职工作
 *
 * 你打算利用空闲时间来做兼职工作赚些零花钱。

 这里有 n 份兼职工作，每份工作预计从 startTime[i] 开始到 endTime[i] 结束，报酬为 profit[i]。

 给你一份兼职工作表，包含开始时间 startTime，结束时间 endTime 和预计报酬 profit 三个数组，请你计算并返回可以获得的最大报酬。

 注意，时间上出现重叠的 2 份工作不能同时进行。

 如果你选择的工作在时间 X 结束，那么你可以立刻进行在时间 X 开始的下一份工作。

  

 示例 1：



 输入：startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
 输出：120
 解释：
 我们选出第 1 份和第 4 份工作，
 时间范围是 [1-3]+[3-6]，共获得报酬 120 = 50 + 70。
 示例 2：



 输入：startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
 输出：150
 解释：
 我们选择第 1，4，5 份工作。
 共获得报酬 150 = 20 + 70 + 60。
 示例 3：



 输入：startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
 输出：6
  

 提示：

 1 <= startTime.length == endTime.length == profit.length <= 5 * 10^4
 1 <= startTime[i] < endTime[i] <= 10^9
 1 <= profit[i] <= 10^4


 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/maximum-profit-in-job-scheduling
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        // 先按照 start 和 end 升序排序
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((o1, o2) -> {
            int compare = Integer.compare(o1[1], o2[1]);
            if (compare != 0) {
                return compare;
            }
            return Integer.compare(o1[0], o2[0]);
        });
        int n = startTime.length;
        for (int i = 0; i < n; i++) {
            priorityQueue.add(new int[] {
                    startTime[i], endTime[i], profit[i]
            });
        }
        // 用一个 treeMap 表示 endtime 为key 最多能拿多少钱
        TreeMap<Integer, Long> endTimeProfitMap = new TreeMap<>();
        // 遍历 排序后的数组 每次从 treeMap中取小于等于 startime的 最多钱 计算如果在这之前 完成最多能获得多少钱
        long max = 0;
        while (!priorityQueue.isEmpty()) {
            int[] poll = priorityQueue.poll();
            int stime = poll[0];
            // 结束时间在stime之前的情况 小于等于 stime
            Map.Entry<Integer, Long> beforeStimeEntry = endTimeProfitMap.floorEntry(stime);
            int p = poll[2];
            long currentProfix = p;
            if (beforeStimeEntry != null) {
                currentProfix += beforeStimeEntry.getValue();
            }
            int etime = poll[1];
            // 看看之前etime 最大的
            Map.Entry<Integer, Long> beforeEtimeEntry = endTimeProfitMap.floorEntry(etime);
            // 需要比较下 做这个和 endtime 之前不做的max值
            if (beforeEtimeEntry != null) {
                currentProfix = Math.max(currentProfix, beforeEtimeEntry.getValue().longValue());
            }
            // 设置e
            endTimeProfitMap.put(etime, currentProfix);
            max = Math.max(max, currentProfix);

        }
        return (int) max;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] startTime = new int[] {
                1,2,3,4,6
        };
        int[] endTime = new int[] {
                3,5,10,6,9
        };
        int[] profit = new int[] {
                20,20,100,70,60
        };
        int i = solution.jobScheduling(startTime, endTime, profit);
        System.out.println(i);
        Assert.assertEquals(150, i);

        solution = new Solution();
        startTime = new int[] {
                4,2,4,8,2
        };
        endTime = new int[] {
                5,5,5,10,8
        };
        profit = new int[] {
                1,2,8,10,4
        };
        i = solution.jobScheduling(startTime, endTime, profit);
        System.out.println(i);
        Assert.assertEquals(18, i);

    }



}
