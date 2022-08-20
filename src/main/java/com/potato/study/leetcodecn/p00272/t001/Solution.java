package com.potato.study.leetcodecn.p00272.t001;

import com.potato.study.leetcode.domain.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 272. 最接近的二叉搜索树值 II
 *
 * 给定二叉搜索树的根 root 、一个目标值 target 和一个整数 k ，返回BST中最接近目标的 k 个值。你可以按 任意顺序 返回答案。

 题目 保证 该二叉搜索树中只会存在一种 k 个值集合最接近 target

  

 示例 1：



 输入: root = [4,2,5,1,3]，目标值 = 3.714286，且 k = 2
 输出: [4,3]
 示例 2:

 输入: root = [1], target = 0.000000, k = 1
 输出: [1]
  

 提示：

 二叉树的节点总数为 n
 1 <= k <= n <= 104
 0 <= Node.val <= 109
 -109 <= target <= 109
  

 进阶：假设该二叉搜索树是平衡的，请问您是否能在小于 O(n)（ n = total nodes ）的时间复杂度内解决该问题呢？

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/closest-binary-search-tree-value-ii
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        // 大根堆 按照 距离由大到小
        PriorityQueue<Info> infoPriorityQueue = new PriorityQueue<>((o1, o2) -> Double.compare(o2.score, o1.score));
        dfs(infoPriorityQueue, root, target, k);
        // 遍历 堆 生成结果
        List<Integer> result = new ArrayList<>();
        while (!infoPriorityQueue.isEmpty()) {
            result.add(infoPriorityQueue.poll().val);
        }
        return result;
    }

    private void dfs(PriorityQueue<Info> infoPriorityQueue, TreeNode root, double target, int k) {
        if (root == null) {
            return;
        }
        double score = Math.abs(root.val - target);
        if (infoPriorityQueue.size() < k || infoPriorityQueue.peek().score > score) {
            Info info = new Info();
            info.score = score;
            info.val = root.val;
            infoPriorityQueue.add(info);
        }

        // 超过k 需要pop
        if (infoPriorityQueue.size() > k) {
            infoPriorityQueue.poll();
        }

        // dfs
        dfs(infoPriorityQueue, root.left, target, k);
        dfs(infoPriorityQueue, root.right, target, k);

    }

    class Info {
        public double score;
        public int val;
    }
}
