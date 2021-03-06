package com.potato.study.leetcodecn.p01992.t001;

import java.util.ArrayList;
import java.util.List;

/**
 * 1992. 找到所有的农场组
 *
 * 给你一个下标从 0 开始，大小为 m x n 的二进制矩阵 land ，其中 0 表示一单位的森林土地，1 表示一单位的农场土地。

 为了让农场保持有序，农场土地之间以矩形的 农场组 的形式存在。每一个农场组都 仅 包含农场土地。且题目保证不会有两个农场组相邻，也就是说一个农场组中的任何一块土地都 不会 与另一个农场组的任何一块土地在四个方向上相邻。

 land 可以用坐标系统表示，其中 land 左上角坐标为 (0, 0) ，右下角坐标为 (m-1, n-1) 。请你找到所有 农场组 最左上角和最右下角的坐标。一个左上角坐标为 (r1, c1) 且右下角坐标为 (r2, c2) 的 农场组 用长度为 4 的数组 [r1, c1, r2, c2] 表示。

 请你返回一个二维数组，它包含若干个长度为 4 的子数组，每个子数组表示 land 中的一个 农场组 。如果没有任何农场组，请你返回一个空数组。可以以 任意顺序 返回所有农场组。

 示例 1：



 输入：land = [[1,0,0],[0,1,1],[0,1,1]]
 输出：[[0,0,0,0],[1,1,2,2]]
 解释：
 第一个农场组的左上角为 land[0][0] ，右下角为 land[0][0] 。
 第二个农场组的左上角为 land[1][1] ，右下角为 land[2][2] 。
 示例 2：



 输入：land = [[1,1],[1,1]]
 输出：[[0,0,1,1]]
 解释：
 第一个农场组左上角为 land[0][0] ，右下角为 land[1][1] 。
 示例 3：



 输入：land = [[0]]
 输出：[]
 解释：
 没有任何农场组。
  

 提示：

 m == land.length
 n == land[i].length
 1 <= m, n <= 300
 land 只包含 0 和 1 。
 农场组都是 矩形 的形状。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/find-all-groups-of-farmland
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     *
     * @param land
     * @return
     */
    public int[][] findFarmland(int[][] land) {
        List<int[]> list = new ArrayList<>();
        // 从每个 农场点开始 作为 左上点枚举右下点，如果找到右下点 找到了将中间点变成0 输出结果
        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[0].length; j++) {
                // 是否农场
                if (land[i][j] == 0) {
                    continue;
                }
                // 开始点 ij
                int di = 1;
                while (i + di < land.length && land[i + di][j] == 1) {
                    di++;
                }
                int dj = 1;
                while (j + dj < land[0].length && land[i][j + dj] == 1) {
                    dj++;
                }
                // 从开始点往下找 结束点 找到记录 结果 中间的 点全部变成0
                list.add(new int[] {i, j, i + di - 1, j + dj - 1});
                // 变成0
                for (int k = i; k < i + di; k++) {
                    for (int l = j; l < j + dj; l++) {
                        land[k][l] = 0;
                    }
                }
            }
        }
        int[][] res = new int[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

}
