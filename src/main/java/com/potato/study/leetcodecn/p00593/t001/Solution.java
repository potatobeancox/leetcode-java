package com.potato.study.leetcodecn.p00593.t001;


import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

/**
 * 593. 有效的正方形
 *
 * 给定二维空间中四点的坐标，返回四点是否可以构造一个正方形。
 *
 * 一个点的坐标（x，y）由一个有两个整数的整数数组表示。
 *
 * 示例:
 *
 * 输入: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
 * 输出: True
 *  
 *
 * 注意:
 *
 * 所有输入整数都在 [-10000，10000] 范围内。
 * 一个有效的正方形有四个等长的正长和四个等角（90度角）。
 * 输入点没有顺序。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-square
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {



    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        // 按照 x生序 y生序 排列
        int[][] p = new int[][] {
                p1, p2, p3, p4
        };
        Arrays.sort(p, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int compare = Integer.compare(o1[0], o2[0]);
                if (compare != 0) {
                    return compare;
                }
                return Integer.compare(o1[1], o2[1]);
            }
        });
        // 点依次为 p0, p1, p2, p3，那么我们可以发现正方形的四条边依次为 p0p1，p1p3，p3p2 和 p2p0，对角线为 p0p3 和 p1p2

        // 4个边相等 2个对角线相等
        int dis1 = getDistance(p[0], p[1]);
        int dis2 = getDistance(p[3], p[1]);
        int dis3 = getDistance(p[3], p[2]);
        int dis4 = getDistance(p[0], p[2]);
        // 对角线
        int dis5 = getDistance(p[3], p[0]);
        int dis6 = getDistance(p[1], p[2]);

        return dis1 != 0 && dis1 == dis2 && dis3 == dis4 && dis1 == dis3
                && dis5 == dis6;
    }

    private int getDistance(int[] p1, int[] p2) {
        return (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
    }

}
