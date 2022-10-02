package com.potato.study.leetcodecn.p01066.t001;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 1066. 校园自行车分配 II
 *
 * 在由 2D 网格表示的校园里有 n 位工人（worker）和 m 辆自行车（bike），n <= m。所有工人和自行车的位置都用网格上的 2D 坐标表示。

 我们为每一位工人分配一辆专属自行车，使每个工人与其分配到的自行车之间的 曼哈顿距离 最小化。

 返回 每个工人与分配到的自行车之间的曼哈顿距离的最小可能总和 。

 p1 和 p2 之间的 曼哈顿距离 为 Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|。

  

 示例 1：



 输入：workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
 输出：6
 解释：
 自行车 0 分配给工人 0，自行车 1 分配给工人 1 。分配得到的曼哈顿距离都是 3, 所以输出为 6 。
 示例 2：



 输入：workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
 输出：4
 解释：
 先将自行车 0 分配给工人 0，再将自行车 1 分配给工人 1（或工人 2），自行车 2 给工人 2（或工人 1）。如此分配使得曼哈顿距离的总和为 4。
 示例 3:

 输入：workers = [[0,0],[1,0],[2,0],[3,0],[4,0]], bikes = [[0,999],[1,999],[2,999],[3,999],[4,999]]
 输出：4995
  

 提示：

 n == workers.length
 m == bikes.length
 1 <= n <= m <= 10
 workers[i].length == 2
 bikes[i].length == 2
 0 <= workers[i][0], workers[i][1], bikes[i][0], bikes[i][1] < 1000
 所有的工人和自行车的位置都是 不同 的。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/campus-bikes-ii
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    private int minDistance;


    public int assignBikes(int[][] workers, int[][] bikes) {
        this.minDistance = Integer.MAX_VALUE;
        // 回溯 用一个 visit 记录每个车的使用情况
        int bikeCount = bikes.length;
        boolean[] used = new boolean[bikeCount];
        int workIndex = 0;
        dfs(workers, workIndex, bikes, used, 0);
        return minDistance;
    }

    private void dfs(int[][] workers, int workIndex, int[][] bikes,
                     boolean[] used, int currentCost) {
        // 终止条件 每个人都有自行车
        if (workIndex >= workers.length) {
            this.minDistance = Math.min(minDistance, currentCost);
            return;
        }
        // 找到一个没有被使用的 自行车
        for (int i = 0; i < bikes.length; i++) {
            if (used[i]) {
                continue;
            }
            // 匹配这个车 计算cost
            int distance = distance(workers[workIndex], bikes[i]);
            // 先匹配
            used[i] = true;
            dfs(workers, workIndex + 1, bikes, used,
                    currentCost + distance);
            // 还原 模拟不匹配的时候
            used[i] = false;
        }
    }


    private int distance(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }

}
