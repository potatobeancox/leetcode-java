package com.potato.study.leetcodecn.p00305.t001;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;

import com.potato.study.leetcode.util.LeetcodeInputUtils;

/**
 * 305. 岛屿数量 II
 *
 * 给你一个大小为 m x n 的二进制网格 grid 。网格表示一个地图，其中，0 表示水，1 表示陆地。最初，grid 中的所有单元格都是水单元格（即，所有单元格都是 0）。
 *
 * 可以通过执行 addLand 操作，将某个位置的水转换成陆地。给你一个数组 positions ，其中 positions[i] = [ri, ci] 是要执行第 i 次操作的位置 (ri, ci) 。
 *
 * 返回一个整数数组 answer ，其中 answer[i] 是将单元格 (ri, ci) 转换为陆地后，地图中岛屿的数量。
 *
 * 岛屿 的定义是被「水」包围的「陆地」，通过水平方向或者垂直方向上相邻的陆地连接而成。你可以假设地图网格的四边均被无边无际的「水」所包围。
 *
 *  
 * 示例 1：
 *
 *
 * 输入：m = 3, n = 3, positions = [[0,0],[0,1],[1,2],[2,1]]
 * 输出：[1,1,2,3]
 * 解释：
 * 起初，二维网格 grid 被全部注入「水」。（0 代表「水」，1 代表「陆地」）
 * - 操作 #1：addLand(0, 0) 将 grid[0][0] 的水变为陆地。此时存在 1 个岛屿。
 * - 操作 #2：addLand(0, 1) 将 grid[0][1] 的水变为陆地。此时存在 1 个岛屿。
 * - 操作 #3：addLand(1, 2) 将 grid[1][2] 的水变为陆地。此时存在 2 个岛屿。
 * - 操作 #4：addLand(2, 1) 将 grid[2][1] 的水变为陆地。此时存在 3 个岛屿。
 * 示例 2：
 *
 * 输入：m = 1, n = 1, positions = [[0,0]]
 * 输出：[1]
 *  
 *
 * 提示：
 *
 * 1 <= m, n, positions.length <= 104
 * 1 <= m * n <= 104
 * positions[i].length == 2
 * 0 <= ri < m
 * 0 <= ci < n
 *  
 *
 * 进阶：你可以设计一个时间复杂度 O(k log(mn)) 的算法解决此问题吗？（其中 k == positions.length）
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/number-of-islands-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 并查集
     * https://leetcode.cn/problems/number-of-islands-ii/solution/dao-yu-shu-liang-ii-by-leetcode/
     * @param m
     * @param n
     * @param positions
     * @return
     */
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        int total = m * n;
        UnionFind unionFind = new UnionFind(total);
        Set<Integer> islandSet = new HashSet<>();
        // 遍历 positions 每次增加是判断 4个方向 是否可以连通 如果可以连通 进行 union 操作
        int[][] dir = new int[][] {
                {-1, 0},
                {1, 0},
                {0, -1},
                {0, 1}
        };
        List<Integer> isLandCountList = new ArrayList<>();
        for (int i = 0; i < positions.length; i++) {
            int x = positions[i][0];
            int y = positions[i][1];

            int key = x * n + y;
            // 出现重复的点
            if (islandSet.contains(key)) {
                isLandCountList.add(unionFind.getAreaCount());
                continue;
            }
            // 添加当前点作为岛屿
            islandSet.add(key);
            // 增加岛屿个数
            unionFind.addAreaCount();

            // 连接的四个方向
            for (int j = 0; j < 4; j++) {
                int dx = x + dir[j][0];
                int dy = y + dir[j][1];
                // 超出边界
                if (dx < 0 || dx >= m || dy < 0 || dy >= n) {
                    continue;
                }
                // 看下周边是否已经存在岛屿
                int nextKey = dx * n + dy;
                if (!islandSet.contains(nextKey)) {
                    continue;
                }
                // 存在岛屿
                unionFind.union(key, nextKey);
            }
            isLandCountList.add(unionFind.getAreaCount());
        }
        // 得到并查集 连通分量 个数 就是目前岛屿个数
        return isLandCountList;
    }


    class UnionFind {

        private int[] parent;

        private int areaCount;

        public int getAreaCount() {
            return this.areaCount;
        }

        public void addAreaCount() {
            areaCount++;
        }

        public UnionFind(int count) {
            this.parent = new int[count];
            for (int i = 0; i < count; i++) {
                parent[i] = i;
            }
        }

        public void union(int target1, int target2) {
            int p1 = find(target1);
            int p2 = find(target2);

            if (p1 == p2) {
                return;
            }
            areaCount--;
            parent[p2] = p1;
        }

        public int find(int target) {
            while (parent[target] != target) {
                target = parent[target];
            }
            return target;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int m = 3;
        int n = 3;
        String input = "[[0,1],[1,2],[2,1],[1,0],[0,2],[0,0],[1,1]]";
        int[][] positions = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        List<Integer> list = solution.numIslands2(m, n, positions);
        // [1,2,3,4,3,2,1]
        System.out.println(list);


        m = 3;
        n = 3;
        input = "[[0,0],[0,1],[1,2],[1,2]]";
        positions = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        list = solution.numIslands2(m, n, positions);
        // [1,1,2,2]
        System.out.println(list);
    }
}
