package com.potato.study.leetcodecn.p00252.t001;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * 252. 会议室
 *
 * 给定一个会议时间安排的数组 intervals ，每个会议时间都会包括开始和结束的时间 intervals[i] = [starti, endi] ，请你判断一个人是否能够参加这里面的全部会议。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：intervals = [[0,30],[5,10],[15,20]]
 * 输出：false
 * 示例 2：
 *
 * 输入：intervals = [[7,10],[2,4]]
 * 输出：true
 *  
 *
 * 提示：
 *
 * 0 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti < endi <= 106
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/meeting-rooms
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public boolean canAttendMeetings(int[][] intervals) {
        TreeMap<Integer, Integer> countMap = new TreeMap<>();
        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];

            int startCount = countMap.getOrDefault(start, 0);
            startCount++;
            countMap.put(start, startCount);

            int endCount = countMap.getOrDefault(end, 0);
            endCount--;
            countMap.put(end, endCount);
        }
        // 过一边 countMap 记录状态
        int status = 0;
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            status += entry.getValue();
            if (status > 1) {
                return false;
            }
        }
        return true;
    }


}
