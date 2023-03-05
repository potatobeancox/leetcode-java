package com.potato.study.leetcodecn.p00759.t001;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.potato.study.leetcode.domain.Interval;
import com.potato.study.leetcode.util.LeetcodeInputUtils;

/**
 * 759. 员工空闲时间
 *
 * 给定员工的 schedule 列表，表示每个员工的工作时间。
 *
 * 每个员工都有一个非重叠的时间段  Intervals 列表，这些时间段已经排好序。
 *
 * 返回表示 所有 员工的 共同，正数长度的空闲时间 的有限时间段的列表，同样需要排好序。
 *
 * 示例 1：
 *
 * 输入：schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
 * 输出：[[3,4]]
 * 解释：
 * 共有 3 个员工，并且所有共同的
 * 空间时间段是 [-inf, 1], [3, 4], [10, inf]。
 * 我们去除所有包含 inf 的时间段，因为它们不是有限的时间段。
 *  
 *
 * 示例 2：
 *
 * 输入：schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
 * 输出：[[5,6],[7,9]]
 *  
 *
 * （尽管我们用 [x, y] 的形式表示 Intervals ，内部的对象是 Intervals 而不是列表或数组。例如，schedule[0][0].start = 1, schedule[0][0].end = 2，并且
 * schedule[0][0][0] 是未定义的）
 *
 * 而且，答案中不包含 [5, 5] ，因为长度为 0。
 *
 *  
 *
 * 注：
 *
 * schedule 和 schedule[i] 为长度范围在 [1, 50]的列表。
 * 0 <= schedule[i].start < schedule[i].end <= 10^8。
 * 注：输入类型于 2019 年 4 月 15 日 改变。请重置为默认代码的定义以获取新方法。
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/employee-free-time
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        // 查分数组 便利 schedule 使用 treeMap 记录 每个位置的个数 开始 位置 + 1 结束位置 -1
        TreeMap<Integer, Integer> countMap = new TreeMap<>();
        for (List<Interval> intervals : schedule) {
            for (Interval interval : intervals) {
                int start = interval.start;
                int end = interval.end;

                int startStatus = countMap.getOrDefault(start, 0);
                startStatus += 1;
                countMap.put(start, startStatus);

                int endStatus = countMap.getOrDefault(end, 0);
                endStatus -= 1;
                countMap.put(end, endStatus);
            }
        }
        // 遍历 treeMap 过程中记录 之前出现 status = 0 的位置
        int status = 0;
        int lastIntervalIndex = -1;
        List<Interval> freeTimeList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            int key = entry.getKey();
            if (lastIntervalIndex != -1 && status == 0) {
                freeTimeList.add(new Interval(lastIntervalIndex, key));
            }
            lastIntervalIndex = key;
            status += entry.getValue();
        }
        return freeTimeList;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();


        List<List<Interval>> schedule = new ArrayList<>();

        List<Interval> list1 = new ArrayList<>();
        // [[[1,2],[5,6]],[[1,3]],[[4,10]]]
        list1.add(new Interval(1,2));
        list1.add(new Interval(5,6));
        schedule.add(list1);

        List<Interval> list2 = new ArrayList<>();
        // [[[1,2],[5,6]],[[1,3]],[[4,10]]]
        list2.add(new Interval(1,3));
        schedule.add(list2);


        List<Interval> list3 = new ArrayList<>();
        // [[[1,2],[5,6]],[[1,3]],[[4,10]]]
        list3.add(new Interval(4,10));
        schedule.add(list3);


        List<Interval> intervals = solution.employeeFreeTime(schedule);
        System.out.println(intervals);
    }
}

