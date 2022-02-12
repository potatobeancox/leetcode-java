package com.potato.study.leetcodecn.p01124.t001;


import org.junit.Assert;

import com.potato.study.leetcode.domain.TreeNode;

/**
 * 1124. 表现良好的最长时间段
 *
 * 给你一份工作时间表 hours，上面记录着某一位员工每天的工作小时数。
 *
 * 我们认为当员工一天中的工作小时数大于 8 小时的时候，那么这一天就是「劳累的一天」。
 *
 * 所谓「表现良好的时间段」，意味在这段时间内，「劳累的天数」是严格 大于「不劳累的天数」。
 *
 * 请你返回「表现良好时间段」的最大长度。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：hours = [9,9,6,0,6,6,9]
 * 输出：3
 * 解释：最长的表现良好时间段是 [9,9,6]。
 * 示例 2：
 *
 * 输入：hours = [6,6,6]
 * 输出：0
 *  
 *
 * 提示：
 *
 * 1 <= hours.length <= 104
 * 0 <= hours[i] <= 16
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-well-performing-interval
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode-cn.com/problems/longest-well-performing-interval/solution/1124java-bao-li-fa-ha-xi-biao-onde-fang-fa-xiang-j/
     * @param hours
     * @return
     */
    public int longestWPI(int[] hours) {
        for (int i = 0; i < hours.length; i++) {
            if (hours[i] > 8) {
                hours[i] = 1;
            } else {
                hours[i] = -1;
            }
        }
        // 暴力计算
        int max = 0;
        for (int i = 0; i < hours.length; i++) {
            int current = 0;
            for (int j = i; j < hours.length; j++) {
                current += hours[j];
                if (current > 0) {
                    max = Math.max(max, j - i + 1);
                }
            }
            // 简直
            if (max > hours.length - i) {
                break;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[] {
                9,9,6,0,6,6,9
        };
        int i = solution.longestWPI(arr);
        System.out.println(i);
        Assert.assertEquals(3, i);
    }
}
