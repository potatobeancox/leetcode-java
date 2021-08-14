package com.potato.study.leetcodecn.p01779.t001;

import com.google.common.collect.Lists;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 1779. 找到最近的有相同 X 或 Y 坐标的点
 *
 * 给你两个整数 x 和 y ，表示你在一个笛卡尔坐标系下的 (x, y) 处。同时，在同一个坐标系下给你一个数组 points ，其中 points[i] = [ai, bi] 表示在 (ai, bi) 处有一个点。当一个点与你所在的位置有相同的 x 坐标或者相同的 y 坐标时，我们称这个点是 有效的 。

 请返回距离你当前位置 曼哈顿距离 最近的 有效 点的下标（下标从 0 开始）。如果有多个最近的有效点，请返回下标 最小 的一个。如果没有有效点，请返回 -1 。

 两个点 (x1, y1) 和 (x2, y2) 之间的 曼哈顿距离 为 abs(x1 - x2) + abs(y1 - y2) 。

  

 示例 1：

 输入：x = 3, y = 4, points = [[1,2],[3,1],[2,4],[2,3],[4,4]]
 输出：2
 解释：所有点中，[3,1]，[2,4] 和 [4,4] 是有效点。有效点中，[2,4] 和 [4,4] 距离你当前位置的曼哈顿距离最小，都为 1 。[2,4] 的下标最小，所以返回 2 。
 示例 2：

 输入：x = 3, y = 4, points = [[3,4]]
 输出：0
 提示：答案可以与你当前所在位置坐标相同。
 示例 3：

 输入：x = 3, y = 4, points = [[2,3]]
 输出：-1
 解释：没有有效点。
  

 提示：

 1 <= points.length <= 104
 points[i].length == 2
 1 <= x, y, ai, bi <= 104

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/find-nearest-point-that-has-the-same-x-or-y-coordinate
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 直接用一个优先级队列就行
     * @param x
     * @param y
     * @param points
     * @return
     */
    public int nearestValidPoint(int x, int y, int[][] points) {
        // 0是距离 1是index
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int compare = Integer.compare(o1[0], o2[0]);
                if (compare != 0) {
                    return compare;
                }
                return Integer.compare(o1[1], o2[1]);
            }
        });
        for (int i = 0; i < points.length; i++) {
            if (points[i][0] == x || points[i][1] == y) {
                int distance = Math.abs(points[i][0] - x) + Math.abs(points[i][1] - y);
                priorityQueue.add(new int[] {distance, i});
            }
        }

        if (!priorityQueue.isEmpty()) {
            return priorityQueue.peek()[1];
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int x = 3;
        int y = 4;
        int[][] points = new int[][] {
                {1,2},{3,1},{2,4},{2,3},{4,4}
        };
        int i = solution.nearestValidPoint(x, y, points);
        System.out.println(i);
        Assert.assertEquals(2, i);
    }
}
