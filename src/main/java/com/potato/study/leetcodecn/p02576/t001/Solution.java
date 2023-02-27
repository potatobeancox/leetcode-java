package com.potato.study.leetcodecn.p02576.t001;

import java.util.Arrays;

/**
 * 2576. 求出最多标记下标

 给你一个下标从 0 开始的整数数组 nums 。

 一开始，所有下标都没有被标记。你可以执行以下操作任意次：

 选择两个 互不相同且未标记 的下标 i 和 j ，满足 2 * nums[i] <= nums[j] ，标记下标 i 和 j 。
 请你执行上述操作任意次，返回 nums 中最多可以标记的下标数目。

  

 示例 1：

 输入：nums = [3,5,2,4]
 输出：2
 解释：第一次操作中，选择 i = 2 和 j = 1 ，操作可以执行的原因是 2 * nums[2] <= nums[1] ，标记下标 2 和 1 。
 没有其他更多可执行的操作，所以答案为 2 。
 示例 2：

 输入：nums = [9,2,5,4]
 输出：4
 解释：第一次操作中，选择 i = 3 和 j = 0 ，操作可以执行的原因是 2 * nums[3] <= nums[0] ，标记下标 3 和 0 。
 第二次操作中，选择 i = 1 和 j = 2 ，操作可以执行的原因是 2 * nums[1] <= nums[2] ，标记下标 1 和 2 。
 没有其他更多可执行的操作，所以答案为 4 。
 示例 3：

 输入：nums = [7,6,8]
 输出：0
 解释：没有任何可以执行的操作，所以答案为 0 。
  

 提示：

 1 <= nums.length <= 105
 1 <= nums[i] <= 109

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/find-the-maximum-number-of-marked-indices
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode.cn/problems/find-the-maximum-number-of-marked-indices/solution/er-fen-da-an-pythonjavacgo-by-endlessche-t9f5/
     * @param nums
     * @return
     */
    public int maxNumOfMarkedIndices(int[] nums) {
        // 排序
        Arrays.sort(nums);
        int i = 0;
        // 选择中间点往后 依次枚举j 让j匹配 i的关系 往后移动i 最后记录移动了多少个i
        for (int j = (nums.length+1)/2 ; j < nums.length; j++) {
            if (nums[i] * 2 <= nums[j]) {
                i++;
            }
        }
        return 2 * i;
    }

}
