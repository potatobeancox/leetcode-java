package com.potato.study.leetcodecn.p01110.t001;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import com.potato.study.leetcode.domain.TreeNode;

/**
 * 1110. 删点成林
 *
 * 给出二叉树的根节点 root，树上每个节点都有一个不同的值。
 *
 * 如果节点值在 to_delete 中出现，我们就把该节点从树上删去，最后得到一个森林（一些不相交的树构成的集合）。
 *
 * 返回森林中的每棵树。你可以按任意顺序组织答案。
 *
 *  
 *
 * 示例：
 *
 *
 *
 * 输入：root = [1,2,3,4,5,6,7], to_delete = [3,5]
 * 输出：[[1,2,null,4],[6],[7]]
 *  
 *
 * 提示：
 *
 * 树中的节点数最大为 1000。
 * 每个节点都有一个介于 1 到 1000 之间的值，且各不相同。
 * to_delete.length <= 1000
 * to_delete 包含一些从 1 到 1000、各不相同的值。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/delete-nodes-and-return-forest
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public List<TreeNode> delNodes(TreeNode root, int[] toDelete) {
        List<TreeNode> resultList = new ArrayList<>();
        Set<Integer> deleteSet = new HashSet<>();
        for (int delete : toDelete) {
            deleteSet.add(delete);
        }
        isRootDelete(root, resultList, true, deleteSet);
        return resultList;
    }

    /**
     * 判断当前节点是否被删除
     * 并且 如果 当前节点没有被删除 且 父亲节点已经被删除 （没有父亲）
     * 插入到结果集合中
     * @param root          当前节点
     * @param resultList    结果结合
     * @param isParentMiss  父亲是否被删除了
     * @param deleteSet 需要被删除的节点值
     * @return  当前点是否被删除
     */
    private boolean isRootDelete(TreeNode root, List<TreeNode> resultList, boolean isParentMiss, Set<Integer> deleteSet) {
        if (root == null) {
            return false;
        }
        boolean isRootDelete = deleteSet.contains(root.val);
        // 处理孩子 左右孩子
        boolean isLeftDel = isRootDelete(root.left, resultList, isRootDelete, deleteSet);
        if (isLeftDel) {
            root.left = null;
        }
        boolean isRightDel = isRootDelete(root.right, resultList, isRootDelete, deleteSet);
        if (isRightDel) {
            root.right = null;
        }
        // 判断当前 root 是否可以被记录
        if (!isRootDelete && isParentMiss) {
            resultList.add(root);
        }
        return isRootDelete;
    }
}
