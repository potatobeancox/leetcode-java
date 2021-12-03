package com.potato.study.leetcodecn.p01706.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.Arrays;

/**
 * 1706. 球会落何处
 *
 * 用一个大小为 m x n 的二维网格 grid 表示一个箱子。你有 n 颗球。箱子的顶部和底部都是开着的。

 箱子中的每个单元格都有一个对角线挡板，跨过单元格的两个角，可以将球导向左侧或者右侧。

 将球导向右侧的挡板跨过左上角和右下角，在网格中用 1 表示。
 将球导向左侧的挡板跨过右上角和左下角，在网格中用 -1 表示。
 在箱子每一列的顶端各放一颗球。每颗球都可能卡在箱子里或从底部掉出来。如果球恰好卡在两块挡板之间的 "V" 形图案，或者被一块挡导向到箱子的任意一侧边上，就会卡住。

 返回一个大小为 n 的数组 answer ，其中 answer[i] 是球放在顶部的第 i 列后从底部掉出来的那一列对应的下标，如果球卡在盒子里，则返回 -1 。

  

 示例 1：



 输入：grid = [[1,1,1,-1,-1],[1,1,1,-1,-1],[-1,-1,-1,1,1],[1,1,1,1,-1],[-1,-1,-1,-1,-1]]
 输出：[1,-1,-1,-1,-1]
 解释：示例如图：
 b0 球开始放在第 0 列上，最终从箱子底部第 1 列掉出。
 b1 球开始放在第 1 列上，会卡在第 2、3 列和第 1 行之间的 "V" 形里。
 b2 球开始放在第 2 列上，会卡在第 2、3 列和第 0 行之间的 "V" 形里。
 b3 球开始放在第 3 列上，会卡在第 2、3 列和第 0 行之间的 "V" 形里。
 b4 球开始放在第 4 列上，会卡在第 2、3 列和第 1 行之间的 "V" 形里。
 示例 2：

 输入：grid = [[-1]]
 输出：[-1]
 解释：球被卡在箱子左侧边上。
 示例 3：

 输入：grid = [[1,1,1,1,1,1],[-1,-1,-1,-1,-1,-1],[1,1,1,1,1,1],[-1,-1,-1,-1,-1,-1]]
 输出：[0,1,2,3,4,-1]
  

 提示：

 m == grid.length
 n == grid[i].length
 1 <= m, n <= 100
 grid[i][j] 为 1 或 -1

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/where-will-the-ball-fall
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int[] findBall(int[][] grid) {
        // 小球当前的位置 遍历 grid 对于每个位置进行判定
        int n = grid.length;
        int m = grid[0].length;
        int[] current = new int[grid[0].length];
        for (int i = 0; i < m; i++) {
            current[i] = i;
        }
        for (int i = 0; i < n; i++) {
            findBallLayer(grid, current, i);
        }
        return current;
    }

    /**
     * 判定每层结果
     * @param grid
     * @param current
     * @param i
     */
    private void findBallLayer(int[][] grid, int[] current, int i) {
        int[] layer = grid[i];
        // 遍历current 判断结果
        for (int j = 0; j < current.length; j++) {
            int index = current[j];
            if (index == -1) {
                continue;
            }
            // 左右两侧 不能往下走
            if (index == 0 && layer[index] == -1) {
                current[j] = -1;
                continue;
            }

            if (index == current.length - 1 && layer[index] == 1) {
                current[j] = -1;
                continue;
            }

            // 中间不能往下走
            if (index != 0
                    && layer[index-1] == 1 && layer[index] == -1) {
                current[j] = -1;
                continue;
            }

            if (index != current.length - 1
                    && layer[index] == 1 && layer[index+1] == -1) {
                current[j] = -1;
                continue;

            }

            // 能往下走
            current[j] += layer[index];
        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String str = "[[-1,1,-1,-1,-1,-1,-1,-1,1,-1,-1,-1,-1,1,1,-1,-1,-1,1,1,1,-1,-1,1,1,-1,-1,1,-1,-1,-1,-1,-1,-1,-1,-1,-1,1,-1,1,-1,-1,-1,-1,-1,-1,-1,1,-1,-1,1,-1,1,-1,-1,1,1,-1,1,-1,-1,-1,-1,1,1,1,1,1,1,-1,1,1,1,-1,1,1,1,-1,-1,-1,1,-1,1,-1,-1,1,1,-1,-1,1,-1,1,-1,1,1,1,-1,-1,-1,-1]]";
        int[][] arr = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(str);
        int[] ball = solution.findBall(arr);
        System.out.println(Arrays.toString(ball));

        Assert.assertArrayEquals(new int[] {
                -1,-1,-1,2,3,4,5,6,-1,-1,9,10,11,14,-1,-1,15,16,19,20,-1,-1,21,24,-1,-1,25,-1,-1,28,29,30,31,32,33,34,35,-1,-1,-1,-1,40,41,42,43,44,45,-1,-1,48,-1,-1,-1,-1,53,56,-1,-1,-1,-1,59,60,61,64,65,66,67,68,-1,-1,71,72,-1,-1,75,76,-1,-1,77,78,-1,-1,-1,-1,83,86,-1,-1,87,-1,-1,-1,-1,94,95,-1,-1,96,97,98
        }, ball);

    }


}
