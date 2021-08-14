package com.potato.study.leetcodecn.other.swordoffer.p0024.p1.t001;

import com.potato.study.leetcode.domain.ListNode;

/**
 * 剑指 Offer 24. 反转链表
 *
 * 义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。

  

 示例:

 输入: 1->2->3->4->5->NULL
 输出: 5->4->3->2->1->NULL
  

 限制：

 0 <= 节点个数 <= 5000

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 反转链表 操作
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (null == head) {
            return null;
        }
        ListNode pre = null;
        ListNode p = head;
        while (p != null) {
            ListNode q = p.next;
            p.next = pre;
            pre = p;
            p = q;
        }
        return pre;
    }

}
