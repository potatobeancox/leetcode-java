package com.potato.study.leetcodecn.p01958.t001;

import org.junit.Assert;

import com.potato.study.leetcode.util.LeetcodeInputUtils;

/**
 * 1958. 检查操作是否合法
 *
 * 给你一个下标从 0 开始的 8 x 8 网格 board ，其中 board[r][c] 表示游戏棋盘上的格子 (r, c) 。棋盘上空格用 '.' 表示，白色格子用 'W' 表示，黑色格子用 'B' 表示。
 *
 * 游戏中每次操作步骤为：选择一个空格子，将它变成你正在执行的颜色（要么白色，要么黑色）。但是，合法 操作必须满足：涂色后这个格子是 好线段的一个端点 （好线段可以是水平的，竖直的或者是对角线）。
 *
 * 好线段 指的是一个包含 三个或者更多格子（包含端点格子）的线段，线段两个端点格子为 同一种颜色 ，且中间剩余格子的颜色都为 另一种颜色 （线段上不能有任何空格子）。你可以在下图找到好线段的例子：
 *
 *
 * 给你两个整数 rMove 和 cMove 以及一个字符 color ，表示你正在执行操作的颜色（白或者黑），如果将格子 (rMove, cMove)
 *  变成颜色 color 后，是一个 合法 操作，那么返回 true ，如果不是合法操作返回 false 。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：board = [[".",".",".","B",".",".",".","."],[".",".",".","W",".",".",".","."],[".",".",".","W",".",".",".","."],
 * [".",".",".","W",".",".",".","."],["W","B","B",".","W","W","W","B"],[".",".",".","B",".",".",".","."],[".",".","
 * .","B",".",".",".","."],[".",".",".","W",".",".",".","."]], rMove = 4, cMove = 3, color = "B"
 * 输出：true
 * 解释：'.'，'W' 和 'B' 分别用颜色蓝色，白色和黑色表示。格子 (rMove, cMove) 用 'X' 标记。
 * 以选中格子为端点的两个好线段在上图中用红色矩形标注出来了。
 * 示例 2：
 *
 *
 *
 * 输入：board = [[".",".",".",".",".",".",".","."],[".","B",".",".","W",".",".","."],[".",".","W",".",".",".",".","."],
 * [".",".",".","W","B",".",".","."],[".",".",".",".",".",".",".","."],[".",".",".",".","B","W",".","."],[".",".","
 * .",".",".",".","W","."],[".",".",".",".",".",".",".","B"]], rMove = 4, cMove = 4, color = "W"
 * 输出：false
 * 解释：虽然选中格子涂色后，棋盘上产生了好线段，但选中格子是作为中间格子，没有产生以选中格子为端点的好线段。
 *  
 *
 * 提示：
 *
 * board.length == board[r].length == 8
 * 0 <= rMove, cMove < 8
 * board[rMove][cMove] == '.'
 * color 要么是 'B' 要么是 'W' 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/check-if-move-is-legal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 往8个方向找 是否能找到满足题目含义的值
     * @param board
     * @param rMove
     * @param cMove
     * @param color
     * @return
     */
    public boolean checkMove(char[][] board, int rMove, int cMove, char color) {
        // 8个方向
        int[][] direction = new int[][] {
                {-1, 0},
                {1, 0},
                {0, -1},
                {0, 1},
                {-1, -1},
                {-1, 1},
                {1, -1},
                {1, 1}
        };
        // 遍历 任意方向可以返回 true 否则 false
        for (int i = 0; i < 8; i++) {
            boolean isValid = getValidStatus(direction[i], rMove, cMove, color, board);
            if (isValid) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断
     * @param direction 方向
     * @param rMove
     * @param cMove
     * @param color
     * @return
     */
    private boolean getValidStatus(int[] direction, int rMove, int cMove, char color, char[][] board) {
        // 沿着方向一直走 直到遇到边界或者找到 color 相同的模块
        int dx = rMove + direction[0];
        int dy = cMove + direction[1];

        int diffCount = 0;
        boolean hasSame = false;
        while (0 <= dx && dx < board.length && 0 <= dy && dy < board[0].length) {
            if (board[dx][dy] == '.') {
                return false;
            }
            if (board[dx][dy] != color) {
                diffCount++;
            } else {
                // board[dx][dy] == color;
                hasSame = true;
                break;
            }
            dx += direction[0];
            dy += direction[1];
        }
        // 不一样的小于3
        if (diffCount < 1) {
            return false;
        }
        // 没有相同堵头
        return hasSame;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();

        String input = "[[\".\",\".\",\"W\",\".\",\"B\",\"W\",\"W\",\"B\"],[\"B\",\"W\",\".\",\"W\",\".\",\"W\","
                + "\"B\",\"B\"],[\".\",\"W\",\"B\",\"W\",\"W\",\".\",\"W\",\"W\"],[\"W\",\"W\",\".\",\"W\",\".\",\""
                + ".\",\"B\",\"B\"],[\"B\",\"W\",\"B\",\"B\",\"W\",\"W\",\"B\",\".\"],[\"W\",\".\",\"W\",\".\",\".\","
                + "\"B\",\"W\",\"W\"],[\"B\",\".\",\"B\",\"B\",\".\",\".\",\"B\",\"B\"],[\".\",\"W\",\".\",\"W\",\""
                + ".\",\"W\",\".\",\"W\"]]";
        char[][] board = LeetcodeInputUtils.inputString2CharArrayTwoDimensional(input);
        int rMove = 5;
        int cMove = 4;
        char color = 'W';
        boolean b = solution.checkMove(board, rMove, cMove, color);
        System.out.println(b);
        Assert.assertEquals(true, b);
    }

}
