package com.potato.study.leetcodecn.other.Interview.p0016.p0004;


/**
 * 面试题 16.04. 井字游戏
 *
 * 设计一个算法，判断玩家是否赢了井字游戏。输入是一个 N x N 的数组棋盘，由字符" "，"X"和"O"组成，其中字符" "代表一个空位。
 *
 * 以下是井字游戏的规则：
 *
 * 玩家轮流将字符放入空位（" "）中。
 * 第一个玩家总是放字符"O"，且第二个玩家总是放字符"X"。
 * "X"和"O"只允许放置在空位中，不允许对已放有字符的位置进行填充。
 * 当有N个相同（且非空）的字符填充任何行、列或对角线时，游戏结束，对应该字符的玩家获胜。
 * 当所有位置非空时，也算为游戏结束。
 * 如果游戏结束，玩家不允许再放置字符。
 * 如果游戏存在获胜者，就返回该游戏的获胜者使用的字符（"X"或"O"）；如果游戏以平局结束，则返回 "Draw"；如果仍会有行动（游戏未结束），则返回 "Pending"。
 *
 * 示例 1：
 *
 * 输入： board = ["O X"," XO","X O"]
 * 输出： "X"
 * 示例 2：
 *
 * 输入： board = ["OOX","XXO","OXO"]
 * 输出： "Draw"
 * 解释： 没有玩家获胜且不存在空位
 * 示例 3：
 *
 * 输入： board = ["OOX","XXO","OX "]
 * 输出： "Pending"
 * 解释： 没有玩家获胜且仍存在空位
 * 提示：
 *
 * 1 <= board.length == board[i].length <= 100
 * 输入一定遵循井字棋规则
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/tic-tac-toe-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 16.04
    public String tictactoe(String[] board) {
        // n 个相同
        int n = board.length;
        // 检查每一行 直接计数吧
        for (String line : board) {
            // "X"和"O"
            int count1 = 0;
            int count2 = 0;

            for (char ch : line.toCharArray()) {
                if (ch == 'X') {
                    count1++;
                } else {
                    count2++;
                }
            }

            if (count1 == n) {
                return "X";
            } else if (count2 == n) {
                return "O";
            }

        }
        // 检查每一列
        for (int i = 0; i < n; i++) {
            // "X"和"O"
            int count1 = 0;
            int count2 = 0;
            for (String line : board) {
                char ch = line.charAt(i);
                if (ch == 'X') {
                    count1++;
                } else {
                    count2++;
                }
            }

            if (count1 == n) {
                return "X";
            } else if (count2 == n) {
                return "O";
            }
        }

        // 检查左对角线
        int i = 0;
        int j = 0;
        int count1 = 0;
        int count2 = 0;
        for (int k = 0; k < n; k++) {
            int di = i + k;
            int dj = j + k;

            char ch = board[di].charAt(dj);

            if (ch == 'X') {
                count1++;
            } else {
                count2++;
            }
        }
        if (count1 == n) {
            return "X";
        } else if (count2 == n) {
            return "O";
        }

        // 检查右边对角线
        i = 0;
        j = n-1;
        count1 = 0;
        count2 = 0;
        for (int k = 0; k < n; k++) {
            int di = i + k;
            int dj = j - k;

            char ch = board[di].charAt(dj);

            if (ch == 'X') {
                count1++;
            } else {
                count2++;
            }
        }
        if (count1 == n) {
            return "X";
        } else if (count2 == n) {
            return "O";
        }
        return "Draw";
    }
}
