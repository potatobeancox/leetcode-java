package com.potato.study.leetcodecn.p01621.t001;

/**
 * 1621. 大小为 K 的不重叠线段的数目
 *
 * 给你一维空间的 n 个点，其中第 i 个点（编号从 0 到 n-1）位于 x =
 * i 处，请你找到 恰好 k 个不重叠 线段且每个线段至少覆盖两个点的方案数。线段的两个端点必须都是 整数坐标 。这 k 个线段不需要全部覆盖全部 n 个点，且它们的端点 可以 重合。
 *
 * 请你返回 k 个不重叠线段的方案数。由于答案可能很大，请将结果对 109 + 7 取余 后返回。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：n = 4, k = 2
 * 输出：5
 * 解释：
 * 如图所示，两个线段分别用红色和蓝色标出。
 * 上图展示了 5 种不同的方案 {(0,2),(2,3)}，{(0,1),(1,3)}，{(0,1),(2,3)}，{(1,2),(2,3)}，{(0,1),(1,2)} 。
 * 示例 2：
 *
 * 输入：n = 3, k = 1
 * 输出：3
 * 解释：总共有 3 种不同的方案 {(0,1)}, {(0,2)}, {(1,2)} 。
 * 示例 3：
 *
 * 输入：n = 30, k = 7
 * 输出：796297179
 * 解释：画 7 条线段的总方案数为 3796297200 种。将这个数对 109 + 7 取余得到 796297179 。
 * 示例 4：
 *
 * 输入：n = 5, k = 3
 * 输出：7
 * 示例 5：
 *
 * 输入：n = 3, k = 2
 * 输出：1
 *  
 *
 * 提示：
 *
 * 2 <= n <= 1000
 * 1 <= k <= n-1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-sets-of-k-non-overlapping-line-segments
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 1621
    public int numberOfSets(int n, int k) {
        // dp ij 从 0 - i 分成 j段的可能数 i（包含i位置的点）
        int[][] dp = new int[n][k+1];
        // 初始化 dp x 1 = 1;
        for (int i = 0; i < n; i++) {
            dp[i][1] = 1;
        }
        // dp ij = sum dp k (0-i) j-1
        for (int i = 0; i < n; i++) {
            // 控制分段数量
            for (int j = 1; j <= k; j++) {
                // 分段数 大于 字符数
                if (i + 1 < j) {
                    continue;
                }
                // 控制之前的分段数
                for (int l = 0; l < i; l++) {
                    dp[i][j] += dp[l][j-1];
                }
            }
        }
        return dp[n-1][k];
    }


}
