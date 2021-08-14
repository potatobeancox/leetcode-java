package com.potato.study.leetcodecn.other.Interview.p0004.p0003;


import com.potato.study.leetcode.domain.ListNode;
import com.potato.study.leetcode.domain.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 面试题 04.03. 特定深度节点链表
 *
 * 给定一棵二叉树，设计一个算法，创建含有某一深度上所有节点的链表（比如，若一棵树的深度为 D，则会创建出 D 个链表）。返回一个包含所有深度的链表的数组。

  

 示例：

 输入：[1,2,3,4,5,null,7,8]

 1
 /  \
 2    3
 / \    \
 4   5    7
 /
 8

 输出：[[1],[2,3],[4,5,7],[8]]

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/list-of-depth-lcci
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * 层序遍历
     * queue 为空遍历，记录每层长度，生成当前层的结果，结果使用 list 存储
     * 最终转换 list 成数组
     * @param tree
     * @return
     */
    public ListNode[] listOfDepth(TreeNode tree) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(tree);
        List<ListNode> resultList = new ArrayList<>();
        while (!queue.isEmpty()) {
            int length = queue.size();
            ListNode head = new ListNode(-1);
            ListNode p = head;
            for (int i = 0; i < length; i++) {
                TreeNode poll = queue.poll();
                p.next = new ListNode(poll.val);
                p = p.next;
                // 之后的遍历方向
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }
            // 每层生成结果之后放入list中
            resultList.add(head.next);
        }
        // list -> array
        ListNode[] resultArray = new ListNode[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            resultArray[i] = resultList.get(i);
        }
        return resultArray;
    }
}
