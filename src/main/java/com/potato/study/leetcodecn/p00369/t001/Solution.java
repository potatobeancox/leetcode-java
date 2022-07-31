package com.potato.study.leetcodecn.p00369.t001;

import com.potato.study.leetcode.domain.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 369. 给单链表加一
 *
 * 给定一个用链表表示的非负整数， 然后将这个整数 再加上 1 。

 这些数字的存储是这样的：最高位有效的数字位于链表的首位 head 。

  

 示例 1:

 输入: head = [1,2,3]
 输出: [1,2,4]
 示例 2:

 输入: head = [0]
 输出: [1]
  

 提示：

 链表中的节点数在 [1, 100] 的范围内。
 0 <= Node.val <= 9
 由链表表示的数字不包含前导零，除了零本身。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/plus-one-linked-list
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public ListNode plusOne(ListNode head) {
        // 单链表翻转
        ListNode pre = null;
        ListNode p = head;
        while (p != null) {
            ListNode next = p.next;
            p.next = pre;
            // p pre 转移
            pre = p;
            p = next;
        }
        // pre 是新的head
        ListNode q = pre;
        // 单链表 + 1
        boolean needPlus = true;
        while (q != null) {
            if (needPlus) {
                q.val++;
                needPlus = false;
            }
            // 判断是否需要进位
            if (q.val >= 10) {
                needPlus = true;
                q.val %= 10;
            } else {
                // 没超过10 直接返回
                break;
            }
            q = q.next;
        }
        // 是否需要插入节点 在最终节点
        if (needPlus) {
            ListNode one = new ListNode(1);
            head.next = one;
            head = head.next;
        }
        // 再次翻转
        ListNode r = pre;
        ListNode newPre = null;
        while (r != null) {
            ListNode next = r.next;
            r.next = newPre;

            newPre = r;
            r = next;
        }
        return head;
    }
}
