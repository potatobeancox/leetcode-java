package com.potato.study.leetcodecn.p02209.t001;

/**
 * 2209. 用地毯覆盖后的最少白色砖块
 *
 * 给你一个下标从 0 开始的 二进制 字符串 floor ，它表示地板上砖块的颜色。

 floor[i] = '0' 表示地板上第 i 块砖块的颜色是 黑色 。
 floor[i] = '1' 表示地板上第 i 块砖块的颜色是 白色 。
 同时给你 numCarpets 和 carpetLen 。你有 numCarpets 条 黑色 的地毯，每一条 黑色 的地毯长度都为 carpetLen 块砖块。请你使用这些地毯去覆盖砖块，使得未被覆盖的剩余 白色 砖块的数目 最小 。地毯相互之间可以覆盖。

 请你返回没被覆盖的白色砖块的 最少 数目。

  

 示例 1：



 输入：floor = "10110101", numCarpets = 2, carpetLen = 2
 输出：2
 解释：
 上图展示了剩余 2 块白色砖块的方案。
 没有其他方案可以使未被覆盖的白色砖块少于 2 块。
 示例 2：



 输入：floor = "11111", numCarpets = 2, carpetLen = 3
 输出：0
 解释：
 上图展示了所有白色砖块都被覆盖的一种方案。
 注意，地毯相互之间可以覆盖。
  

 提示：

 1 <= carpetLen <= floor.length <= 1000
 floor[i] 要么是 '0' ，要么是 '1' 。
 1 <= numCarpets <= 1000


 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/minimum-white-tiles-after-covering-with-carpets
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode-cn.com/problems/minimum-white-tiles-after-covering-with-carpets/solution/by-endlesscheng-pa3v/
     * @param floor
     * @param numCarpets
     * @param carpetLen
     * @return
     */
    public int minimumWhiteTiles(String floor, int numCarpets, int carpetLen) {
        // 覆盖1 使1 剩余最少
        int length = floor.length();
        // dp 表示 使用 i 个地毯 延伸到位置 j 包含，最少能漏出多少个白块
        int[][] dp = new int[numCarpets+1][length];
        // 最开始 设置没有地毯的时候的白块数量 floor[i] = '1' 表示地板上第 i 块砖块的颜色是 白色 。
        dp[0][0] = floor.charAt(0) == '1' ? 1: 0;
        for (int i = 1; i < length; i++) {
            dp[0][i] = dp[0][i-1];
            if (floor.charAt(i) == '1') {
                dp[0][i]++;
            }
        }
        // 从第i条毛毯开始 生成 dp 最小值
        for (int i = 1; i <= numCarpets; i++) {
            // carpetLen 之前 由第一个 毛毯覆盖 只要有一个毛毯覆盖就都是0
            for (int j = carpetLen; j < length; j++) {
                // 用毛毯覆盖或者不用覆盖 最小值
                dp[i][j] = dp[i][j-1] + (floor.charAt(j) == '1' ? 1 : 0);
                // 毛毯覆盖住了 所以没有了
                dp[i][j] = Math.min(dp[i][j], dp[i-1][j-carpetLen]);
            }
        }
        // 内部 从 carpetLen 开始往最后一个位置遍历 生成
        return dp[numCarpets][length - 1];
    }

}
