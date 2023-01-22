package com.potato.study.leetcodecn.p02282.t001;

import java.util.Stack;

/**
 * 2282. 在一个网格中可以看到的人数
 *
 * 给定一个 m x n 下标从 0 开始的二维正整数数组 heights，其中 heights[i][j] 是站在位置 (i, j) 上的人的高度。
 *
 * 站在 (row1, col1) 位置的人可以看到站在 (row2, col2) 位置的人，前提是:
 *
 * (row2, col2) 的人在 (row1, col1) 的人的右边 或 下面。更正式地说，要么 row1 == row2 时 col1 < col2，要么 row1 < row2 时 col1 == col2。
 * 他们中间的人 都 比他们两个矮。
 * 返回一个 m x n 的二维整数数组answer，其中 answer[i][j] 是位于 (i, j) 位置的人可以看到的人数。
 *
 *  
 *
 * 示例 1:
 *
 *
 * 输入: heights = [[3,1,4,2,5]]
 * 输出: [[2,1,2,1,0]]
 * 解释:
 * - (0,0) 上的人可以看到 (0,1) 和 (0,2) 的人。
 *   注意，他看不到 (0,4) 上的人，因为 (0,2) 上的人比他高。
 * - (0,1) 上的人可以看到 (0,2) 上的人。
 * - (0,2) 上的人可以看到 (0,3) 和 (0,4) 的人。
 * - (0,3) 上的人可以看到 (0,4) 上的人。
 * - (0,4) 上的人看不到任何人。
 * 示例 2:
 *
 *
 * 输入: heights = [[5,1],[3,1],[4,1]]
 * 输出: [[3,1],[2,1],[1,0]]
 * 解释:
 * - (0,0) 上的人可以看到 (0,1)、(1,0) 和 (2,0) 的人。
 * - (0,1) 上的人可以看到 (1,1) 上的人。
 * - (1,0) 上的人可以看到 (1,1) 和 (2,0) 的人。
 * - (1,1) 上的人可以看到 (2,1) 上的人。
 * - (2,0) 上的人可以看到 (2,1) 上的人。
 * - (2,1) 上的人看不到任何人。
 *  
 *
 * 提示:
 *
 * 1 <= heights.length <= 400
 * 1 <= heights[i].length <= 400
 * 1 <= heights[i][j] <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/number-of-people-that-can-be-seen-in-a-grid
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int[][] seePeople(int[][] heights) {
        // 弄已给结果数组 记录 每个位置的结果
        int m = heights.length;
        int n = heights[0].length;
        int[][] res = new int[m][n];
        // 对于 每行每列分别 固定 行和列 从尾部开始往前找 用一个栈 记录 当前有多少 可以看到的 单调递减栈
        for (int i = 0; i < m; i++) {
            fill(res, i, i+1, n-1, -1, 1, -1, heights);
        }
        for (int j = 0; j < n; j++) {
            fill(res, m-1, -1, j, j+1, -1, 1, heights);
        }
        return res;
    }

    private void fill(int[][] res, int startI, int endI, int startJ, int endJ, int di, int dj, int[][] heights) {
        // 从 startI 到 endI（不包括），每次移动 di
        Stack<Integer> stack = new Stack<>();
        // 同理 dj 过程中用一个 stack 递减栈 栈顶最小 依次找到 栈内比 当前值小的值 这些值都是 ij点可以看见的
        for (int i = startI; i != endI; i+=di) {
            for (int j = startJ; j != endJ; j+=dj) {
                while (!stack.isEmpty() && stack.peek() < heights[i][j]) {
                    res[i][j]++;
                    stack.pop();
                }
                if (!stack.isEmpty()) {
                    res[i][j]++;
                }
                if (stack.isEmpty() || stack.peek() != heights[i][j]) {
                    stack.push(heights[i][j]);
                }
            }
        }
    }
}
