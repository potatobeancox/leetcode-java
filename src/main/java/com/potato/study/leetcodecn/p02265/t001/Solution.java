package com.potato.study.leetcodecn.p02265.t001;

import com.potato.study.leetcode.domain.Node;
import com.potato.study.leetcode.domain.TreeNode;
import com.potato.study.leetcode.util.LeetcodeInputUtils;
import com.potato.study.leetcode.util.TreeNodeUtil;
import org.junit.Assert;

/**
 * 2265. 统计值等于子树平均值的节点数
 *
 * 给你一棵二叉树的根节点 root ，找出并返回满足要求的节点数，要求节点的值等于其 子树 中值的 平均值 。
 *
 * 注意：
 *
 * n 个元素的平均值可以由 n 个元素 求和 然后再除以 n ，并 向下舍入 到最近的整数。
 * root 的 子树 由 root 和它的所有后代组成。
 *  
 *
 * 示例 1：
 *
 *
 * 输入：root = [4,8,5,0,1,null,6]
 * 输出：5
 * 解释：
 * 对值为 4 的节点：子树的平均值 (4 + 8 + 5 + 0 + 1 + 6) / 6 = 24 / 6 = 4 。
 * 对值为 5 的节点：子树的平均值 (5 + 6) / 2 = 11 / 2 = 5 。
 * 对值为 0 的节点：子树的平均值 0 / 1 = 0 。
 * 对值为 1 的节点：子树的平均值 1 / 1 = 1 。
 * 对值为 6 的节点：子树的平均值 6 / 1 = 6 。
 * 示例 2：
 *
 *
 * 输入：root = [1]
 * 输出：1
 * 解释：对值为 1 的节点：子树的平均值 1 / 1 = 1。
 *  
 *
 * 提示：
 *
 * 树中节点数目在范围 [1, 1000] 内
 * 0 <= Node.val <= 1000
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/count-nodes-equal-to-average-of-subtree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    private int count;

    public int averageOfSubtree(TreeNode root) {
        // dfs 后续遍历 找到
        this.count = 0;
        dfs(root);
        return count;
    }

    /**
     * 返回 当前子树的和 和个数
     * @param root
     * @return
     */
    private NodeInfo dfs(TreeNode root) {
        if (root == null) {
            return null;
        }
        NodeInfo leftInfo = dfs(root.left);
        NodeInfo rightInfo = dfs(root.right);
        if (leftInfo == null && rightInfo == null) {
            count++;
//            System.out.println(root);
            NodeInfo nodeInfo = new NodeInfo();
            nodeInfo.num = 1;
            nodeInfo.sum = root.val;
            return nodeInfo;
        } else if (leftInfo == null) {
            return getNodeInfo(root, rightInfo);
        } else if (rightInfo == null) {
            // 判断是否平均值相同
            return getNodeInfo(root, leftInfo);
        } else {
            long thisSum = root.val + leftInfo.sum + rightInfo.sum;
            int thisCount = 1 + leftInfo.num + rightInfo.num;
            if (thisSum / thisCount == root.val) {
                count++;
//                System.out.println(root);
            }
            NodeInfo nodeInfo = new NodeInfo();
            nodeInfo.num = thisCount;
            nodeInfo.sum = thisSum;
            return nodeInfo;
        }
    }

    private NodeInfo getNodeInfo(TreeNode root, NodeInfo chilidNodeInfo) {
        // 判断是否平均值相同
        if ((chilidNodeInfo.sum + root.val) / (chilidNodeInfo.num + 1) == root.val) {
            count++;
//            System.out.println(root);
        }
        NodeInfo nodeInfo = new NodeInfo();
        nodeInfo.num = 1 + chilidNodeInfo.num;
        nodeInfo.sum = root.val + chilidNodeInfo.sum;
        return nodeInfo;
    }

    class NodeInfo {
        // 和
        public long sum;
        // 个数
        public int num;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "[4,8,5,0,1,null,6]";
        TreeNode treeNode = TreeNodeUtil.generateTreeByArrayString(input);
        int i = solution.averageOfSubtree(treeNode);
        System.out.println(i);
        Assert.assertEquals(5, i);
    }
}
