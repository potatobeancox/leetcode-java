package com.potato.study.leetcodecn.p01057.t001;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 1057. 校园自行车分配
 *
 * 在 X-Y 平面上表示的校园中，有 n 名工人和 m 辆自行车，其中 n <= m。
 *
 * 给定一个长度为 n 的数组 workers ，其中 worker [i] = [xi, yi] 表示第 i 个工人的位置。你也得到一个长度为 m 的自行车数组 bikers ，其中 bikes[j] = [xj, yj] 是第 j 辆自行车的位置。所有给定的位置都是 唯一 的。
 *
 * 我们需要为每位工人分配一辆自行车。在所有可用的自行车和工人中，我们选取彼此之间 曼哈顿距离 最短的工人自行车对 (workeri, bikej) ，并将其中的自行车分配給工人。
 *
 * 如果有多个 (workeri, bikej) 对之间的 曼哈顿距离 相同，那么我们选择 工人索引最小 的那对。类似地，如果有多种不同的分配方法，则选择 自行车索引最小 的一对。不断重复这一过程，直到所有工人都分配到自行车为止。
 *
 * 返回长度为 n 的向量 answer，其中 answer[i] 是第 i 位工人分配到的自行车的索引（从 0 开始）。
 *
 * 给定两点 p1 和 p2 之间的 曼哈顿距离 为 Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
 * 输出：[1,0]
 * 解释：工人 1 分配到自行车 0，因为他们最接近且不存在冲突，工人 0 分配到自行车 1 。所以输出是 [1,0]。
 * 示例 2：
 *
 *
 *
 * 输入：workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
 * 输出：[0,2,1]
 * 解释：工人 0 首先分配到自行车 0 。工人 1 和工人 2 与自行车 2 距离相同，因此工人 1 分配到自行车 2，工人 2 将分配到自行车 1 。因此输出为 [0,2,1]。
 *  
 *
 * 提示：
 *
 * n == workers.length
 * m == bikes.length
 * 1 <= n <= m <= 1000
 * workers[i].length == bikes[j].length == 2
 * 0 <= xi, yi < 1000
 * 0 <= xj, yj < 1000
 * 所有工人和自行车的位置都不相同
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/campus-bikes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int[] assignBikes(int[][] workers, int[][] bikes) {
        int n = workers.length;
        // 使用大根堆存储
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((o1, o2) -> {
            int compare = Integer.compare(o1[0], o2[0]);
            if (compare != 0) {
                return compare;
            }
            // 如果有多个 (workeri, bikej) 对之间的 曼哈顿距离 相同，那么我们选择 工人索引最小 的那对。类似地，如果有多种不同的分配方法，则选择 自行车索引最小 的一对。不断重复这一过程，直到所有工人都分配到自行车为止。
            compare = Integer.compare(o1[1], o2[1]);
            if (compare != 0) {
                return compare;
            }
            compare = Integer.compare(o1[2], o2[2]);
            return compare;
        });
        // 生成 每个 workers 和 bikes 之间的距离
        for (int i = 0; i < workers.length; i++) {
            for (int j = 0; j < bikes.length; j++) {
                int dis = getDistance(workers[i], bikes[j]);

                priorityQueue.add(new int[] {
                        dis, i, j
                });
            }
        }
        int[] res = new int[n];
        Arrays.fill(res, -1);
        // 一次从堆肿么pop 东西 往赌赢的位置上防
        Set<Integer> usedBikeSet = new HashSet<>();
        while (!priorityQueue.isEmpty()) {
            int[] poll = priorityQueue.poll();
            // bike 已经被用了 或者 已经有偶车了 continue
            int bikeIndex = poll[2];
            int workerIndex = poll[1];
            if (res[workerIndex] >= 0) {
                continue;
            }
            if (usedBikeSet.contains(bikeIndex)) {
                continue;
            }
            res[workerIndex] = bikeIndex;
            usedBikeSet.add(bikeIndex);
        }
        return res;
    }

    private int getDistance(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }
}
