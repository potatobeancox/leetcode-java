package com.potato.study.leetcodecn.p01558.t001;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1558. 得到目标数组的最少函数调用次数
 *
 * 给你一个与 nums 大小相同且初始值全为 0 的数组 arr ，请你调用以上函数得到整数数组 nums 。
 *
 * 请你返回将 arr 变成 nums 的最少函数调用次数。
 *
 * 答案保证在 32 位有符号整数以内。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,5]
 * 输出：5
 * 解释：给第二个数加 1 ：[0, 0] 变成 [0, 1] （1 次操作）。
 * 将所有数字乘以 2 ：[0, 1] -> [0, 2] -> [0, 4] （2 次操作）。
 * 给两个数字都加 1 ：[0, 4] -> [1, 4] -> [1, 5] （2 次操作）。
 * 总操作次数为：1 + 2 + 2 = 5 。
 * 示例 2：
 *
 * 输入：nums = [2,2]
 * 输出：3
 * 解释：给两个数字都加 1 ：[0, 0] -> [0, 1] -> [1, 1] （2 次操作）。
 * 将所有数字乘以 2 ： [1, 1] -> [2, 2] （1 次操作）。
 * 总操作次数为： 2 + 1 = 3 。
 * 示例 3：
 *
 * 输入：nums = [4,2,5]
 * 输出：6
 * 解释：（初始）[0,0,0] -> [1,0,0] -> [1,0,1] -> [2,0,2] -> [2,1,2] -> [4,2,4] -> [4,2,5] （nums 数组）。
 * 示例 4：
 *
 * 输入：nums = [3,2,2,4]
 * 输出：7
 * 示例 5：
 *
 * 输入：nums = [2,4,8,16]
 * 输出：8
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-numbers-of-function-calls-to-make-target-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 两种操作 1 元素加1 整体乘2
     * 转换成 找到 nums 中最高位的数目 和找到 一共有多少个位置是 1
     * @param nums
     * @return
     */
    public int minOperations(int[] nums) {
        int oneCount = 0;
        int maxBit = 0;
        for (int i = 0; i < 32; i++) {
            int bit = (1 << i);
            // 对于nums 每个位置 找到最大i位置 找到总1 数量
            for (int num : nums) {
                // 如果这个位置是1
                if ((num & bit) != 0) {
                    oneCount++;
                    maxBit = Math.max(maxBit, i);
                }
            }
        }
        return maxBit + oneCount;
    }
}
