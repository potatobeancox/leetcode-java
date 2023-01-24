package com.potato.study.leetcodecn.p02343.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 2343. 裁剪数字后查询第 K 小的数字
 *
 * 给你一个下标从 0 开始的字符串数组 nums ，其中每个字符串 长度相等 且只包含数字。
 *
 * 再给你一个下标从 0 开始的二维整数数组 queries ，其中 queries[i] = [ki, trimi] 。对于每个 queries[i] ，你需要：
 *
 * 将 nums 中每个数字 裁剪 到剩下 最右边 trimi 个数位。
 * 在裁剪过后的数字中，找到 nums 中第 ki 小数字对应的 下标 。如果两个裁剪后数字一样大，那么下标 更小 的数字视为更小的数字。
 * 将 nums 中每个数字恢复到原本字符串。
 * 请你返回一个长度与 queries 相等的数组 answer，其中 answer[i]是第 i 次查询的结果。
 *
 * 提示：
 *
 * 裁剪到剩下 x 个数位的意思是不断删除最左边的数位，直到剩下 x 个数位。
 * nums 中的字符串可能会有前导 0 。
 *  
 *
 * 示例 1：
 *
 * 输入：nums = ["102","473","251","814"], queries = [[1,1],[2,3],[4,2],[1,2]]
 * 输出：[2,2,1,0]
 * 解释：
 * 1. 裁剪到只剩 1 个数位后，nums = ["2","3","1","4"] 。最小的数字是 1 ，下标为 2 。
 * 2. 裁剪到剩 3 个数位后，nums 没有变化。第 2 小的数字是 251 ，下标为 2 。
 * 3. 裁剪到剩 2 个数位后，nums = ["02","73","51","14"] 。第 4 小的数字是 73 ，下标为 1 。
 * 4. 裁剪到剩 2 个数位后，最小数字是 2 ，下标为 0 。
 *    注意，裁剪后数字 "02" 值为 2 。
 * 示例 2：
 *
 * 输入：nums = ["24","37","96","04"], queries = [[2,1],[2,2]]
 * 输出：[3,0]
 * 解释：
 * 1. 裁剪到剩 1 个数位，nums = ["4","7","6","4"] 。第 2 小的数字是 4 ，下标为 3 。
 *    有两个 4 ，下标为 0 的 4 视为小于下标为 3 的 4 。
 * 2. 裁剪到剩 2 个数位，nums 不变。第二小的数字是 24 ，下标为 0 。
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 100
 * 1 <= nums[i].length <= 100
 * nums[i] 只包含数字。
 * 所有 nums[i].length 的长度 相同 。
 * 1 <= queries.length <= 100
 * queries[i].length == 2
 * 1 <= ki <= nums.length
 * 1 <= trimi <= nums[0].length
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/query-kth-smallest-trimmed-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {
        int n = queries.length;
        // 存的是下标我擦
        int[] res = new int[n];
        for (int i = 0; i < queries.length; i++) {
            // queries[i] = [ki, trimi]
            int[] query = queries[i];
            int k = query[0];
            int trim = query[1];
            String[] sub = new String[nums.length];
            List<Integer> indexes = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                indexes.add(j);
            }
            for (int j = 0; j < nums.length; j++) {
                sub[j] = nums[j].substring(nums[0].length() - trim);
            }
            // 排序 按照字母升序就行
            Collections.sort(indexes, (o1, o2) -> {
                return sub[o1].compareTo(sub[o2]);
            });
            res[i] = indexes.get(k-1);
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] nums = new String[] {
                "102","473","251","814"
        };
        int[][] queries = LeetcodeInputUtils.inputString2IntArrayTwoDimensional("[[1,1],[2,3],[4,2],[1,2]]");
        int[] ints = solution.smallestTrimmedNumbers(nums, queries);
        System.out.println(Arrays.toString(ints));
    }


}
