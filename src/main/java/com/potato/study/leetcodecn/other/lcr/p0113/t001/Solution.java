package com.potato.study.leetcodecn.other.lcr.p0113.t001;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 剑指 Offer II 113. 课程顺序
 *
 * 现在总共有 numCourses 门课需要选，记为 0 到 numCourses-1。
 *
 * 给定一个数组 prerequisites ，它的每一个元素 prerequisites[i] 表示两门课程之间的先修顺序。 例如 prerequisites[i] = [ai, bi] 表示想要学习课程 ai ，需要先完成课程 bi 。
 *
 * 请根据给出的总课程数  numCourses 和表示先修顺序的 prerequisites 得出一个可行的修课序列。
 *
 * 可能会有多个正确的顺序，只要任意返回一种就可以了。如果不可能完成所有课程，返回一个空数组。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: numCourses = 2, prerequisites = [[1,0]]
 * 输出: [0,1]
 * 解释: 总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
 * 示例 2:
 *
 * 输入: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * 输出: [0,1,2,3] or [0,2,1,3]
 * 解释: 总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
 *  因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
 * 示例 3:
 *
 * 输入: numCourses = 1, prerequisites = []
 * 输出: [0]
 * 解释: 总共 1 门课，直接修第一门课就可。
 *  
 *
 * 提示:
 *
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= numCourses * (numCourses - 1)
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * ai != bi
 * prerequisites 中不存在重复元素
 *  
 *
 * 注意：本题与主站 210 题相同：https://leetcode-cn.com/problems/course-schedule-ii/
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/QA2IGt
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // ii 113
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 将 prerequisites 转换成 临界点的状态
        List<Integer>[] grid = new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            grid[i] = new LinkedList<>();
        }
        int[] in = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            // bi
            int from = prerequisite[1];
            // ai
            int to = prerequisite[0];

            grid[from].add(to);
            in[to]++;
        }
        // 表示想要学习课程 ai ，需要先完成课程 bi 。 给个顺序
        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visit = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (in[i] == 0) {
                queue.add(i);
                result.add(i);

                visit[i] = true;
            }
        }
        if (result.size() == numCourses) {
            int[] ints = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                ints[i] = result.get(i);
            }
            return ints;
        }
        // 同时生成 入度记录 bfs 先找到 入读为0的 每次 都 入读 修建
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            // 删掉这个点 临界点 in 减少
            List<Integer> nextList = grid[cur];
            for (int next : nextList) {
                in[next]--;

                if (in[next] == 0) {
                    queue.add(next);
                    result.add(next);
                }
            }
        }
        if (result.size() < numCourses) {
            return new int[]{};
        }
        int[] ints = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            ints[i] = result.get(i);
        }
        return ints;
    }


}
