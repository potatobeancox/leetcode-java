package com.potato.study.leetcodecn.other.lcp.p0041.t001;


import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * LCP 41. 黑白翻转棋
 *
 * 在 n*m 大小的棋盘中，有黑白两种棋子，黑棋记作字母 "X", 白棋记作字母 "O"，空余位置记作 "."。当落下的棋子与其他相同颜色的棋子在行、列或对角线完全包围（中间不存在空白位置）另一种颜色的棋子，则可以翻转这些棋子的颜色。



 「力扣挑战赛」黑白翻转棋项目中，将提供给选手一个未形成可翻转棋子的棋盘残局，其状态记作 chessboard。若下一步可放置一枚黑棋，请问选手最多能翻转多少枚白棋。

 注意：

 若翻转白棋成黑棋后，棋盘上仍存在可以翻转的白棋，将可以 继续 翻转白棋
 输入数据保证初始棋盘状态无可以翻转的棋子且存在空余位置
 示例 1：

 输入：chessboard = ["....X.","....X.","XOOO..","......","......"]

 输出：3

 解释：
 可以选择下在 [2,4] 处，能够翻转白方三枚棋子。

 示例 2：

 输入：chessboard = [".X.",".O.","XO."]

 输出：2

 解释：
 可以选择下在 [2,2] 处，能够翻转白方两枚棋子。


 示例 3：

 输入：chessboard = [".......",".......",".......","X......",".O.....","..O....","....OOX"]

 输出：4

 解释：
 可以选择下在 [6,3] 处，能够翻转白方四枚棋子。


 提示：

 1 <= chessboard.length, chessboard[i].length <= 8
 chessboard[i] 仅包含 "."、"O" 和 "X"

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/fHi6rV
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // lcp 41
    public int flipChess(String[] chessboard) {
        // 黑棋记作字母 `"X"`, 白棋记作字母 `"O"`，空余位置记作 `"."
        // 将chessboard 换成数组
        char[][] chessOrigin = new char[chessboard.length][];
        for (int i = 0; i < chessboard.length; i++) {
            chessOrigin[i] = chessboard[i].toCharArray();
        }
        // 执行一步 判断最多能有多少个 白棋 置换
        // 遍历每个空余位置 对这个位置开始进行替换 比较多少 每个空位都作为开始点 看看 8个方向的变换
        int n = chessboard.length;
        int m = chessboard[0].length();
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char ch = chessboard[i].charAt(j);
                if (ch != '.') {
                    continue;
                }
                char[][] chess = copyChessboard(chessOrigin);
                // 落子 判读有多少个 白替换
                chess[i][j] = 'X';
                int current = getWhiteChangeCount(chess, i, j);
                max = Math.max(max, current);
            }
        }
        return max;
    }

    /**
     *
     * @param chess
     * @param i
     * @param j
     * @return
     */
    private int getWhiteChangeCount(char[][] chess, int i, int j) {
        // 定义 8个方向
        int[][] dir = new int[][] {
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1},
                {1, 1},
                {1, -1},
                {-1, 1},
                {-1, -1}
        };
        // 分别往某个方向找直到找到黑点 将中间的所有点都改成黑的 然后 递归的 计算最大值
        List<int[]> totalChangePoint = new ArrayList<>();
        for (int k = 0; k < 8; k++) {
            int[] thisDirection = dir[k];
            List<int[]> changePoint = new ArrayList<>();
            int di = i + thisDirection[0];
            int dj = j + thisDirection[1];
            while (di >= 0 && di < chess.length
                    && dj >= 0 && dj < chess[0].length
                    && chess[di][dj] == 'O') {
                changePoint.add(new int[] {di, dj});
                chess[di][dj] = 'X';

                di += thisDirection[0];
                dj += thisDirection[1];
            }
            if (di >= 0 && di < chess.length
                    && dj >= 0 && dj < chess[0].length
                    && chess[di][dj] == 'X') {
                totalChangePoint.addAll(changePoint);
            } else {
                // 重新改回来
                for (int[] pos : changePoint) {
                    chess[pos[0]][pos[1]] = 'O';
                }
            }
        }
        // 遍历 totalChangePoint dfs
        if (totalChangePoint.size() == 0) {
            // 当前位置改了 没有
            return 0;
        }
        int total = totalChangePoint.size();
        for (int[] pos : totalChangePoint) {
            total += getWhiteChangeCount(chess, pos[0], pos[1]);
        }
        return total;
    }


    private char[][] copyChessboard(char[][] chessboard) {
        char[][] chess = new char[chessboard.length][chessboard[0].length];
        for (int i = 0; i < chessboard.length; i++) {
            for (int j = 0; j < chessboard[0].length; j++) {
                chess[i][j] = chessboard[i][j];
            }
        }
        return chess;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        String[] chessboard = new String[]{
                "....X.",
                "....X.",
                "XOOO..",
                "......",
                "......"};

        int i = solution.flipChess(chessboard);
        System.out.println(i);
        Assert.assertEquals(3, i);


        chessboard = new String[]{
                ".......",
                ".......",
                ".......",
                "X......",
                ".O.....",
                "..O....",
                "....OOX"};

        i = solution.flipChess(chessboard);
        System.out.println(i);
        Assert.assertEquals(4, i);

    }


}
