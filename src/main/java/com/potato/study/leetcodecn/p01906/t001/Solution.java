package com.potato.study.leetcodecn.p01906.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.Arrays;

/**
 * 1906. 查询差绝对值的最小值
 *
 * 一个数组 a 的 差绝对值的最小值 定义为：0 <= i < j < a.length 且 a[i] != a[j] 的 |a[i] - a[j]| 的 最小值。如果 a 中所有元素都 相同 ，那么差绝对值的最小值为 -1 。

 比方说，数组 [5,2,3,7,2] 差绝对值的最小值是 |2 - 3| = 1 。注意答案不为 0 ，因为 a[i] 和 a[j] 必须不相等。
 给你一个整数数组 nums 和查询数组 queries ，其中 queries[i] = [li, ri] 。对于每个查询 i ，计算 子数组 nums[li...ri] 中 差绝对值的最小值 ，子数组 nums[li...ri] 包含 nums 数组（下标从 0 开始）中下标在 li 和 ri 之间的所有元素（包含 li 和 ri 在内）。

 请你返回 ans 数组，其中 ans[i] 是第 i 个查询的答案。

 子数组 是一个数组中连续的一段元素。

 |x| 的值定义为：

 如果 x >= 0 ，那么值为 x 。
 如果 x < 0 ，那么值为 -x 。
  

 示例 1：

 输入：nums = [1,3,4,8], queries = [[0,1],[1,2],[2,3],[0,3]]
 输出：[2,1,4,1]
 解释：查询结果如下：
 - queries[0] = [0,1]：子数组是 [1,3] ，差绝对值的最小值为 |1-3| = 2 。
 - queries[1] = [1,2]：子数组是 [3,4] ，差绝对值的最小值为 |3-4| = 1 。
 - queries[2] = [2,3]：子数组是 [4,8] ，差绝对值的最小值为 |4-8| = 4 。
 - queries[3] = [0,3]：子数组是 [1,3,4,8] ，差的绝对值的最小值为 |3-4| = 1 。
 示例 2：

 输入：nums = [4,5,2,2,7,10], queries = [[2,3],[0,2],[0,5],[3,5]]
 输出：[-1,1,1,3]
 解释：查询结果如下：
 - queries[0] = [2,3]：子数组是 [2,2] ，差绝对值的最小值为 -1 ，因为所有元素相等。
 - queries[1] = [0,2]：子数组是 [4,5,2] ，差绝对值的最小值为 |4-5| = 1 。
 - queries[2] = [0,5]：子数组是 [4,5,2,2,7,10] ，差绝对值的最小值为 |4-5| = 1 。
 - queries[3] = [3,5]：子数组是 [2,7,10] ，差绝对值的最小值为 |7-10| = 3 。
  

 提示：

 2 <= nums.length <= 105
 1 <= nums[i] <= 100
 1 <= queries.length <= 2 * 104
 0 <= li < ri < nums.length

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/minimum-absolute-difference-queries
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int[] minDifference(int[] nums, int[][] queries) {
        // 使用一个 二维数组记录 每个数字 出现的次数 0 表示没有出现过
        int[][] count = new int[nums.length + 1][101];
        for (int i = 0; i < nums.length; i++) {
            // 设置 原来的值
            for (int j = 0; j < 101; j++) {
                count[i+1][j] = count[i][j];
            }
            count[i+1][nums[i]]++;
        }
        int[] ans = new int[queries.length];
        // 对于 每次 queries from to 找到 to+1 - from 相邻数字种最小的值 记录
        for (int i = 0; i < queries.length; i++) {
            int from = queries[i][0];
            int to = queries[i][1] + 1;
            // -1 就是没有
            int lastNum = -1;
            // 遍历 1-100
            int minDiff = Integer.MAX_VALUE;
            for (int j = 0; j < 101; j++) {
                if (count[to][j] - count[from][j] == 0) {
                    continue;
                }
                // 第一个 不是 0 的数字
                if (lastNum == -1) {
                    lastNum = j;
                    continue;
                }
                // 找到最小值
                minDiff = Math.min(minDiff, j - lastNum);
                lastNum = j;
            }
            if (minDiff == Integer.MAX_VALUE) {
                ans[i] = -1;
            } else {
                ans[i] = minDiff;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        String input = "[1,3,4,8]";
        int[] nums = LeetcodeInputUtils.inputString2IntArray(input);
        String arrayStr = "[[0,1],[1,2],[2,3],[0,3]]";
        int[][] queries = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(arrayStr);
        int[] ints = solution.minDifference(nums, queries);
        System.out.println(Arrays.toString(ints));
        Assert.assertArrayEquals(new int[] {
                2,1,4,1
        }, ints);
    }

}
