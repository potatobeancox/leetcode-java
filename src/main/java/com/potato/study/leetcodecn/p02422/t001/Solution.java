package com.potato.study.leetcodecn.p02422.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 2422. Merge Operations to Turn Array Into a Palindrome
 *
 * You are given an array nums consisting of positive integers.
 *
 * You can perform the following operation on the array any number of times:
 *
 * Choose any two adjacent elements and replace them with their sum.
 * For example, if nums = [1,2,3,1], you can apply one operation to make it [1,5,1].
 * Return the minimum number of operations needed to turn the array into a palindrome.
 *
 *  
 *
 * Example 1:
 *
 * Input: nums = [4,3,2,1,2,3,1]
 * Output: 2
 * Explanation: We can turn the array into a palindrome in 2 operations as follows:
 * - Apply the operation on the fourth and fifth element of the array, nums becomes equal to [4,3,2,3,3,1].
 * - Apply the operation on the fifth and sixth element of the array, nums becomes equal to [4,3,2,3,4].
 * The array [4,3,2,3,4] is a palindrome.
 * It can be shown that 2 is the minimum number of operations needed.
 * Example 2:
 *
 * Input: nums = [1,2,3,4]
 * Output: 3
 * Explanation: We do the operation 3 times in any position, we obtain the array [10] at the end which is a palindrome.
 *  
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 106
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/merge-operations-to-turn-array-into-a-palindrome
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int minimumOperations(int[] nums) {
        // 双端队列 两面 判断 是不是相同 不相同 小的那个 相加
        Deque<Long> deque = new LinkedList<>();
        for (int num : nums) {
            deque.add((long)num);
        }
        int count = 0;
        while (deque.size() > 1) {
            long left = deque.pollFirst();
            long right = deque.pollLast();
            if (left == right) {
                continue;
            }
            // 不相等
            if (deque.size() == 0) {
                if (left != right) {
                    count++;
                }
                break;
            }
            if (left < right) {
                left += deque.pollFirst();

                deque.addFirst(left);
                deque.addLast(right);
            } else {
                right += deque.pollLast();

                deque.addFirst(left);
                deque.addLast(right);
            }
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] ints = LeetcodeInputUtils.inputString2IntArray("[4,3,2,1,2,3,1]");
        int i = solution.minimumOperations(ints);
        System.out.println(i);
        Assert.assertEquals(2, i);


        ints = LeetcodeInputUtils.inputString2IntArray("[1,2,3,4]");
        i = solution.minimumOperations(ints);
        System.out.println(i);
        Assert.assertEquals(3, i);


        ints = LeetcodeInputUtils.inputString2IntArray("[90330,16730,7898,110,104,641,1,217318,229335,47379,2722,169825,446667,502479,646991,477905,470800,302249,931779,931779,302249,470800,477905,646991,502479,446667,169825,497711,115814]");
        i = solution.minimumOperations(ints);
        System.out.println(i);
        Assert.assertEquals(26, i);
    }

}
