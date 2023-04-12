package com.potato.study.leetcodecn.other.Interview.p0016.p0013;


/**
 * 面试题 16.13. 平分正方形
 *
 * 给定两个正方形及一个二维平面。请找出将这两个正方形分割成两半的一条直线。假设正方形顶边和底边与 x 轴平行。
 *
 * 每个正方形的数据square包含3个数值，正方形的左下顶点坐标[X,Y] = [square[0],square[1]]，以及正方形的边长square[2]。所求直线穿过两个正方形会形成4个交点，请返回4个交点形成线段的两端点坐标（两个端点即为4个交点中距离最远的2个点，这2个点所连成的线段一定会穿过另外2个交点）。2个端点坐标[X1,Y1]和[X2,Y2]的返回格式为{X1,Y1,X2,Y2}，要求若X1 != X2，需保证X1 < X2，否则需保证Y1 <= Y2。
 *
 * 若同时有多条直线满足要求，则选择斜率最大的一条计算并返回（与Y轴平行的直线视为斜率无穷大）。
 *
 * 示例：
 *
 * 输入：
 * square1 = {-1, -1, 2}
 * square2 = {0, -1, 2}
 * 输出： {-1,0,2,0}
 * 解释： 直线 y = 0 能将两个正方形同时分为等面积的两部分，返回的两线段端点为[-1,0]和[2,0]
 * 提示：
 *
 * square.length == 3
 * square[2] > 0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/bisect-squares-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 16.13
    public double[] cutSquares(int[] square1, int[] square2) {
        // square1 左下点的坐标和正方形边长 找到中间点
        int x1 = square1[0];
        int y1 = square1[1];
        int sideLen1 = square1[2];

        int x2 = square2[0];
        int y2 = square2[1];
        int sideLen2 = square2[2];
        // 中心点
        double cx1 = x1 + sideLen1 / 2.0;
        double cy1 = y1 - sideLen1 / 2.0;

        double cx2 = x2 + sideLen2 / 2.0;
        double cy2 = y2 - sideLen2 / 2.0;
        // 判断是不是 垂直于 x轴的 也就是 x坐标一致
        if (Math.abs(cx1 - cx2) < 10e-9) {
            return new double[] {
            };
        }

        // 不一致计算下方程 y = kx + b

        // 根据 k 与1的关系 确定两个点
        return null;
    }
}
