package com.potato.study.leetcodecn.p00212.t001;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 212. 单词搜索 II
 *
 * 给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words，找出所有同时在二维网格和字典中出现的单词。

 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。

  

 示例 1：


 输入：board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
 输出：["eat","oath"]
 示例 2：


 输入：board = [["a","b"],["c","d"]], words = ["abcb"]
 输出：[]
  

 提示：

 m == board.length
 n == board[i].length
 1 <= m, n <= 12
 board[i][j] 是一个小写英文字母
 1 <= words.length <= 3 * 104
 1 <= words[i].length <= 10
 words[i] 由小写英文字母组成
 words 中的所有字符串互不相同

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/word-search-ii
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    private Dic dic;

    public List<String> findWords(char[][] board, String[] words) {
        this.dic = new Dic();
        // 将word 插入 字典树
        for (String word : words) {
            addWord(word);
        }
        Set<String> result = new HashSet<>();
        boolean[][] visited = new boolean[board.length][board[0].length];
        this.direction = new int[][] {
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1}
        };
        // 遍历 board 每个位置开始找单词 上线所有
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                find(i, j, visited, board, dic, result);
            }
        }
        return new ArrayList<>(result);
    }

    private int[][] direction;

    private void find(int startI, int startJ, boolean[][] visited, char[][] board, Dic dic, Set<String> result) {
        if (visited[startI][startJ]) {
            return;
        }
        if (dic == null) {
            return;
        }
        int index = board[startI][startJ] - 'a';
        if (dic.child[index] == null) {
            return;
        }
        if (dic.child[index] != null && dic.child[index].isEnd) {
            result.add(dic.child[index].word);
        }
        visited[startI][startJ] = true;
        for (int i = 0; i < direction.length; i++) {
            int di = startI + direction[i][0];
            int dj = startJ + direction[i][1];

            if (di < 0 || di >= board.length
                    || dj < 0 || dj >= board[0].length) {
                continue;
            }

            find(di, dj, visited, board, dic.child[index], result);
        }
        visited[startI][startJ] = false;
    }


    private void addWord(String word) {
        Dic dic = this.dic;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (dic.child[index] == null) {
                dic.child[index] = new Dic();
            }
            dic = dic.child[index];
        }
        // set flag
        dic.isEnd = true;
        dic.word = word;
    }


    class Dic {
        public Dic[] child;
        public boolean isEnd;
        public String word;

        public Dic() {
            this.child = new Dic[26];
        }
    }
}
