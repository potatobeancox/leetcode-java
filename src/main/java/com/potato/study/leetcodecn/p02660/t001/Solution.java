package com.potato.study.leetcodecn.p02660.t001;

import java.util.Arrays;

/**
 *
 * 2660. 保龄球游戏的获胜者
 *
 * 给你两个下标从 0 开始的整数数组 player1 和 player2 ，分别表示玩家 1 和玩家 2 击中的瓶数。
 *
 * 保龄球比赛由 n 轮组成，每轮的瓶数恰好为 10 。
 *
 * 假设玩家在第 i 轮中击中 xi 个瓶子。玩家第 i 轮的价值为：
 *
 * 如果玩家在前两轮中击中了 10 个瓶子，则为 2xi 。
 * 否则，为 xi 。
 * 玩家的得分是其 n 轮价值的总和。
 *
 * 返回
 *
 * 如果玩家 1 的得分高于玩家 2 的得分，则为 1 ；
 * 如果玩家 2 的得分高于玩家 1 的得分，则为 2 ；
 * 如果平局，则为 0 。
 *  
 *
 * 示例 1：
 *
 * 输入：player1 = [4,10,7,9], player2 = [6,5,2,3]
 * 输出：1
 * 解释：player1 的得分是 4 + 10 + 2*7 + 2*9 = 46 。
 * player2 的得分是 6 + 5 + 2 + 3 = 16 。
 * player1 的得分高于 player2 的得分，所以 play1 在比赛中获胜，答案为 1 。
 * 示例 2：
 *
 * 输入：player1 = [3,5,7,6], player2 = [8,10,10,2]
 * 输出：2
 * 解释：player1 的得分是 3 + 5 + 7 + 6 = 21 。
 * player2 的得分是 8 + 10 + 2*10 + 2*2 = 42 。
 * player2 的得分高于 player1 的得分，所以 play2 在比赛中获胜，答案为 2 。
 * 示例 3：
 *
 * 输入：player1 = [2,3], player2 = [4,1]
 * 输出：0
 * 解释：player1 的得分是 2 + 3 = 5 。
 * player2 的得分是 4 + 1 = 5 。
 * player1 的得分等于 player2 的得分，所以这一场比赛平局，答案为 0 。
 *  
 *
 * 提示：
 *
 * n == player1.length == player2.length
 * 1 <= n <= 1000
 * 0 <= player1[i], player2[i] <= 10
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/determine-the-winner-of-a-bowling-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int isWinner(int[] player1, int[] player2) {
        int score1 = 0;
        int score2 = 0;

        int n = player1.length;

        boolean isDouble1 = false;
        boolean isDouble2 = false;

        for (int i = 0; i < n; i++) {

            if (isDouble1) {
                score1 += 2 * player1[i];
            } else {
                score1 += player1[i];
            }

            if (isDouble2) {
                score2 += 2 * player2[i];
            } else {
                score2 += player2[i];
            }

            isDouble1 = player1[i] == 10;
            isDouble2 = player2[i] == 10;

            if (i > 0) {
                isDouble1 =  isDouble1 || player1[i-1] == 10;
                isDouble2 =  isDouble2 || player2[i-1] == 10;
            }
        }

        if (score1 > score2) {
            return 1;
        } else if (score2 > score1) {
            return 2;
        } else {
            return 0;
        }
    }



}
