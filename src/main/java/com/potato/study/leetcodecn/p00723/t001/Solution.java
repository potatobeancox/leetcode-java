package com.potato.study.leetcodecn.p00723.t001;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.potato.study.leetcode.util.LeetcodeInputUtils;

/**
 *
 *
 * 723. 粉碎糖果
 *
 * 这个问题是实现一个简单的消除算法。
 *
 * 给定一个 m x n 的二维整数数组 board 代表糖果所在的方格，不同的正整数 board[i][j] 代表不同种类的糖果，如果 board[i][j] == 0 代表 (i, j) 这个位置是空的。
 *
 * 给定的方格是玩家移动后的游戏状态，现在需要你根据以下规则粉碎糖果，使得整个方格处于稳定状态并最终输出：
 *
 * 如果有三个及以上水平或者垂直相连的同种糖果，同一时间将它们粉碎，即将这些位置变成空的。
 * 在同时粉碎掉这些糖果之后，如果有一个空的位置上方还有糖果，那么上方的糖果就会下落直到碰到下方的糖果或者底部，这些糖果都是同时下落，也不会有新的糖果从顶部出现并落下来。
 * 通过前两步的操作，可能又会出现可以粉碎的糖果，请继续重复前面的操作。
 * 当不存在可以粉碎的糖果，也就是状态稳定之后，请输出最终的状态。
 * 你需要模拟上述规则并使整个方格达到稳定状态，并输出。
 *
 *  
 *
 * 示例 1 :
 *
 *
 *
 * 输入: board = [[110,5,112,113,114],[210,211,5,213,214],[310,311,3,313,314],[410,411,412,5,414],[5,1,512,3,3],[610,4,
 * 1,613,614],[710,1,2,713,714],[810,1,2,1,1],[1,1,2,2,2],[4,1,4,4,1014]]
 * 输出: [[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[110,0,0,0,114],[210,0,0,0,214],[310,0,0,113,314],[410,0,0,213,414],[610,
 * 211,112,313,614],[710,311,412,613,714],[810,411,512,713,1014]]
 * 示例 2:
 *
 * 输入: board = [[1,3,5,5,2],[3,4,3,3,1],[3,2,4,5,2],[2,4,4,5,5],[1,4,4,1,1]]
 * 输出: [[1,3,0,0,0],[3,4,0,5,2],[3,2,0,3,1],[2,4,0,5,2],[1,4,3,1,1]]
 *  
 *
 * 提示:
 *
 * m == board.length
 * n == board[i].length
 * 3 <= m, n <= 50
 * 1 <= board[i][j] <= 2000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/candy-crush
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class Solution {


    public int[][] candyCrush(int[][] board) {
        int[][] candyCrushBoard = getCandyCrush(board);
        while (!isMatrixSame(candyCrushBoard, board)) {
            board = candyCrushBoard;
            candyCrushBoard = getCandyCrush(candyCrushBoard);
        }
        return candyCrushBoard;
    }


    private boolean isMatrixSame(int[][] board1, int[][] board2) {
        for (int i = 0; i < board1.length; i++) {
            for (int j = 0; j < board1[i].length; j++) {
                if (board1[i][j] != board2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private int[][] getCandyCrush(int[][] board) {
        int[][] newBoard = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                newBoard[i][j] = board[i][j];
            }
        }


        // 遍历每个位置 往后 行方向
        for (int i = 0; i < newBoard.length; i++) {
            for (int j = 0; j < newBoard[0].length-2; j++) {
                // 每个位置横向走 3个
                if (Math.abs(newBoard[i][j]) == Math.abs(newBoard[i][j+1])
                        && Math.abs(newBoard[i][j+1]) == Math.abs(newBoard[i][j+2])) {
                    newBoard[i][j] = -1 * Math.abs(newBoard[i][j]);
                    newBoard[i][j+1] = -1 * Math.abs(newBoard[i][j+1]);
                    newBoard[i][j+2] = -1 * Math.abs(newBoard[i][j+2]);
                }
            }
        }
        // 遍历每个位置 往下 列方向 如果一样 变成负数
        for (int i = 0; i < newBoard.length-2; i++) {
            for (int j = 0; j < newBoard[0].length; j++) {
                // 每个位置横向走 3个
                if (Math.abs(newBoard[i][j]) == Math.abs(newBoard[i+1][j])
                        && Math.abs(newBoard[i+1][j]) == Math.abs(newBoard[i+2][j])) {
                    newBoard[i][j] = -1 * Math.abs(newBoard[i][j]);
                    newBoard[i+1][j] = -1 * Math.abs(newBoard[i+1][j]);
                    newBoard[i+2][j] = -1 * Math.abs(newBoard[i+2][j]);
                }
            }
        }
        // 再次遍历 从没裂开始 都往下走 最终 设置 剩余的为0
        for (int i = 0; i < newBoard[0].length; i++) {
            // 从下
            int index = newBoard.length - 1;
            for (int j = newBoard.length - 1; j >= 0; j--) {
                if (newBoard[j][i] > 0) {
                    newBoard[index--][i] = newBoard[j][i];
                }
            }
            // index - 0 都变成 0
            for (int j = 0; j <= index; j++) {
                newBoard[j][i] = 0;
            }
        }
        return newBoard;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String input =
                 "[[110,5,112,113,114],"
                + "[210,211,5,213,214],"
                + "[310,311,3,313,314],"
                 + "[410,411,412,5,414],"
                 + "[5,1,512,3,3],"
                 + "[610,4,1,613,614],"
                 + "[710,1,2,713,714],"
                 + "[810,1,2,1,1],"
                 + "[1,1,2,2,2],"
                 + "[4,1,4,4,1014]]";
        int[][] board = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        int[][] ints = solution.candyCrush(board);
        System.out.println(Arrays.deepToString(ints));
    }
}
