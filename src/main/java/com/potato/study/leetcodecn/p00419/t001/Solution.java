package com.potato.study.leetcodecn.p00419.t001;

import org.junit.Assert;

/**
 * 419. 甲板上的战舰
 *
 * 给定一个二维的甲板， 请计算其中有多少艘战舰。 战舰用 'X'表示，空位用 '.'表示。 你需要遵守以下规则：

 给你一个有效的甲板，仅由战舰或者空位组成。
 战舰只能水平或者垂直放置。换句话说,战舰只能由 1xN (1 行, N 列)组成，或者 Nx1 (N 行, 1 列)组成，其中N可以是任意大小。
 两艘战舰之间至少有一个水平或垂直的空位分隔 - 即没有相邻的战舰。
 示例 :

 X..X
 ...X
 ...X
 在上面的甲板中有2艘战舰。

 无效样例 :

 ...X
 XXXX
 ...X
 你不会收到这样的无效甲板 - 因为战舰之间至少会有一个空位将它们分开。

 进阶:

 你可以用一次扫描算法，只使用O(1)额外空间，并且不修改甲板的值来解决这个问题吗？

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/battleships-in-a-board
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 直接 遍历 board 如果当前 ij 上面和 左面 没有 东西 那么 ++ 否则就是 以前计算过 直接跳过
     * @param board
     * @return
     */
    public int countBattleships(char[][] board) {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // 如果当前 X 那么可能是一个新的战舰或者战舰的一部分
                if (board[i][j] == 'X') {
                    if (i > 0 && board[i-1][j] == 'X') {
                        continue;
                    }
                    if (j > 0 && board[i][j-1] == 'X') {
                        continue;
                    }
                    count++;
                }
            }
        }
        return count;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] board = new char[][] {
                {},
                {},
                {}
        };
        int count = solution.countBattleships(board);
        System.out.println(count);
        Assert.assertEquals(2, count);
    }
}
