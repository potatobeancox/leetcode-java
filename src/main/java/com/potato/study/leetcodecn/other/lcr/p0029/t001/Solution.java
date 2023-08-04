package com.potato.study.leetcodecn.other.lcr.p0029.t001;

import com.potato.study.leetcode.domain.node.val.next.Node;

/**
 * LCR 029. 循环有序列表的插入
 *
 * 给定循环单调非递减列表中的一个点，写一个函数向这个列表中插入一个新元素 insertVal ，使这个列表仍然是循环升序的。
 *
 * 给定的可以是这个列表中任意一个顶点的指针，并不一定是这个列表中最小元素的指针。
 *
 * 如果有多个满足条件的插入位置，可以选择任意一个位置插入新的值，插入后整个列表仍然保持有序。
 *
 * 如果列表为空（给定的节点是 null），需要创建一个循环有序列表并返回这个节点。否则。请返回原先给定的节点。
 *
 *  
 *
 * 示例 1：
 *
 *
 *  
 *
 * 输入：head = [3,4,1], insertVal = 2
 * 输出：[3,4,1,2]
 * 解释：在上图中，有一个包含三个元素的循环有序列表，你获得值为 3 的节点的指针，我们需要向表中插入元素 2 。新插入的节点应该在 1 和 3 之间，插入之后，整个列表如上图所示，最后返回节点 3 。
 *
 *
 * 示例 2：
 *
 * 输入：head = [], insertVal = 1
 * 输出：[1]
 * 解释：列表为空（给定的节点是 null），创建一个循环有序列表并返回这个节点。
 * 示例 3：
 *
 * 输入：head = [1], insertVal = 0
 * 输出：[1,0]
 *  
 *
 * 提示：
 *
 * 0 <= Number of Nodes <= 5 * 10^4
 * -10^6 <= Node.val <= 10^6
 * -10^6 <= insertVal <= 10^6
 *  
 *
 * 注意：本题与主站 708 题相同： https://leetcode-cn.com/problems/insert-into-a-sorted-circular-linked-list/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/4ueAj6
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 只有一个 next 节点
     * @param head
     * @param insertVal
     * @return
     */
    public Node insert(Node head, int insertVal) {
        // 1 这个圈里边没有节点 直接插入 并构造圈
        Node insertNode = new Node(insertVal);
        if (head == null) {
            insertNode.next = insertNode;
            head = insertNode;
            return head;
        }
        // 2 当前这个圈只有一个节点 直接插入到 head后面
        if (head == head.next) {
            head.next = insertNode;
            insertNode.next = head;
            return head;
        }
        // 3 有2个节点以上 找到开始节点（最小）和结束节点 （最大）
        Node current = head;
        Node next = current.next;
        // next 小于current时或者 走到 head时
        while (current.val <= next.val && next != head) {
            current = next;
            next = next.next;
        }
        // 当前next 一定是开始节点 current 是最后一个
        Node start = next;
        Node end = current;
        // 3.1 比较下是不是 小于开始节点或者 大于最后节点
        if (insertVal <= start.val) {
            // 小于等于 最小值 放在最前面
            insertNode.next = start;
            end.next = insertNode;
            return head;
        }
        if (insertVal >= end.val) {
            // 大于等于 最大值 放在最后面
            end.next = insertNode;
            insertNode.next = start;
            return head;
        }
        // 3.2 肯定在中间，要找下问题，先判定 整个圈是否一致 一致的话 直接插入最小的后面
        if (start.val == end.val) {
            // 整个元素都一致
            // 小于等于 最小值 放在最前面
            insertNode.next = start;
            end.next = insertNode;
            return head;
        }
        // 3.3 不一致 找到要插入的位置 p是小于等于 val的 且 q是大于等于 val的 插入pq中间
        Node p = start;
        Node q = start.next;

        while (p.val > insertVal || q.val < insertVal) {
            p = q;
            q = q.next;
        }

        // pq 之间就是位置
        p.next = insertNode;
        insertNode.next = q;
        return head;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        Node head = new Node(3);
        head.next = new Node(4);
        head.next.next = new Node(1);
        head.next.next.next = head;
        Node insert = solution.insert(head, 2);
        System.out.println(insert);
    }
}
