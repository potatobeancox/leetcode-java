package com.potato.study.leetcodecn.p02406.t001;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 2406. 将区间分为最少组数
 *
 * 给你一个二维整数数组 intervals ，其中 intervals[i] = [lefti, righti] 表示 闭 区间 [lefti, righti] 。
 *
 * 你需要将 intervals 划分为一个或者多个区间 组 ，每个区间 只 属于一个组，且同一个组中任意两个区间 不相交 。
 *
 * 请你返回 最少 需要划分成多少个组。
 *
 * 如果两个区间覆盖的范围有重叠（即至少有一个公共数字），那么我们称这两个区间是 相交 的。比方说区间 [1, 5] 和 [5, 8] 相交。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：intervals = [[5,10],[6,8],[1,5],[2,3],[1,10]]
 * 输出：3
 * 解释：我们可以将区间划分为如下的区间组：
 * - 第 1 组：[1, 5] ，[6, 8] 。
 * - 第 2 组：[2, 3] ，[5, 10] 。
 * - 第 3 组：[1, 10] 。
 * 可以证明无法将区间划分为少于 3 个组。
 * 示例 2：
 *
 * 输入：intervals = [[1,3],[5,6],[8,10],[11,13]]
 * 输出：1
 * 解释：所有区间互不相交，所以我们可以把它们全部放在一个组内。
 *  
 *
 * 提示：
 *
 * 1 <= intervals.length <= 105
 * intervals[i].length == 2
 * 1 <= lefti <= righti <= 106
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/divide-intervals-into-minimum-number-of-groups
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int minGroups(int[][] intervals) {
        // 对 intervals 进行排序 按照 left 升序 right 升序排列
        Arrays.sort(intervals, (a1, a2) -> {
            int compare = Integer.compare(a1[0], a2[0]);
            if (compare != 0) {
                return compare;
            }
            return Integer.compare(a1[1], a2[1]);
        });
        // 使用小跟堆维护 每个 queue 的对尾
        PriorityQueue<Integer> rightQueue = new PriorityQueue<>();
        // 遍历 intervals 如果当前peek 完全小于 left 可以放到这个queue里边
        for (int[] interval : intervals) {
            if (!rightQueue.isEmpty() && rightQueue.peek() < interval[0]) {
                rightQueue.poll();
            }
            rightQueue.add(interval[1]);
        }
        // 返回堆大小
        return rightQueue.size();
    }

}
