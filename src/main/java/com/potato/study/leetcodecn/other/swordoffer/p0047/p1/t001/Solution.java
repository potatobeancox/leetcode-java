package com.potato.study.leetcodecn.other.swordoffer.p0047.p1.t001;

/**
 * 剑指 Offer 47. 礼物的最大价值
 *
 * 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？

  

 示例 1:

 输入:
 [
   [1,3,1],
   [1,5,1],
   [4,2,1]
 ]
 输出: 12
 解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物
  

 提示：

 0 < grid.length <= 200
 0 < grid[0].length <= 200

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/li-wu-de-zui-da-jie-zhi-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int maxValue(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            if (i == 0) {
                dp[i][0] = grid[i][0];
                continue;
            }
            dp[i][0] = grid[i][0] + dp[i-1][0];
        }

        for (int i = 1; i < grid[0].length; i++) {
            dp[0][i] = dp[0][i-1] + grid[0][i];
        }

        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }
        return dp[grid.length-1][grid[0].length -1];
    }

//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        String s = "abcabcbb";
//        int i = solution.lengthOfLongestSubstring(s);
//        System.out.println(i);
//        Assert.assertEquals(3, i);
//
//        s = "bbbbb";
//        i = solution.lengthOfLongestSubstring(s);
//        System.out.println(i);
//        Assert.assertEquals(1, i);
//        //
//    }

}
