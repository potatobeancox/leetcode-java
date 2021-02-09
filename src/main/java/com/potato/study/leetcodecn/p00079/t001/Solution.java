package com.potato.study.leetcodecn.p00079.t001;


import java.util.ArrayList;
import java.util.List;

/**
 * 79. 单词搜索
 *
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。

 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

  

 示例:

 board =
 [
 ['A','B','C','E'],
 ['S','F','C','S'],
 ['A','D','E','E']
 ]

 给定 word = "ABCCED", 返回 true
 给定 word = "SEE", 返回 true
 给定 word = "ABCB", 返回 false
  

 提示：

 board 和 word 中只包含大写和小写英文字母。
 1 <= board.length <= 200
 1 <= board[i].length <= 200
 1 <= word.length <= 10^3

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/word-search
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 深度优先搜索
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        int i = 0;
        int j = 0;
        int index = 0;
        return dfsJudgeExist(board, word, i, j, index);
    }

    /**
     * 判定 word 是够在矩阵中存在
     * @param board
     * @param word
     * @param i
     * @param j
     * @param index
     * @return
     */
    private boolean dfsJudgeExist(char[][] board, String word, int i, int j, int index) {
    }
}
