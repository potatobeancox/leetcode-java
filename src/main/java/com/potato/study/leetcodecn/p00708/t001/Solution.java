package com.potato.study.leetcodecn.p00708.t001;

import com.potato.study.leetcode.domain.node.val.next.Node;


/**
 * 708. 循环有序列表的插入
 *
 * 给定循环单调非递减列表中的一个点，写一个函数向这个列表中插入一个新元素 insertVal ，使这个列表仍然是循环非降序的。

 给定的可以是这个列表中任意一个顶点的指针，并不一定是这个列表中最小元素的指针。

 如果有多个满足条件的插入位置，你可以选择任意一个位置插入新的值，插入后整个列表仍然保持有序。

 如果列表为空（给定的节点是 null），你需要创建一个循环有序列表并返回这个节点。否则。请返回原先给定的节点。

  

 示例 1：


  
 输入：head = [3,4,1], insertVal = 2
 输出：[3,4,1,2]
 解释：在上图中，有一个包含三个元素的循环有序列表，你获得值为 3 的节点的指针，我们需要向表中插入元素 2 。
 新插入的节点应该在 1 和 3 之间，插入之后，整个列表如上图所示，最后返回节点 3 。


 示例 2：

 输入：head = [], insertVal = 1
 输出：[1]
 解释：列表为空（给定的节点是 null），创建一个循环有序列表并返回这个节点。
 示例 3：

 输入：head = [1], insertVal = 0
 输出：[1,0]
  

 提示：

 0 <= Number of Nodes <= 5 * 104
 -106 <= Node.val, insertVal <= 106

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/insert-into-a-sorted-circular-linked-list
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     *
     * @param head
     * @param insertVal
     * @return
     */
    public Node insert(Node head, int insertVal) {
        // 极端情况 head 为空
        if (head == null) {
            head = new Node(insertVal);
            head.next = head;
            return head;
        }
        // 从 head 开始 往后遍历 找到最小的点 如果 后一下小于等于 当前 说明 后一个是最小的节点
        Node cur = head;
        Node next = head.next;

        while (next != head && cur.val <= next.val) {
            cur = next;
            next = next.next;
        }
        // 记录 第一个和最后一个node的位置
        Node first;
        Node last;
        // 如果当前位置 再次回到了起点已经满足 后一个小于等于前一个，说明 当前环种每个点值都一样 head随便找开始和终止
        if (next == cur) {
            first = head;
            last = head.next;
        } else {
            // cur.val > next.val
            first = next;
            last = cur;
        }
        // 判断是否 小于等于 最小的 或者大于等于最大的
        if (insertVal <= first.val || insertVal >= last.val) {
            Node targetNode = new Node(insertVal);
            targetNode.next = first;
            last.next = targetNode;
            return head;
        }
        // 否则从 最小的开始找到位置  最小的 小于等于 target next 大于等于 target
        cur = first;
        next = first.next;

        while (cur.val > insertVal || insertVal > next.val) {
            cur = next;
            next = next.next;
        }
        // 肯定能找到 因为之前将最大值和最小值都处理了
        Node targetNode = new Node(insertVal);
        cur.next = targetNode;
        targetNode.next = next;

        return head;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        Node head = new Node(3);
        head.next = new Node(4);
        head.next.next = new Node(1);
        head.next.next.next = head;

        Node insert = solution.insert(head, 2);
        System.out.println(insert.val);
    }
}
