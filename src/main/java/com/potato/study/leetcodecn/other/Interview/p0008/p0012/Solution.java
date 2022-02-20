package com.potato.study.leetcodecn.other.Interview.p0008.p0012;


import java.util.*;

/**
 * 面试题 08.12. 八皇后
 *
 * 设计一种算法，打印 N 皇后在 N × N 棋盘上的各种摆法，其中每个皇后都不同行、不同列，也不在对角线上。这里的“对角线”指的是所有的对角线，不只是平分整个棋盘的那两条对角线。
 *
 * 注意：本题相对原题做了扩展
 *
 * 示例:
 *
 *  输入：4
 *  输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 *  解释: 4 皇后问题存在如下两个不同的解法。
 * [
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/eight-queens-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // dfs 如何保证不重复 选择 同一种 皇后摆放


    /**
     * https://leetcode-cn.com/problems/eight-queens-lcci/solution/ba-huang-hou-by-leetcode-solution/
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        // 用回溯的方式 使用一个 数组 记录 列是否放了 dfs 中需要记录 放置在哪个位置了
        List<List<String>> result = new ArrayList<>();
        Set<Integer> columnSet = new HashSet<>();
        Set<Integer> diagonalSet1 = new HashSet<>();
        Set<Integer> diagonalSet2 = new HashSet<>();
        int[] queens = new int[n];
        int rowIndex = 0;
        backtracking(result, columnSet, diagonalSet1, diagonalSet2, queens, n, rowIndex);
        return result;
    }

    private void backtracking(List<List<String>> result, Set<Integer> columnSet, Set<Integer> diagonalSet1,
                              Set<Integer> diagonalSet2, int[] queens, int n, int rowIndex) {
        // 终止条件 如果到了 最后一个 row 直接生成结果集合
        if (rowIndex == n) {
            List<String> list = generateResult(queens);
            result.add(list);
            return;
        }
        // 没有的话 在当前行位置 找到对应可以安排的 column位置 往里放 放完了 递归
        for (int i = 0; i < n; i++) {
            // 列的位置
            if (columnSet.contains(i)) {
                continue;
            }
            if (diagonalSet1.contains(i + rowIndex)) {
                continue;
            }
            if (diagonalSet2.contains(i - rowIndex)) {
                continue;
            }
            queens[rowIndex] = i;
            columnSet.add(i);
            diagonalSet1.add(i + rowIndex);
            diagonalSet2.add(i - rowIndex);
            backtracking(result, columnSet, diagonalSet1, diagonalSet2, queens, n, rowIndex + 1);
            diagonalSet1.remove(i + rowIndex);
            diagonalSet2.remove(i - rowIndex);
            columnSet.remove(i);
            queens[rowIndex] = -1;
        }
    }


    /**
     *
     * @param queens 对饮放置在 哪个行的那个列上
     * @return
     */
    private List<String> generateResult(int[] queens) {
        List<String> resultList = new ArrayList<>();
        int n = queens.length;
        for (int i = 0; i < queens.length; i++) {
            char[] chars = new char[n];
            Arrays.fill(chars, '.');
            chars[queens[i]] = 'Q';
            resultList.add(new String(chars));
        }
        return resultList;
    }





}
