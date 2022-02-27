package com.potato.study.leetcodecn.p00863.t001;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import com.potato.study.leetcode.domain.TreeNode;
import com.potato.study.leetcode.util.LeetcodeInputUtils;

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
        Map<Integer, TreeNode> parentMap = new HashMap<>();
        List<Integer> resultList = new ArrayList<>();
        // 生成 map key 节点值 value 这个值对应的父亲节点
        dfsGetParentMap(parentMap, root, resultList, 0, k, root == target, target);
        // 在map中递归找到
        dfsGetResultFromParentMap(target, parentMap, k, resultList, 0);
        return resultList;
    }

    /**
     *
     * @param parentMap
     * @param root
     */
    private void dfsGetParentMap(Map<Integer, TreeNode> parentMap, TreeNode root,
            List<Integer> resultList, int currentLevel, int k, boolean isFromTarget, TreeNode target) {
        if (root == null) {
            return;
        }
        // 叶子节点直接返回
        if (currentLevel == k) {
            resultList.add(root.val);
        }
        if (root.left == null && root.right == null) {
            return;
        }
        if (root.left != null) {
            parentMap.put(root.left.val, root);
            dfsGetParentMap(parentMap, root.left, resultList, currentLevel + 1, k,
                    isFromTarget || root == target, target);
        }
        if (root.right != null) {
            parentMap.put(root.right.val, root);
            dfsGetParentMap(parentMap, root.right, resultList, currentLevel + 1, k,
                    isFromTarget || root == target, target);
        }
    }

    /**
     * 找到结果
     * @param root
     * @param parentMap
     * @param k
     * @param resultList
     * @param currentLevel
     */
    private void dfsGetResultFromParentMap(TreeNode root, Map<Integer, TreeNode> parentMap,
            int k, List<Integer> resultList, int currentLevel) {
        if (currentLevel > k) {
            return;
        }
        if (currentLevel == k) {
            resultList.add(root.val);
            return;
        }
        TreeNode nextNode = parentMap.get(root.val);
        if (nextNode != null) {
            dfsGetResultFromParentMap(nextNode, parentMap, k, resultList, currentLevel + 1);
        }
    }
}
