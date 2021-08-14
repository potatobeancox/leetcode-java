package com.potato.study.leetcodecn.other.swordoffer.p0032.p2.t001;

import com.potato.study.leetcode.domain.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 剑指 Offer 32 - II. 从上到下打印二叉树 II
 *
 * 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。

  

 例如:
 给定二叉树: [3,9,20,null,null,15,7],

 3
 / \
 9  20
 /  \
 15   7
 返回其层次遍历结果：

 [
 [3],
 [9,20],
 [15,7]
 ]
  

 提示：

 节点总数 <= 1000

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-ii-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<List<Integer>> list = new ArrayList<>();
        if (null == root) {
            return list;
        }
        while (!queue.isEmpty()) {
            int len = queue.size();
            List<Integer> thisLayer = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                TreeNode poll = queue.poll();
                thisLayer.add(poll.val);
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }
            list.add(thisLayer);
        }
        return list;
    }

}
