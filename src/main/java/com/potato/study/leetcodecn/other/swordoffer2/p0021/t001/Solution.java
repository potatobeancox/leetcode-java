package com.potato.study.leetcodecn.other.swordoffer2.p0021.t001;

import com.potato.study.leetcode.domain.ListNode;

/**
 * 剑指 Offer II 021. 删除链表的倒数第 n 个结点
 *
 * 给定一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 * 示例 2：
 *
 * 输入：head = [1], n = 1
 * 输出：[]
 * 示例 3：
 *
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 *  
 *
 * 提示：
 *
 * 链表中结点的数目为 sz
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 *  
 *
 * 进阶：能尝试使用一趟扫描实现吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/SLwz0R
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // jianzhi2 offer 021
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // fast 先往前走 n个点
        ListNode newHead = new ListNode(-1);
        newHead.next = head;
        ListNode fast = newHead;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        ListNode slow = newHead;
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        // slow next 就是要被删除的
        ListNode next = slow.next;
        slow.next = next.next;
        // 删除节点
        next.next = null;
        return newHead.next;
    }
}
