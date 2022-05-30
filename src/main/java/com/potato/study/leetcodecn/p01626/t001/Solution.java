package com.potato.study.leetcodecn.p01626.t001;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.junit.Assert;

/**
 * 1626. 无矛盾的最佳球队
 *
 * 假设你是球队的经理。对于即将到来的锦标赛，你想组合一支总体得分最高的球队。球队的得分是球队中所有球员的分数 总和 。
 *
 * 然而，球队中的矛盾会限制球员的发挥，所以必须选出一支 没有矛盾 的球队。如果一名年龄较小球员的分数 严格大于 一名年龄较大的球员，则存在矛盾。同龄球员之间不会发生矛盾。
 *
 * 给你两个列表 scores 和 ages，其中每组 scores[i] 和 ages[i] 表示第 i 名球员的分数和年龄。请你返回 所有可能的无矛盾球队中得分最高那支的分数 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：scores = [1,3,5,10,15], ages = [1,2,3,4,5]
 * 输出：34
 * 解释：你可以选中所有球员。
 * 示例 2：
 *
 * 输入：scores = [4,5,6,5], ages = [2,1,2,1]
 * 输出：16
 * 解释：最佳的选择是后 3 名球员。注意，你可以选中多个同龄球员。
 * 示例 3：
 *
 * 输入：scores = [1,2,3,5], ages = [8,9,10,1]
 * 输出：6
 * 解释：最佳的选择是前 3 名球员。
 *  
 *
 * 提示：
 *
 * 1 <= scores.length, ages.length <= 1000
 * scores.length == ages.length
 * 1 <= scores[i] <= 106
 * 1 <= ages[i] <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/best-team-with-no-conflicts
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int bestTeamScore(int[] scores, int[] ages) {
        // index 列表 按照 年龄 升序 得分升序排序
        int n = scores.length;
        Integer[] indexes = new Integer[n];
        for (int i = 0; i < n; i++) {
            indexes[i] = i;
        }
        Arrays.sort(indexes, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int compare = Integer.compare(ages[o1], ages[o2]);
                if (compare != 0) {
                    return compare;
                }
                return Integer.compare(scores[o1], scores[o2]);
            }
        });
        // dp i 包含 i的 最大得分（到i位置） dpi= max dpj + scorei j 小于 i
        int[] dp = new int[n];
        // 初始化 dp i 等于 只有 i点的得分
        for (int i = 0; i < n; i++) {
            dp[i] = scores[i];
        }
        // i 从 0-n遍历 内部 j从 0- j
        int max = 0;
        for (int i = 0; i < n; i++) {
            int ii = indexes[i];
            for (int j = 0; j < i; j++) {
                // 如果 j位置 得分小于等于 i位置 那么可以 从j转移
                int jj = indexes[j];
                if (scores[jj] <= scores[ii]) {
                    dp[ii] = Math.max(dp[jj] + scores[ii], dp[ii]);
                }
            }
            max = Math.max(max, dp[ii]);
        }
        return max;
    }
}
