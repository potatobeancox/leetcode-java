package com.potato.study.leetcodecn.p01240.t001;


import org.junit.Assert;

/**
 * 1240. 铺瓷砖
 *
 * 你是一位施工队的工长，根据设计师的要求准备为一套设计风格独特的房子进行室内装修。

 房子的客厅大小为 n x m，为保持极简的风格，需要使用尽可能少的 正方形 瓷砖来铺盖地面。

 假设正方形瓷砖的规格不限，边长都是整数。

 请你帮设计师计算一下，最少需要用到多少块方形瓷砖？

  

 示例 1：



 输入：n = 2, m = 3
 输出：3
 解释：3 块地砖就可以铺满卧室。
 2 块 1x1 地砖
 1 块 2x2 地砖
 示例 2：



 输入：n = 5, m = 8
 输出：5
 示例 3：



 输入：n = 11, m = 13
 输出：6
  

 提示：

 1 <= n <= 13
 1 <= m <= 13

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/tiling-a-rectangle-with-the-fewest-squares
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    private int res;

    /**
     * https://leetcode.cn/problems/tiling-a-rectangle-with-the-fewest-squares/solution/java-dfsbao-li-di-gui-by-arglone-alau/
     * @param n
     * @param m
     * @return
     */
    public int tilingRectangle(int n, int m) {
        this.res = Integer.MAX_VALUE;
        boolean[][] used = new boolean[n][m];
        // dfs 用一个二维数组记录 被填充的情况
        dfs(used, 0);
        return this.res;
    }

    /**
     *
     * @param used
     * @param tileCount 当前用了多少个瓷砖
     */
    private void dfs(boolean[][] used, int tileCount) {
        if (tileCount >= res) {
            return;
        }
        // 终止条件 当前 全用过 没有漏的
        int[] startPosition = getStartPosition(used);
        if (startPosition[0] == -1 || startPosition[1] == -1) {
            res = Math.min(res, tileCount);
            return;
        }
        // 从大到小枚举 正方形边长
        int limit = Math.min(used.length - startPosition[0], used[0].length - startPosition[1]);
        for (int i = limit; i >= 1; i--) {
            // 检验并设置 true
            boolean hasPass = checkAndSetUse(used, i, startPosition, true);

            if (hasPass) {
                dfs(used, tileCount + 1);
                // 设置false
                checkAndSetUse(used, i, startPosition, false);
            }
        }

    }

    /**
     * 判断 从 startPosition开始能不能设置成status，不能返回false
     * @param used
     * @param limit
     * @param startPosition
     * @param status
     * @return
     */
    private boolean checkAndSetUse(boolean[][] used, int limit, int[] startPosition, boolean status) {
        for (int i = startPosition[0]; i < startPosition[0] + limit; i++) {
            for (int j = startPosition[1]; j < startPosition[1] + limit; j++) {
                if (status == used[i][j]) {
                    return false;
                }
            }
        }

        for (int i = startPosition[0]; i < startPosition[0] + limit; i++) {
            for (int j = startPosition[1]; j < startPosition[1] + limit; j++) {
                used[i][j] = status;
            }
        }
        return true;
    }


    private int[] getStartPosition(boolean[][] used) {
        for (int i = 0; i < used.length; i++) {
            for (int j = 0; j < used[0].length; j++) {
                if (!used[i][j]) {
                    return new int[] {i, j};
                }
            }
        }
        return new int[] {-1, -1};
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.tilingRectangle(11, 13);
        System.out.println(i);
        Assert.assertEquals(6, i);
    }
}
