package com.potato.study.leetcodecn.p02455.t001;

import org.junit.Assert;

/**
 * 2455. 可被三整除的偶数的平均值
 *
 * 给你一个由正整数组成的整数数组 nums ，返回其中可被 3 整除的所有偶数的平均值。

 注意：n 个元素的平均值等于 n 个元素 求和 再除以 n ，结果 向下取整 到最接近的整数。

  

 示例 1：

 输入：nums = [1,3,6,10,12,15]
 输出：9
 解释：6 和 12 是可以被 3 整除的偶数。(6 + 12) / 2 = 9 。
 示例 2：

 输入：nums = [1,2,4,7,10]
 输出：0
 解释：不存在满足题目要求的整数，所以返回 0 。
  

 提示：

 1 <= nums.length <= 1000
 1 <= nums[i] <= 1000

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/average-value-of-even-numbers-that-are-divisible-by-three
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int averageValue(int[] nums) {
        // 2455 可以被3整除的所有 偶数的平均值，也就是 可以被6整除
        long sum = 0;
        int count = 0;
        for (int num : nums) {
            if (num % 6 == 0) {
                sum += num;
                count++;
            }
        }
        if (count == 0) {
            return 0;
        }
        return (int) (sum / count);
    }

}
