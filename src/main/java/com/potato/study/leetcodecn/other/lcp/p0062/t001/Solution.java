package com.potato.study.leetcodecn.other.lcp.p0062.t001;

/**
 * LCP 62. 交通枢纽
 *
 * 为了缓解「力扣嘉年华」期间的人流压力，组委会在活动期间开设了一些交通专线。path[i] = [a, b] 表示有一条从地点 a通往地点 b 的 单向 交通专线。
 * 若存在一个地点，满足以下要求，我们则称之为 交通枢纽：
 *
 * 所有地点（除自身外）均有一条 单向 专线 直接 通往该地点；
 * 该地点不存在任何 通往其他地点 的单向专线。
 * 请返回交通专线的 交通枢纽。若不存在，则返回 -1。
 *
 * 注意：
 *
 * 对于任意一个地点，至少被一条专线连通。
 * 示例 1：
 *
 * 输入：path = [[0,1],[0,3],[1,3],[2,0],[2,3]]
 *
 * 输出：3
 *
 * 解释：如下图所示：
 * 地点 0,1,2 各有一条通往地点 3 的交通专线，
 * 且地点 3 不存在任何通往其他地点的交通专线。
 *
 *
 * 示例 2：
 *
 * 输入：path = [[0,3],[1,0],[1,3],[2,0],[3,0],[3,2]]
 *
 * 输出：-1
 *
 * 解释：如下图所示：不存在满足 交通枢纽 的地点。
 *
 *
 * 提示：
 *
 * 1 <= path.length <= 1000
 * 0 <= path[i][0], path[i][1] <= 1000
 * path[i][0] 与 path[i][1] 不相等
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/D9PW8w
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // lcp 62
    public int transportationHub(int[][] path) {
        // 遍历 path 找到最大值 就是 节点号从 0开始
        int maxNodeIndex = 0;
        // 入度
        int[] in = new int[1001];
        // 出度
        int[] out = new int[1001];
        for (int[] p : path) {
            int from = p[0];
            int to = p[1];

            // 表示有一条从地点 `a`通往地点 `b 入读n-1 出度为0
            in[to]++;
            out[from]++;

            maxNodeIndex = Math.max(maxNodeIndex, from);
            maxNodeIndex = Math.max(maxNodeIndex, to);
        }

        // 遍历到 max 找到符合条件的节点个数
        int targetIn = maxNodeIndex;
        for (int i = 0; i <= maxNodeIndex; i++) {
            if (targetIn == in[i] && 0 == out[i]) {
                return i;
            }
        }
        return -1;
    }


}
