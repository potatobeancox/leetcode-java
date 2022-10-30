package com.potato.study.leetcodecn.p02359.t001;

import java.util.Arrays;

/**
 * 2359. 找到离给定两个节点最近的节点
 *
 * 给你一个 n 个节点的 有向图 ，节点编号为 0 到 n - 1 ，每个节点 至多 有一条出边。

 有向图用大小为 n 下标从 0 开始的数组 edges 表示，表示节点 i 有一条有向边指向 edges[i] 。如果节点 i 没有出边，那么 edges[i] == -1 。

 同时给你两个节点 node1 和 node2 。

 请你返回一个从 node1 和 node2 都能到达节点的编号，使节点 node1 和节点 node2 到这个节点的距离 较大值最小化。如果有多个答案，请返回 最小 的节点编号。如果答案不存在，返回 -1 。

 注意 edges 可能包含环。

  

 示例 1：



 输入：edges = [2,2,3,-1], node1 = 0, node2 = 1
 输出：2
 解释：从节点 0 到节点 2 的距离为 1 ，从节点 1 到节点 2 的距离为 1 。
 两个距离的较大值为 1 。我们无法得到一个比 1 更小的较大值，所以我们返回节点 2 。
 示例 2：



 输入：edges = [1,2,-1], node1 = 0, node2 = 2
 输出：2
 解释：节点 0 到节点 2 的距离为 2 ，节点 2 到它自己的距离为 0 。
 两个距离的较大值为 2 。我们无法得到一个比 2 更小的较大值，所以我们返回节点 2 。
  

 提示：

 n == edges.length
 2 <= n <= 105
 -1 <= edges[i] < n
 edges[i] != i
 0 <= node1, node2 < n

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/find-closest-node-to-given-two-nodes
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {



    public int closestMeetingNode(int[] edges, int node1, int node2) {
        //bfs 分别找到到达某个点的路径
        int n = edges.length;
        int[] step1 = getStep(edges, node1);
        int[] step2 = getStep(edges, node2);

        // 遍历 step 12 找到 每个点的最大距离 维护最小值 index
        int minIndex = -1;
        int minValue = -1;

        for (int i = 0; i < n; i++) {
            if (step1[i] == -1 || step2[i] == -1) {
                continue;
            }
            int max = Math.max(step1[i], step2[i]);
            if (minIndex == -1) {
                minIndex = i;
                minValue = max;
            } else {
                if (max < minValue) {
                    minIndex = i;
                    minValue = max;
                }
            }
        }
        return minIndex;
    }

    /**
     * 从start开始依次生成距离
     * @param edges
     * @param start
     * @return
     */
    private int[] getStep(int[] edges, int start) {
        int n = edges.length;
        int[] step = new int[n];
        Arrays.fill(step, -1);
        int target = start;
        int stepCount = 0;
        while (target >= 0 && step[target] == -1) {
            // 下一个点
            step[target] = stepCount;
            target = edges[target];

            stepCount++;
        }
        return step;
    }


}
