package com.potato.study.leetcodecn.p01950.t001;

import java.util.Stack;

/**
 * 1950. 所有子数组最小值中的最大值
 *
 * 给你一个长度为 n 的整数数组 nums ，你需要处理 n 个查询。

 对于第 i （0 <= i < n）个查询：

 你需要先找出 nums 的所有长度为 i + 1 的子数组中的 最小值 。
 在这些最小值中找出 最大值 作为答案。
 返回一个 下标从 0 开始 的长度为 n 的整数数组 ans ，ans[i] 代表第 i 个查询的答案。

  

 示例 1：

 输入: nums = [0,1,2,4]
 输出: [4,2,1,0]
 解释:
 i = 0:
 - 大小为 1 的子数组为 [0], [1], [2], [4]
 - 有最大的最小值的子数组是 [4], 它的最小值是 4
 i = 1:
 - 大小为 2 的子数组为 [0,1], [1,2], [2,4]
 - 有最大的最小值的子数组是 [2,4], 它的最小值是 2
 i = 2:
 - 大小为 3 的子数组为 [0,1,2], [1,2,4]
 - 有最大的最小值的子数组是 [1,2,4], 它的最小值是 1
 i = 3:
 - 大小为 4 的子数组为 [0,1,2,4]
 - 有最大的最小值的子数组是 [0,1,2,4], 它的最小值是 0
 示例 2：

 输入: nums = [10,20,50,10]
 输出: [50,20,10,10]
 解释:
 i = 0:
 - 大小为 1 的子数组为 [10], [20], [50], [10]
 - 有最大的最小值的子数组是 [50], 它的最小值是 50
 i = 1:
 - 大小为 2 的子数组为 [10,20], [20,50], [50,10]
 - 有最大的最小值的子数组是 [20,50], 它的最小值是 20
 i = 2:
 - 大小为 3 的子数组为 [10,20,50], [20,50,10]
 - 有最大的最小值的子数组是 [10,20,50], 它的最小值是 10
 i = 3:
 - 大小为 4 的子数组为 [10,20,50,10]
 - 有最大的最小值的子数组是 [10,20,50,10], 它的最小值是 10
  

 提示：

 n == nums.length
 1 <= n <= 105
 0 <= nums[i] <= 109


 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/maximum-of-minimum-values-in-all-subarrays
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int[] findMaximums(int[] nums) {
        // 一个单调递增栈 站内 单调递增 存index 如果栈顶 小于 cur 说明 栈顶才是最小值
        Stack<Integer> indexStack = new Stack<>();
        // 如果当前值 小于等于 栈顶 说明 从 栈顶+1 到 curIndex 这个长度的子数组 curIndex 是最小值
        int[] res = new int[nums.length];
        for (int i = 0; i <= nums.length; i++) {
            int currentVal;
            if (i == nums.length) {
                currentVal = 0;
            } else {
                currentVal = nums[i];
            }
            // 当前值是不是最小值
            while (!indexStack.isEmpty() && nums[indexStack.peek()] >= currentVal) {
                // indexStack.peek() 在哪个子数组可以作为最小值
                int targetIndex = indexStack.pop();
                int left;
                if (indexStack.isEmpty()) {
                    left = 0;
                } else {
                    left = indexStack.peek() + 1;
                }
                int right = i - 1;
                int len = right - left + 1;
                res[len-1] = Math.max(res[len - 1], nums[targetIndex]);
            }
            indexStack.push(i);
        }
        // 从 最长开始找
        for (int i = nums.length-1; i > 0; i--) {
            res[i-1] = Math.max(res[i-1], res[i]);
        }
        return res;
    }

}
