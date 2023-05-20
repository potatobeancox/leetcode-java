package com.potato.study.leetcodecn.p02674.t001;

import com.potato.study.leetcode.domain.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * 2674. 拆分循环链表
 *
 * 现给定一个由正整数组成的 循环链表 list ，你的任务是将其拆分为 2 个 循环链表 ，使得第一个链表包含 list 前半部分 的节点（即 ceil(list.length / 2) 个节点），顺序与 list 中的顺序相同，而第二个链表包含 list 中 剩余 的节点，顺序也与 list 中的顺序相同。
 *
 * 返回一个长度为 2 的数组，其中第一个元素是表示 前半部分 链表的 循环链表 ，第二个元素是表示 后半部分 链表的 循环链表 。
 *
 * 循环链表 是一个普通的链表，唯一的区别是最后一个节点的下一个节点是头节点。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,5,7]
 * 输出：[[1,5],[7]]
 * 解释：初始链表有3个节点，因此前半部分是前两个元素，剩下的 1 个节点在后半部分。
 * 示例 2：
 *
 * 输入：nums = [2,6,1,5]
 * 输出：[[2,6],[1,5]]
 * 解释：初始链表有4个节点，因此前半部分是前两个元素，剩下的 2 个节点在后半部分。
 *  
 *
 * 提示：
 *
 * list 中的节点数范围为 [2, 105]
 * 0 <= Node.val <= 109
 * LastNode.next = FirstNode ，其中 LastNode 是链表的最后一个节点，FirstNode 是第一个节点。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/split-a-circular-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     *
     * @param list
     * @return
     */
    public ListNode[] splitCircularLinkedList(ListNode list) {
        if (list == null) {
            return new ListNode[2];
        }
        // 遍历 list 计数直到找到下一个 list
        int size = 1;
        ListNode p = list;
        while (p.next != list) {
            p = p.next;
            size++;
        }
        // 计算前半段要多少 根据数量 进行遍历 生成
        int firstPartLen = (size + 1) / 2;
        p = list;
        ListNode firstHead = new ListNode(-1);
        ListNode insertNode = firstHead;
        for (int i = 0; i < firstPartLen; i++) {
            insertNode.next = p;
            insertNode = insertNode.next;
            p = p.next;
        }
        // p 还能继续 insertNode需要进行连接
        insertNode.next = firstHead.next;
        ListNode[] resArray = new ListNode[2];
        resArray[0] = firstHead.next;

        ListNode secondHead = new ListNode(-1);
        insertNode = secondHead;

        while (p != list) {
            insertNode.next = p;
            insertNode = insertNode.next;
            p = p.next;
        }
        // 连接 insertNode 和 second 头部
        insertNode.next = secondHead.next;
        resArray[1] = secondHead.next;
        return resArray;
    }
}
