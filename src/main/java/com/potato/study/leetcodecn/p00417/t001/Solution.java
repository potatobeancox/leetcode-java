package com.potato.study.leetcodecn.p00417.t001;

import java.util.ArrayList;
import java.util.List;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

/**
 * 417. 太平洋大西洋水流问题
 *
 * 给定一个 m x n 的非负整数矩阵来表示一片大陆上各个单元格的高度。“太平洋”处于大陆的左边界和上边界，而“大西洋”处于大陆的右边界和下边界。
 *
 * 规定水流只能按照上、下、左、右四个方向流动，且只能从高到低或者在同等高度上流动。
 *
 * 请找出那些水流既可以流动到“太平洋”，又能流动到“大西洋”的陆地单元的坐标。
 *
 *  
 *
 * 提示：
 *
 * 输出坐标的顺序不重要
 * m 和 n 都小于150
 *  
 *
 * 示例：
 *
 *  
 *
 * 给定下面的 5x5 矩阵:
 *
 *   太平洋 ~   ~   ~   ~   ~
 *        ~  1   2   2   3  (5) *
 *        ~  3   2   3  (4) (4) *
 *        ~  2   4  (5)  3   1  *
 *        ~ (6) (7)  1   4   5  *
 *        ~ (5)  1   1   2   4  *
 *           *   *   *   *   * 大西洋
 *
 * 返回:
 *
 * [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (上图中带括号的单元).
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/pacific-atlantic-water-flow
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class Solution {



    // 417
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        // dp 从 第一个开始 往右下
//        boolean[][] canReach1 = new boolean[heights.length][heights[0].length];
//        boolean[][] canReach2 = new boolean[heights.length][heights[0].length];
//        // 第一行和最后一行
//        for (int i = 0; i < heights[0].length; i++) {
//            visit1[0][i] = true;
//            result1[0][i] = true;
//
//            visit2[heights.length-1][i] = true;
//            result2[heights.length-1][i] = true;
//        }
//        // 第一列和最后一列
//        for (int i = 0; i < heights.length; i++) {
//            visit1[i][0] = true;
//            result1[i][0] = true;
//
//            visit2[i][heights[0].length-1] = true;
//            result2[i][heights[0].length-1] = true;
//        }
//
//        for (int i = 0; i < heights.length; i++) {
//            for (int j = 0; j < heights[0].length; j++) {
//                dfs(heights, visit1, result1, i, j);
//                dfs(heights, visit2, result2, heights.length - 1 - i, heights[0].length - 1 - j);
//            }
//        }
//
//
//
//        List<List<Integer>> resultList = new ArrayList<>();
//        for (int i = 0; i < heights.length; i++) {
//            for (int j = 0; j < heights[0].length; j++) {
//                if (result1[i][j] && result2[i][j]) {
//
//                    List<Integer> list = new ArrayList<>();
//                    list.add(i);
//                    list.add(j);
//
//                    resultList.add(list);
//                }
//            }
//        }
//        return resultList;
        return null;
    }



    private int[][] direction = new int[][] {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
    };
    /**
     *
     * @param heights 结果
     * @param visit
     * @param result
     */
    private void dfs(int[][] heights, boolean[][] visit, boolean[][] result, int i, int j) {
        if (visit[i][j]) {
            return;
        }
        visit[i][j] = true;
        // 四个方向比较结果
        for (int k = 0; k < direction.length; k++) {
            int di = i + direction[k][0];
            int dj = j + direction[k][1];
            // 坐标有效性
            if (di < 0 || di >= heights.length
                    || dj < 0 || dj >= heights[0].length) {
                continue;
            }
            // 水不能往高处流
            if (heights[i][j] < heights[di][dj]) {
                continue;
            }
            dfs(heights, visit, result, di, dj);
            result[i][j] |= result[di][dj];
            if (result[i][j]) {
                break;
            }
        }

    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "[[1,2,3],[8,9,4],[7,6,5]]";
        int[][] arr = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        List<List<Integer>> lists = solution.pacificAtlantic(arr);
        System.out.println(lists);


        input = "[[11,3,2,4,14,6,13,18,1,4,12,2,4,1,16],[5,11,18,0,15,14,6,17,2,17,19,15,12,3,14],[10,2,5,13,11,11,13,19,11,17,14,18,14,3,11],[14,2,10,7,5,11,6,11,15,11,6,11,12,3,11],[13,1,16,15,8,2,16,10,9,9,10,14,7,15,13],[17,12,4,17,16,5,0,4,10,15,15,15,14,5,18],[9,13,18,4,14,6,7,8,5,5,6,16,13,7,2],[19,9,16,19,16,6,1,11,7,2,12,10,9,18,19],[19,5,19,10,7,18,6,10,7,12,14,8,4,11,16],[13,3,18,9,16,12,1,0,1,14,2,6,1,16,6],[14,1,12,16,7,15,9,19,14,4,16,6,11,15,7],[6,15,19,13,3,2,13,7,19,11,13,16,0,16,16],[1,5,9,7,12,9,2,18,6,12,1,8,1,10,19],[10,11,10,11,3,5,12,0,0,8,15,7,5,13,19],[8,1,17,18,3,6,8,15,0,9,8,8,12,5,18],[8,3,6,12,18,15,10,10,12,19,16,7,17,17,1],[12,13,6,4,12,18,18,9,4,9,13,11,5,3,14],[8,4,12,11,2,2,10,3,11,17,14,2,17,4,7],[8,0,14,0,13,17,11,0,16,13,15,17,4,8,3],[18,15,8,11,18,3,10,18,3,3,15,9,11,15,15]]";
        arr = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        lists = solution.pacificAtlantic(arr);
        System.out.println(lists);
    }
}
