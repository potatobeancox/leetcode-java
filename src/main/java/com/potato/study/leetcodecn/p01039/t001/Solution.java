package com.potato.study.leetcodecn.p01039.t001;

import com.potato.study.leetcode.domain.TreeNode;

import java.util.Arrays;

/**
 * 1039. 多边形三角剖分的最低得分
 *
 * 给定 N，想象一个凸 N 边多边形，其顶点按顺时针顺序依次标记为 A[0], A[i], ..., A[N-1]。

 假设您将多边形剖分为 N-2 个三角形。对于每个三角形，该三角形的值是顶点标记的乘积，三角剖分的分数是进行三角剖分后所有 N-2 个三角形的值之和。

 返回多边形进行三角剖分后可以得到的最低分。
  

 示例 1：

 输入：[1,2,3]
 输出：6
 解释：多边形已经三角化，唯一三角形的分数为 6。
 示例 2：



 输入：[3,7,4,5]
 输出：144
 解释：有两种三角剖分，可能得分分别为：3*7*5 + 4*5*7 = 245，或 3*4*5 + 3*4*7 = 144。最低分数为 144。
 示例 3：

 输入：[1,3,1,4,1,5]
 输出：13
 解释：最低分数三角剖分的得分情况为 1*1*3 + 1*1*4 + 1*1*5 + 1*1*1 = 13。
  

 提示：

 3 <= A.length <= 50
 1 <= A[i] <= 100


 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/minimum-score-triangulation-of-polygon
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode-cn.com/problems/minimum-score-triangulation-of-polygon/solution/1039-duo-bian-xing-san-jiao-pou-fen-de-z-xvq2/
     * @param values
     * @return
     */
    public int minScoreTriangulation(int[] values) {
        // dp ij 从 i到j 最小划分划分 dp ij 等于 min dp ik + dp kj  val i* j * k k在ij中间
        int n = values.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 2; j < n; j++) {
                if (j == i + 2) {
                    dp[i][j] = values[i] * values[j-1] * values[j];
                    continue;
                }
                for (int k = i + 1; k < j; k++) {


                    if (k >= i+2 && j >= k+2) {
                        dp[i][j] = Math.min(dp[i][j],
                                dp[i][k] + dp[k][j] + values[i] * values[k] * values[j]);
                    } else if (j >= k+2) {
                        dp[i][j] = Math.min(dp[i][j],
                                dp[k][j] + values[i] * values[k] * values[j]);
                    } else if (k >= i+2) {
                        dp[i][j] = Math.min(dp[i][j],
                                dp[i][k] + values[i] * values[k] * values[j]);
                    } else {
                        dp[i][j] = values[i] * values[k] * values[j];
                    }

                }
            }
        }
        return dp[0][n-1];
    }


}
