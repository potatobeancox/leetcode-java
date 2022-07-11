package com.potato.study.leetcodecn.other.Interview.p0002.p0004;


import com.potato.study.leetcode.domain.ListNode;

/**
 * 面试题 02.04. 分割链表
 *
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 *
 * 你不需要 保留 每个分区中各节点的初始相对位置。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,4,3,2,5,2], x = 3
 * 输出：[1,2,2,4,3,5]
 * 示例 2：
 *
 * 输入：head = [2,1], x = 2
 * 输出：[1,2]
 *  
 *
 * 提示：
 *
 * 链表中节点的数目在范围 [0, 200] 内
 * -100 <= Node.val <= 100
 * -200 <= x <= 200
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/partition-list-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public ListNode partition(ListNode head, int x) {
        // 两个 链表 小于放在前面 大于放在后面
        ListNode head1 = new ListNode(-1);
        ListNode head2 = new ListNode(-1);

        ListNode p1 = head1;
        ListNode p2 = head2;

        ListNode p = head;
        while (p!= null) {
            ListNode next = p.next;
            p.next = null;
            if (p.val < x) {
                p1.next = p;
                p1 = p1.next;
            } else {
                p2.next = p;
                p2 = p2.next;
            }
            p = next;
        }
        // 链接 p1 -》 h2
        p1.next = head2.next;
        return head1.next;
    }
}
