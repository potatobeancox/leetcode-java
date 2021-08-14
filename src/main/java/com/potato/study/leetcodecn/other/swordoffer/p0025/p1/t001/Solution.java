package com.potato.study.leetcodecn.other.swordoffer.p0025.p1.t001;

import com.potato.study.leetcode.domain.ListNode;

/**
 * 剑指 Offer 25. 合并两个排序的链表
 *
 * 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。

 示例1：

 输入：1->2->4, 1->3->4
 输出：1->1->2->3->4->4
 限制：

 0 <= 链表长度 <= 1000

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1);
        ListNode target = head;
        ListNode p = l1;
        ListNode q = l2;
        while (p != null || q!= null) {
            if (q == null || (p!= null && p.val < q.val)) {
                target.next = p;
                p = p.next;
                target = target.next;
            } else {
                target.next = q;
                q = q.next;
                target = target.next;
            }
        }
        return head.next;
    }

}
