package com.potato.study.leetcodecn.p01559.t001;

import org.junit.Assert;

import com.potato.study.leetcode.util.LeetcodeInputUtils;

/**
 * 1559. 二维网格图中探测环
 *
 * 给你一个二维字符网格数组 grid ，大小为 m x n ，你需要检查 grid 中是否存在 相同值 形成的环。

 一个环是一条开始和结束于同一个格子的长度 大于等于 4 的路径。对于一个给定的格子，你可以移动到它上、下、左、右四个方向相邻的格子之一，可以移动的前提是这两个格子有 相同的值 。

 同时，你也不能回到上一次移动时所在的格子。比方说，环  (1, 1) -> (1, 2) -> (1, 1) 是不合法的，因为从 (1, 2) 移动到 (1, 1) 回到了上一次移动时的格子。

 如果 grid 中有相同值形成的环，请你返回 true ，否则返回 false 。

  

 示例 1：



 输入：grid = [["a","a","a","a"],["a","b","b","a"],["a","b","b","a"],["a","a","a","a"]]
 输出：true
 解释：如下图所示，有 2 个用不同颜色标出来的环：

 示例 2：



 输入：grid = [["c","c","c","a"],["c","d","c","c"],["c","c","e","c"],["f","c","c","c"]]
 输出：true
 解释：如下图所示，只有高亮所示的一个合法环：

 示例 3：



 输入：grid = [["a","b","b"],["b","z","b"],["b","b","a"]]
 输出：false
  

 提示：

 m == grid.length
 n == grid[i].length
 1 <= m <= 500
 1 <= n <= 500
 grid 只包含小写英文字母。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/detect-cycles-in-2d-grid
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 1559
     * @param grid
     * @return
     */
    public boolean containsCycle(char[][] grid) {
        UnionFind unionFind = new UnionFind(grid.length * grid[0].length + 1);
        // 遍历 grid 判断 上面和左边是否相同， 形同的话 利用一个 并查 判断是否存在环
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // 计算当前坐标
                int current = i * grid[0].length + j;
                if (j > 0 && grid[i][j] == grid[i][j-1]) {
                    int leftIndex = current - 1;
                    boolean isConnected = unionFind.union(current, leftIndex);
                    if (isConnected) {
                        return true;
                    }
                }
                // 左边坐标和 上面坐标
                if (i > 0 && grid[i][j] == grid[i-1][j]) {
                    int upIndex = current - grid[0].length;
                    boolean isConnected = unionFind.union(current, upIndex);
                    if (isConnected) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    class UnionFind {

        private int[] parent;

        public UnionFind(int n) {
            this.parent = new int[n+1];
            for (int i = 0; i < n + 1; i++) {
                parent[i] = i;
            }
        }

        public boolean union(int target1, int target2) {
            int p1 = find(target1);
            int p2 = find(target2);
            if (p1 == p2) {
                return true;
            }
            parent[p1] = p2;
            return false;
        }

        public int find(int target) {
            while (target != parent[target]) {
                target = parent[target];
            }
            return target;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "[[\"c\",\"c\",\"c\",\"a\"],[\"c\",\"d\",\"c\",\"c\"],[\"c\",\"c\",\"e\",\"c\"],[\"f\",\"c\","
                + "\"c\",\"c\"]]";
        char[][] chars = LeetcodeInputUtils.inputString2CharArrayTwoDimensional(input);
        boolean b = solution.containsCycle(chars);
        System.out.println(b);
        Assert.assertEquals(true, b);
    }
}
