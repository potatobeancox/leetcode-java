package com.potato.study.leetcodecn.other.lcr.p0077.t001;

import java.util.PriorityQueue;

import com.potato.study.leetcode.domain.ListNode;

/**
 * 剑指 Offer II 077. 链表排序
 *
 * 给定链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 * 示例 2：
 *
 *
 *
 * 输入：head = [-1,5,3,4,0]
 * 输出：[-1,0,3,4,5]
 * 示例 3：
 *
 * 输入：head = []
 * 输出：[]
 *  
 *
 * 提示：
 *
 * 链表中节点的数目在范围 [0, 5 * 104] 内
 * -105 <= Node.val <= 105
 *  
 *
 * 进阶：你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/7WHec2
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // offer II 077 链表排序
    public ListNode sortList(ListNode head) {
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>((n1, n2) -> {
            return Integer.compare(n1.val, n2.val);
        });
        ListNode p = head;
        while (p != null) {
            priorityQueue.add(p);
            p = p.next;
        }
        ListNode newHead = new ListNode(-1);
        p = newHead;
        while (!priorityQueue.isEmpty()) {
            p.next = priorityQueue.poll();
            p = p.next;
        }
        p.next = null;
        return newHead.next;
    }
}
