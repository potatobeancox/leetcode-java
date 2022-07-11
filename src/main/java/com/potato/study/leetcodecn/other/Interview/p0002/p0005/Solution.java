package com.potato.study.leetcodecn.other.Interview.p0002.p0005;


import com.potato.study.leetcode.domain.ListNode;

/**
 * 面试题 02.05. 链表求和
 *
 * 给定两个用链表表示的整数，每个节点包含一个数位。
 *
 * 这些数位是反向存放的，也就是个位排在链表首部。
 *
 * 编写函数对这两个整数求和，并用链表形式返回结果。
 *
 *  
 *
 * 示例：
 *
 * 输入：(7 -> 1 -> 6) + (5 -> 9 -> 2)，即617 + 295
 * 输出：2 -> 1 -> 9，即912
 * 进阶：思考一下，假设这些数位是正向存放的，又该如何解决呢?
 *
 * 示例：
 *
 * 输入：(6 -> 1 -> 7) + (2 -> 9 -> 5)，即617 + 295
 * 输出：9 -> 1 -> 2，即912
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/sum-lists-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p1 = l1;
        ListNode p2 = l2;
        // 结果的哨兵节点
        ListNode head = new ListNode(-1);
        ListNode p = head;
        // 是否进位
        boolean isProcess = false;
        while (p1 != null || p2 != null || isProcess) {
            // 没有点了 只有进位
            if (p1 == null && p2 == null) {
                p.next = new ListNode(1);
                p = p.next;
                isProcess = false;
            } else if (p1 == null) {
                // 节点值
                int val = p2.val;
                if (isProcess) {
                    val++;
                }
                if (val >= 10) {
                    val %= 10;
                    isProcess = true;
                } else {
                    isProcess = false;
                }
                ListNode node = new ListNode(val);
                p.next = node;
                p = p.next;

                p2 = p2.next;
            } else if (p2 == null) {
                // 节点值
                int val = p1.val;
                if (isProcess) {
                    val++;
                }
                if (val >= 10) {
                    val %= 10;
                    isProcess = true;
                } else {
                    isProcess = false;
                }
                ListNode node = new ListNode(val);
                p.next = node;
                p = p.next;

                p1 = p1.next;
            } else {
                // 都不是 0
                int val = p1.val + p2.val;
                if (isProcess) {
                    val++;
                }
                if (val >= 10) {
                    val %= 10;
                    isProcess = true;
                } else {
                    isProcess = false;
                }
                ListNode node = new ListNode(val);
                p.next = node;
                p = p.next;

                p1 = p1.next;
                p2 = p2.next;
            }
        }
        return head.next;
    }
}
