package com.potato.study.leetcodecn.p00444.t001;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 444. 序列重建
 *
 * 给定一个长度为 n 的整数数组 nums ，其中 nums 是范围为 [1，n] 的整数的排列。还提供了一个 2D 整数数组 sequences ，其中 sequences[i] 是 nums 的子序列。
 * 检查 nums 是否是唯一的最短 超序列 。最短 超序列 是 长度最短 的序列，并且所有序列 sequences[i] 都是它的子序列。对于给定的数组 sequences ，可能存在多个有效的 超序列 。
 *
 * 例如，对于 sequences = [[1,2],[1,3]] ，有两个最短的 超序列 ，[1,2,3] 和 [1,3,2] 。
 * 而对于 sequences = [[1,2],[1,3],[1,2,3]] ，唯一可能的最短 超序列 是 [1,2,3] 。[1,2,3,4] 是可能的超序列，但不是最短的。
 * 如果 nums 是序列的唯一最短 超序列 ，则返回 true ，否则返回 false 。
 * 子序列 是一个可以通过从另一个序列中删除一些元素或不删除任何元素，而不改变其余元素的顺序的序列。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3], sequences = [[1,2],[1,3]]
 * 输出：false
 * 解释：有两种可能的超序列：[1,2,3]和[1,3,2]。
 * 序列 [1,2] 是[1,2,3]和[1,3,2]的子序列。
 * 序列 [1,3] 是[1,2,3]和[1,3,2]的子序列。
 * 因为 nums 不是唯一最短的超序列，所以返回false。
 * 示例 2：
 *
 * 输入：nums = [1,2,3], sequences = [[1,2]]
 * 输出：false
 * 解释：最短可能的超序列为 [1,2]。
 * 序列 [1,2] 是它的子序列：[1,2]。
 * 因为 nums 不是最短的超序列，所以返回false。
 * 示例 3：
 *
 * 输入：nums = [1,2,3], sequences = [[1,2],[1,3],[2,3]]
 * 输出：true
 * 解释：最短可能的超序列为[1,2,3]。
 * 序列 [1,2] 是它的一个子序列：[1,2,3]。
 * 序列 [1,3] 是它的一个子序列：[1,2,3]。
 * 序列 [2,3] 是它的一个子序列：[1,2,3]。
 * 因为 nums 是唯一最短的超序列，所以返回true。
 *  
 *
 * 提示：
 *
 * n == nums.length
 * 1 <= n <= 104
 * nums 是 [1, n] 范围内所有整数的排列
 * 1 <= sequences.length <= 104
 * 1 <= sequences[i].length <= 104
 * 1 <= sum(sequences[i].length) <= 105
 * 1 <= sequences[i][j] <= n
 * sequences 的所有数组都是 唯一 的
 * sequences[i] 是 nums 的一个子序列
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/sequence-reconstruction
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public boolean sequenceReconstruction(int[] nums, List<List<Integer>> sequences) {
        // 唯一最短超序列 满足这个条件 使用拓扑排序 排序过程中 每次都必须只能有一个 入读为0的点 否则不满足唯一
        int n = nums.length;
        int[] indegree = new int[n];
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (List<Integer> list : sequences) {
            for (int i = 1; i < list.size(); i++) {
                int from = list.get(i-1);
                int to = list.get(i);

                graph.get(from-1).add(to-1);
                indegree[to-1]++;
            }
        }
        Queue<Integer> queue = new PriorityQueue<>();
        // 数组 遍历 sequences 记录每个店的入度
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                queue.add(i);

            }
        }
        if (queue.size() != 1) {
            return false;
        }
        List<Integer> list = new ArrayList<>();
        // 用队列 每次 pop的时候 都要满足 只有一个 点可以 pop
        while (!queue.isEmpty()) {
            if (queue.size() != 1) {
                return false;
            }
            int index = queue.poll();
            list.add(index);
            // 临街的 入读改变
            List<Integer> nextList = graph.get(index);
            if (null != nextList) {
                for (int next : nextList) {
                    indegree[next]--;

                    // 是否到0
                    if (indegree[next] == 0) {
                        queue.add(next);
                    }
                }
            }
        }

        // 比较结果
        if (list.size() != nums.length) {
            return false;
        }
        for (int i = 0; i < n; i++) {
            if (list.get(i) + 1 != nums[i]) {
                return false;
            }
        }
        return true;
    }


}
