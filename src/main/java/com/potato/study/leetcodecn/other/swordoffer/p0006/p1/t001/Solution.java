package com.potato.study.leetcodecn.other.swordoffer.p0006.p1.t001;

import com.potato.study.leetcode.domain.ListNode;

/**
 * 剑指 Offer 06. 从尾到头打印链表
 *
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。

  

 示例 1：

 输入：head = [1,3,2]
 输出：[2,3,1]
  

 限制：

 0 <= 链表长度 <= 10000

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 反转链表
     * 顺序打印
     * @param head
     * @return
     */
    public int[] reversePrint(ListNode head) {
        if (null == head) {
            return new int[0];
        }
        int len = 0;
        ListNode pre = null;
        ListNode p = head;
        while (p != null) {
            len++;
            ListNode q = p.next;
            p.next = pre;
            // 移动指针
            pre = p;
            p = q;
        }
        // 新的头就 pre
        int[] arr = new int[len];
        int index = 0;
        while (pre != null) {
            arr[index++] = pre.val;
            pre = pre.next;
        }
        return arr;
    }

}
