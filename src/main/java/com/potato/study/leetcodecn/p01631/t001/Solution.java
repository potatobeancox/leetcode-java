package com.potato.study.leetcodecn.p01631.t001;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.junit.Assert;

import com.potato.study.leetcode.util.LeetcodeInputUtils;

/**
 * 1631. 最小体力消耗路径
 *
 * 你准备参加一场远足活动。给你一个二维 rows x columns 的地图 heights ，其中 heights[row][col] 表示格子 (row, col) 的高度。一开始你在最左上角的格子 (0, 0)
 *  ，且你希望去最右下角的格子 (rows-1, columns-1) （注意下标从 0 开始编号）。你每次可以往 上，下，左，右 四个方向之一移动，你想要找到耗费 体力 最小的一条路径。
 *
 * 一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。
 *
 * 请你返回从左上角走到右下角的最小 体力消耗值 。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：heights = [[1,2,2],[3,8,2],[5,3,5]]
 * 输出：2
 * 解释：路径 [1,3,5,3,5] 连续格子的差值绝对值最大为 2 。
 * 这条路径比路径 [1,2,2,2,5] 更优，因为另一条路径差值最大值为 3 。
 * 示例 2：
 *
 *
 *
 * 输入：heights = [[1,2,3],[3,8,4],[5,3,5]]
 * 输出：1
 * 解释：路径 [1,2,3,4,5] 的相邻格子差值绝对值最大为 1 ，比路径 [1,3,5,3,5] 更优。
 * 示例 3：
 *
 *
 * 输入：heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
 * 输出：0
 * 解释：上图所示路径不需要消耗任何体力。
 *  
 *
 * 提示：
 *
 * rows == heights.length
 * columns == heights[i].length
 * 1 <= rows, columns <= 100
 * 1 <= heights[i][j] <= 106
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-with-minimum-effort
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 1631
    public int minimumEffortPath(int[][] heights) {
        // bfs 二分 left = 0 right = 最大值
        int right = heights[0][0];
        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < heights[0].length; j++) {
                right = Math.max(right, heights[i][j]);
            }
        }
        int left = 0;
        int res = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            boolean canReach = canReach(heights, mid);
            if (canReach) {
                res = mid;
                right = mid - 1;
            } else {
                // 到不了 给小了
                left = mid + 1;
            }
        }
        return res;
    }


    /**
     * 话费 cost 是否可以 到达最终点
     * bfs
     * @param heights
     * @param cost
     * @return
     */
    private boolean canReach(int[][] heights, int cost) {
        int[][] direction = new int[][] {
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1}
        };
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {0, 0});
        int n = heights[0].length;
        Set<Integer> visited = new HashSet<>();
        visited.add(0 * n + 0);
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            for (int i = 0; i < direction.length; i++) {
                int di = poll[0] + direction[i][0];
                int dj = poll[1] + direction[i][1];
                if (di < 0 || di >= heights.length || dj < 0 || dj >= heights[0].length) {
                    continue;
                }
                // 判断是否访问过
                if (visited.contains(di * n + dj)) {
                    continue;
                }
                // 判断 差 是否小于等于 cost
                int diff = Math.abs(heights[di][dj] - heights[poll[0]][poll[1]]);
                if (diff > cost) {
                    continue;
                }
                if (di == heights.length - 1 && dj == heights[0].length - 1) {
                    return true;
                }
                queue.add(new int[]{
                        di, dj
                });
                visited.add(di * n + dj);
            }
        }
        return false;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String str = "[[1,2,2],[3,8,2],[5,3,5]]";
        int[][] heights = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(str);
        int i = solution.minimumEffortPath(heights);
        System.out.println(i);
        Assert.assertEquals(2, i);


        str = "[[3]]";
        heights = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(str);
        i = solution.minimumEffortPath(heights);
        System.out.println(i);
        Assert.assertEquals(0, i);


        str = "[[1,10,6,7,9,10,4,9]]";
        heights = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(str);
        i = solution.minimumEffortPath(heights);
        System.out.println(i);
        Assert.assertEquals(9, i);
    }

}
