package com.potato.study.leetcodecn.p01042.t001;

import java.util.Arrays;

import org.junit.Assert;

import com.potato.study.leetcode.util.LeetcodeInputUtils;

/**
 * 1042. 不邻接植花
 *
 * 有 n 个花园，按从 1 到 n 标记。另有数组 paths ，其中 paths[i] = [xi, yi] 描述了花园 xi 到花园 yi 的双向路径。在每个花园中，你打算种下四种花之一。
 *
 * 另外，所有花园 最多 有 3 条路径可以进入或离开.
 *
 * 你需要为每个花园选择一种花，使得通过路径相连的任何两个花园中的花的种类互不相同。
 *
 * 以数组形式返回 任一 可行的方案作为答案 answer，其中 answer[i] 为在第 (i+1) 个花园中种植的花的种类。花的种类用  1、2、3、4 表示。保证存在答案。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 3, paths = [[1,2],[2,3],[3,1]]
 * 输出：[1,2,3]
 * 解释：
 * 花园 1 和 2 花的种类不同。
 * 花园 2 和 3 花的种类不同。
 * 花园 3 和 1 花的种类不同。
 * 因此，[1,2,3] 是一个满足题意的答案。其他满足题意的答案有 [1,2,4]、[1,4,2] 和 [3,2,1]
 * 示例 2：
 *
 * 输入：n = 4, paths = [[1,2],[3,4]]
 * 输出：[1,2,1,2]
 * 示例 3：
 *
 * 输入：n = 4, paths = [[1,2],[2,3],[3,4],[4,1],[1,3],[2,4]]
 * 输出：[1,2,3,4]
 *  
 *
 * 提示：
 *
 * 1 <= n <= 104
 * 0 <= paths.length <= 2 * 104
 * paths[i].length == 2
 * 1 <= xi, yi <= n
 * xi != yi
 * 每个花园 最多 有 3 条路径可以进入或离开
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flower-planting-with-no-adjacent
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int[] gardenNoAdj(int n, int[][] paths) {
        // 将path 转换成 邻接矩阵
        boolean[][] connect = new boolean[n+1][n+1];
        for (int i = 0; i < paths.length; i++) {
            int from = paths[i][0];
            int to = paths[i][1];
            connect[from][to] = true;
            connect[to][from] = true;
        }
        // 用结果记录 每次 判断 是否用过了 所有颜色 上色
        int[] color = new int[n];
        for (int i = 1; i <= n; i++) {
            boolean[] hasUse = new boolean[5];
            for (int j = 1; j <= n; j++) {
                if (!connect[i][j]) {
                    continue;
                }
                if (color[j-1] == 0) {
                    continue;
                }
                hasUse[color[j-1]] = true;
            }
            // 找到第一个没用的颜色
            for (int j = 1; j <= 4; j++) {
                if (!hasUse[j]) {
                    color[i-1] = j;
                    break;
                }
            }
        }
        return color;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 3;
        String input = "[[1,2],[2,3],[3,1]]";
        int[][] paths = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        int[] ints = solution.gardenNoAdj(n, paths);
        System.out.println(Arrays.toString(ints));
    }
}
