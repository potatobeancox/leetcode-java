package com.potato.study.leetcodecn.p02662.t001;


import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 *
 * 2662. 前往目标的最小代价
 *
 * 给你一个数组 start ，其中 start = [startX, startY] 表示你的初始位置位于二维空间上的 (startX, startY) 。另给你一个数组 target ，其中 target = [targetX, targetY] 表示你的目标位置 (targetX, targetY) 。
 *
 * 从位置 (x1, y1) 到空间中任一其他位置 (x2, y2) 的代价是 |x2 - x1| + |y2 - y1| 。
 *
 * 给你一个二维数组 specialRoads ，表示空间中存在的一些特殊路径。其中 specialRoads[i] = [x1i, y1i, x2i, y2i, costi] 表示第 i 条特殊路径可以从 (x1i, y1i) 到 (x2i, y2i) ，但成本等于 costi 。你可以使用每条特殊路径任意次数。
 *
 * 返回从 (startX, startY) 到 (targetX, targetY) 所需的最小代价。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：start = [1,1], target = [4,5], specialRoads = [[1,2,3,3,2],[3,4,4,5,1]]
 * 输出：5
 * 解释：从 (1,1) 到 (4,5) 的最优路径如下：
 * - (1,1) -> (1,2) ，移动的代价是 |1 - 1| + |2 - 1| = 1 。
 * - (1,2) -> (3,3) ，移动使用第一条特殊路径，代价是 2 。
 * - (3,3) -> (3,4) ，移动的代价是 |3 - 3| + |4 - 3| = 1.
 * - (3,4) -> (4,5) ，移动使用第二条特殊路径，代价是 1 。
 * 总代价是 1 + 2 + 1 + 1 = 5 。
 * 可以证明无法以小于 5 的代价完成从 (1,1) 到 (4,5) 。
 * 示例 2：
 *
 * 输入：start = [3,2], target = [5,7], specialRoads = [[3,2,3,4,4],[3,3,5,5,5],[3,4,5,6,6]]
 * 输出：7
 * 解释：最优路径是不使用任何特殊路径，直接以 |5 - 3| + |7 - 2| = 7 的代价从初始位置到达目标位置。
 *  
 *
 * 提示：
 *
 * start.length == target.length == 2
 * 1 <= startX <= targetX <= 105
 * 1 <= startY <= targetY <= 105
 * 1 <= specialRoads.length <= 200
 * specialRoads[i].length == 5
 * startX <= x1i, x2i <= targetX
 * startY <= y1i, y2i <= targetY
 * 1 <= costi <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-cost-of-a-path-with-special-roads
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://blog.csdn.net/m0_50913327/article/details/125099211
     * dijkstra 算法
     * 两个集合 一个是已经访问过的节点集合 初始化有原点，另一个集合是s 当前要考虑的点的集合 初始化为 原点相邻的点
     * 一个数组 记录从原点开始 到当前点i 的最短距离 原点到原点的距离为 0 ，s中的点的记录 应该都是有的 没有的就是 最大值 就是无法临接
     *
     * 过程中每次都找到s中最小的点 更新临接的最小值
     *
     * 开始的时候 对于 s中
     *
     * @param start
     * @param target
     * @param specialRoads
     * @return
     */
    public int minimumCost(int[] start, int[] target, int[][] specialRoads) {
        // start 开始位置
        // 正常距离 为 从位置 (x1, y1) 到空间中任一其他位置 (x2, y2) 的代价是 |x2 - x1| + |y2 - y1| 。
        // specialRoads 小路 可能 会近距离一些 每次 找到 从 0
        // 用一个优先级队列 记录从 start 到某个点最短的距离 初始化 记录起点位置
        // info 表示从起点到点p的最小距离
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(info -> info[2]));
        // 起始点加入距离中
        priorityQueue.add(new int[] {start[0], start[1], 0});
        // key是到达点 target[0]+'_' + target[1]的字符串
        Map<String, Integer> distanceMap = new HashMap<>();
        distanceMap.put(getDistanceKey(start), 0);
        // 对优先级队列 中的点进行输出，当前点就是要进行计算的点 遍历 specialRoads 计算 从开始点到 当前点p+ specialRoads开始点 + 路径长度  是否比 目前从开始点到
        int minDistance = getDistance(start, target);
        while (!priorityQueue.isEmpty()) {
            int[] poll = priorityQueue.poll();
            // 从起点到到达的点 也就是当前所在的点
            int[] targetPoint = new int[] {
                    poll[0], poll[1]
            };
            int targetDistance = poll[2];
            // 计算一下 从起点到 targetPoint 加上 targetPoint 到specialRoads起点 距离 加上 specialRoads距离 等同于从起点到specialRoads终点的距离可能值 是不是最小
            for (int[] specialRoadPoints : specialRoads) {
                int[] specialStartPoint = new int[] {specialRoadPoints[0], specialRoadPoints[1]};
                int[] specialEndPoint = new int[] {specialRoadPoints[2], specialRoadPoints[3]};
                int specialRoadDistance = specialRoadPoints[4];
                int tempDistance = targetDistance + getDistance(targetPoint, specialStartPoint) + specialRoadDistance;
                // 当前 tempDistance 是到 specialEndPoint 最短的路径
                String specialEndPointKey = getDistanceKey(specialEndPoint);
                if (!distanceMap.containsKey(specialEndPointKey) || tempDistance < distanceMap.get(specialEndPointKey)) {
                    distanceMap.put(specialEndPointKey, tempDistance);
                    priorityQueue.add(new int[] {specialEndPoint[0], specialEndPoint[1], tempDistance});
                    // 加上从 specialEndPointKey 终点到 target的距离
                    minDistance = Math.min(minDistance, tempDistance + getDistance(specialEndPoint, target));
                }
            }
        }
        return minDistance;
    }

    /**
     * 获取 from 到 to 的 曼哈顿距离
     * @param from
     * @param to
     * @return
     */
    private int getDistance(int[] from, int[] to) {
        return Math.abs(from[0] - to[0]) + Math.abs(from[1] - to[1]);
    }

    private String getDistanceKey(int[] p) {
        return p[0] + "_" + p[1];
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] start = new int[] {1,1};
        int[] target = new int[] {5,10};
        int[][] specialRoads = LeetcodeInputUtils.inputString2IntArrayTwoDimensional("[[3,4,5,2,5],[4,5,3,8,3],[3,2,5,3,1]]");
        int i = solution.minimumCost(start, target, specialRoads);
        System.out.println(i);
        Assert.assertEquals(11, i);
    }






}
