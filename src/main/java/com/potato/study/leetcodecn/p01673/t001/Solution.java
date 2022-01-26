package com.potato.study.leetcodecn.p01673.t001;

import org.junit.Assert;

import java.util.Arrays;
import java.util.Stack;

/**
 * 1673. 找出最具竞争力的子序列
 *
 * 给你一个整数数组 nums 和一个正整数 k ，返回长度为 k 且最具 竞争力 的 nums 子序列。

 数组的子序列是从数组中删除一些元素（可能不删除元素）得到的序列。

 在子序列 a 和子序列 b 第一个不相同的位置上，如果 a 中的数字小于 b 中对应的数字，那么我们称子序列 a 比子序列 b（相同长度下）更具 竞争力 。 例如，[1,3,4] 比 [1,3,5] 更具竞争力，在第一个不相同的位置，也就是最后一个位置上， 4 小于 5 。

  

 示例 1：

 输入：nums = [3,5,2,6], k = 2
 输出：[2,6]
 解释：在所有可能的子序列集合 {[3,5], [3,2], [3,6], [5,2], [5,6], [2,6]} 中，[2,6] 最具竞争力。
 示例 2：

 输入：nums = [2,4,3,3,5,4,9,6], k = 4
 输出：[2,3,3,4]
  

 提示：

 1 <= nums.length <= 105
 0 <= nums[i] <= 109
 1 <= k <= nums.length

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/find-the-most-competitive-subsequence
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int[] mostCompetitive(int[] nums, int k) {
        // 单调栈 最开始添加一个 -1 作为哨兵好处理
        Stack<Integer> stack = new Stack<>();
        stack.add(-1);
        // 遍历 nums 当前数字比栈顶小 是否可以出栈 循环出栈
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            // 当前数字比 栈顶小且 剩余数字 + 当前栈数字 大于 k
            while (stack.peek() > num
                    && stack.size() - 1 + nums.length - i > k
                    && stack.size() > 1) {
                stack.pop();
            }
            if (stack.size() - 1 < k) {
                stack.push(num);
            }
        }
        // 添加的 栈内 pop reverse
        int[] result = new int[k];
        for (int i = k-1; i >= 0; i--) {
            result[i] = stack.pop();
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {
                2,4,3,3,5,4,9,6
        };
        int k = 4;
        int[] ints = solution.mostCompetitive(nums, k);
        System.out.println(Arrays.toString(ints));
        Assert.assertArrayEquals(new int[]{
                2,3,3,4
        }, ints);
    }
}
