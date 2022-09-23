package com.potato.study.leetcodecn.p02113.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.Arrays;

/**
 * 2113. 查询删除和添加元素后的数组
 *
 * 给你一个 下标从 0 开始 的数组 nums。一开始，在第 0 分钟，数组没有变化。此后每过一分钟，数组的 最左边 的元素将被移除，直到数组为空。然后，每过一分钟，数组的 尾部 将添加一个元素，添加的顺序和删除的顺序相同，直到数组被复原。此后上述操作无限循环进行。
 *
 * 举个例子，如果 nums = [0, 1, 2]，那么数组将按如下流程变化：[0,1,2] → [1,2] → [2] → [] → [0] → [0,1] → [0,1,2] → [1,2] → [2] → [] → [0] → [0,1] → [0,1,2] → ...
 * 然后给你一个长度为 n 的二维数组 queries，其中 queries[j] = [timej, indexj]，表示第 j 个查询。第 j 个查询的答案定义如下：
 *
 * 如果在时刻 timej，indexj < nums.length，那么答案是此时的 nums[indexj]；
 * 如果在时刻 timej，indexj >= nums.length，那么答案是 -1。
 * 请返回一个长度为 n 的整数数组 ans，其中 ans[j] 为第 j 个查询的答案。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: nums = [0,1,2], queries = [[0,2],[2,0],[3,2],[5,0]]
 * 输出: [2,2,-1,0]
 * 解释:
 * 第 0 分钟: [0,1,2] - 数组和 nums 相同。
 * 第 1 分钟: [1,2]   - 最左侧元素 0 被移除。
 * 第 2 分钟: [2]     - 最左侧元素 1 被移除。
 * 第 3 分钟: []      - 最左侧元素 0 被移除。
 * 第 4 分钟: [0]     - 0 被添加到数组尾部。
 * 第 5 分钟: [0,1]   - 1 被添加到数组尾部。
 *
 * 在第 0 分钟, nums[2] 是 2。
 * 在第 2 分钟, nums[0] 是 2。
 * 在第 3 分钟, nums[2] 不存在。
 * 在第 5 分钟, nums[0] 是 0。
 * 示例 2:
 *
 * 输入: nums = [2], queries = [[0,0],[1,0],[2,0],[3,0]]
 * 输出: [2,-1,2,-1]
 * 第 0 分钟: [2] - 数组和 nums 相同。
 * 第 1 分钟: []  - 最左侧元素 2 被移除。
 * 第 2 分钟: [2] - 2 被添加到数组尾部。
 * 第 3 分钟: []  - 最左侧元素 2 被移除。
 *
 * 在第 0 分钟, nums[0] 是 2。
 * 在第 1 分钟, nums[0] 不存在。
 * 在第 2 分钟, nums[0] 是 2。
 * 在第 3 分钟, nums[0] 不存在。
 *  
 *
 * 提示:
 *
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 * n == queries.length
 * 1 <= n <= 105
 * queries[j].length == 2
 * 0 <= timej <= 105
 * 0 <= indexj < nums.length
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/elements-in-array-after-removing-and-replacing-elements
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int[] elementInNums(int[] nums, int[][] queries) {
        // 一个循环多少
        int len = nums.length * 2;
        // 求对上面的余数
        int[] result = new int[queries.length];
        // 如果余数落在 一倍里边  1-len
        for (int i = 0; i < queries.length; i++) {
            int lineIndex = queries[i][0];
            int index = queries[i][1];
            int temp = lineIndex % len;
            if (0 < temp && temp <= nums.length) {
                if (index + temp >= nums.length) {
                    result[i] = -1;
                } else {
                    result[i] = nums[index + temp];
                }
            } else {
                if (temp != 0 && index >= temp - nums.length) {
                    result[i] = -1;
                } else {
                    result[i] = nums[index];
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {
                0,1,2
        };
        String input = "[[5,1],[5,1],[5,1],[5,1],[5,1]]";
        int[][] queries = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        int[] ints = solution.elementInNums(nums, queries);
        System.out.println(Arrays.toString(ints));
        Assert.assertArrayEquals(new int[] {
                1,1,1,1,1
        }, ints);
    }
}
