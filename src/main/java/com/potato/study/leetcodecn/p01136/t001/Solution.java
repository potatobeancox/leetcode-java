package com.potato.study.leetcodecn.p01136.t001;


import java.util.ArrayList;
import java.util.List;

/**
 * 1136. 并行课程
 *
 * 给你一个整数 n ，表示编号从 1 到 n 的 n 门课程。另给你一个数组 relations ，
 * 其中 relations[i] = [prevCoursei, nextCoursei] ，
 * 表示课程 prevCoursei 和课程 nextCoursei 之间存在先修关系：课程 prevCoursei 必须在 nextCoursei 之前修读完成。
 *
 * 在一个学期内，你可以学习 任意数量 的课程，但前提是你已经在上一学期修读完待学习课程的所有先修课程。
 *
 * 请你返回学完全部课程所需的 最少 学期数。如果没有办法做到学完全部这些课程的话，就返回 -1。


 示例 1：


 输入：n = 3, relations = [[1,3],[2,3]]
 输出：2
 解释：上图表示课程之间的关系图：
 在第一学期，可以修读课程 1 和 2 。
 在第二学期，可以修读课程 3 。
 示例 2：


 输入：n = 3, relations = [[1,2],[2,3],[3,1]]
 输出：-1
 解释：没有课程可以学习，因为它们互为先修课程。
  

 提示：

 1 <= n <= 5000
 1 <= relations.length <= 5000
 relations[i].length == 2
 1 <= prevCoursei, nextCoursei <= n
 prevCoursei != nextCoursei
 所有 [prevCoursei, nextCoursei] 互不相同

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/parallel-courses
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int minimumSemesters(int n, int[][] relations) {
        // 遍历 relations 生成 每个 节点到 孩子的list list
        List<List<Integer>> graph = new ArrayList<>();
        // 上述遍历 过程中 记录 节点的度
        int[] indegree = new int[n+1];

        // 把节点 0 度的 方入queue 中 每次 弹出 作为 set 遍历过 bfs

        // 最终看看 还有没有没访问过的

        return -1;
    }
}
