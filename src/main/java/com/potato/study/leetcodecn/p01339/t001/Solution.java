package com.potato.study.leetcodecn.p01339.t001;


import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import com.potato.study.leetcode.domain.TreeNode;

/**
 * 1339. 分裂二叉树的最大乘积
 *
 * 给你一棵二叉树，它的根为 root 。请你删除 1 条边，使二叉树分裂成两棵子树，且它们子树和的乘积尽可能大。
 *
 * 由于答案可能会很大，请你将结果对 10^9 + 7 取模后再返回。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [1,2,3,4,5,6]
 * 输出：110
 * 解释：删除红色的边，得到 2 棵子树，和分别为 11 和 10 。它们的乘积是 110 （11*10）
 * 示例 2：
 *
 *
 *
 * 输入：root = [1,null,2,3,4,null,null,5,6]
 * 输出：90
 * 解释：移除红色的边，得到 2 棵子树，和分别是 15 和 6 。它们的乘积为 90 （15*6）
 * 示例 3：
 *
 * 输入：root = [2,3,9,10,7,8,6,5,4,11,1]
 * 输出：1025
 * 示例 4：
 *
 * 输入：root = [1,1]
 * 输出：1
 *  
 *
 * 提示：
 *
 * 每棵树最多有 50000 个节点，且至少有 2 个节点。
 * 每个节点的值在 [1, 10000] 之间。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-product-of-splitted-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    private long max;
    private int mod;

    public int maxProduct(TreeNode root) {
        // dfs 统计一次 每个点和他子树的和 root 值就是 sum
        Map<TreeNode, Long> rootSumMap = new HashMap<>();
        dfsBuildTheMap(root, rootSumMap);
        // dfs 每个点计算一次
        long sum = rootSumMap.get(root);
        this.max = 0;
        this.mod = 1_000_000_000 + 7;
        dfs(root, rootSumMap, sum);
        return (int) (max % mod);
    }

    /**
     *
     * @param root
     * @param rootSumMap
     */
    private void dfs(TreeNode root, Map<TreeNode, Long> rootSumMap, long sum) {
        if (root == null) {
            return;
        }
        long rootSum = rootSumMap.get(root);
        long remind = sum - rootSum;
        max = Math.max(max, (remind * rootSum));
        dfs(root.left, rootSumMap, sum);
        dfs(root.right, rootSumMap, sum);
    }

    private void dfsBuildTheMap(TreeNode root, Map<TreeNode, Long> rootSumMap) {
        // 终止条件 叶子节点
        if (root == null) {
            return;
        }
        // leaf
        if (root.left == null && root.right == null) {
            rootSumMap.put(root, (long)root.val);
            return;
        }
        dfsBuildTheMap(root.left, rootSumMap);
        dfsBuildTheMap(root.right, rootSumMap);
        long sum = root.val;
        if (root.left != null) {
            sum += rootSumMap.getOrDefault(root.left, 0L);
        }
        if (root.right != null) {
            sum += rootSumMap.getOrDefault(root.right, 0L);
        }
        // put
        rootSumMap.put(root, sum);
        return;
    }
}
