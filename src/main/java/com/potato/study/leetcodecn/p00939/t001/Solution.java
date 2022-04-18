package com.potato.study.leetcodecn.p00939.t001;

import com.potato.study.leetcode.domain.TreeNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 939. 最小面积矩形
 *
 * 给定在 xy 平面上的一组点，确定由这些点组成的矩形的最小面积，其中矩形的边平行于 x 轴和 y 轴。

 如果没有任何矩形，就返回 0。

  

 示例 1：

 输入：[[1,1],[1,3],[3,1],[3,3],[2,2]]
 输出：4
 示例 2：

 输入：[[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
 输出：2
  

 提示：

 1 <= points.length <= 500
 0 <= points[i][0] <= 40000
 0 <= points[i][1] <= 40000
 所有的点都是不同的。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/minimum-area-rectangle
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode-cn.com/problems/minimum-area-rectangle/solution/zui-xiao-mian-ji-ju-xing-by-leetcode/
     * @param points
     * @return
     */
    public int minAreaRect(int[][] points) {
        // 枚举对角线上的点
        Set<Long> set = new HashSet<>();
        for (int[] point : points) {
            // 0 <= points[i][0] <= 40000
            set.add(40001L * point[0] + point[1]);
        }
        // 找一下 剩余的点是否也在上面 在的话 求最小面积
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
            for (int j = i+1; j < points.length; j++) {
                if (points[i][0] == points[j][0] || points[i][1] == points[j][1]) {
                    continue;
                }
                // 找下
                if (!set.contains(40001L * points[i][0] + points[j][1])
                        || !set.contains(40001L * points[j][0] + points[i][1])) {
                    continue;
                }
                long area = Math.abs((long)points[j][0] - points[i][0])
                        * Math.abs((long)points[j][1] - points[i][1]);
                min = (int) Math.min(min, area);
            }
        }
        if (min == Integer.MAX_VALUE) {
            return 0;
        }
        return min;
    }


}
