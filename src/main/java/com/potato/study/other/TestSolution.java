package com.potato.study.other;

import com.potato.study.leetcode.domain.ListNode;
import com.potato.study.leetcode.util.ListNodeUtil;

/**
 * @author liuzhao03 <liuzhao03@kuaishou.com>
 * Created on 2022-08-02
 */
public class TestSolution {


    /**
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * 示例：
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     * @return
     */
    public ListNode addListNode(ListNode root1, ListNode root2) {
        // 边界处理
        if (null == root1) {
            return root2;
        }
        if (null == root2) {
            return root1;
        }
        // pq 指向头部
        ListNode p = root1;
        ListNode q = root2;
        ListNode newHead = new ListNode(-1);
        ListNode r = newHead;
        // 遍历 列表 往后 判断 是否到了某一个的终止位置 ，是的话直接用 剩下的那个
        boolean isProcess = false;
        while (p!= null || q != null) {
            if (p == null) {
                int val = q.val;
                if (isProcess) {
                    val++;
                }
                // 结算 进位符号
                if (val >= 10) {
                    val %= 10;
                    isProcess = true;
                } else {
                    isProcess = false;
                }
                r.next = new ListNode(val);
                r = r.next;

                q = q.next;
            } else if (q == null) {
                int val = p.val;
                if (isProcess) {
                    val++;
                }
                // 结算 进位符号
                if (val >= 10) {
                    val %= 10;
                    isProcess = true;
                } else {
                    isProcess = false;
                }
                r.next = new ListNode(val);
                r = r.next;

                p = p.next;
            } else {
                // pq 都不是空
                int val = p.val + q.val;
                if (isProcess) {
                    val++;
                }
                // 结算 进位符号
                if (val >= 10) {
                    val %= 10;
                    isProcess = true;
                } else {
                    isProcess = false;
                }
                r.next = new ListNode(val);
                r = r.next;

                p = p.next;
                q = q.next;
            }
        }
        // 两个都有 进行 加和 求  注意进位标志
        if (isProcess) {
            r.next = new ListNode(1);
        }
        return newHead.next;
    }

    public static void main(String[] args) {
        TestSolution testSolution = new TestSolution();
        ListNode root1 = ListNodeUtil.stringToListNode("2->4->3");
        ListNode root2 = ListNodeUtil.stringToListNode("5->6->4");
        ListNode node = testSolution.addListNode(root1, root2);
        ListNodeUtil.printListNode(node);
    }

}
