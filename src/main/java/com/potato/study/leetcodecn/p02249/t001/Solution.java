package com.potato.study.leetcodecn.p02249.t001;

import java.util.HashMap;
import java.util.Map;

/**
 * 2249. 统计圆内格点数目
 *
 * 给你一个二维整数数组 circles ，其中 circles[i] = [xi, yi, ri] 表示网格上圆心为 (xi, yi) 且半径为 ri 的第 i 个圆，返回出现在 至少一个 圆内的 格点数目 。
 *
 * 注意：
 *
 * 格点 是指整数坐标对应的点。
 * 圆周上的点 也被视为出现在圆内的点。
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：circles = [[2,2,1]]
 * 输出：5
 * 解释：
 * 给定的圆如上图所示。
 * 出现在圆内的格点为 (1, 2)、(2, 1)、(2, 2)、(2, 3) 和 (3, 2)，在图中用绿色标识。
 * 像 (1, 1) 和 (1, 3) 这样用红色标识的点，并未出现在圆内。
 * 因此，出现在至少一个圆内的格点数目是 5 。
 * 示例 2：
 *
 *
 *
 * 输入：circles = [[2,2,2],[3,4,1]]
 * 输出：16
 * 解释：
 * 给定的圆如上图所示。
 * 共有 16 个格点出现在至少一个圆内。
 * 其中部分点的坐标是 (0, 2)、(2, 0)、(2, 4)、(3, 2) 和 (4, 4) 。
 *  
 *
 * 提示：
 *
 * 1 <= circles.length <= 200
 * circles[i].length == 3
 * 1 <= xi, yi <= 100
 * 1 <= ri <= min(xi, yi)
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-lattice-points-inside-a-circle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 2249
    public int countLatticePoints(int[][] circles) {
        // 遍历 每个 圆圈 找到左上角和 右下角 找到做成中 左上角的最小值和 右下角的最大值
        int leftMin = Integer.MAX_VALUE;
        int upMin = Integer.MAX_VALUE;
        int rightMax = Integer.MIN_VALUE;
        int downMax = Integer.MIN_VALUE;
        for (int[] circle : circles) {
            int radius = circle[2];
            int x = circle[0];
            int y = circle[1];

            upMin = Math.min(upMin, x - radius);
            leftMin = Math.min(leftMin, y - radius);

            downMax = Math.max(downMax, x + radius);
            rightMax = Math.max(rightMax, y + radius);
        }
        // 对 上述区域里边的每个点 半径距离 是否小于 等于半径
        int count = 0;
        for (int i = upMin; i <= downMax; i++) {
            for (int j = leftMin; j <= rightMax; j++) {
                // 判断距离
                boolean isValid = false;
                for (int[] circle : circles) {
                    int radius = circle[2];
                    int x = circle[0];
                    int y = circle[1];
                    if ((x-i)*(x-i) + (y-j)*(y-j) <= radius * radius) {
                        isValid = true;
                        break;
                    }
                }
                if (isValid) {
                    count++;
                }
            }
        }
        return count;
    }
}
