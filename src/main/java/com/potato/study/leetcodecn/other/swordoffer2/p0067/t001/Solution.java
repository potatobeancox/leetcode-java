package com.potato.study.leetcodecn.other.swordoffer2.p0067.t001;

/**
 * 剑指 Offer II 067. 最大的异或
 *
 * 给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。

  

 示例 1：

 输入：nums = [3,10,5,25,2,8]
 输出：28
 解释：最大运算结果是 5 XOR 25 = 28.
 示例 2：

 输入：nums = [14,70,53,83,49,91,36,80,92,51,66,70]
 输出：127
  

 提示：

 1 <= nums.length <= 2 * 105
 0 <= nums[i] <= 231 - 1
  

 注意：本题与主站 421 题相同： https://leetcode-cn.com/problems/maximum-xor-of-two-numbers-in-an-array/

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/ms70jA
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // ii 067
    public int findMaximumXOR(int[] nums) {
        int n = nums.length;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                max = Math.max(max, nums[i]^nums[j]);
            }
        }
        if (max == Integer.MIN_VALUE) {
            return 0;
        }
        return max;
    }


}
