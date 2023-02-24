package com.potato.study.leetcodecn.p02568.t001;

import java.util.Arrays;

/**
 * 2568. 最小无法得到的或值
 *
 * 给你一个下标从 0 开始的整数数组 nums 。

 如果存在一些整数满足 0 <= index1 < index2 < ... < indexk < nums.length ，得到 nums[index1] | nums[index2] | ... | nums[indexk] = x ，那么我们说 x 是 可表达的 。换言之，如果一个整数能由 nums 的某个子序列的或运算得到，那么它就是可表达的。

 请你返回 nums 不可表达的 最小非零整数 。

  

 示例 1：

 输入：nums = [2,1]
 输出：4
 解释：1 和 2 已经在数组中，因为 nums[0] | nums[1] = 2 | 1 = 3 ，所以 3 是可表达的。由于 4 是不可表达的，所以我们返回 4 。
 示例 2：

 输入：nums = [5,3,2]
 输出：1
 解释：1 是最小不可表达的数字。
  

 提示：

 1 <= nums.length <= 105
 1 <= nums[i] <= 109

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/minimum-impossible-or
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {



    public int minImpossibleOR(int[] nums) {
        // 找到2的n次幂 第一个不在nums 中的
        for (int i = 0; i < 32; i++) {
            int target = (1 << i);
            boolean isContain = false;
            for (int num : nums) {
                if (num == target) {
                    isContain = true;
                    break;
                }
            }
            if (!isContain) {
                return target;
            }
        }
        return -1;
    }

}
