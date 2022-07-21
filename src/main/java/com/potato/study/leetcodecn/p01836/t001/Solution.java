package com.potato.study.leetcodecn.p01836.t001;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import org.junit.Assert;

import com.potato.study.leetcode.domain.ListNode;
import com.potato.study.leetcode.util.LeetcodeInputUtils;

/**
 * 1836. 从未排序的链表中移除重复元素
 *
 * 给定一个链表的第一个节点 head ，找到链表中所有出现多于一次的元素，并删除这些元素所在的节点。
 *
 * 返回删除后的链表。
 *
 *  
 *
 * 示例 1:
 *
 *
 * 输入: head = [1,2,3,2]
 * 输出: [1,3]
 * 解释: 2 在链表中出现了两次，所以所有的 2 都需要被删除。删除了所有的 2 之后，我们还剩下 [1,3] 。
 * 示例 2:
 *
 *
 * 输入: head = [2,1,1,2]
 * 输出: []
 * 解释: 2 和 1 都出现了两次。所有元素都需要被删除。
 * 示例 3:
 *
 *
 * 输入: head = [3,2,2,1,3,2,4]
 * 输出: [1,4]
 * 解释: 3 出现了两次，且 2 出现了三次。移除了所有的 3 和 2 后，我们还剩下 [1,4] 。
 *  
 *
 * 提示：
 *
 * 链表中节点个数的范围是 [1, 105]
 * 1 <= Node.val <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/remove-duplicates-from-an-unsorted-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public ListNode deleteDuplicatesUnsorted(ListNode head) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> duplicateSet = new HashSet<>();
        ListNode p = head;
        while (p != null) {
            if (set.contains(p.val)) {
                duplicateSet.add(p.val);
            }
            set.add(p.val);
            p = p.next;
        }
        ListNode newHead = new ListNode(-1);
        newHead.next = head;
        p = newHead;
        while (p.next != null) {
            // 删除
            if (duplicateSet.contains(p.next.val)) {
                p.next = p.next.next;
            } else {
                p = p.next;
            }
        }
        return newHead.next;
    }
}
