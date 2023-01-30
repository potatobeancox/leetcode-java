package com.potato.study.leetcodecn.p01820.t001;

import java.util.Arrays;

/**
 * 1820. 最多邀请的个数
 *
 * 某一个班级有 m 个男孩和 n 个女孩，即将举行一个派对。
 *
 * 给定一个 m x n 的整数矩阵 grid ，其中 grid[i][j] 等于 0 或 1 。 若 grid[i][j] == 1 ，则表示第 i 个男孩可以邀请第 j 个女孩参加派对。 一个男孩最多可以邀请一个女孩，一个女孩最多可以接受一个男孩的一个邀请。
 *
 * 返回可能的最多邀请的个数。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: grid = [[1,1,1],
 *                [1,0,1],
 *                [0,0,1]]
 * 输出: 3
 * 解释: 按下列方式邀请：
 * - 第 1 个男孩邀请第 2 个女孩。
 * - 第 2 个男孩邀请第 1 个女孩。
 * - 第 3 个男孩邀请第 3 个女孩。
 * 示例 2:
 *
 * 输入: grid = [[1,0,1,0],
 *                [1,0,0,0],
 *                [0,0,1,0],
 *                [1,1,1,0]]
 * 输出: 3
 * 解释: 按下列方式邀请：
 * - 第 1 个男孩邀请第 3 个女孩。
 * - 第 2 个男孩邀请第 1 个女孩。
 * - 第 3 个男孩未邀请任何人。
 * - 第 4 个男孩邀请第 2 个女孩。
 *  
 *
 * 提示：
 *
 * grid.length == m
 * grid[i].length == n
 * 1 <= m, n <= 200
 * grid[i][j] 是 0 或 1 之一。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-number-of-accepted-invitations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int maximumInvitations(int[][] grid) {
        // 对于 男孩 找到女生
        int boyCount = grid.length;
        int girlCount = grid[0].length;
        // 记录当前男孩子的女伴 -1为还没有找到
        int[] girlMatched = new int[girlCount];
        Arrays.fill(girlMatched, -1);
        int inviteCount = 0;
        for (int i = 0; i < boyCount; i++) {
            boolean[] used = new boolean[girlCount];
            // 第i个男孩 找舞伴 假设女生都没有舞伴
            int boy = i;
            boolean canInvite = invite(grid, i, used, girlMatched);
            if (canInvite) {
                inviteCount++;
            }
        }
        return inviteCount;
    }

    private boolean invite(int[][] grid, int boyIndex, boolean[] used, int[] girlMatched) {
        // 都能邀请谁
        int[] girls = grid[boyIndex];
        for (int i = 0; i < girls.length; i++) {
            // 这个boy 不能邀请
            if (girls[i] == 0) {
                continue;
            }
            if (used[i]) {
                continue;
            }
            used[i] = true;
            // 这个女孩还没被邀请过 就用这个了
            if (girlMatched[i] == -1) {
                girlMatched[i] = boyIndex;
                return true;
            }
            // 这个女孩被邀请了 让邀请的那个换个女孩
            boolean canInvite = invite(grid, girlMatched[i], used, girlMatched);
            if (canInvite) {
                girlMatched[i] = boyIndex;
                return true;
            }
        }
        return false;
    }
}
