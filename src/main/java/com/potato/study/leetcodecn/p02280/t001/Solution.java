package com.potato.study.leetcodecn.p02280.t001;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 2280. 表示一个折线图的最少线段数
 *
 * 给你一个二维整数数组 stockPrices ，其中 stockPrices[i] = [dayi,
 * pricei] 表示股票在 dayi 的价格为 pricei 。折线图 是一个二维平面上的若干个点组成的图，横坐标表示日期，纵坐标表示价格，折线图由相邻的点连接而成。比方说下图是一个例子：
 *
 *
 * 请你返回要表示一个折线图所需要的 最少线段数 。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：stockPrices = [[1,7],[2,6],[3,5],[4,4],[5,4],[6,3],[7,2],[8,1]]
 * 输出：3
 * 解释：
 * 上图为输入对应的图，横坐标表示日期，纵坐标表示价格。
 * 以下 3 个线段可以表示折线图：
 * - 线段 1 （红色）从 (1,7) 到 (4,4) ，经过 (1,7) ，(2,6) ，(3,5) 和 (4,4) 。
 * - 线段 2 （蓝色）从 (4,4) 到 (5,4) 。
 * - 线段 3 （绿色）从 (5,4) 到 (8,1) ，经过 (5,4) ，(6,3) ，(7,2) 和 (8,1) 。
 * 可以证明，无法用少于 3 条线段表示这个折线图。
 * 示例 2：
 *
 *
 *
 * 输入：stockPrices = [[3,4],[1,2],[7,8],[2,3]]
 * 输出：1
 * 解释：
 * 如上图所示，折线图可以用一条线段表示。
 *  
 *
 * 提示：
 *
 * 1 <= stockPrices.length <= 105
 * stockPrices[i].length == 2
 * 1 <= dayi, pricei <= 109
 * 所有 dayi 互不相同 。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-lines-to-represent-a-line-chart
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     *
     * @param stockPrices
     * @return
     */
    public int minimumLines(int[][] stockPrices) {
        Arrays.sort(stockPrices, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });
        // 从第一个点开始 往后遍历 计算斜率 如果 跟之前不一样就多一条线
        int lineCount = 0;
        String lastSlope = null;
        for (int i = 1; i < stockPrices.length; i++) {
            int y = stockPrices[i][1] - stockPrices[i-1][1];
            int x = stockPrices[i][0] - stockPrices[i-1][0];
            int gcd = gcd(y, x);

            String key = (y/gcd) + "_" + (x/gcd);

            if (i == 1) {
                lineCount++;
                lastSlope = key;
                continue;
            }
            if (key.equals(lastSlope)) {
                continue;
            }
            lineCount++;
            lastSlope = key;
        }

        return lineCount;
    }


    private int gcd(int a, int b) {
        if (a % b == 0) {
            return b;
        }
        return gcd(b, a%b);
    }
}
