package com.potato.study.leetcodecn.other.swordoffer.p0012.p1.t001;

import org.junit.Assert;

/**
 * 剑指 Offer 12. 矩阵中的路径
 *
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 *  
 *
 * 例如，在下面的 3×4 的矩阵中包含单词 "ABCCED"（单词中的字母已标出）。
 *
 *
 *
 *  
 *
 * 示例 1：
 *
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 * 示例 2：
 *
 * 输入：board = [["a","b"],["c","d"]], word = "abcd"
 * 输出：false
 *  
 *
 * 提示：
 *
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 * board 和 word 仅由大小写英文字母组成
 *  
 *
 * 注意：本题与主站 79 题相同：https://leetcode-cn.com/problems/word-search/
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public boolean exist(char[][] board, String word) {
        boolean[][] visit = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (exist(board, word, 0, visit, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * dfs 查找
     * @param board
     * @param word
     * @param index
     * @param visit
     * @return
     */
    private boolean exist(char[][] board, String word, int index,
            boolean[][] visit, int i, int j) {
        if (index >= word.length()) {
            return true;
        }
        if (visit[i][j]) {
            return false;
        }
        char ch = word.charAt(index);
        if (board[i][j] != ch) {
            return false;
        }
        // 如果当前位置是最后一个位置，那么可以直接返回
        if (index == word.length() - 1) {
            return true;
        }
        // 找下个位置
        boolean isExist = false;
        int m = board.length;
        int n = board[0].length;
        visit[i][j] = true;
        if (i > 0) {
            isExist = exist(board, word, index + 1, visit, i-1, j);
            if (isExist) {
                return true;
            }
        }
        if (i < m -1) {
            isExist = exist(board, word, index + 1, visit, i+1, j);
            if (isExist) {
                return true;
            }
        }
        if (j > 0) {
            isExist = exist(board, word, index + 1, visit, i, j-1);
            if (isExist) {
                return true;
            }
        }
        if (j < n-1) {
            isExist = exist(board, word, index + 1, visit, i, j+1);
            if (isExist) {
                return true;
            }
        }
        visit[i][j] = false;
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] board = new char[][] {
                {'a'}
        };
        String word = "a";
        boolean exist = solution.exist(board, word);
        System.out.println(exist);
        Assert.assertEquals(true, exist);
    }

}
