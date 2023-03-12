package com.potato.study.leetcodecn.p02263.t001;

import java.util.Arrays;

/**
 * 2263. 数组变为有序的最小操作次数
 *
 * 给你一个下标从 0 开始的整数数组 nums 。在一步操作中，你可以：
 *
 * 在范围 0 <= i < nums.length 内选出一个下标 i
 * 将 nums[i] 的值变为 nums[i] + 1 或 nums[i] - 1
 * 返回将数组 nums 变为 非递增 或 非递减 所需的 最小 操作次数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [3,2,4,5,0]
 * 输出：4
 * 解释：
 * 一种可行的操作顺序，能够将 nums 变为非递增排列：
 * - nums[1] 加 1 一次，使其变为 3 。
 * - nums[2] 减 1 一次，使其变为 3 。
 * - nums[3] 减 1 两次，使其变为 3 。
 * 经过 4 次操作后，nums 变为 [3,3,3,3,0] ，按非递增顺序排列。
 * 注意，也可以用 4 步操作将 nums 变为 [4,4,4,4,0] ，同样满足题目要求。
 * 可以证明最少需要 4 步操作才能将数组变为非递增或非递减排列。
 * 示例 2：
 *
 * 输入：nums = [2,2,3,4]
 * 输出：0
 * 解释：数组已经是按非递减顺序排列了，无需执行任何操作，返回 0 。
 * 示例 3：
 *
 * 输入：nums = [0]
 * 输出：0
 * 解释：数组已经是按非递减顺序排列了，无需执行任何操作，返回 0 。
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/make-array-non-decreasing-or-non-increasing
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode-cn.com/problems/total-appeal-of-a-string/solution/by-endlesscheng-g405/
     * @param s
     * @return
     */
    public long appealSum(String s) {
        // 一次遍历 记录 s中每个字符出现的最后一个位置 每次更新
        int[] lastAppear = new int[26];
        Arrays.fill(lastAppear, -1);
        // 使用 一个 遍历 记录 以当前i作为最后一个 字符的字串 的引力数的和 current = current + (i - pre[这个字母出现最后位置]);
        long result = 0;
        long currentSum = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            int charIndex = chars[i] - 'a';
            currentSum += (i-lastAppear[charIndex]);
            // 计算结果值
            result += currentSum;
            // 记录最后一次出现的位置
            lastAppear[charIndex] = i;
        }
        return result;
    }

}
