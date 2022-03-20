package com.potato.study.leetcodecn.p00330.t001;

import org.junit.Assert;

import java.util.Stack;

/**
 * 330. 按要求补齐数组
 *
 * 给定一个已排序的正整数数组 nums ，和一个正整数 n 。从 [1, n] 区间内选取任意个数字补充到 nums 中，使得 [1, n] 区间内的任何数字都可以用 nums 中某几个数字的和来表示。

 请返回 满足上述要求的最少需要补充的数字个数 。

  

 示例 1:

 输入: nums = [1,3], n = 6
 输出: 1
 解释:
 根据 nums 里现有的组合 [1], [3], [1,3]，可以得出 1, 3, 4。
 现在如果我们将 2 添加到 nums 中， 组合变为: [1], [2], [3], [1,3], [2,3], [1,2,3]。
 其和可以表示数字 1, 2, 3, 4, 5, 6，能够覆盖 [1, 6] 区间里所有的数。
 所以我们最少需要添加一个数字。
 示例 2:

 输入: nums = [1,5,10], n = 20
 输出: 2
 解释: 我们需要添加 [2,4]。
 示例 3:

 输入: nums = [1,2,2], n = 5
 输出: 0
  

 提示：

 1 <= nums.length <= 1000
 1 <= nums[i] <= 104
 nums 按 升序排列
 1 <= n <= 231 - 1

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/patching-array
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode-cn.com/problems/patching-array/solution/an-yao-qiu-bu-qi-shu-zu-by-leetcode-solu-klp1/
     * @param nums
     * @param n
     * @return
     */
    public int minPatches(int[] nums, int n) {
        // 使用 x 代表当前没有 匹配的数字  如果x 匹配了  【0 - 2 * x-1】 也匹配了 下一个就是 2 * x
        long x = 1;
        int count = 0;
        int index = 0;
        while (x <= n) {
            // 先比较 nums
            while (index < nums.length && nums[index] <= x) {
                x += nums[index];
                index++;
            }
            if (x > n) {
                break;
            }
            // x 没有 需要进行计数
            count++;
            x *= 2;
        }
        return count;
    }
}
