package com.potato.study.leetcodecn.p02267.t001;

import java.sql.Struct;

import org.junit.Assert;

import com.potato.study.leetcode.util.LeetcodeInputUtils;

/**
 * 2267. 检查是否有合法括号字符串路径
 *
 * 一个括号字符串是一个 非空 且只包含 '(' 和 ')' 的字符串。如果下面 任意 条件为 真 ，那么这个括号字符串就是 合法的 。
 *
 * 字符串是 () 。
 * 字符串可以表示为 AB（A 连接 B），A 和 B 都是合法括号序列。
 * 字符串可以表示为 (A) ，其中 A 是合法括号序列。
 * 给你一个 m x n 的括号网格图矩阵 grid 。网格图中一个 合法括号路径 是满足以下所有条件的一条路径：
 *
 * 路径开始于左上角格子 (0, 0) 。
 * 路径结束于右下角格子 (m - 1, n - 1) 。
 * 路径每次只会向 下 或者向 右 移动。
 * 路径经过的格子组成的括号字符串是 合法 的。
 * 如果网格图中存在一条 合法括号路径 ，请返回 true ，否则返回 false 。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：grid = [["(","(","("],[")","(",")"],["(","(",")"],["(","(",")"]]
 * 输出：true
 * 解释：上图展示了两条路径，它们都是合法括号字符串路径。
 * 第一条路径得到的合法字符串是 "()(())" 。
 * 第二条路径得到的合法字符串是 "((()))" 。
 * 注意可能有其他的合法括号字符串路径。
 * 示例 2：
 *
 *
 *
 * 输入：grid = [[")",")"],["(","("]]
 * 输出：false
 * 解释：两条可行路径分别得到 "))(" 和 ")((" 。由于它们都不是合法括号字符串，我们返回 false 。
 *  
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 100
 * grid[i][j] 要么是 '(' ，要么是 ')' 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/check-if-there-is-a-valid-parentheses-string-path
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 2267
    public boolean hasValidPath(char[][] grid) {
        // dfs
        int i = 0;
        int j = 0;
        int status = 0;
        // 剪枝
        if ((grid.length * grid[0].length)%2 == 1) {
            return false;
        }
        if (grid[0][0] == ')' || grid[grid.length - 1][grid[0].length - 1] == '(') {
            return false;
        }
        boolean[][][] visit = new boolean[grid.length][grid[0].length][grid[0].length + grid.length];
        boolean res = dfs(i, j, status, grid, visit);
        return res;
    }

    /**
     *
     * @param i
     * @param j
     * @param status
     * @return
     */
    private boolean dfs(int i, int j, int status, char[][] grid,  boolean[][][] visit) {
        // 终止条件，最后一个节点 或者 status 小于 0
        if (status < 0) {
            return false;
        }
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return false;
        }
        if (visit[i][j][status]) {
            return false;
        }
        visit[i][j][status] = true;
        // 计算当前值
        if (grid[i][j] == '(') {
            status++;
        } else {
            status--;
        }
        // 如果当前剩下的 字符 都是 ） 都不够
        if (status > grid.length + grid[0].length - i - j - 2) {
            return false;
        }
        if (i == grid.length - 1 && j == grid[0].length - 1) {
            return status == 0;
        }
        // 往下 or 右边 走
        return dfs(i+1, j, status, grid, visit) || dfs(i, j+1, status, grid, visit);
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "[[\"(\",\"(\",\"(\"],[\")\",\"(\",\")\"],[\"(\",\"(\",\")\"],[\"(\",\"(\",\")\"]]";
        char[][] grid = LeetcodeInputUtils.inputString2CharArrayTwoDimensional(input);
        boolean b = solution.hasValidPath(grid);
        System.out.println(b);
        Assert.assertEquals(true, b);


        input = "[[\")\",\")\"],[\"(\",\"(\"]]";
        grid = LeetcodeInputUtils.inputString2CharArrayTwoDimensional(input);
        b = solution.hasValidPath(grid);
        System.out.println(b);
        Assert.assertEquals(false, b);


        input = "[[\"(\",\")\",\")\",\"(\",\"(\",\"(\",\"(\",\")\",\")\",\"(\",\")\",\"(\",\")\",\"(\",\"(\",\"(\",\""
                + "(\",\")\",\"(\",\")\",\"(\"],[\"(\",\"(\",\")\",\")\",\"(\",\")\",\")\",\")\",\"(\",\")\",\"(\","
                + "\")\",\"(\",\"(\",\")\",\"(\",\"(\",\"(\",\"(\",\"(\",\")\"],[\")\",\")\",\"(\",\")\",\")\",\"(\","
                + "\"(\",\")\",\"(\",\"(\",\")\",\"(\",\")\",\")\",\"(\",\")\",\")\",\"(\",\"(\",\")\",\")\"],[\"(\","
                + "\"(\",\")\",\"(\",\")\",\"(\",\")\",\")\",\")\",\"(\",\")\",\"(\",\"(\",\")\",\"(\",\")\",\")\",\""
                + "(\",\")\",\")\",\")\"],[\"(\",\"(\",\"(\",\")\",\"(\",\"(\",\")\",\"(\",\")\",\")\",\"(\",\")\","
                + "\")\",\")\",\")\",\")\",\")\",\"(\",\")\",\"(\",\"(\"],[\")\",\")\",\"(\",\"(\",\")\",\")\",\")\","
                + "\")\",\")\",\"(\",\")\",\")\",\")\",\"(\",\"(\",\")\",\"(\",\"(\",\"(\",\"(\",\")\"],[\")\",\")\","
                + "\")\",\")\",\"(\",\")\",\"(\",\")\",\"(\",\"(\",\")\",\"(\",\"(\",\")\",\"(\",\"(\",\")\",\")\",\""
                + "(\",\")\",\"(\"],[\"(\",\")\",\"(\",\"(\",\"(\",\")\",\")\",\")\",\")\",\"(\",\"(\",\")\",\"(\",\""
                + "(\",\")\",\")\",\"(\",\")\",\")\",\")\",\"(\"],[\"(\",\")\",\"(\",\")\",\"(\",\"(\",\"(\",\"(\","
                + "\")\",\"(\",\"(\",\"(\",\"(\",\"(\",\"(\",\")\",\"(\",\")\",\"(\",\")\",\")\"],[\"(\",\")\",\"(\","
                + "\"(\",\"(\",\")\",\"(\",\")\",\")\",\")\",\")\",\"(\",\"(\",\"(\",\"(\",\")\",\")\",\"(\",\"(\",\""
                + "(\",\")\"],[\"(\",\"(\",\")\",\"(\",\")\",\")\",\"(\",\")\",\"(\",\")\",\")\",\")\",\")\",\")\",\""
                + "(\",\")\",\"(\",\")\",\")\",\")\",\"(\"],[\")\",\"(\",\"(\",\"(\",\")\",\"(\",\")\",\")\",\"(\","
                + "\")\",\"(\",\")\",\"(\",\"(\",\")\",\"(\",\"(\",\")\",\"(\",\"(\",\")\"],[\"(\",\")\",\"(\",\")\","
                + "\")\",\"(\",\"(\",\")\",\"(\",\")\",\"(\",\")\",\")\",\")\",\"(\",\"(\",\"(\",\"(\",\")\",\"(\","
                + "\")\"],[\"(\",\"(\",\")\",\"(\",\")\",\")\",\"(\",\"(\",\"(\",\")\",\"(\",\")\",\"(\",\"(\",\")\","
                + "\")\",\"(\",\"(\",\"(\",\")\",\")\"],[\"(\",\"(\",\"(\",\"(\",\")\",\")\",\"(\",\")\",\"(\",\"(\","
                + "\"(\",\")\",\")\",\"(\",\")\",\"(\",\")\",\")\",\")\",\")\",\"(\"],[\"(\",\"(\",\"(\",\")\",\")\","
                + "\")\",\"(\",\")\",\")\",\"(\",\")\",\")\",\"(\",\"(\",\")\",\"(\",\")\",\"(\",\"(\",\"(\",\")\"],"
                + "[\")\",\")\",\")\",\")\",\")\",\")\",\"(\",\")\",\")\",\")\",\"(\",\"(\",\")\",\"(\",\")\",\"(\","
                + "\"(\",\"(\",\"(\",\")\",\")\"]]";
        grid = LeetcodeInputUtils.inputString2CharArrayTwoDimensional(input);
        b = solution.hasValidPath(grid);
        System.out.println(b);
        Assert.assertEquals(false, b);
    }
}
