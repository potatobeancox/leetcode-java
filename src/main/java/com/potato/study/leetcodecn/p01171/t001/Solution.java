package com.potato.study.leetcodecn.p01171.t001;


import com.potato.study.leetcode.domain.ListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 1171. 从链表中删去总和值为零的连续节点
 *
 * 给你一个链表的头节点 head，请你编写代码，反复删去链表中由 总和 值为 0 的连续节点组成的序列，直到不存在这样的序列为止。

 删除完毕后，请你返回最终结果链表的头节点。

  

 你可以返回任何满足题目要求的答案。

 （注意，下面示例中的所有序列，都是对 ListNode 对象序列化的表示。）

 示例 1：

 输入：head = [1,2,-3,3,1]
 输出：[3,1]
 提示：答案 [1,2,1] 也是正确的。
 示例 2：

 输入：head = [1,2,3,-3,4]
 输出：[1,2,4]
 示例 3：

 输入：head = [1,2,3,-3,-2]
 输出：[1]
  

 提示：

 给你的链表中可能有 1 到 1000 个节点。
 对于链表中的每个节点，节点的值：-1000 <= node.val <= 1000.

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {



    public ListNode removeZeroSumSublists(ListNode head) {
        // 使用 map 记录 sum值 2 最后依次出现的节点
        int sum = 0;
        // 哨兵节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode p = dummy;
        Map<Integer, ListNode> valueLastAppearMap = new HashMap<>();
        while (p != null) {
            sum += p.val;
            valueLastAppearMap.put(sum, p);

            p = p.next;
        }
        // 再次遍历 从map中找到节点 如果不是当前节点 就删除 中间的节点
        sum = 0;
        p = dummy;
        while (p != null) {
            sum += p.val;
            ListNode lastNode = valueLastAppearMap.get(sum);
            // 如果还有其他节点是 sum 一致的，直接删除
            if (!lastNode.equals(p)) {
                p.next = lastNode.next;
            }

            p = p.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        // [1,2,-3,3,1]
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(-3);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(1);
        ListNode listNode = solution.removeZeroSumSublists(head);
        System.out.println(listNode);
    }



}
