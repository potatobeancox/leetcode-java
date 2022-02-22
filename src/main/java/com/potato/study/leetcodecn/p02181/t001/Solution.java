package com.potato.study.leetcodecn.p02181.t001;

import com.potato.study.leetcode.domain.ListNode;

/**
 * 2181. 合并零之间的节点
 *
 * 给你一个链表的头节点 head ，该链表包含由 0 分隔开的一连串整数。链表的 开端 和 末尾 的节点都满足 Node.val == 0 。

 对于每两个相邻的 0 ，请你将它们之间的所有节点合并成一个节点，其值是所有已合并节点的值之和。然后将所有 0 移除，修改后的链表不应该含有任何 0 。

  返回修改后链表的头节点 head 。

  

 示例 1：


 输入：head = [0,3,1,0,4,5,2,0]
 输出：[4,11]
 解释：
 上图表示输入的链表。修改后的链表包含：
 - 标记为绿色的节点之和：3 + 1 = 4
 - 标记为红色的节点之和：4 + 5 + 2 = 11
 示例 2：


 输入：head = [0,1,0,3,0,2,2,0]
 输出：[1,3,4]
 解释：
 上图表示输入的链表。修改后的链表包含：
 - 标记为绿色的节点之和：1 = 1
 - 标记为红色的节点之和：3 = 3
 - 标记为黄色的节点之和：2 + 2 = 4
  

 提示：

 列表中的节点数目在范围 [3, 2 * 105] 内
 0 <= Node.val <= 1000
 不 存在连续两个 Node.val == 0 的节点
 链表的 开端 和 末尾 节点都满足 Node.val == 0

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/merge-nodes-in-between-zeros
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public ListNode mergeNodes(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode p = head;
        ListNode newHead = new ListNode(-1);
        newHead.next = head;
        ListNode q = newHead;
        ListNode pre = newHead;
        while (p != null) {
            if (p.val == 0) {
                pre = q;
                q = q.next;
                q.val = 0;
            } else {
                q.val += p.val;
            }
            p = p.next;
        }
        if (q.val == 0) {
            pre.next = null;
        } else {
            q.next = null;
        }
        return newHead.next;
    }
}
