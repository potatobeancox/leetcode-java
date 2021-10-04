package com.potato.study.leetcodecn.p00725.t001;

import com.potato.study.leetcode.domain.ListNode;
import org.junit.Assert;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 725. 分隔链表
 *
 * 给你一个头结点为 head 的单链表和一个整数 k ，请你设计一个算法将链表分隔为 k 个连续的部分。

 每部分的长度应该尽可能的相等：任意两部分的长度差距不能超过 1 。这可能会导致有些部分为 null 。

 这 k 个部分应该按照在链表中出现的顺序排列，并且排在前面的部分的长度应该大于或等于排在后面的长度。

 返回一个由上述 k 部分组成的数组。

  
 示例 1：


 输入：head = [1,2,3], k = 5
 输出：[[1],[2],[3],[],[]]
 解释：
 第一个元素 output[0] 为 output[0].val = 1 ，output[0].next = null 。
 最后一个元素 output[4] 为 null ，但它作为 ListNode 的字符串表示是 [] 。
 示例 2：


 输入：head = [1,2,3,4,5,6,7,8,9,10], k = 3
 输出：[[1,2,3,4],[5,6,7],[8,9,10]]
 解释：
 输入被分成了几个连续的部分，并且每部分的长度相差不超过 1 。前面部分的长度大于等于后面部分的长度。
  

 提示：

 链表中节点的数目在范围 [0, 1000]
 0 <= Node.val <= 1000
 1 <= k <= 50

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/split-linked-list-in-parts
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 生成一组几个的list 然后按照数量拆分
     * @param head
     * @param k
     * @return
     */
    public ListNode[] splitListToParts(ListNode head, int k) {
        // 过一遍找到长度 放队列
        Queue<ListNode> queue = new LinkedList<>();
        int length = 0;
        ListNode p = head;
        while (p != null) {
            length++;
            queue.add(p);
            p = p.next;
        }
        // 根据长度计算每个段需要多长
        int[] size = new int[k];
        int tmp = length / k;
        Arrays.fill(size, tmp);
        tmp = length % k;
        for (int i = 0; i < size.length; i++) {
            if (tmp == 0) {
                break;
            }
            size[i]++;
            tmp--;
        }
        // 遍历一遍列表 生成结果
        ListNode[] result = new ListNode[k];
        for (int i = 0; i < k; i++) {
            // result i 位置
            ListNode tail = result[i];
            for (int j = 0; j < size[i]; j++) {
                ListNode poll = queue.poll();
                if (tail == null) {
                    result[i] = poll;
                } else {
                    tail.next = poll;
                }
                poll.next = null;
                tail = poll;
            }


        }
        return result;
    }
}
