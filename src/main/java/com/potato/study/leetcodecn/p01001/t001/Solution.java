package com.potato.study.leetcodecn.p01001.t001;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 1001. 网格照明
 *
 * 在大小为 n x n 的网格 grid 上，每个单元格都有一盏灯，最初灯都处于 关闭 状态。
 *
 * 给你一个由灯的位置组成的二维数组 lamps ，其中 lamps[i] = [rowi, coli] 表示 打开 位于 grid[rowi][coli] 的灯。即便同一盏灯可能在 lamps 中多次列出，不会影响这盏灯处于 打开 状态。
 *
 * 当一盏灯处于打开状态，它将会照亮 自身所在单元格 以及同一 行 、同一 列 和两条 对角线 上的 所有其他单元格 。
 *
 * 另给你一个二维数组 queries ，其中 queries[j] = [rowj, colj] 。对于第 j 个查询，如果单元格 [rowj, colj] 是被照亮的，则查询结果为 1 ，否则为 0 。在第 j 次查询之后 [按照查询的顺序] ，关闭 位于单元格 grid[rowj][colj] 上及相邻 8 个方向上（与单元格 grid[rowi][coli] 共享角或边）的任何灯。
 *
 * 返回一个整数数组 ans 作为答案， ans[j] 应等于第 j 次查询 queries[j] 的结果，1 表示照亮，0 表示未照亮。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：n = 5, lamps = [[0,0],[4,4]], queries = [[1,1],[1,0]]
 * 输出：[1,0]
 * 解释：最初所有灯都是关闭的。在执行查询之前，打开位于 [0, 0] 和 [4, 4] 的灯。第 0 次查询检查 grid[1][1] 是否被照亮（蓝色方框）。该单元格被照亮，所以 ans[0] = 1 。然后，关闭红色方框中的所有灯。
 *
 * 第 1 次查询检查 grid[1][0] 是否被照亮（蓝色方框）。该单元格没有被照亮，所以 ans[1] = 0 。然后，关闭红色矩形中的所有灯。
 *
 * 示例 2：
 *
 * 输入：n = 5, lamps = [[0,0],[4,4]], queries = [[1,1],[1,1]]
 * 输出：[1,1]
 * 示例 3：
 *
 * 输入：n = 5, lamps = [[0,0],[0,4]], queries = [[0,4],[0,1],[1,4]]
 * 输出：[1,1,0]
 *  
 *
 * 提示：
 *
 * 1 <= n <= 109
 * 0 <= lamps.length <= 20000
 * 0 <= queries.length <= 20000
 * lamps[i].length == 2
 * 0 <= rowi, coli < n
 * queries[j].length == 2
 * 0 <= rowj, colj < n
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/grid-illumination
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 1001
    public int[] gridIllumination(int n, int[][] lamps, int[][] queries) {
        // 遍历 lamps 用map记录 行列 正对角线 负对角线 的照亮情况 过程中用 set 记录灯的位置 set 是亮的灯
        Map<Integer, Integer> lineCountMap = new HashMap<>();
        Map<Integer, Integer> columnCountMap = new HashMap<>();
        Map<Integer, Integer> diagonalCountMap = new HashMap<>();
        Map<Integer, Integer> negativeDiagonalCountMap = new HashMap<>();

        Set<String> lampSet = new HashSet<>();

        for (int[] lamp : lamps) {
            int i = lamp[0];
            int j = lamp[1];

            // 四个方向增加
            lineCountMap.put(i, lineCountMap.getOrDefault(i, 0) + 1);
            columnCountMap.put(j, columnCountMap.getOrDefault(j, 0) + 1);
            diagonalCountMap.put(i+j, diagonalCountMap.getOrDefault(i+j, 0) + 1);
            negativeDiagonalCountMap.put(i-j, negativeDiagonalCountMap.getOrDefault(i-j, 0) + 1);

            lampSet.add(i + "_" + j);
        }
        // 返回一个整数数组 ans 作为答案， ans[j] 应等于第 j 次查询 queries[j] 的结果，1 表示照亮，0 表示未照亮。
        int[] res = new int[queries.length];
        // 遍历 queries 每次获取 当前坐标ij对于 8个方向的 灯依次计算出坐标 根据计算的坐标 看看 有没有灯 并修改
        int[][] dir = new int[][] {
                {-1, 0},
                {1, 0},
                {0, 1},
                {0, -1},
                {1, 1},
                {1, -1},
                {-1, 1},
                {-1, -1},
                {0, 0}
        };
        for (int i = 0; i < queries.length; i++) {
            int x = queries[i][0];
            int y = queries[i][1];
            // 判断当前点 是不是明亮
            if (lineCountMap.containsKey(x) || columnCountMap.containsKey(y)
                    || diagonalCountMap.containsKey(x+y) || negativeDiagonalCountMap.containsKey(x-y)) {
                res[i] = 1;
            }
            // 关灯
            for (int j = 0; j < 9; j++) {
                int di = x + dir[j][0];
                int dj = y + dir[j][0];

                if (di < 0 || di >= n || dj < 0 || dj >= n) {
                    continue;
                }
                String key = di + "_" + dj;
                if (lampSet.contains(key)) {
                    lampSet.remove(key);
                }
                removeIfZero(lineCountMap, di);
                removeIfZero(columnCountMap, dj);
                removeIfZero(diagonalCountMap, di + dj);
                removeIfZero(negativeDiagonalCountMap, di - dj);
            }
        }
        return res;
    }

    /**
     * 如果 num 只有一个 remove ，否则--
     * @param map
     * @param num
     */
    private void removeIfZero(Map<Integer, Integer> map, int num) {
        Integer lineCount = map.get(num);
        if (lineCount == 1) {
            map.remove(num);
        } else {
            map.put(num, lineCount - 1);
        }
    }


}
