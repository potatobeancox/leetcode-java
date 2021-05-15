package com.potato.study.leetcodecn.sword2offer.p0018.p1.t001;

import com.potato.study.leetcode.domain.ListNode;

import java.util.Arrays;

/**
 * 剑指 Offer 18. 删除链表的节点
 *
 * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。

 返回删除后的链表的头节点。

 注意：此题对比原题有改动

 示例 1:

 输入: head = [4,5,1,9], val = 5
 输出: [4,1,9]
 解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
 示例 2:

 输入: head = [4,5,1,9], val = 1
 输出: [4,5,9]
 解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
  

 说明：

 题目保证链表中节点的值互不相同
 若使用 C 或 C++ 语言，你不需要 free 或 delete 被删除的节点

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/shan-chu-lian-biao-de-jie-dian-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public ListNode deleteNode(ListNode head, int val) {
        ListNode headNode = new ListNode(-1);
        headNode.next = head;
        ListNode pre = headNode;
        ListNode p = head;
        while (p != null && p.val != val) {
            p = p.next;
            pre = pre.next;
        }
        pre.next = p.next;
        return headNode.next;
    }

//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int n = 1;
//        int[] ints = solution.printNumbers(n);
//        System.out.println(Arrays.toString(ints));
//    }

}
