package com.potato.study.leetcodecn.p00469.t001;


import org.junit.Assert;

import java.util.List;

/**
 * 469. 凸多边形
 *
 * 给定 X-Y 平面上的一组点 points ，其中 points[i] = [xi, yi] 。这些点按顺序连成一个多边形。

 如果该多边形为 凸 多边形（凸多边形的定义）则返回 true ，否则返回 false 。

 你可以假设由给定点构成的多边形总是一个 简单的多边形（简单多边形的定义）。换句话说，我们要保证每个顶点处恰好是两条边的汇合点，并且这些边 互不相交 。

  

 示例 1：



 输入: points = [[0,0],[0,5],[5,5],[5,0]]
 输出: true
 示例 2：



 输入: points = [[0,0],[0,10],[10,10],[10,0],[5,5]]
 输出: false
  

 提示:

 3 <= points.length <= 104
 points[i].length == 2
 -104 <= xi, yi <= 104
 所有点都 不同


 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/convex-polygon
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public boolean isConvex(List<List<Integer>> points) {
        // 依次求向量积 判断 符号是否发生变化
        if (points == null || points.size() < 3) {
            return false;
        }
        // 第一个符号
        int temp = 0;
        for (int i = 0; i < points.size(); i++) {

            int x1 = points.get((i+1)%points.size()).get(0) - points.get(i).get(0);
            int y1 = points.get((i+1)%points.size()).get(1) - points.get(i).get(1);

            int x2 = points.get((i+2)%points.size()).get(0) - points.get(i).get(0);
            int y2 = points.get((i+2)%points.size()).get(1) - points.get(i).get(1);

            int status = x1 * y2 - x2 * y1;
            if (temp == 0) {
                temp = status;
                continue;
            }

            if ((temp < 0 && status > 0) || (temp > 0 && status < 0)) {
                return false;
            }
        }
        return true;
    }
}
