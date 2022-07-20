package com.potato.study.leetcodecn.p00253.t001;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import com.potato.study.leetcode.domain.TreeNode;

import jdk.nashorn.internal.ir.IdentNode;

/**
 * 253. 会议室 II
 *
 * 给你一个会议时间安排的数组 intervals ，每个会议时间都会包括开始和结束的时间 intervals[i] = [starti, endi] ，返回 所需会议室的最小数量 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：intervals = [[0,30],[5,10],[15,20]]
 * 输出：2
 * 示例 2：
 *
 * 输入：intervals = [[7,10],[2,4]]
 * 输出：1
 *  
 *
 * 提示：
 *
 * 1 <= intervals.length <= 104
 * 0 <= starti < endi <= 106
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/meeting-rooms-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int minMeetingRooms(int[][] intervals) {
        // 按照开始时间 升序 排序当前会议array
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });
        // 用一个小跟堆维护目前在占用的会议室的结束时间
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        // 遍历 intervals 如果当前堆中存在结束时间小于开始时间 的将 这个值pop 加入新会议的结束时间
        int maxCount = 0;
        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];
            // 最小结束时间的 会议 都大于当前开始时间
            if (priorityQueue.isEmpty() || priorityQueue.peek() > start) {
                priorityQueue.add(end);
                maxCount = Math.max(maxCount, priorityQueue.size());
            }  else {
                // 当前需要pop 再add
                priorityQueue.poll();
                priorityQueue.add(end);
            }
        }
        // 过程中维护 堆的最大值
        return maxCount;
    }


}
