package com.potato.study.leetcodecn.p02189.t001;

/**
 * 2189. 建造纸牌屋的方法数
 *
 * 给你一个整数 n，代表你拥有牌的数量。一个 纸牌屋 满足以下条件:

 一个 纸牌屋 由一行或多行 三角形 和水平纸牌组成。
 三角形 是由两张卡片相互靠在一起形成的。
 一张卡片必须水平放置在一行中 所有相邻 的三角形之间。
 比第一行高的任何三角形都必须放在前一行的水平牌上。
 每个三角形都被放置在行中 最左边 的可用位置。
 返回使用所有 n 张卡片可以构建的不同纸牌屋的数量。如果存在一行两个纸牌屋包含不同数量的纸牌，那么两个纸牌屋被认为是不同的。

  

 示例 1:


 输入: n = 16
 输出: 2
 解释: 有两种有效的纸牌屋摆法。
 图中的第三个纸牌屋无效，因为第一行最右边的三角形没有放在水平纸牌的顶部。
 Example 2:


 输入: n = 2
 输出: 1
 解释: 这是唯一可行的纸牌屋。
 Example 3:


 输入: n = 4
 输出: 0
 解释: 图中的三种纸牌都是无效的。
 第一个纸牌屋需要在两个三角形之间放置一张水平纸牌。
 第二个纸牌屋使用 5 张纸牌。
 第三个纸牌屋使用 2 张纸牌。
  

 提示:

 1 <= n <= 500

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/number-of-ways-to-build-house-of-cards
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode.cn/problems/number-of-ways-to-build-house-of-cards/solution/by-xupy-e4qe/
     * @param n
     * @return
     */
    public int houseOfCards(int n) {
        // dp i 使用i个专最多能有多少种组成
        int[] dp = new int[n+1];
        // 一个都不用只有一种方式
        dp[0] = 1;
        // 枚举 每种消耗情况 从 2 到 2+ 3*k
        for (int i = 2; i <= n; i+=3) {
            // 相当于这个东西作为底座 有多少种可能  从 n个 到 剩余 num个 ，看看有多少种可能
            for (int j = n; j >= i; j--) {
                // i 留一层
                dp[j] += dp[j-i];
            }
        }
        return dp[n];
    }

}
