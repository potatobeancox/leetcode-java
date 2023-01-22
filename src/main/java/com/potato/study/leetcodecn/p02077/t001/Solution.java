package com.potato.study.leetcodecn.p02077.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.Arrays;

/**
 * 2077. 殊途同归
 *
 * 迷宫由 n 个从 1 到 n 的房间组成，有些房间由走廊连接。给定一个二维整数数组 corridors，其中 corridors[i] = [room1i, room2i] 表示有一条走廊连接 room1i 和room2i，允许迷宫中的一个人从 room1i 到 room1i ，反之亦然。
 *
 * 迷宫的设计者想知道迷宫有多让人困惑。迷宫的 混乱分数 是 长度为 3 的不同的环的数量。
 *
 * 例如, 1 → 2 → 3 → 1 是长度为 3 的环, 但 1 → 2 → 3 → 4 和 1 → 2 → 3 → 2 → 1 不是。
 * 如果在第一个环中访问的一个或多个房间 不在 第二个环中，则认为两个环是 不同 的。
 *
 * 返回迷宫的混乱分数。
 *
 * 示例 1:
 *
 *
 * 输入: n = 5, corridors = [[1,2],[5,2],[4,1],[2,4],[3,1],[3,4]]
 * 输出: 2
 * 解释:
 * 一个长度为 3 的环为 4→1→3→4，用红色表示。
 * 注意，这是与 3→4→1→3 或 1→3→4→1 相同的环，因为房间是相同的。
 * 另一个长度为 3 的环为 1→2→4→1，用蓝色表示。
 * 因此，有两个长度为 3 的不同的环。
 * 示例 2:
 *
 *
 * 输入: n = 4, corridors = [[1,2],[3,4]]
 * 输出: 0
 * 解释:
 * 没有长度为 3 的环。
 *  
 *
 * 提示:
 *
 * 2 <= n <= 1000
 * 1 <= corridors.length <= 5 * 104
 * corridors[i].length == 2
 * 1 <= room1i, room2i <= n
 * room1i != room2i
 * 没有重复的走廊。
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/paths-in-maze-that-lead-to-same-room
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int numberOfPaths(int n, int[][] corridors) {
        // bool 形二维 数组 记录两个点之间是否链接
        boolean[][] connect = new boolean[n][n];
        for (int[] corridor : corridors) {
            int fromIndex = corridor[0] - 1;
            int toIndex = corridor[1] - 1;

            connect[fromIndex][toIndex] = true;
            connect[toIndex][fromIndex] = true;
        }
        // 从 1-n 找到小于 两个点的端点 判断是否可以计数
        int count = 0;
        for (int[] corridor : corridors) {
            for (int i = 0; i < Math.min(corridor[0] - 1, corridor[1] - 1); i++) {
                // 第三个点跟两个定点都是连接的
                if (connect[i][corridor[0]-1] && connect[i][corridor[1]-1]) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 5;
        int[][] corridors = LeetcodeInputUtils.inputString2IntArrayTwoDimensional("[[1,2],[5,2],[4,1],[2,4],[3,1],[3,4]]");
        int i = solution.numberOfPaths(n, corridors);
        System.out.println(i);
//        Assert.assertEquals(, i);
    }


}
