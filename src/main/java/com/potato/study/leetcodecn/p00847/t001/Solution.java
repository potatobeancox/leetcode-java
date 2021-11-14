package com.potato.study.leetcodecn.p00847.t001;


import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import javax.jws.soap.SOAPBinding;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 847. 访问所有节点的最短路径
 *
 * 存在一个由 n 个节点组成的无向连通图，图中的节点按从 0 到 n - 1 编号。

 给你一个数组 graph 表示这个图。其中，graph[i] 是一个列表，由所有与节点 i 直接相连的节点组成。

 返回能够访问所有节点的最短路径的长度。你可以在任一节点开始和停止，也可以多次重访节点，并且可以重用边。

  

 示例 1：


 输入：graph = [[1,2,3],[0],[0],[0]]
 输出：4
 解释：一种可能的路径为 [1,0,2,0,3]
 示例 2：



 输入：graph = [[1],[0,2,4],[1,3,4],[2],[1,2]]
 输出：4
 解释：一种可能的路径为 [0,1,4,2,3]
  

 提示：

 n == graph.length
 1 <= n <= 12
 0 <= graph[i].length < n
 graph[i] 不包含 i
 如果 graph[a] 包含 b ，那么 graph[b] 也包含 a
 输入的图总是连通图

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/shortest-path-visiting-all-nodes
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int shortestPathLength(int[][] graph) {

        // 层序遍历 使用 一个 queue 存 当前index 当前visit 的情况 和当前层数
        Queue<int[]> queue = new LinkedList<>();
        // 开始将每个位置放进去
        int n = graph.length;
        // 记录访问状态
        int finalStatus = (1 << n) - 1;
        boolean[][] visit = new boolean[n][finalStatus + 1];
        for (int i = 0; i < n; i++) {
            // 开始的话 这个位置是访问过的
            int status = (1 << i);
            queue.add(new int[] {i, status, 0});
            visit[i][status] = true;
        }
        // 全是1的状态是都访问过的状态


        // 开始每个届节点都是 距离为 0 遍历 每个bit为咩歌 index 是否访问过 0时没有访问过 1时访问过 一旦找到 直接返回距离
        while (!queue.isEmpty()) {
            // bfs
            int[] poll = queue.poll();
            int index = poll[0];
            int status = poll[1];
            int pathLength = poll[2];
            if (status == finalStatus) {
                return pathLength;
            }
            for (int i = 0; i < graph[index].length; i++) {
                int targetIndex = graph[index][i];
                int targetStatus = (status | ( 1 << targetIndex));
                // 是否访问过
                if (visit[targetIndex][targetStatus]) {
                    continue;
                }
                int thisPathLength = pathLength + 1;
                queue.add(new int[] {targetIndex, targetStatus, thisPathLength});
                visit[targetIndex][targetStatus] = true;
            }

        }
        return -1;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] arrayTwoDimensional = LeetcodeInputUtils.inputString2IntArrayTwoDimensional("[[1,2,3],[0],[0],[0]]");
        int i = solution.shortestPathLength(arrayTwoDimensional);
        System.out.println(i);
        Assert.assertEquals(4, i);

        arrayTwoDimensional = LeetcodeInputUtils.inputString2IntArrayTwoDimensional("[[1,2,3],[0],[0],[0]]");
        i = solution.shortestPathLength(arrayTwoDimensional);
        System.out.println(i);
        Assert.assertEquals(4, i);
    }
}
