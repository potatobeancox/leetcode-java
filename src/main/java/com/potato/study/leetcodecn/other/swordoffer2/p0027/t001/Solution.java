package com.potato.study.leetcodecn.other.swordoffer2.p0027.t001;

import com.potato.study.leetcode.domain.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer II 027. 回文链表
 *
 * 如果一个链表是回文，那么链表节点序列从前往后看和从后往前看是相同的。

  

 示例 1：



 输入: head = [1,2,3,3,2,1]
 输出: true
 示例 2：



 输入: head = [1,2]
 输出: false
  

 提示：

 链表 L 的长度范围为 [1, 105]
 0 <= node.val <= 9
  

 进阶：能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/aMhZSa
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 回文
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode p = head;
        while (p != null) {
            list.add(p.val);

            p = p.next;
        }

        // 看看 是够
        int left = 0;
        int right = list.size() - 1;
        while (left < right) {
            if (list.get(left) != list.get(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
