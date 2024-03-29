package com.potato.study.leetcodecn.p02487.t001;

import com.potato.study.leetcode.domain.ListNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 2487. 从链表中移除节点
 *
 * 给你一个链表的头节点 head 。
 *
 * 对于列表中的每个节点 node ，如果其右侧存在一个具有 严格更大 值的节点，则移除 node 。
 *
 * 返回修改后链表的头节点 head 。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：head = [5,2,13,3,8]
 * 输出：[13,8]
 * 解释：需要移除的节点是 5 ，2 和 3 。
 * - 节点 13 在节点 5 右侧。
 * - 节点 13 在节点 2 右侧。
 * - 节点 8 在节点 3 右侧。
 * 示例 2：
 *
 * 输入：head = [1,1,1,1]
 * 输出：[1,1,1,1]
 * 解释：每个节点的值都是 1 ，所以没有需要移除的节点。
 *  
 *
 * 提示：
 *
 * 给定列表中的节点数目在范围 [1, 105] 内
 * 1 <= Node.val <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/remove-nodes-from-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public ListNode removeNodes(ListNode head) {
        // 如果其右侧存在一个具有 严格更大
        ListNode tmp = head;
        Deque<ListNode> deque = new LinkedList<>();
        while (tmp != null) {
            // 循环比较 deque deque 一定从 栈底到栈顶是递减的 ， 那么一次比较 如果当前值 小于等于 入栈，否则循环出栈
            while (!deque.isEmpty() && deque.peekLast().val < tmp.val) {
                deque.pollLast();
            }
            deque.add(tmp);
            tmp = tmp.next;
        }
        // 组装
        ListNode newHead = new ListNode(-1);
        ListNode p = newHead;
        while (!deque.isEmpty()) {
            ListNode node = deque.pollFirst();
            p.next = node;
            p = p.next;
            node.next = null;
        }
        return newHead.next;
    }
}
