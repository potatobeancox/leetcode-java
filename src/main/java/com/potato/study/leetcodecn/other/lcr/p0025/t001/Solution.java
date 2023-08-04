package com.potato.study.leetcodecn.other.lcr.p0025.t001;

import com.potato.study.leetcode.domain.ListNode;
import com.potato.study.leetcode.util.ListNodeUtil;

/**
 * 剑指 Offer II 025. 链表中的两数相加
 *
 * 给定两个 非空链表 l1和 l2 来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。

 可以假设除了数字 0 之外，这两个数字都不会以零开头。

  

 示例1：



 输入：l1 = [7,2,4,3], l2 = [5,6,4]
 输出：[7,8,0,7]
 示例2：

 输入：l1 = [2,4,3], l2 = [5,6,4]
 输出：[8,0,7]
 示例3：

 输入：l1 = [0], l2 = [0]
 输出：[0]
  

 提示：

 链表的长度范围为 [1, 100]
 0 <= node.val <= 9
 输入数据保证链表代表的数字无前导 0
  

 进阶：如果输入链表不能修改该如何处理？换句话说，不能对列表中的节点进行翻转。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/lMSNwu
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 先翻转
        ListNode h1 = revert(l1);
        ListNode h2 = revert(l2);
        // 相加
        ListNode sum = add(h1, h2);
        // 翻转
        return revert(sum);
    }

    private ListNode add(ListNode h1, ListNode h2) {
        boolean needPlus = false;
        ListNode p1 = h1;
        ListNode p2 = h2;

        ListNode newHead = new ListNode(-1);
        ListNode p = newHead;

        while (p1 != null || p2 != null) {
            if (p1 == null) {
                int val = p2.val;
                if (needPlus) {
                    val++;
                }
                // 进位
                if (val >= 10) {
                    val %= 10;
                    needPlus = true;
                } else {
                    needPlus = false;
                }
                p2 = p2.next;

                p.next = new ListNode(val);
                p = p.next;
                continue;
            }

            if (p2 == null) {
                int val = p1.val;
                if (needPlus) {
                    val++;
                }
                // 进位
                if (val >= 10) {
                    val %= 10;
                    needPlus = true;
                } else {
                    needPlus = false;
                }
                p1 = p1.next;

                p.next = new ListNode(val);
                p = p.next;
                continue;
            }

            int val = needPlus ? 1: 0;
            val += p1.val;
            val += p2.val;


            if (val >= 10) {
                val %= 10;
                needPlus = true;
            } else {
                needPlus = false;
            }
            p1 = p1.next;
            p2 = p2.next;

            p.next = new ListNode(val);
            p = p.next;

        }
        if (needPlus) {
            p.next = new ListNode(1);
        }
        return newHead.next;
    }

    private ListNode revert(ListNode head) {
        ListNode pre = null;
        ListNode p = head;
        while (p != null) {
            ListNode next = p.next;
            p.next = pre;

            pre = p;
            p = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode h1 = ListNodeUtil.arrayStringToListNode("[7,2,4,3]");
        ListNode h2 = ListNodeUtil.arrayStringToListNode("[5,6,4]");

        ListNode listNode = solution.addTwoNumbers(h1, h2);
        System.out.println(listNode);

    }
}
