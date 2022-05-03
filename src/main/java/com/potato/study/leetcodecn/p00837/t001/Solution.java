package com.potato.study.leetcodecn.p00837.t001;

import org.junit.Assert;

/**
 * 837. 新 21 点
 *
 * 爱丽丝参与一个大致基于纸牌游戏 “21点” 规则的游戏，描述如下：

 爱丽丝以 0 分开始，并在她的得分少于 k 分时抽取数字。 抽取时，她从 [1, maxPts] 的范围中随机获得一个整数作为分数进行累计，其中 maxPts 是一个整数。 每次抽取都是独立的，其结果具有相同的概率。

 当爱丽丝获得 k 分 或更多分 时，她就停止抽取数字。

 爱丽丝的分数不超过 n 的概率是多少？

 与实际答案误差不超过 10-5 的答案将被视为正确答案。

  
 示例 1：

 输入：n = 10, k = 1, maxPts = 10
 输出：1.00000
 解释：爱丽丝得到一张牌，然后停止。
 示例 2：

 输入：n = 6, k = 1, maxPts = 10
 输出：0.60000
 解释：爱丽丝得到一张牌，然后停止。 在 10 种可能性中的 6 种情况下，她的得分不超过 6 分。
 示例 3：

 输入：n = 21, k = 17, maxPts = 10
 输出：0.73278
  

 提示：

 0 <= k <= n <= 104
 1 <= maxPts <= 104

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/new-21-game
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode-cn.com/problems/new-21-game/solution/javani-xiang-dong-tai-gui-hua-jie-jue-shuang-100-b/
     * @param n
     * @param k
     * @param maxPts
     * @return
     */
    public double new21Game(int n, int k, int maxPts) {
        // 如果 k-1出发 即使走了 maxPts 依然到不了 n 返回 1
        if (k-1+maxPts <= n) {
            return 1;
        }
        // dp k+maxPts个 dp i 表示 从index i位置出发 能够赢得的概率 最开始 大于 n 的都是 0 大于等于 k 且小于 n 的都是 1
        double[] dp = new double[k+maxPts];
        for (int i = k; i <= n; i++) {
            if (i >= dp.length) {
                break;
            }
            dp[i] = 1;
        }
        // 从后往前遍历 生成dp k-1 开始 最终返回 dp0  dp i= sum（dp i+1， dpi+maxPts） ／ maxPts
        double tempSum = 0;
        for (int i = k; i < k + maxPts; i++) {
            tempSum += dp[i];
        }
        for (int i = k-1; i >= 0; i--) {
            dp[i] = tempSum / maxPts;
            tempSum += dp[i];
            tempSum -= dp[i + maxPts];
        }

        // 返回从0 开始 能获胜的概率
        return dp[0];
    }

}
