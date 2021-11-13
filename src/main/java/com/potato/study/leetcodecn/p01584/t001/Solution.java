package com.potato.study.leetcodecn.p01584.t001;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import org.junit.Assert;

/**
 * 1584. 连接所有点的最小费用
 *
 * 给你一个points 数组，表示 2D 平面上的一些点，其中 points[i] = [xi, yi] 。
 *
 * 连接点 [xi, yi] 和点 [xj, yj] 的费用为它们之间的 曼哈顿距离 ：|xi - xj| + |yi - yj| ，其中 |val| 表示 val 的绝对值。
 *
 * 请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有 一条简单路径时，才认为所有点都已连接。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
 * 输出：20
 * 解释：
 *
 * 我们可以按照上图所示连接所有点得到最小总费用，总费用为 20 。
 * 注意到任意两个点之间只有唯一一条路径互相到达。
 * 示例 2：
 *
 * 输入：points = [[3,12],[-2,5],[-4,1]]
 * 输出：18
 * 示例 3：
 *
 * 输入：points = [[0,0],[1,1],[1,0],[-1,1]]
 * 输出：4
 * 示例 4：
 *
 * 输入：points = [[-1000000,-1000000],[1000000,1000000]]
 * 输出：4000000
 * 示例 5：
 *
 * 输入：points = [[0,0]]
 * 输出：0
 *  
 *
 * 提示：
 *
 * 1 <= points.length <= 1000
 * -106 <= xi, yi <= 106
 * 所有点 (xi, yi) 两两不同。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/min-cost-to-connect-all-points
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * 求图中节点的最小生成树
     *
     * 计算每两个点之间的长度 并将长度和开始结束点放入 优先级队列中
     *
     * 每次出队 将点放入set 中
     * 如果两个端点已经在set 中了 那么不计数
     * 否则计数 直到优先级队列为空
     * @param points
     * @return
     */
    public int minCostConnectPoints(int[][] points) {
        int len = points.length;
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        for (int i = 0; i < len; i++) {
            for (int j = i+1; j < len; j++) {
                int distance = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                priorityQueue.add(new int[]{distance, i, j});
            }
        }

        UnionFind unionFind = new UnionFind(len);
        int cost = 0;
        while (!priorityQueue.isEmpty()) {
            int[] poll = priorityQueue.poll();
            boolean isNotConnect = unionFind.union(poll[1], poll[2]);
            if (isNotConnect) {
                cost += poll[0];
            }
        }
        return cost;
    }

    class UnionFind {
        private int[] parent;
        private int n;

        public UnionFind(int n) {
            this.n = n;
            this.parent = new int[n];
            // 初始化
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }


        /**
         * 链接
         * true 是当前需要连接
         * false 是不需要
         * @param target1
         * @param target2
         * @return
         */
        public boolean union(int target1, int target2) {
            int p1 = find(target1);
            int p2 = find(target2);
            if (p1 == p2) {
                return false;
            }
            parent[p1] = p2;
            return true;
        }

        /**
         * 找到 target 的父亲
         * @param target
         * @return
         */
        public int find(int target) {
            while (parent[target] != target) {
                target = parent[target];
            }
            // 相等了 就是终点
            return target;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] points = new int[][] {
                {0,0},{2,2},{3,10},{5,2},{7,0}
        };
        int num = solution.minCostConnectPoints(points);
        System.out.println(num);
        Assert.assertEquals(20, num);

        // [[2,-3],[-17,-8],[13,8],[-17,-15]]
        points = new int[][] {
                {2,-3},{-17,-8},{13,8},{-17,-15}
        };
        num = solution.minCostConnectPoints(points);
        System.out.println(num);
        Assert.assertEquals(53, num);
    }
}
