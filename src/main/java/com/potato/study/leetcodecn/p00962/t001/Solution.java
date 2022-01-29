package com.potato.study.leetcodecn.p00962.t001;

import java.util.Arrays;
import java.util.Stack;

import org.junit.Assert;

/**
 * 962. 最大宽度坡
 *
 * 给定一个整数数组 A，坡是元组 (i, j)，其中  i < j 且 A[i] <= A[j]。这样的坡的宽度为 j - i。
 *
 * 找出 A 中的坡的最大宽度，如果不存在，返回 0 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：[6,0,8,2,1,5]
 * 输出：4
 * 解释：
 * 最大宽度的坡为 (i, j) = (1, 5): A[1] = 0 且 A[5] = 5.
 * 示例 2：
 *
 * 输入：[9,8,1,0,1,9,4,0,4,1]
 * 输出：7
 * 解释：
 * 最大宽度的坡为 (i, j) = (2, 9): A[2] = 1 且 A[9] = 1.
 *  
 *
 * 提示：
 *
 * 2 <= A.length <= 50000
 * 0 <= A[i] <= 50000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-width-ramp
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode-cn.com/problems/maximum-width-ramp/solution/zui-da-kuan-du-po-dan-diao-zhan-cun-de-s-myj9/
     * @param nums
     * @return
     */
    public int maxWidthRamp(int[] nums) {
        // 用一个栈存储 当前 可能的左边节点 如果 index 小于 栈顶 入栈 否则 continue
        Stack<Integer> leftIndexStack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            if (leftIndexStack.isEmpty() || nums[leftIndexStack.peek()] > nums[i]) {
                leftIndexStack.add(i);
            }
        }
        // 从后往前便利 如果当前 栈顶小于 i 位置 循环出战并修改max
        int maxLen = 0;
        for (int right = nums.length - 1; right >= 0; right--) {
            while (!leftIndexStack.isEmpty() && nums[leftIndexStack.peek()] <= nums[right]) {
                Integer pop = leftIndexStack.pop();
                maxLen = Math.max(maxLen, right - pop);
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {
                6,0,8,2,1,5
        };
        int i = solution.maxWidthRamp(nums);
        System.out.println(i);
        Assert.assertEquals(4, i);
    }


}
