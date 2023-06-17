package com.potato.study.leetcodecn.p01691.t001;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 1691. 堆叠长方体的最大高度
 *
 * 给你 n 个长方体 cuboids ，其中第 i 个长方体的长宽高表示为 cuboids[i] = [widthi, lengthi, heighti]（下标从 0 开始）。请你从 cuboids 选出一个 子集 ，并将它们堆叠起来。

 如果 widthi <= widthj 且 lengthi <= lengthj 且 heighti <= heightj ，你就可以将长方体 i 堆叠在长方体 j 上。你可以通过旋转把长方体的长宽高重新排列，以将它放在另一个长方体上。

 返回 堆叠长方体 cuboids 可以得到的 最大高度 。

  

 示例 1：



 输入：cuboids = [[50,45,20],[95,37,53],[45,23,12]]
 输出：190
 解释：
 第 1 个长方体放在底部，53x37 的一面朝下，高度为 95 。
 第 0 个长方体放在中间，45x20 的一面朝下，高度为 50 。
 第 2 个长方体放在上面，23x12 的一面朝下，高度为 45 。
 总高度是 95 + 50 + 45 = 190 。
 示例 2：

 输入：cuboids = [[38,25,45],[76,35,3]]
 输出：76
 解释：
 无法将任何长方体放在另一个上面。
 选择第 1 个长方体然后旋转它，使 35x3 的一面朝下，其高度为 76 。
 示例 3：

 输入：cuboids = [[7,11,17],[7,17,11],[11,7,17],[11,17,7],[17,7,11],[17,11,7]]
 输出：102
 解释：
 重新排列长方体后，可以看到所有长方体的尺寸都相同。
 你可以把 11x7 的一面朝下，这样它们的高度就是 17 。
 堆叠长方体的最大高度为 6 * 17 = 102 。
  

 提示：

 n == cuboids.length
 1 <= n <= 100
 1 <= widthi, lengthi, heighti <= 100

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/maximum-height-by-stacking-cuboids
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     *
     * @param cuboids
     * @return
     */
    public int maxHeight(int[][] cuboids) {
        // 遍历 cuboids 对其中的每个数组进行升序排序
        for (int i = 0; i < cuboids.length; i++) {
            Arrays.sort(cuboids[i]);
        }
        // 再对 cuboids 进行升序排序
        Arrays.sort(cuboids, Comparator.comparingInt((int[] cuboid) -> cuboid[0])
                .thenComparingInt(cuboid -> cuboid[1])
                .thenComparingInt(cuboid -> cuboid[2]));
        // dp i 以为最下面的最大高度 遍历每个位置 dp i等于 内部遍历 j 如果满足 j + 当前高度
        int[] dp = new int[cuboids.length];
        int max = 0;
        for (int i = 0; i < cuboids.length; i++) {
            // 当前要作为底座的盒子 只用一个盒子的高度
            dp[i] = cuboids[i][2];
            // 每个位置都 更新max 高度 可能从小于 i的j过来
            for (int j = 0; j < i; j++) {
                // 第二维度和第三维度是不是合适
                if (cuboids[j][1] > cuboids[i][1]
                        || cuboids[j][2] > cuboids[i][2]) {
                    continue;
                }
                // 合适
                dp[i] = Math.max(dp[i], dp[j] + cuboids[i][2]);
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

}
