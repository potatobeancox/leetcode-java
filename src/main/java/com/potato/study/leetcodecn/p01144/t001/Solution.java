package com.potato.study.leetcodecn.p01144.t001;


import org.junit.Assert;

/**
 * 1144. 递减元素使数组呈锯齿状
 *
 * 给你一个整数数组 nums，每次 操作 会从中选择一个元素并 将该元素的值减少 1。

 如果符合下列情况之一，则数组 A 就是 锯齿数组：

 每个偶数索引对应的元素都大于相邻的元素，即 A[0] > A[1] < A[2] > A[3] < A[4] > ...
 或者，每个奇数索引对应的元素都大于相邻的元素，即 A[0] < A[1] > A[2] < A[3] > A[4] < ...
 返回将数组 nums 转换为锯齿数组所需的最小操作次数。

  

 示例 1：

 输入：nums = [1,2,3]
 输出：2
 解释：我们可以把 2 递减到 0，或把 3 递减到 1。
 示例 2：

 输入：nums = [9,6,1,6,2]
 输出：4
  

 提示：

 1 <= nums.length <= 1000
 1 <= nums[i] <= 1000

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/decrease-elements-to-make-array-zigzag
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode-cn.com/problems/decrease-elements-to-make-array-zigzag/solution/jie-ti-si-lu-by-vambo/
     * @param nums
     * @return
     */
    public int movesToMakeZigzag(int[] nums) {
        // 分别计算 奇数坐标和偶数坐标 如果需要小于 左右邻居需要减去多少次 最少
        int odd = 0;
        int even = 0;
        for (int i = 0; i < nums.length; i++) {
            int leftCount = 0;
            if (i > 0 && nums[i] >= nums[i-1]) {
                leftCount = nums[i] - nums[i-1] + 1;
            }
            int rightCount = 0;
            if (i < nums.length - 1 && nums[i] >= nums[i+1]) {
                rightCount = nums[i] - nums[i+1] + 1;
            }
            int needMinusCount = Math.max(leftCount, rightCount);
            if (i % 2 == 0) {
                even += needMinusCount;
            } else {
                odd += needMinusCount;
            }
        }
        return Math.min(odd, even);
    }
}
