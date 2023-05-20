package com.potato.study.leetcodecn.other.lcp.p0077.t001;

import org.junit.Assert;

import java.util.Arrays;

/**
 * LCP 77. 符文储备
 *
 * 远征队在出发前需要携带一些「符文」，作为后续的冒险储备。runes[i] 表示第 i 枚符文的魔力值。

 他们将从中选取若干符文进行携带，并对这些符文进行重新排列，以确保任意相邻的两块符文之间的魔力值相差不超过 1。

 请返回他们能够携带的符文 最大数量。

 示例 1：

 输入：runes = [1,3,5,4,1,7]

 输出：3

 解释：最佳的选择方案为[3,5,4]
 将其排列为 [3,4,5] 后，任意相邻的两块符文魔力值均不超过 1，携带数量为 3
 其他满足条件的方案为 [1,1] 和 [7]，数量均小于 3。
 因此返回可携带的最大数量 3。

 示例 2：

 输入：runes = [1,1,3,3,2,4]

 输出：6

 解释：排列为 [1,1,2,3,3,4]，可携带所有的符文

 提示：

 1 <= runes.length <= 10^4
 0 <= runes[i] <= 10^4

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/W2ZX4X
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int runeReserve(int[] runes) {
        // 排序 遍历 一下如果当前 比之前差距小于等于1 那么len ++ 否则 len= 1 记录max
        Arrays.sort(runes);
        int len = 0;
        int max = 0;
        for (int i = 0; i < runes.length; i++) {
            if (i == 0 || Math.abs(runes[i] - runes[i-1]) <= 1) {
                len++;
            } else {
                // 超过1
                len = 1;
            }
            max = Math.max(max, len);
        }
        return max;
    }

}
