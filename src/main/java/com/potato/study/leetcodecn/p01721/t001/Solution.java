package com.potato.study.leetcodecn.p01721.t001;

import com.potato.study.leetcode.domain.ListNode;

/**
 * 1721. 交换链表中的节点
 *
 * 给你链表的头节点 head 和一个整数 k 。
 *
 * 交换 链表正数第 k 个节点和倒数第 k 个节点的值后，返回链表的头节点（链表 从 1 开始索引）。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[1,4,3,2,5]
 * 示例 2：
 *
 * 输入：head = [7,9,6,6,7,8,3,0,9,5], k = 5
 * 输出：[7,9,6,6,8,7,3,0,9,5]
 * 示例 3：
 *
 * 输入：head = [1], k = 1
 * 输出：[1]
 * 示例 4：
 *
 * 输入：head = [1,2], k = 1
 * 输出：[2,1]
 * 示例 5：
 *
 * 输入：head = [1,2,3], k = 2
 * 输出：[1,2,3]
 *  
 *
 * 提示：
 *
 * 链表中节点的数目是 n
 * 1 <= k <= n <= 105
 * 0 <= Node.val <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/swapping-nodes-in-a-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 正向遍历到 节点 k
     * 同时快慢指针 往前遍历到 k个节点
     *
     * 交换值
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode swapNodes(ListNode head, int k) {
        ListNode slow = head;
        ListNode fast = head;
        // faset 先走
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        ListNode lastTarget = null;
        ListNode target = null;
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        lastTarget = slow;
        ListNode p = head;
        for (int i = 0; i < k -1; i++) {
            p = p.next;
        }
        target = p;
        // 交换
        int tmp = target.val;
        target.val = lastTarget.val;
        lastTarget.val = tmp;
        return head;
    }
}
