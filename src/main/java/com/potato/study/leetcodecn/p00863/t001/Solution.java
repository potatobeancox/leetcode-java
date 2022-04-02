package com.potato.study.leetcodecn.p00863.t001;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.potato.study.leetcode.domain.TreeNode;

/**
 * 863. 二叉树中所有距离为 K 的结点
 *
 * 给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 K 。
 *
 * 返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
 * 输出：[7,4,1]
 * 解释：
 * 所求结点为与目标结点（值为 5）距离为 2 的结点，
 * 值分别为 7，4，以及 1
 *
 *
 *
 * 注意，输入的 "root" 和 "target" 实际上是树上的结点。
 * 上面的输入仅仅是对这些对象进行了序列化描述。
 *  
 *
 * 提示：
 *
 * 给定的树是非空的。
 * 树上的每个结点都具有唯一的值 0 <= node.val <= 500 。
 * 目标结点 target 是树上的结点。
 * 0 <= K <= 1000.
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/all-nodes-distance-k-in-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {
    // 863
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        // 递归生成 map key index ，value parent 节点
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        // 生成 parentMap
        getParentMap(root, parentMap);
        // 从当前跟开始 递归 获取结果
        List<Integer> resultList = new ArrayList<>();
        getResultList(target, k, 0, null, resultList, parentMap);
        return resultList;
    }

    private void getResultList(TreeNode currentNode, int targetDepth, int currentDepth,
            TreeNode from, List<Integer> resultList, Map<TreeNode, TreeNode> parentMap) {
        // 判断当前节点 是否满足
        if (currentNode == null) {
            return;
        }
        if (targetDepth == currentDepth) {
            resultList.add(currentNode.val);
            return;
        }
        if (targetDepth < currentDepth) {
            return;
        }

        if (currentNode.left != from) {
            getResultList(currentNode.left, targetDepth, currentDepth + 1,
                    currentNode, resultList, parentMap);
        }

        if (currentNode.right != from) {
            getResultList(currentNode.right, targetDepth, currentDepth + 1,
                    currentNode, resultList, parentMap);
        }

        if (parentMap.get(currentNode) != from) {
            getResultList(parentMap.get(currentNode), targetDepth, currentDepth + 1,
                    currentNode, resultList, parentMap);
        }
        return;
    }

    /**
     * dfs 生成 记录每个点与父亲节点的关系
     * @param root
     * @param parentMap
     */
    private void getParentMap(TreeNode root, Map<TreeNode, TreeNode> parentMap) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            parentMap.put(root.left, root);
            getParentMap(root.left, parentMap);
        }
        if (root.right != null) {
            parentMap.put(root.right, root);
            getParentMap(root.right, parentMap);
        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(3);

        TreeNode target = new TreeNode(2);
        root.left.right = target;


        int k = 1;
        List<Integer> list = solution.distanceK(root, target, k);
        System.out.println(list);
    }


}
