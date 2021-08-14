package com.potato.study.leetcodecn.other.Interview.p0008.p0012;


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

//    /**
//     *
//     * @param n
//     * @return
//     */
//    public List<List<String>> solveNQueens(int n) {
//        List<List<String>> resultList = new ArrayList<>();
//        boolean[][] visited = new boolean[n][n];
//        char[][] checkerboard = new char[n][n];
//        // 都弄成 .
//        for (int i = 0; i < checkerboard.length; i++) {
//            checkerboard[i] = new char[n];
//            Arrays.fill(checkerboard, '.');
//        }
//        solveNQueens(n, 0, checkerboard, 0, 0, resultList, visited);
//        return resultList;
//    }
//
//    private void solveNQueens(int n, int hasSetNum, char[][] checkerboard,
//            int i, int j, List<List<String>> resultList, boolean[][] visited) {
//        // 终止条件 已经全部放完了
//        if (n == hasSetNum) {
//            List<String> serialization = serializeCheckerboard(checkerboard);
//            resultList.add(serialization);
//            return;
//        }
//        // ij 放置 Q 判定是不是ok
//        if (visited[i][j]) {
//            return;
//        }
//        visited[i][j] = true;
//        // 不ok 直接返回s
//        boolean canSet = checkNewQueueOnTheCheckerboard(checkerboard, i, j);
//        if (canSet) {
//            checkerboard[i][j] = 'Q';
//        }
//        // ok 的话 递归 搞下个位置
//        for (int k = 0; k < n; k++) {
//            for (int l = 0; l < n; l++) {
//                if (visited[k][l]) {
//                    continue;
//                }
//                solveNQueens(n, );
//            }
//        }
//        // 其他位置都搞完 还原这个
//        visited[i][j] = false;
//        checkerboard[i][j] = '.';
//
//    }
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        List<String> list = solution.generateParenthesis(3);
//        System.out.println(list);
//    }

}
