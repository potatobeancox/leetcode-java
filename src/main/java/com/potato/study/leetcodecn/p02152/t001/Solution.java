package com.potato.study.leetcodecn.p02152.t001;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 2152. 穿过所有点的所需最少直线数量
 *
 * 给定一个 points 数组，points[i] = [xi, yi] 表示直角坐标系 X-Y 的一个点。

 现在考虑向 X-Y 坐标系中添加 直线，使得每个点 至少 在一条直线上。

 返回能够穿过所有点的所需 最少直线 数量。

  

 示例 1:


 输入: points = [[0,1],[2,3],[4,5],[4,3]]
 输出: 2
 解释: 所需最少直线数量为 2 ，一种可能的答案是添加:
 - 一条穿过点 (0, 1) 和 点(4, 5) 的直线
 - 另一条穿过点 (2, 3) 和点 (4, 3) 的直线
 示例 2:


 输入: points = [[0,2],[-2,-2],[1,4]]
 输出: 1
 解释: 所需最少直线数量为 1 ，唯一的答案是:
 - 一条穿过点 (-2, -2) 和点 (1, 4) 的直线
  

 提示:

 1 <= points.length <= 10
 points[i].length == 2
 -100 <= xi, yi <= 100
 points 中元素都是唯一的

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/minimum-number-of-lines-to-cover-points
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int minimumLines(int[][] points) {
        // 先判断 当前有多少个点 1 or 2 直接返回1条直线
        int size = points.length;
        if (size <= 2) {
            return 1;
        }
        // 枚举 任意2个点 分别找到第三个一条直线的点 有序
        int min = size;
        for (int i = 0; i < size; i++) {
            for (int j = i+1; j < size; j++) {
                List<int[]> pointList = new ArrayList<>();
                // 遍历其他点，找到 第三个点 不跟这两个共线的 点 对于 找到之后 判断 数量
                for (int k = 0; k < size; k++) {
                    if (i == k || j == k) {
                        continue;
                    }
                    boolean sameLine = isSameLine(points[i], points[j], points[k]);
                    if (sameLine) {
                        continue;
                    }
                    pointList.add(points[k]);
                }
                // 没有 不共线 那就直接返回
                if (pointList.size() == 0) {
                    return 1;
                }
                if (pointList.size() == 1) {
                    // 只有一个 说明 只有 2条先
                    min = Math.min(2, min);
                } else {
                    // 递归找不共线的 就按先一共多少
                    int[][] ints = pointList.toArray(new int[0][]);
                    int otherLine = minimumLines(ints);
                    min = Math.min(otherLine + 1, min);
                }
            }
        }
        return min;
    }


    private boolean isSameLine(int[] p1, int[] p2, int[] p3) {
        return (p1[0] - p3[0]) * (p1[1] - p2[1]) == (p1[1] - p3[1]) * (p1[0] - p2[0]);
    }
}
