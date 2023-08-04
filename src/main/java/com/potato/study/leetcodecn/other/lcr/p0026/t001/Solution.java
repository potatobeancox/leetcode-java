package com.potato.study.leetcodecn.other.lcr.p0026.t001;

import com.potato.study.leetcode.domain.ListNode;
import com.potato.study.leetcode.util.ListNodeUtil;

/**
 * LCR 026. 重排链表
 *
 * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 *
 *  L0 → L1 → … → Ln-1 → Ln 
 * 请将其重新排列后变为：
 *
 * L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …
 *
 * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 *  
 *
 * 示例 1:
 *
 *
 *
 * 输入: head = [1,2,3,4]
 * 输出: [1,4,2,3]
 * 示例 2:
 *
 *
 *
 * 输入: head = [1,2,3,4,5]
 * 输出: [1,5,2,4,3]
 *  
 *
 * 提示：
 *
 * 链表的长度范围为 [1, 5 * 104]
 * 1 <= node.val <= 1000
 *  
 *
 * 注意：本题与主站 143 题相同：https://leetcode-cn.com/problems/reorder-list/ 
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/LGjMqU
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     *
     * @param head
     */
    public void reorderList(ListNode head) {
        // 哨兵节点
        ListNode newHead = new ListNode(-1);
        newHead.next = head;
        // 快慢指针 找到中间点 偶数是后面那个
        ListNode fast = newHead;
        ListNode slow = newHead;
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
            if (fast.next == null) {
                break;
            }
            fast = fast.next;
        }
        // slow next 就是下一个的开始
        ListNode nextHead = slow.next;
        slow.next = null;
        // revert 最后那段
        ListNode pre = null;
        ListNode p = nextHead;
        while (p != null) {
            ListNode next = p.next;
            p.next = pre;
            // 转移
            pre = p;
            p = next;
        }
        // 反转之后的点
        nextHead = pre;
        // 归并一下
        ListNode res = new ListNode(-1);
        ListNode q = res;
        ListNode p1 = newHead.next;
        ListNode p2 = nextHead;
        while (p1 != null || p2 != null) {
            if (p1 != null && p2 != null) {
                q.next = p1;
                p1 = p1.next;
                q = q.next;


                q.next = p2;
                p2 = p2.next;
                q = q.next;

            } else if (p1 != null) {
                q.next = p1;
                q = q.next;

                p1 = p1.next;
            } else {
                // p2 != null
                q.next = p2;
                q = q.next;

                p2 = p2.next;
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        solution.reorderList(head);
        ListNodeUtil.printListNode(head);
    }
}
