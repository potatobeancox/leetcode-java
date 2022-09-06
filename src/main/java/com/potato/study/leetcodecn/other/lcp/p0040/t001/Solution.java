package com.potato.study.leetcodecn.other.lcp.p0040.t001;

import java.util.Arrays;

/**
 * LCP 40. 心算挑战
 *
 * 「力扣挑战赛」心算项目的挑战比赛中，要求选手从 N 张卡牌中选出 cnt 张卡牌，若这 cnt 张卡牌数字总和为偶数，则选手成绩「有效」且得分为 cnt 张卡牌数字总和。
 * 给定数组 cards 和 cnt，其中 cards[i] 表示第 i 张卡牌上的数字。 请帮参赛选手计算最大的有效得分。若不存在获取有效得分的卡牌方案，则返回 0。
 *
 * 示例 1：
 *
 * 输入：cards = [1,2,8,9], cnt = 3
 *
 * 输出：18
 *
 * 解释：选择数字为 1、8、9 的这三张卡牌，此时可获得最大的有效得分 1+8+9=18。
 *
 * 示例 2：
 *
 * 输入：cards = [3,3,1], cnt = 1
 *
 * 输出：0
 *
 * 解释：不存在获取有效得分的卡牌方案。
 *
 * 提示：
 *
 * 1 <= cnt <= cards.length <= 10^5
 * 1 <= cards[i] <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/uOAnQW
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int maxmiumScore(int[] cards, int cnt) {
        // 排序
        Arrays.sort(cards);
        // 选择 后 cnt 张牌面
        int totalSum = 0;
        for (int i = cards.length - cnt; i < cards.length; i++) {
            totalSum += cards[i];
        }
        if (totalSum % 2 == 0) {
            return totalSum;
        }
        // 奇数 找到奇偶不同的两个数字求差
        int diff = Integer.MAX_VALUE;
        for (int i = cards.length - cnt; i < cards.length; i++) {
            // 找到替换它的数
            for (int j = cards.length - cnt - 1; j >= 0; j--) {
                if ((cards[i] + cards[j]) % 2 == 1) {
                    diff = Math.min(diff, cards[i] - cards[j]);
                    break;
                }
            }
        }
        if (diff == Integer.MAX_VALUE) {
            return 0;
        }
        return totalSum - diff;
    }


}
