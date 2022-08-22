package com.potato.study.leetcodecn.p00348.t001;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 348. 设计井字棋
 *
 * 请在 n × n 的棋盘上，实现一个判定井字棋（Tic-Tac-Toe）胜负的神器，判断每一次玩家落子后，是否有胜出的玩家。
 *
 * 在这个井字棋游戏中，会有 2 名玩家，他们将轮流在棋盘上放置自己的棋子。
 *
 * 在实现这个判定器的过程中，你可以假设以下这些规则一定成立：
 *
 *       1. 每一步棋都是在棋盘内的，并且只能被放置在一个空的格子里；
 *
 *       2. 一旦游戏中有一名玩家胜出的话，游戏将不能再继续；
 *
 *       3. 一个玩家如果在同一行、同一列或者同一斜对角线上都放置了自己的棋子，那么他便获得胜利。
 *
 * 示例:
 *
 * 给定棋盘边长 n = 3, 玩家 1 的棋子符号是 "X"，玩家 2 的棋子符号是 "O"。
 *
 * TicTacToe toe = new TicTacToe(3);
 *
 * toe.move(0, 0, 1); -> 函数返回 0 (此时，暂时没有玩家赢得这场对决)
 * |X| | |
 * | | | |    // 玩家 1 在 (0, 0) 落子。
 * | | | |
 *
 * toe.move(0, 2, 2); -> 函数返回 0 (暂时没有玩家赢得本场比赛)
 * |X| |O|
 * | | | |    // 玩家 2 在 (0, 2) 落子。
 * | | | |
 *
 * toe.move(2, 2, 1); -> 函数返回 0 (暂时没有玩家赢得比赛)
 * |X| |O|
 * | | | |    // 玩家 1 在 (2, 2) 落子。
 * | | |X|
 *
 * toe.move(1, 1, 2); -> 函数返回 0 (暂没有玩家赢得比赛)
 * |X| |O|
 * | |O| |    // 玩家 2 在 (1, 1) 落子。
 * | | |X|
 *
 * toe.move(2, 0, 1); -> 函数返回 0 (暂无玩家赢得比赛)
 * |X| |O|
 * | |O| |    // 玩家 1 在 (2, 0) 落子。
 * |X| |X|
 *
 * toe.move(1, 0, 2); -> 函数返回 0 (没有玩家赢得比赛)
 * |X| |O|
 * |O|O| |    // 玩家 2 在 (1, 0) 落子.
 * |X| |X|
 *
 * toe.move(2, 1, 1); -> 函数返回 1 (此时，玩家 1 赢得了该场比赛)
 * |X| |O|
 * |O|O| |    // 玩家 1 在 (2, 1) 落子。
 * |X|X|X|
 *  
 *
 * 进阶:
 * 您有没有可能将每一步的 move() 操作优化到比 O(n2) 更快吗?
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/design-tic-tac-toe
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class TicTacToe {

    private Set<Long> set1;
    private Set<Long> set2;

    private int n;

    public TicTacToe(int n) {
        this.set1 = new HashSet<>();
        this.set2 = new HashSet<>();
        this.n = n;
    }

    public int move(int row, int col, int player) {
        long key = row * n + col;
        if (player == 1) {
            set1.add(key);
        } else {
            set2.add(key);
        }
        // row 行
        boolean isRow1AllSame = true;
        boolean isRow2AllSame = true;
        for (int i = 0; i < n-1; i++) {
            long rowKey = row * n + i;
            long nextRowKey = row * n + i + 1;

            if (set1.contains(rowKey) != set1.contains(nextRowKey)) {
                isRow1AllSame = false;
            }

            if (set2.contains(rowKey) != set2.contains(nextRowKey)) {
                isRow2AllSame = false;
            }

            if (!isRow1AllSame || !isRow2AllSame) {
                break;
            }
        }

        if (isRow1AllSame) {
            return 1;
        }

        if (isRow2AllSame) {
            return 2;
        }

        // col 列
        boolean isCol1AllSame = true;
        boolean isCol2AllSame = true;
        for (int i = 0; i < n-1; i++) {
            long colKey = i * n + col;
            long nextColKey = (i+1) * n + col;

            if (set1.contains(colKey) != set1.contains(nextColKey)) {
                isCol1AllSame = false;
            }

            if (set2.contains(colKey) != set2.contains(nextColKey)) {
                isCol2AllSame = false;
            }

            if (!isCol1AllSame || !isCol2AllSame) {
                break;
            }
        }

        // 对角线


        return player;
    }
}
