package com.potato.study.leetcodecn.p02097.t001;

import com.potato.study.leetcode.domain.TreeNode;
import com.potato.study.leetcode.util.LeetcodeInputUtils;
import com.potato.study.leetcode.util.TreeNodeUtil;
import org.junit.Assert;

import java.util.*;

/**
 * 2097. 合法重新排列数对
 *
 * 给你一个下标从 0 开始的二维整数数组 pairs ，其中 pairs[i] = [starti, endi] 。如果 pairs 的一个重新排列，满足对每一个下标 i （ 1 <= i < pairs.length ）都有 endi-1 == starti ，那么我们就认为这个重新排列是 pairs 的一个 合法重新排列 。

 请你返回 任意一个 pairs 的合法重新排列。

 注意：数据保证至少存在一个 pairs 的合法重新排列。

  

 示例 1：

 输入：pairs = [[5,1],[4,5],[11,9],[9,4]]
 输出：[[11,9],[9,4],[4,5],[5,1]]
 解释：
 输出的是一个合法重新排列，因为每一个 endi-1 都等于 starti 。
 end0 = 9 == 9 = start1
 end1 = 4 == 4 = start2
 end2 = 5 == 5 = start3
 示例 2：

 输入：pairs = [[1,3],[3,2],[2,1]]
 输出：[[1,3],[3,2],[2,1]]
 解释：
 输出的是一个合法重新排列，因为每一个 endi-1 都等于 starti 。
 end0 = 3 == 3 = start1
 end1 = 2 == 2 = start2
 重新排列后的数组 [[2,1],[1,3],[3,2]] 和 [[3,2],[2,1],[1,3]] 都是合法的。
 示例 3：

 输入：pairs = [[1,2],[1,3],[2,1]]
 输出：[[1,2],[2,1],[1,3]]
 解释：
 输出的是一个合法重新排列，因为每一个 endi-1 都等于 starti 。
 end0 = 2 == 2 = start1
 end1 = 1 == 1 = start2
  

 提示：

 1 <= pairs.length <= 105
 pairs[i].length == 2
 0 <= starti, endi <= 109
 starti != endi
 pairs 中不存在一模一样的数对。
 至少 存在 一个合法的 pairs 重新排列。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/valid-arrangement-of-pairs
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 找到一条欧拉通路
     * @param pairs
     * @return
     */
    public int[][] validArrangement(int[][] pairs) {
        // 将 pairs 做成list 图表示
        Map<Integer, List<Integer>> graph = new HashMap<>();
        // 从哪个点开始 统计入度和 出度 每个点
        Map<Integer, int[]> degreeMap = new HashMap<>();
        // 统计入度和 出度 出度比入度多1的 是开始点 否则 0开始
        for (int[] pair : pairs) {
            int from = pair[0];
            // 出度 ++
            int[] orDefault = degreeMap.getOrDefault(from, new int[2]);
            orDefault[1]++;
            degreeMap.put(from, orDefault);
            int to = pair[1];
            orDefault = degreeMap.getOrDefault(to, new int[2]);
            orDefault[0]++;
            degreeMap.put(to, orDefault);
            // graph
            List<Integer> toList = graph.getOrDefault(from, new ArrayList<>());
            toList.add(to);
            graph.put(from, toList);
        }
        int start = pairs[0][0];
        for (Map.Entry<Integer, int[]> entry : degreeMap.entrySet()) {
            int[] value = entry.getValue();
            if (value[1] - value[0] == 1) {
                start = entry.getKey();
                break;
            }
        }

        List<Integer> path = new ArrayList<>();
        // dfs 找到结果
        dfs(graph, path, start);
        // 将结果list 翻转成最终结果
        int[][] result = new int[pairs.length][];
        for (int i = 0; i < pairs.length; i++) {
            // 开始点和 结束点
            int from = path.get(path.size() - 1 - i);
            int to = path.get(path.size() - 2 - i);
            result[i] = new int[] {
                    from, to
            };
        }
        return result;
    }

    private void dfs(Map<Integer, List<Integer>> graph, List<Integer> path, int start) {
        // 找到 start 能到的点
        List<Integer> nextList = graph.get(start);
        while (nextList != null && nextList.size() > 0) {
            Integer remove = nextList.remove(nextList.size() - 1);
            // 往下找
            dfs(graph, path, remove);
        }
        // 遍历过了 将 start 放入结果
        path.add(start);

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "[[1,3],[3,2],[2,1]]";
        int[][] pairs = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        int[][] ints = solution.validArrangement(pairs);
        System.out.println(Arrays.deepToString(ints));
    }


}
