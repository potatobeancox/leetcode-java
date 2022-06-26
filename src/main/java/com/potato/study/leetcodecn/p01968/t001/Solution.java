package com.potato.study.leetcodecn.p01968.t001;

import java.util.Arrays;

/**
 * 1968. 构造元素不等于两相邻元素平均值的数组
 *
 * 给你一个 下标从 0 开始 的数组 nums ，数组由若干 互不相同的 整数组成。你打算重新排列数组中的元素以满足：重排后，数组中的每个元素都 不等于 其两侧相邻元素的 平均值 。

 更公式化的说法是，重新排列的数组应当满足这一属性：对于范围 1 <= i < nums.length - 1 中的每个 i ，(nums[i-1] + nums[i+1]) / 2 不等于 nums[i] 均成立 。

 返回满足题意的任一重排结果。

  

 示例 1：

 输入：nums = [1,2,3,4,5]
 输出：[1,2,4,5,3]
 解释：
 i=1, nums[i] = 2, 两相邻元素平均值为 (1+4) / 2 = 2.5
 i=2, nums[i] = 4, 两相邻元素平均值为 (2+5) / 2 = 3.5
 i=3, nums[i] = 5, 两相邻元素平均值为 (4+3) / 2 = 3.5
 示例 2：

 输入：nums = [6,2,0,9,7]
 输出：[9,7,6,2,0]
 解释：
 i=1, nums[i] = 7, 两相邻元素平均值为 (9+6) / 2 = 7.5
 i=2, nums[i] = 6, 两相邻元素平均值为 (7+2) / 2 = 4.5
 i=3, nums[i] = 2, 两相邻元素平均值为 (6+0) / 2 = 3
  

 提示：

 3 <= nums.length <= 105
 0 <= nums[i] <= 105

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/array-with-elements-not-equal-to-average-of-neighbors
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int[] rearrangeArray(int[] nums) {
        Arrays.sort(nums);
        int left = 0;
        int right = (nums.length + 1)/ 2;
        int index = 0;
        int[] result = new int[nums.length];
        while (index < nums.length) {
            if (index % 2 == 0) {
                result[index] = nums[left];
                left++;
            } else {
                result[index] = nums[right];
                right++;
            }
            index++;
        }
        return result;
    }

}
