package com.potato.study.leetcodecn.p00487.t001;

import org.junit.Assert;

/**
 * 487. 最大连续1的个数 II
 *
 * 给定一个二进制数组 nums ，如果最多可以翻转一个 0 ，则返回数组中连续 1 的最大个数。

  

 示例 1：

 输入：nums = [1,0,1,1,0]
 输出：4
 解释：翻转第一个 0 可以得到最长的连续 1。
      当翻转以后，最大连续 1 的个数为 4。
 示例 2:

 输入：nums = [1,0,1,1,0,1]
 输出：4
  

 提示:

 1 <= nums.length <= 105
 nums[i] 不是 0 就是 1.
  

 进阶：如果输入的数字是作为 无限流 逐个输入如何处理？换句话说，内存不能存储下所有从流中输入的数字。您可以有效地解决吗？

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/max-consecutive-ones-ii
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int findMaxConsecutiveOnes(int[] nums) {
        int left = 0;
        int zeroCount = 0;
        int maxOneCount = 0;
        int oneCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeroCount++;
            }
            oneCount++;
            // 超过了 1个0 将 left 往右滑动到 去掉0
            while (zeroCount > 1) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                oneCount--;
                left++;
            }
            // 窗口内部的max
            maxOneCount = Math.max(maxOneCount, oneCount);
        }
        return maxOneCount;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[] {
                1,0,1,1,0
        };
        int maxConsecutiveOnes = solution.findMaxConsecutiveOnes(arr);
        System.out.println(maxConsecutiveOnes);
        Assert.assertEquals(4, maxConsecutiveOnes);
    }

}
