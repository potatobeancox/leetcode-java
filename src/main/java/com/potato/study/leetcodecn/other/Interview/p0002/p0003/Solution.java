package com.potato.study.leetcodecn.other.Interview.p0002.p0003;


import com.potato.study.leetcode.domain.ListNode;

/**
 * 面试题 02.03. 删除中间节点
 *
 * 实现一种算法，删除单向链表中间的某个节点（即不是第一个或最后一个节点），假定你只能访问该节点。

  

 示例：

 输入：单向链表a->b->c->d->e->f中的节点c
 结果：不返回任何数据，但该链表变为a->b->d->e->f

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/delete-middle-node-lcci
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。。
 *
 */
public class Solution {


    /**
     * 删除下一个节点
     * @param node
     */
    public void deleteNode(ListNode node) {
        ListNode next = node.next;
        node.val = next.val;
        node.next = next.next;
        next.next = null;
    }
}
