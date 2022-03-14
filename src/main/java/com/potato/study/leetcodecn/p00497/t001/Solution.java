package com.potato.study.leetcodecn.p00497.t001;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 496. 下一个更大元素 I
 *
 * 给你两个 没有重复元素 的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。

 请你找出 nums1 中每个元素在 nums2 中的下一个比其大的值。

 nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出 -1 。

  

 示例 1:

 输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
 输出: [-1,3,-1]
 解释:
 对于 num1 中的数字 4 ，你无法在第二个数组中找到下一个更大的数字，因此输出 -1 。
 对于 num1 中的数字 1 ，第二个数组中数字1右边的下一个较大数字是 3 。
 对于 num1 中的数字 2 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
 示例 2:

 输入: nums1 = [2,4], nums2 = [1,2,3,4].
 输出: [3,-1]
 解释:
     对于 num1 中的数字 2 ，第二个数组中的下一个较大数字是 3 。
 对于 num1 中的数字 4 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
  

 提示：

 1 <= nums1.length <= nums2.length <= 1000
 0 <= nums1[i], nums2[i] <= 104
 nums1和nums2中所有整数 互不相同
 nums1 中的所有整数同样出现在 nums2 中
  

 进阶：你可以设计一个时间复杂度为 O(nums1.length + nums2.length) 的解决方案吗？

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/next-greater-element-i
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 下一个 更大的元素
     * 使用 stack 记录下一个更大的元素
     * 从右往左遍历 nums2 num 使用 map 记录下一个更大的元素
     * 如果 当前 num 大于 stack 说明 stack 可以循环出栈 知道 出现更大的peek 此时 num 对应更大的就是 peek
     * 否则 （num 小于 stack peek） 说明当前peek 就是 更大的元素
     * 遍历 过程中 使用 map 记录 出现的元素 更大的元素
     * 遍历 num1 通过之前的map 生成结果
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> nextGreaterMap = new HashMap<>();
        for (int i = nums2.length - 1; i >= 0; i--) {
            // 如果 当前 num 大于 stack 说明 stack 可以循环出栈 知道 出现更大的peek 此时 num 对应更大的就是 peek
            while (!stack.isEmpty() && nums2[i] > stack.peek()) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                // 栈空 说明已经得到了最大
                nextGreaterMap.put(nums2[i], -1);
            } else {
                // 否则 （num 小于 stack peek） 说明当前peek 就是 更大的元素
                nextGreaterMap.put(nums2[i], stack.peek());
            }
            // 当前元素 入栈
            stack.push(nums2[i]);
        }
        // 遍历 num1 通过之前的map 生成结果
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            Integer target = nextGreaterMap.get(nums1[i]);
            result[i] = target;
        }
        return result;
    }

}
