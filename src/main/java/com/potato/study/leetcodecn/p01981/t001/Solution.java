package com.potato.study.leetcodecn.p01981.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.HashSet;
import java.util.Set;

/**
 * 1981. 最小化目标值与所选元素的差
 *
 * 你一个大小为 m x n 的整数矩阵 mat 和一个整数 target 。

 从矩阵的 每一行 中选择一个整数，你的目标是 最小化 所有选中元素之 和 与目标值 target 的 绝对差 。

 返回 最小的绝对差 。

 a 和 b 两数字的 绝对差 是 a - b 的绝对值。

  

 示例 1：



 输入：mat = [[1,2,3],[4,5,6],[7,8,9]], target = 13
 输出：0
 解释：一种可能的最优选择方案是：
 - 第一行选出 1
 - 第二行选出 5
 - 第三行选出 7
 所选元素的和是 13 ，等于目标值，所以绝对差是 0 。
 示例 2：



 输入：mat = [[1],[2],[3]], target = 100
 输出：94
 解释：唯一一种选择方案是：
 - 第一行选出 1
 - 第二行选出 2
 - 第三行选出 3
 所选元素的和是 6 ，绝对差是 94 。
 示例 3：



 输入：mat = [[1,2,9,8,7]], target = 6
 输出：1
 解释：最优的选择方案是选出第一行的 7 。
 绝对差是 1 。
  

 提示：

 m == mat.length
 n == mat[i].length
 1 <= m, n <= 70
 1 <= mat[i][j] <= 70
 1 <= target <= 800

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/minimize-the-difference-between-target-and-chosen-elements
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int minimizeTheDifference(int[][] mat, int target) {
        // dp ij 前i行能 是否能凑出和为j的元素
        int line = mat.length;
        boolean[][] dp = new boolean[line][4901];
        // 第一行 不选是0
        for (int i = 0; i < mat[0].length; i++) {
            dp[0][mat[0][i]] = true;
        }
        // 对于 枚举每个元素 看看 之前 4900 里那个是 true 相对的 设置成 true
        for (int i = 0; i < line - 1; i++) {
            for (int j = 0; j < 4901; j++) {
                // i 行去不到j
                if (!dp[i][j]) {
                    continue;
                }
                // 能渠道 看看这行 选
                for (int next : mat[i+1]) {
                    dp[i+1][next + j] = true;
                }
            }
        }
        // 遍历 dp len -1
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 4901; i++) {
            if (!dp[line - 1][i]) {
                continue;
            }
            min = Math.min(min, Math.abs(i - target));
        }
        if (min == Integer.MAX_VALUE) {
            return -1;
        }
        return min;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] mat = LeetcodeInputUtils.inputString2IntArrayTwoDimensional("[[1,2,9,8,7]]");
        int target = 6;
        int i = solution.minimizeTheDifference(mat, target);
        System.out.println(i);
        Assert.assertEquals(1, i);



        mat = LeetcodeInputUtils.inputString2IntArrayTwoDimensional("[[10,3,7,7,9,6,9,8,9,5],[1,1,6,8,6,7,7,9,3,9],[3,4,4,1,3,6,3,3,9,9],[6,9,9,3,8,7,9,6,10,6]]");
        target = 5;
        i = solution.minimizeTheDifference(mat, target);
        System.out.println(i);
        Assert.assertEquals(3, i);
    }


}
