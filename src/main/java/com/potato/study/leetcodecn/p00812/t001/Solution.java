package com.potato.study.leetcodecn.p00812.t001;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 812. 最大三角形面积
 *
 * 给定包含多个点的集合，从其中取三个点组成三角形，返回能组成的最大三角形的面积。

 示例:
 输入: points = [[0,0],[0,1],[1,0],[0,2],[2,0]]
 输出: 2
 解释:
 这五个点如下图所示。组成的橙色三角形是最大的，面积为2。


 注意:

 3 <= points.length <= 50.
 不存在重复的点。
  -50 <= points[i][j] <= 50.
 结果误差值在 10^-6 以内都认为是正确答案。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/largest-triangle-area
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 有个求面积公式
     * @param points
     * @return
     */
    public double largestTriangleArea(int[][] points) {
        int n = points.length;
        double max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j+1; k < n; k++) {
                    max = Math.max(max,
                            area(points[i], points[j], points[k]));
                }
            }
        }
        return max;
    }


    /**
     * 求面积
     * @param p1
     * @param p2
     * @param p3
     * @return
     */
    private double area(int[] p1, int[] p2, int[] p3) {
        return 0.5 * Math.abs(p1[0]*p2[1] + p2[0]*p3[1] + p3[0]*p1[1]
                - p1[1]*p2[0] - p2[1]*p3[0] - p3[1]*p1[0]);
    }
}
