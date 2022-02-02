package com.potato.study.leetcodecn.p00934.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 934. 最短的桥
 *
 * 在给定的二维二进制数组 A 中，存在两座岛。（岛是由四面相连的 1 形成的一个最大组。）

 现在，我们可以将 0 变为 1，以使两座岛连接起来，变成一座岛。

 返回必须翻转的 0 的最小数目。（可以保证答案至少是 1 。）

  

 示例 1：

 输入：A = [[0,1],[1,0]]
 输出：1
 示例 2：

 输入：A = [[0,1,0],[0,0,0],[0,0,1]]
 输出：2
 示例 3：

 输入：A = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
 输出：1
  

 提示：

 2 <= A.length == A[0].length <= 100
 A[i][j] == 0 或 A[i][j] == 1

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/shortest-bridge
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int shortestBridge(int[][] grid) {
        int[][] direction = new int[][] {
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1}
        };
        // dfs 对其中一个岛进行标记 标记过程中 将边界放入queue中 并置换为2
        Set<String> set = new HashSet<>();
        all:
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    dfs(i, j, grid, set);
                    break all;
                }
            }
        }
        Queue<int[]> queue = new LinkedList<>();
        Set<String> visitSet = new HashSet<>();
        for (String position : set) {
            String[] split = position.split("_");
            queue.add(new int[]{Integer.parseInt(split[0]), Integer.parseInt(split[1])});
            visitSet.add(position);
        }
        // bfs 按照层找 直到找到2 返回
        int pathLength = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] poll = queue.poll();
                for (int k = 0; k < 4; k++) {
                    int di = poll[0] + direction[k][0];
                    int dj = poll[1] + direction[k][1];
                    // 是否出街
                    if (di < 0 || di >= grid.length
                            || dj < 0 || dj >= grid[0].length) {
                        continue;
                    }
                    // 找到边界
                    if (grid[di][dj] == 1) {
                        return pathLength;
                    } else if (grid[di][dj] == 2) {
                        continue;
                    }
                    // 邻接点 0 放入
                    int[] next = new int[]{di, dj};
                    if (!visitSet.contains(di + "_" + dj)) {
                        queue.add(next);
                        visitSet.add(di + "_" + dj);
                    }
                }

            }
            pathLength++;
        }

        return pathLength;
    }

    /**
     * dfs 找到 ij 相连的每个点 并修改成2
     * @param i
     * @param j
     * @param grid
     */
    private void dfs(int i, int j, int[][] grid, Set<String> set) {
        int[][] direction = new int[][] {
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1}
        };
        if (grid[i][j] == 0 || grid[i][j] == 2) {
            return;
        }
        grid[i][j] = 2;
        for (int k = 0; k < 4; k++) {
            int di = i + direction[k][0];
            int dj = j + direction[k][1];
            // 是否出街
            if (di < 0 || di >= grid.length
                    || dj < 0 || dj >= grid[0].length) {
                set.add(i + "_" + j);
                continue;
            }
            if (grid[di][dj] != 1) {
                if (grid[di][dj] == 0) {
                    set.add(i + "_" + j);
                }
                continue;
            }
            dfs(di, dj, grid, set);
        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "[[0,1],[1,0]]";
        int[][] arrayTwoDimensional = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        int i = solution.shortestBridge(arrayTwoDimensional);
        System.out.println(i);
        Assert.assertEquals(1, i);


        input = "[[0,1,0],[0,0,0],[0,0,1]]";
        arrayTwoDimensional = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        i = solution.shortestBridge(arrayTwoDimensional);
        System.out.println(i);
        Assert.assertEquals(2, i);


        input = "[[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]";
        arrayTwoDimensional = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        i = solution.shortestBridge(arrayTwoDimensional);
        System.out.println(i);
        Assert.assertEquals(1, i);
    }
}
