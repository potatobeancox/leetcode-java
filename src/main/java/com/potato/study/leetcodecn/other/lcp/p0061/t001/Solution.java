package com.potato.study.leetcodecn.other.lcp.p0061.t001;

/**
 * LCP 61. 气温变化趋势
 *
 * 力扣城计划在两地设立「力扣嘉年华」的分会场，气象小组正在分析两地区的气温变化趋势，对于第 i ~ (i+1) 天的气温变化趋势，将根据以下规则判断：
 *
 * 若第 i+1 天的气温 高于 第 i 天，为 上升 趋势
 * 若第 i+1 天的气温 等于 第 i 天，为 平稳 趋势
 * 若第 i+1 天的气温 低于 第 i 天，为 下降 趋势
 * 已知 temperatureA[i] 和 temperatureB[i] 分别表示第 i 天两地区的气温。
 * 组委会希望找到一段天数尽可能多，且两地气温变化趋势相同的时间举办嘉年华活动。请分析并返回两地气温变化趋势相同的最大连续天数。
 *
 * 即最大的 n，使得第 i~i+n 天之间，两地气温变化趋势相同
 *
 * 示例 1：
 *
 * 输入：
 * temperatureA = [21,18,18,18,31]
 * temperatureB = [34,32,16,16,17]
 *
 * 输出：2
 *
 * 解释：如下表所示， 第 2～4 天两地气温变化趋势相同，且持续时间最长，因此返回 4-2=2
 *
 *
 * 示例 2：
 *
 * 输入：
 * temperatureA = [5,10,16,-6,15,11,3]
 * temperatureB = [16,22,23,23,25,3,-16]
 *
 * 输出：3
 *
 * 提示：
 *
 * 2 <= temperatureA.length == temperatureB.length <= 1000
 * -20 <= temperatureA[i], temperatureB[i] <= 40
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/6CE719
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int temperatureTrend(int[] temperatureA, int[] temperatureB) {
        // 直接 找一下 看 后-钱的差 是不是一致 是的话 增加 不是的话 情况
        int maxTrend = 0;
        int thisTrend = 0;
        int len = temperatureA.length;
        for (int i = 1; i < len; i++) {
            int diff1 = temperatureA[i] - temperatureA[i-1];
            int diff2 = temperatureB[i] - temperatureB[i-1];

            if (diff1 == diff2) {
                thisTrend++;
            } else {
                // 不相同判断 趋势
                if (diff1 * diff2 > 0) {
                    thisTrend++;
                } else {
                    thisTrend = 0;
                }
            }
            maxTrend = Math.max(maxTrend, thisTrend);
        }
        return maxTrend;
    }


}
