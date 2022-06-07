package com.potato.study.leetcodecn.p02295.t001;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import com.potato.study.leetcode.util.LeetcodeInputUtils;

/**
 * 2295. 替换数组中的元素
 *
 * 给你一个下标从 0 开始的数组 nums ，它包含 n 个 互不相同 的正整数。请你对这个数组执行 m 个操作，在第 i 个操作中，你需要将数字 operations[i][0] 替换成 operations[i][1] 。
 *
 * 题目保证在第 i 个操作中：
 *
 * operations[i][0] 在 nums 中存在。
 * operations[i][1] 在 nums 中不存在。
 * 请你返回执行完所有操作后的数组。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,4,6], operations = [[1,3],[4,7],[6,1]]
 * 输出：[3,2,7,1]
 * 解释：我们对 nums 执行以下操作：
 * - 将数字 1 替换为 3 。nums 变为 [3,2,4,6] 。
 * - 将数字 4 替换为 7 。nums 变为 [3,2,7,6] 。
 * - 将数字 6 替换为 1 。nums 变为 [3,2,7,1] 。
 * 返回最终数组 [3,2,7,1] 。
 * 示例 2：
 *
 * 输入：nums = [1,2], operations = [[1,3],[2,1],[3,2]]
 * 输出：[2,1]
 * 解释：我们对 nums 执行以下操作：
 * - 将数字 1 替换为 3 。nums 变为 [3,2] 。
 * - 将数字 2 替换为 1 。nums 变为 [3,1] 。
 * - 将数字 3 替换为 2 。nums 变为 [2,1] 。
 * 返回最终数组 [2,1] 。
 *  
 *
 * 提示：
 *
 * n == nums.length
 * m == operations.length
 * 1 <= n, m <= 105
 * nums 中所有数字 互不相同 。
 * operations[i].length == 2
 * 1 <= nums[i], operations[i][0], operations[i][1] <= 106
 * 在执行第 i 个操作时，operations[i][0] 在 nums 中存在。
 * 在执行第 i 个操作时，operations[i][1] 在 nums 中不存在。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/replace-elements-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     *
     * @param nums
     * @param operations
     * @return
     */
    public int[] arrayChange(int[] nums, int[][] operations) {
        Map<Integer, List<Integer>> appearMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            List<Integer> list = appearMap.getOrDefault(nums[i], new ArrayList<>());
            list.add(i);
            appearMap.put(nums[i], list);
        }

        for (int i = 0; i < operations.length; i++) {
            int from = operations[i][0];
            int to = operations[i][1];

            List<Integer> list = appearMap.remove(from);
            if (list == null) {
                continue;
            }

            for (int index : list) {
                nums[index] = to;
            }

            List<Integer> addList = appearMap.getOrDefault(to, new ArrayList<>());
            addList.addAll(list);
            appearMap.put(to, addList);

        }
        return nums;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = LeetcodeInputUtils.inputString2IntArray("[1,2]");
        String input = "[[1,3],[2,1],[3,2]]";
        int[][] operations = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        int[] ints = solution.arrayChange(nums, operations);
        System.out.println(Arrays.toString(ints));
        Assert.assertArrayEquals(new int[] {
                2,1
        }, ints);
    }


}
