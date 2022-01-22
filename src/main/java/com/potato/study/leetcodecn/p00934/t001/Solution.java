package com.potato.study.leetcodecn.p00934.t001;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 934. 最短的桥
 *
 * 在给定的二维二进制数组 A 中，存在两座岛。（岛是由四面相连的 1 形成的一个最大组。）

 现在，我们可以将 0 变为 1，以使两座岛连接起来，变成一座岛。

 返回必须翻转的 0 的最小数目。（可以保证答案至少是 1 。）

  

 示例 1：

 输入：A = [[0,1],[1,0]]
 输出：1
 示例 2：

 输入：A = [[0,1,0],[0,0,0],[0,0,1]]
 输出：2
 示例 3：

 输入：A = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
 输出：1
  

 提示：

 2 <= A.length == A[0].length <= 100
 A[i][j] == 0 或 A[i][j] == 1

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/shortest-bridge
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int shortestBridge(int[][] grid) {
        // dfs 对其中一个岛进行标记 标记过程中 将边界放入queue中 并置换为2

        // bfs 按照层找 直到找到2 返回

        return -1;
    }
}
