package com.potato.study.leetcodecn.other.Interview.p0008.p0002;


import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * 面试题 08.02. 迷路的机器人
 *
 * 设想有个机器人坐在一个网格的左上角，网格 r 行 c 列。机器人只能向下或向右移动，但不能走到一些被禁止的网格（有障碍物）。设计一种算法，寻找机器人从左上角移动到右下角的路径。
 *
 *
 *
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 *
 * 返回一条可行的路径，路径由经过的网格的行号和列号组成。左上角为 0 行 0 列。如果没有可行的路径，返回空数组。
 *
 * 示例 1:
 *
 * 输入:
 * [
 *   [0,0,0],
 *   [0,1,0],
 *   [0,0,0]
 * ]
 * 输出: [[0,0],[0,1],[0,2],[1,2],[2,2]]
 * 解释:
 * 输入中标粗的位置即为输出表示的路径，即
 * 0行0列（左上角） -> 0行1列 -> 0行2列 -> 1行2列 -> 2行2列（右下角）
 * 说明：r 和 c 的值均不超过 100。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/robot-in-a-grid-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 08 02
    public List<List<Integer>> pathWithObstacles(int[][] obstacleGrid) {
        // 只能往下或者 往右边 找到一个路径 任意一个
        int[][] dir = new int[][] {
                {1, 0},
                {0, 1}
        };
        if (obstacleGrid[0][0] == 1) {
            return new ArrayList<>();
        }
        // 网格中的障碍物和空位置分别用 1 和 0 来表示。
        List<List<Integer>> currentPath = new ArrayList<>();
        return dfs(obstacleGrid, 0, 0, dir, currentPath);
    }


    /**
     * 到达 坐标 ij 的时候 的路径
     * @param obstacleGrid
     * @param i
     * @param j
     * @param dir
     * @param currentPath
     * @return
     */
    private List<List<Integer>> dfs(int[][] obstacleGrid, int i, int j, int[][] dir, List<List<Integer>> currentPath) {
        // 如果当前 已经到了 末尾
        int r = obstacleGrid.length;
        int c = obstacleGrid[0].length;
        // 当前点已经到了末尾
        List<Integer> list = new ArrayList<>();
        list.add(i);
        list.add(j);
        if (i == r-1 && j == c-1) {
            List<List<Integer>> result = new ArrayList<>(currentPath);
            result.add(list);
            return result;
        }
        // 往下方或者往右边走
        currentPath.add(list);
        for (int k = 0; k < dir.length; k++) {
            int di = i + dir[k][0];
            int dj = j + dir[k][1];
            // 坐标合法性
            if (di < 0 || di >= r || dj < 0 || dj >= c) {
                continue;
            }
            // 障碍
            if (obstacleGrid[di][dj] == 1) {
                continue;
            }
            List<List<Integer>> res = dfs(obstacleGrid, di, dj, dir, currentPath);
            if (res.size() != 0) {
                return res;
            }
        }
        currentPath.remove(currentPath.size() - 1);
        return new ArrayList<>();
    }
}
