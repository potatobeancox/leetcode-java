package com.potato.study.leetcodecn.other.Interview.p0002.p0006;


import java.util.ArrayList;
import java.util.List;

import com.potato.study.leetcode.domain.ListNode;

/**
 * 面试题 02.06. 回文链表
 *
 * 编写一个函数，检查输入的链表是否是回文的。
 *
 *  
 *
 * 示例 1：
 *
 * 输入： 1->2
 * 输出： false
 * 示例 2：
 *
 * 输入： 1->2->2->1
 * 输出： true
 *  
 *
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-linked-list-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // mianshi 0206
    public boolean isPalindrome(ListNode head) {
        List<Integer> list = new ArrayList<>();
        if (head == null) {
            return false;
        }
        ListNode p = head;
        while (p != null) {
            list.add(p.val);
            p = p.next;
        }
        int left = 0;
        int right = list.size() - 1;
        while (left < right) {
            if (list.get(left) != list.get(right)) {
                return false;
            }
        }
        return true;
    }
}
