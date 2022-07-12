package com.potato.study.leetcodecn.p00815.t001;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.junit.Assert;

import com.potato.study.leetcode.util.LeetcodeInputUtils;

/**
 * 815. 公交路线
 *
 * 给你一个数组 routes ，表示一系列公交线路，其中每个 routes[i] 表示一条公交线路，第 i 辆公交车将会在上面循环行驶。
 *
 * 例如，路线 routes[0] = [1, 5, 7] 表示第 0 辆公交车会一直按序列 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... 这样的车站路线行驶。
 * 现在从 source 车站出发（初始时不在公交车上），要前往 target 车站。 期间仅可乘坐公交车。
 *
 * 求出 最少乘坐的公交车数量 。如果不可能到达终点车站，返回 -1 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：routes = [[1,2,7],[3,6,7]], source = 1, target = 6
 * 输出：2
 * 解释：最优策略是先乘坐第一辆公交车到达车站 7 , 然后换乘第二辆公交车到车站 6 。
 * 示例 2：
 *
 * 输入：routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
 * 输出：-1
 *  
 *
 * 提示：
 *
 * 1 <= routes.length <= 500.
 * 1 <= routes[i].length <= 105
 * routes[i] 中的所有值 互不相同
 * sum(routes[i].length) <= 105
 * 0 <= routes[i][j] < 106
 * 0 <= source, target < 106
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/bus-routes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int numBusesToDestination(int[][] routes, int source, int target) {
        // 815
        if (source == target) {
            return 0;
        }
        // 每个 bus 按照 从0- routes.length 顺序编号 bool 形 二维数组 ij 表示 是否可以 连接 （换乘到）
        int n = routes.length;
        // map int list 存某个站点对应可以乘坐的公交list
        Map<Integer, List<Integer>> stationToBusMap = new HashMap<>();
        boolean[][] canTransfer = new boolean[n][n];
        // 便利 routes 两个车站有交叉点 说明可以进行换成
        for (int i = 0; i < routes.length; i++) {
            // 沿途的站点
            for (int j = 0; j < routes[i].length; j++) {
                int station = routes[i][j];
                // 这个站点还有啥车
                List<Integer> busList = stationToBusMap.getOrDefault(station, new ArrayList<>());
                for (int bus : busList) {
                    // 当前车 i 和 bus 是可以换成的
                    canTransfer[i][bus] = true;
                    canTransfer[bus][i] = true;
                }
                // 当前这个车也在这个站点 停靠
                busList.add(i);
                stationToBusMap.put(station, busList);
            }
        }
        // bfs 从每个开始位置开始查找 使用 int【】 cost 记录 坐上每辆车 做了多少辆车的最小次数
        int[] changeTime  = new int[n];
        Arrays.fill(changeTime, -1);
        Queue<Integer> busQueue = new LinkedList<>();
        List<Integer> startBusList = stationToBusMap.getOrDefault(source, new ArrayList<>());
        busQueue.addAll(startBusList);
        for (int startBus : startBusList) {
            changeTime[startBus] = 1;
        }
        // bfs
        while (!busQueue.isEmpty()) {
            int bus = busQueue.poll();
            for (int i = 0; i < n; i++) {
                // 可以换成 并还没有换乘过这个车
                if (canTransfer[bus][i] && changeTime[i] == -1) {
                    changeTime[i] = changeTime[bus] + 1;
                    busQueue.add(i);
                }
            }
        }
        // 最终遍历 cost 生成最少次数
        List<Integer> finalBusList = stationToBusMap.get(target);
        if (null == finalBusList) {
            return -1;
        }
        int minCost = Integer.MAX_VALUE;
        for (int bus : finalBusList) {
            if (changeTime[bus] > 0) {
                minCost = Math.min(changeTime[bus], minCost);
            }
        }
        return minCost == Integer.MAX_VALUE ? -1 : minCost;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "[[1,2,7],[3,6,7]]";
        int source = 1;
        int target = 6;
        int[][] ints = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        int i = solution.numBusesToDestination(ints, source, target);
        System.out.println(i);
        Assert.assertEquals(2, i);
    }
}
