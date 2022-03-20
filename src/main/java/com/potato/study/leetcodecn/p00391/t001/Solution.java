package com.potato.study.leetcodecn.p00391.t001;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * 391. 完美矩形
 *
 * 给你一个数组 rectangles ，其中 rectangles[i] = [xi, yi, ai, bi] 表示一个坐标轴平行的矩形。这个矩形的左下顶点是 (xi, yi) ，右上顶点是 (ai, bi) 。

 如果所有矩形一起精确覆盖了某个矩形区域，则返回 true ；否则，返回 false 。

  
 示例 1：


 输入：rectangles = [[1,1,3,3],[3,1,4,2],[3,2,4,4],[1,3,2,4],[2,3,3,4]]
 输出：true
 解释：5 个矩形一起可以精确地覆盖一个矩形区域。
 示例 2：


 输入：rectangles = [[1,1,2,3],[1,3,2,4],[3,1,4,2],[3,2,4,4]]
 输出：false
 解释：两个矩形之间有间隔，无法覆盖成一个矩形。
 示例 3：


 输入：rectangles = [[1,1,3,3],[3,1,4,2],[1,3,2,4],[2,2,4,4]]
 输出：false
 解释：因为中间有相交区域，虽然形成了矩形，但不是精确覆盖。
  

 提示：

 1 <= rectangles.length <= 2 * 104
 rectangles[i].length == 4
 -105 <= xi, yi, ai, bi <= 105

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/perfect-rectangle
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 所有的矩形正好 可以 拼接成一个 大的矩形
     * @param rectangles
     * @return
     */
    public boolean isRectangleCover(int[][] rectangles) {
        // 是不是可以这样 找到 最左边的点 和最右边的点 计算下面积和 是否相等 方法有个问题 解决不了空档
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxA = Integer.MIN_VALUE;
        int maxB = Integer.MIN_VALUE;
        // 判断点出现的次数 端点只能出现 1次 其他的点只能出现2 次或者4次
        Map<String, Integer> countMap = new HashMap<>();
        // 注意次数要存4个点
        long totalArea = 0;
        for (int i = 0; i < rectangles.length; i++) {
            int x = rectangles[i][0];
            int y = rectangles[i][1];
            int a = rectangles[i][2];
            int b = rectangles[i][3];

            String key1 = x + "_" + y;
            countMap.put(key1, countMap.getOrDefault(key1, 0) + 1);


            String key2 = a + "_" + b;
            countMap.put(key2, countMap.getOrDefault(key2, 0) + 1);

            String key3 = x + "_" + b;
            countMap.put(key3, countMap.getOrDefault(key3, 0) + 1);

            String key4 = a + "_" + y;
            countMap.put(key4, countMap.getOrDefault(key4, 0) + 1);

            minX = Math.min(minX, x);
            minY = Math.min(minY, y);
            maxA = Math.max(maxA, a);
            maxB = Math.max(maxB, b);

            totalArea += ((long) a - x) * ((long) b - y);
        }
        // 判断次数 端点只能出现1次
        if (!countMap.containsKey(minX + "_" + minY)
                || !countMap.containsKey(maxA + "_" + maxB)
                || !countMap.containsKey(minX + "_" + maxB)
                || !countMap.containsKey(maxA + "_" + minY)
                || countMap.get(minX + "_" + minY) != 1
                || countMap.get(maxA + "_" + maxB) != 1
                || countMap.get(minX + "_" + maxB) != 1
                || countMap.get(maxA + "_" + minY) != 1) {
            return false;
        }
        countMap.remove(minX + "_" + minY);
        countMap.remove(maxA + "_" + maxB);
        countMap.remove(minX + "_" + maxB);
        countMap.remove(maxA + "_" + minY);
        // 其他的点 只能出现2次或者 4次
        for (int times : countMap.values()) {
            if (times != 2 && times != 4) {
                return false;
            }
        }

        // 最小的x 最小的 y  最大的a 最大的b 面积是否相等
        boolean isAreaSame = totalArea == ((long) maxA - minX) * ((long) maxB - minY);
        return isAreaSame;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        // [[1,1,3,3],[3,1,4,2],[3,2,4,4],[1,3,2,4],[2,3,3,4]]
        int[][] arr = new int[][] {
                {1,1,3,3},
                {3,1,4,2},
                {3,2,4,4},
                {1,3,2,4},
                {2,3,3,4}
        };
        boolean rectangleCover = solution.isRectangleCover(arr);
        System.out.println(rectangleCover);
        Assert.assertEquals(true, rectangleCover);
    }
}
