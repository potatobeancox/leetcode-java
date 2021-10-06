package com.potato.study.leetcodecn.p02012.t001;

/**
 * 2012. 数组美丽值求和
 *
 * 给你一个下标从 0 开始的整数数组 nums 。对于每个下标 i（1 <= i <= nums.length - 2），nums[i] 的 美丽值 等于：

 2，对于所有 0 <= j < i 且 i < k <= nums.length - 1 ，满足 nums[j] < nums[i] < nums[k]
 1，如果满足 nums[i - 1] < nums[i] < nums[i + 1] ，且不满足前面的条件
 0，如果上述条件全部不满足
 返回符合 1 <= i <= nums.length - 2 的所有 nums[i] 的 美丽值的总和 。

  

 示例 1：

 输入：nums = [1,2,3]
 输出：2
 解释：对于每个符合范围 1 <= i <= 1 的下标 i :
 - nums[1] 的美丽值等于 2
 示例 2：

 输入：nums = [2,4,6,4]
 输出：1
 解释：对于每个符合范围 1 <= i <= 2 的下标 i :
 - nums[1] 的美丽值等于 1
 - nums[2] 的美丽值等于 0
 示例 3：

 输入：nums = [3,2,1]
 输出：0
 解释：对于每个符合范围 1 <= i <= 1 的下标 i :
 - nums[1] 的美丽值等于 0
  

 提示：

 3 <= nums.length <= 105
 1 <= nums[i] <= 105

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/sum-of-beauty-in-the-array
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     *
     * @param nums
     * @return
     */
    public int sumOfBeauties(int[] nums) {
        // left i 之前最大值 right i之后的最小值
        int[] left = new int[nums.length];
        int[] right = new int[nums.length];
        // 遍历 nums 找到是否比left 大且比 right 小，是的话 就是 2 否则 前后比较是不是1
        left[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            left[i] = Math.max(left[i-1], nums[i]);
        }
        right[nums.length - 1] = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            right[i] = Math.min(right[i+1], nums[i]);
        }
        int total = 0;
        for (int i = 1; i < nums.length-1; i++) {
            if (left[i-1] < nums[i] && nums[i] < right[i+1]) {
                total += 2;
            } else if (nums[i-1] < nums[i] && nums[i] < nums[i+1]) {
                total += 1;
            }
        }
        return total;
    }
}

