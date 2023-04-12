package com.potato.study.leetcodecn.other.lcp.p0012.t001;

import java.util.*;

/**
 * LCP 12. 小张刷题计划
 *
 * 为了提高自己的代码能力，小张制定了 LeetCode 刷题计划，他选中了 LeetCode 题库中的 n 道题，编号从 0 到 n-1，并计划在 m 天内按照题目编号顺序刷完所有的题目（注意，小张不能用多天完成同一题）。

 在小张刷题计划中，小张需要用 time[i] 的时间完成编号 i 的题目。此外，小张还可以使用场外求助功能，通过询问他的好朋友小杨题目的解法，可以省去该题的做题时间。为了防止“小张刷题计划”变成“小杨刷题计划”，小张每天最多使用一次求助。

 我们定义 m 天中做题时间最多的一天耗时为 T（小杨完成的题目不计入做题总时间）。请你帮小张求出最小的 T是多少。

 示例 1：

 输入：time = [1,2,3,3], m = 2

 输出：3

 解释：第一天小张完成前三题，其中第三题找小杨帮忙；第二天完成第四题，并且找小杨帮忙。这样做题时间最多的一天花费了 3 的时间，并且这个值是最小的。

 示例 2：

 输入：time = [999,999,999], m = 4

 输出：0

 解释：在前三天中，小张每天求助小杨一次，这样他可以在三天内完成所有的题目并不花任何时间。

  

 限制：

 1 <= time.length <= 10^5
 1 <= time[i] <= 10000
 1 <= m <= 1000

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/xiao-zhang-shua-ti-ji-hua
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {



    // lcp 12
    public int minTime(int[] time, int m) {
        // 将m个最大的干掉 小跟堆
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((index1, index2) -> {
            return Integer.compare(time[index1], time[index2]);
        });
        for (int i = 0; i < time.length; i++) {
            minHeap.add(i);
            if (minHeap.size() > m) {
                minHeap.poll();
            }
        }
        Set<Integer> indexSet = new HashSet<>(minHeap);
        List<Integer> timeList = new ArrayList<>();
        for (int i = 0; i < time.length; i++) {
            if (!indexSet.contains(i)) {
                timeList.add(time[i]);
            }
        }

        // m 是使用的天数 每天可以找一个外援 顺序刷
        long left = 1;
        long right = Integer.MAX_VALUE / 2;
        long res = -1;
        while (left <= right) {
            long mid = (left + right) / 2;
            if (check(timeList, m, mid)) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return (int) res;
    }

    /**
     * 每天花费 target 情况下能否在 m天内完成
     * @param timeList 将m个最大的去掉后的时间
     * @param m
     * @param target
     * @return
     */
    private boolean check(List<Integer> timeList, int m, long target) {
        if (timeList.size() == 0) {
            return true;
        }
        int currentTime = 0;
        int dayCount = 0;
        for (int costTime : timeList) {
            // 某一个任务超过了 当前比较的时间 永远完成不了
            if (costTime > target) {
                return false;
            }
            if (currentTime + costTime > target) {
                dayCount++;
                currentTime = costTime;
            } else {
                currentTime += costTime;
            }
        }
        // 最后一天
        if (currentTime > 0) {
            dayCount++;
        }
        return dayCount <= m;
    }




}
