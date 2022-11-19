package com.potato.study.leetcodecn.p01229.t001;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 1229. 安排会议日程
 *
 * 给定两个人的空闲时间表：slots1 和 slots2，以及会议的预计持续时间 duration，请你为他们安排 时间段最早 且合适的会议时间。
 *
 * 如果没有满足要求的会议时间，就请返回一个 空数组。
 *
 * 「空闲时间」的格式是 [start, end]，由开始时间 start 和结束时间 end 组成，表示从 start 开始，到 end 结束。 
 *
 * 题目保证数据有效：同一个人的空闲时间不会出现交叠的情况，也就是说，对于同一个人的两个空闲时间 [start1, end1] 和 [start2, end2]，要么 start1 > end2，要么 start2 > end1。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 8
 * 输出：[60,68]
 * 示例 2：
 *
 * 输入：slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 12
 * 输出：[]
 *  
 *
 * 提示：
 *
 * 1 <= slots1.length, slots2.length <= 104
 * slots1[i].length, slots2[i].length == 2
 * slots1[i][0] < slots1[i][1]
 * slots2[i][0] < slots2[i][1]
 * 0 <= slots1[i][j], slots2[i][j] <= 109
 * 1 <= duration <= 106
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/meeting-scheduler
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        // 按照 开始位置 升序排序
        Arrays.sort(slots1, Comparator.comparingInt(o -> o[0]));
        Arrays.sort(slots2, Comparator.comparingInt(o -> o[0]));
        // 分别对 两个下边开始找 移动先结束的
        int i1 = 0;
        int i2 = 0;

        while (i1 < slots1.length && i2 < slots2.length) {
            int[] slot1 = slots1[i1];
            int[] slot2 = slots2[i2];
            int left = Math.max(slot1[0], slot2[0]);
            int right = Math.min(slot1[1], slot2[1]);

            int dis = right - left;
            if (dis >= duration) {
                List<Integer> list = new ArrayList<>();
                list.add(left);
                list.add(left + duration);

                return list;
            }
            // 先结束的移动
            if (slot1[1] < slot2[1]) {
                i1++;
            } else {
                i2++;
            }

        }
        return new ArrayList<>();
    }
}
