package com.potato.study.leetcodecn.p00296.t001;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import org.junit.Assert;

/**
 * 296. 最佳的碰头地点
 *
 * 给你一个 m x n  的二进制网格 grid ，其中 1 表示某个朋友的家所处的位置。返回 最小的 总行走距离 。
 *
 * 总行走距离 是朋友们家到碰头地点的距离之和。
 *
 * 我们将使用 曼哈顿距离 来计算，其中 distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y| 。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入: grid = [[1,0,0,0,1],[0,0,0,0,0],[0,0,1,0,0]]
 * 输出: 6
 * 解释: 给定的三个人分别住在(0,0)，(0,4) 和 (2,2):
 *      (0,2) 是一个最佳的碰面点，其总行走距离为 2 + 2 + 2 = 6，最小，因此返回 6。
 * 示例 2:
 *
 * 输入: grid = [[1,1]]
 * 输出: 1
 *  
 *
 * 提示:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * grid[i][j] == 0 or 1.
 * grid 中 至少 有两个朋友
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/best-meeting-point
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 最终选择的点 是中位数 对应点
     * https://leetcode.cn/problems/best-meeting-point/solution/zui-jia-de-peng-tou-di-dian-by-leetcode/
     * @param grid
     * @return
     */
    public int minTotalDistance(int[][] grid) {
        // 最终计算 对于行列都中位数 距离多少
        // 分别收集 行列的 index 值
        List<Integer> lineIndexList = new ArrayList<>();
        List<Integer> columnIndexList = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    lineIndexList.add(i);
                    columnIndexList.add(j);
                }
            }
        }
        // 求中位数 需要对列的 数字list 进行排序
        Collections.sort(columnIndexList);
        int medianLineIndex = lineIndexList.get(lineIndexList.size() / 2);
        int medianColumnIndex = columnIndexList.get(columnIndexList.size() / 2);
        // 对于 每个中位数 计算 距离和
        int distance = 0;
        for (int lineIndex : lineIndexList) {
            distance += Math.abs(lineIndex - medianLineIndex);
        }
        for (int columnIndex : columnIndexList) {
            distance += Math.abs(columnIndex - medianColumnIndex);
        }
        return distance;
    }
}
