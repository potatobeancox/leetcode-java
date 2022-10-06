package com.potato.study.leetcodecn.p00742.t001;

import com.potato.study.leetcode.domain.TreeNode;
import org.junit.Assert;

import java.util.*;

/**
 * 742. 二叉树最近的叶节点
 *
 * 给定一个 每个结点的值互不相同 的二叉树，和一个目标整数值 k，返回 树中与目标值 k  最近的叶结点 。 
 *
 * 与叶结点最近 表示在二叉树中到达该叶节点需要行进的边数与到达其它叶结点相比最少。而且，当一个结点没有孩子结点时称其为叶结点。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [1, 3, 2], k = 1
 * 输出： 2
 * 解释： 2 和 3 都是距离目标 1 最近的叶节点。
 * 示例 2：
 *
 *
 *
 * 输入：root = [1], k = 1
 * 输出：1
 * 解释：最近的叶节点是根结点自身。
 * 示例 3：
 *
 *
 *
 * 输入：root = [1,2,3,4,null,null,null,5,null,6], k = 2
 * 输出：3
 * 解释：值为 3（而不是值为 6）的叶节点是距离结点 2 的最近结点。
 *  
 *
 * 提示：
 *
 * 二叉树节点数在 [1, 1000] 范围内
 * 1 <= Node.val <= 1000
 * 每个节点值都 不同
 * 给定的二叉树中有某个结点使得 node.val == k
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/closest-leaf-in-a-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode.cn/problems/closest-leaf-in-a-binary-tree/solution/er-cha-shu-zui-jin-de-xie-jie-dian-by-leetcode/
     * @param root
     * @param k
     * @return
     */
    public int findClosestLeaf(TreeNode root, int k) {
        // 1.dfs 建图 每个点的 邻接点 （将树转换成图）
        Map<TreeNode, List<TreeNode>> graph = new HashMap<>();
        dfs(root, null, graph);
        // 找到开始的位置
        TreeNode start = null;
        for (TreeNode key : graph.keySet()) {
            if (key != null && key.val == k) {
                start = key;
                break;
            }
        }
        if (start == null) {
            throw new RuntimeException("k 不存在于 树之中");
        }
        // 2.利用1生成结果进行bfs 遍历 如果遇到当前点的 邻接点小于等于1， 说明这个已经是一个叶子了 可以返回 当前层了
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(start);
        Set<TreeNode> visitSet = new HashSet<>();
        visitSet.add(start);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            // 当前节点的叶子
            List<TreeNode> nextNode = graph.get(poll);
            // 有一个是因为有父亲节点
            if (null == nextNode || nextNode.size() <= 1) {
                return poll.val;
            }
            // 当前也不是叶子，往孩子找
            for (TreeNode next : nextNode) {
                if (next == null) {
                    continue;
                }
                if (visitSet.contains(next)) {
                    continue;
                }
                visitSet.add(next);
                queue.add(next);
            }
        }
        return -1;
    }

    private void dfs(TreeNode root, TreeNode parent, Map<TreeNode, List<TreeNode>> graph) {
        if (root == null) {
            return;
        }
        // 初始化
        if (graph.get(root) == null) {
            graph.put(root, new ArrayList<>());
        }
        if (graph.get(parent) == null) {
            graph.put(parent, new ArrayList<>());
        }
        graph.get(root).add(parent);
        graph.get(parent).add(root);

        // 往子节点移动
        dfs(root.left, root, graph);
        dfs(root.right, root, graph);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.right = new TreeNode(2);

        int k = 1;
        int closestLeaf = solution.findClosestLeaf(root, k);
        System.out.println(closestLeaf);
        Assert.assertEquals(3, closestLeaf);



        root = new TreeNode(1);
        root.left = new TreeNode(2);

        k = 1;
        closestLeaf = solution.findClosestLeaf(root, k);
        System.out.println(closestLeaf);
        Assert.assertEquals(2, closestLeaf);


    }


}
