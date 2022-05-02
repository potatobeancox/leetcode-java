package com.potato.study.leetcodecn.p01401.t001;

/**
 * 1401. 圆和矩形是否有重叠
 *
 * 给你一个以 (radius, x_center, y_center) 表示的圆和一个与坐标轴平行的矩形 (x1, y1, x2, y2)，其中 (x1, y1) 是矩形左下角的坐标，(x2, y2) 是右上角的坐标。
 *
 * 如果圆和矩形有重叠的部分，请你返回 True ，否则返回 False 。
 *
 * 换句话说，请你检测是否 存在 点 (xi, yi) ，它既在圆上也在矩形上（两者都包括点落在边界上的情况）。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：radius = 1, x_center = 0, y_center = 0, x1 = 1, y1 = -1, x2 = 3, y2 = 1
 * 输出：true
 * 解释：圆和矩形有公共点 (1,0)
 * 示例 2：
 *
 *
 *
 * 输入：radius = 1, x_center = 0, y_center = 0, x1 = -1, y1 = 0, x2 = 0, y2 = 1
 * 输出：true
 * 示例 3：
 *
 *
 *
 * 输入：radius = 1, x_center = 1, y_center = 1, x1 = -3, y1 = -3, x2 = 3, y2 = 3
 * 输出：true
 * 示例 4：
 *
 * 输入：radius = 1, x_center = 1, y_center = 1, x1 = 1, y1 = -3, x2 = 2, y2 = -1
 * 输出：false
 *  
 *
 * 提示：
 *
 * 1 <= radius <= 2000
 * -10^4 <= x_center, y_center, x1, y1, x2, y2 <= 10^4
 * x1 < x2
 * y1 < y2
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/circle-and-rectangle-overlapping
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        // 如果 圆心 x 在两个 x1 和 x2 之前 那么 dx = 0 ； 否则找 最近的那个作为 -x 作为 dx
        int dx;
        if ((x1 <= xCenter && xCenter <= x2) || (x2 <= xCenter && xCenter <= x1)) {
            dx = 0;
        } else {
            dx = Math.min(Math.abs(x1 - xCenter), Math.abs(x2 - xCenter));
        }
        // 同理 y 也是 求 dy
        int dy;
        if ((y1 <= yCenter && yCenter <= y2) || (y2 <= yCenter && yCenter <= y1)) {
            dy = 0;
        } else {
            dy = Math.min(Math.abs(y1 - yCenter), Math.abs(y2 - yCenter));
        }
        // dx * dx + dy * dy 《= radius * radius
        return dx * dx + dy * dy <= radius * radius;
    }
}
