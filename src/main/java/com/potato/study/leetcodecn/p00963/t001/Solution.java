package com.potato.study.leetcodecn.p00963.t001;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.potato.study.leetcode.domain.TreeNode;

/**
 * 963. 最小面积矩形 II
 *
 * 给定在 xy 平面上的一组点，确定由这些点组成的任何矩形的最小面积，其中矩形的边不一定平行于 x 轴和 y 轴。
 *
 * 如果没有任何矩形，就返回 0。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：[[1,2],[2,1],[1,0],[0,1]]
 * 输出：2.00000
 * 解释：最小面积的矩形出现在 [1,2],[2,1],[1,0],[0,1] 处，面积为 2。
 * 示例 2：
 *
 *
 *
 * 输入：[[0,1],[2,1],[1,1],[1,0],[2,0]]
 * 输出：1.00000
 * 解释：最小面积的矩形出现在 [1,0],[1,1],[2,1],[2,0] 处，面积为 1。
 * 示例 3：
 *
 *
 *
 * 输入：[[0,3],[1,2],[3,1],[1,3],[2,1]]
 * 输出：0
 * 解释：没法从这些点中组成任何矩形。
 * 示例 4：
 *
 *
 *
 * 输入：[[3,1],[1,1],[0,1],[2,1],[3,3],[3,2],[0,2],[2,3]]
 * 输出：2.00000
 * 解释：最小面积的矩形出现在 [2,1],[2,3],[3,3],[3,1] 处，面积为 2。
 *  
 *
 * 提示：
 *
 * 1 <= points.length <= 50
 * 0 <= points[i][0] <= 40000
 * 0 <= points[i][1] <= 40000
 * 所有的点都是不同的。
 * 与真实值误差不超过 10^-5 的答案将视为正确结果。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-area-rectangle-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {
    // 963
    public double minAreaFreeRect(int[][] points) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < points.length; i++) {
            set.add(points[i][0] + "_" + points[i][1]);
        }
        // 枚举 开始两个 点 之后
        int n = points.length;
        double minArea = Double.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int[] p1 = points[i];
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                int[] p2 = points[j];
                // 第三个顶点
                for (int k = j+1; k < n; k++) {
                    int[] p3 = points[k];
                    // 判断第四个点是够在 point 中
                    int[] p4 = new int[] {
                            p2[0] + p3[0] - p1[0],
                            p2[1] + p3[1] - p1[1]
                    };
                    if (!set.contains(p4[0] + "_" + p4[1])) {
                        continue;
                    }
                    // 判断是否是垂直的 p1 p2 和 p1 p3
                    int area = 0; // todo
                    // 是的话 计算 面积
                    minArea = Math.min(minArea, area);
                }
            }
        }
        return minArea;
    }


}
