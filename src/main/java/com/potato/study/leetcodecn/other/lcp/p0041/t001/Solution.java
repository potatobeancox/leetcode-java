package com.potato.study.leetcodecn.other.lcp.p0041.t001;


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
        // 执行一步 判断最多能有多少个 白棋 置换
        // 遍历每个空余位置 对这个位置开始进行替换 比较多少 每个空位都作为开始点 看看 8个方向的变换
        int n = chessboard.length;
        int m = chessboard[0].length();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char ch = chessboard[i].charAt(j);
                if (ch != '.') {
                    continue;
                }
                // 落子
            }
        }

        return -1;
    }


    private String[] copyChessboard(String[] chessboard) {
        String[] chess = new String[chessboard.length];

        for (int i = 0; i < chessboard.length; i++) {
            chess[i] = new String(chessboard[i]);
        }
        return chess;
    }


}
