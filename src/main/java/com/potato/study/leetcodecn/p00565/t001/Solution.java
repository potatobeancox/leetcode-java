package com.potato.study.leetcodecn.p00565.t001;


import java.util.Arrays;

import org.junit.Assert;

/**
 * 565. 数组嵌套
 *
 * 索引从0开始长度为N的数组A，包含0到N - 1的所有整数。找到最大的集合S并返回其大小，其中 S[i] = {A[i], A[A[i]], A[A[A[i]]], ... }且遵守以下的规则。
 *
 * 假设选择索引为i的元素A[i]为S的第一个元素，S的下一个元素应该是A[A[i]]，之后是A[A[A[i]]]... 以此类推，不断添加直到S出现重复的元素。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: A = [5,4,0,3,1,6,2]
 * 输出: 4
 * 解释:
 * A[0] = 5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.
 *
 * 其中一种最长的 S[K]:
 * S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}
 *  
 *
 * 提示：
 *
 * N是[1, 20,000]之间的整数。
 * A中不含有重复的元素。
 * A中的元素大小在[0, N-1]之间。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/array-nesting
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 565
    public int arrayNesting(int[] nums) {
        // 遍历 nums 从每个位置开始找 每次记录 visit 标志，并记录当前这个集合的大小
        boolean[] visit = new boolean[nums.length];
        int maxLen = 0;
        for (int i = 0; i < nums.length; i++) {
            if (visit[i]) {
                continue;
            }
            // 开始找
            int current = nums[i];
            int length = 0;
            do {
                visit[current] = true;
                current = nums[current];
                length++;
                maxLen = Math.max(maxLen, length);
            } while (current != nums[i]);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] nums = new int[] {
                5,4,0,3,1,6,2
        };
        int ints = solution.arrayNesting(nums);
        System.out.println(ints);
        Assert.assertEquals(4, ints);

        nums = new int[] {
                5,4,0,3,1,6,2
        };
        ints = solution.arrayNesting(nums);
        System.out.println(ints);
        Assert.assertEquals(4, ints);
    }

}
