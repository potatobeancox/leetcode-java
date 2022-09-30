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
        Node node = new Node();
        node.val = insertVal;
        // 1. 第一种情况 null 的时候直接创建
        if (null == head) {
            node.next = node;
            return node;
        }
        // 2. 只有一个节点  直接插入在后面就行
        Node next = head.next;
        if (next == head) {
            head.next = node;
            node.next = next;
            return head;
        }
        // 3. 如果和这个连表有环存在的情况 需要先找到位置 再插入，有几种条件
        Node pre = head;
        // 找到插入位置 在  pre 和 next之间
        while (next != head) {
            // 当前点在 两个点之间
            if (pre.val <= insertVal && insertVal <= next.val) {
                pre.next = node;
                node.next = next;
                return head;
            }
            // 当前是不是 分界点
            // 3.1 当前是分界点 且 当前点大于等于 大的那个 那么就是 直接插在最后
            // 3.2 当前是分界点 且当前点 小于 大的那个，从小的那个依次往后找 定位到 node 小于等于 interval 小于等于 next点
            // 3.3 当前点不是分界点 等于 insertVal 小于等于 next 插在之间
            next = next.next;
            pre = pre.next;
        }
        // 不是 3.3 往后找
        pre.next = node;
        node.next = next;
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
