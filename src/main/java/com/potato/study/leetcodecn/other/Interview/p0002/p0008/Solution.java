package com.potato.study.leetcodecn.other.Interview.p0002.p0008;


import com.potato.study.leetcode.domain.ListNode;

/**
 * 面试题 02.08. 环路检测
 *
 * 给定一个链表，如果它是有环链表，实现一个算法返回环路的开头节点。若环不存在，请返回 null。
 *
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是
 * -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：tail connects to node index 1
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * 示例 2：
 *
 *
 *
 * 输入：head = [1,2], pos = 0
 * 输出：tail connects to node index 0
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 * 示例 3：
 *
 *
 *
 * 输入：head = [1], pos = -1
 * 输出：no cycle
 * 解释：链表中没有环。
 *  
 *
 * 进阶：
 *
 * 你是否可以不用额外空间解决此题？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/linked-list-cycle-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        // 快慢指针 找到 相交的点 ，
        ListNode fast = head;
        ListNode slow = head;
        do {
            if (fast == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next;
            // 没有环
            if (fast == null) {
                return null;
            }
            fast = fast.next;
        } while (fast != slow);
        // 一个点从起点出发，一个点从交点出发 相遇的点就是 环的开始
        ListNode p1 = head;
        ListNode p2 = fast;
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }
}
