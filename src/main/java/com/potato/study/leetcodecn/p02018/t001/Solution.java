package com.potato.study.leetcodecn.p02018.t001;

/**
 * 2018. 判断单词是否能放入填字游戏内
 *
 * 给你一个 m x n 的矩阵 board ，它代表一个填字游戏 当前 的状态。填字游戏格子中包含小写英文字母（已填入的单词），表示 空 格的 ' ' 和表示 障碍 格子的 '#' 。
 *
 * 如果满足以下条件，那么我们可以 水平 （从左到右 或者 从右到左）或 竖直 （从上到下 或者 从下到上）填入一个单词：
 *
 * 该单词不占据任何 '#' 对应的格子。
 * 每个字母对应的格子要么是 ' ' （空格）要么与 board 中已有字母 匹配 。
 * 如果单词是 水平 放置的，那么该单词左边和右边 相邻 格子不能为 ' ' 或小写英文字母。
 * 如果单词是 竖直 放置的，那么该单词上边和下边 相邻 格子不能为 ' ' 或小写英文字母。
 * 给你一个字符串 word ，如果 word 可以被放入 board 中，请你返回 true ，否则请返回 false 。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：board = [["#", " ", "#"], [" ", " ", "#"], ["#", "c", " "]], word = "abc"
 * 输出：true
 * 解释：单词 "abc" 可以如上图放置（从上往下）。
 * 示例 2：
 *
 *
 *
 * 输入：board = [[" ", "#", "a"], [" ", "#", "c"], [" ", "#", "a"]], word = "ac"
 * 输出：false
 * 解释：无法放置单词，因为放置该单词后上方或者下方相邻格会有空格。
 * 示例 3：
 *
 *
 *
 * 输入：board = [["#", " ", "#"], [" ", " ", "#"], ["#", " ", "c"]], word = "ca"
 * 输出：true
 * 解释：单词 "ca" 可以如上图放置（从右到左）。
 *  
 *
 * 提示：
 *
 * m == board.length
 * n == board[i].length
 * 1 <= m * n <= 2 * 105
 * board[i][j] 可能为 ' ' ，'#' 或者一个小写英文字母。
 * 1 <= word.length <= max(m, n)
 * word 只包含小写英文字母。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/check-if-word-can-be-placed-in-crossword
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 2018
    public boolean placeWordInCrossword(char[][] board, String word) {
        // 如果每个位置 可以开始 那么就开始 分别往 8个方向找
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                char ch = word.charAt(0);
                if (ch == board[i][j] || board[i][j] == ' ') {
                    boolean canPlaceWord = dfs(i, j, word, board);
                    if (canPlaceWord) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 往 4个方向找
     * @param i
     * @param j
     * @param word
     * @return
     */
    private boolean dfs(int i, int j, String word, char[][] board) {
        int[][] dir = new int[][] {
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1}
        };
        char[] chars = word.toCharArray();
        int di = i;
        int dj = j;
        for (int k = 0; k < 4; k++) {
            int[] direction = dir[k];
            boolean canPlace = true;
            for (int l = 0; l < word.length(); l++) {
                if (board[di][dj] == '#'
                        || (board[di][dj] != ' ' && board[di][dj] != chars[l])) {
                    canPlace = false;
                    break;
                }
                di += direction[0];
                di += direction[1];
            }
            if (canPlace) {
                return true;
            }
        }
        return false;
    }

}

