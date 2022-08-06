package com.potato.study.leetcodecn.p00573.t001;


import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

import com.sun.xml.internal.xsom.XSUnionSimpleType;

/**
 * 573. 松鼠模拟
 *
 * 现在有一棵树，一只松鼠和一些坚果。位置由二维网格的单元格表示。你的目标是找到松鼠收集所有坚果的最小路程，且坚果是一颗接一颗地被放在树下。
 * 松鼠一次最多只能携带一颗坚果，松鼠可以向上，向下，向左和向右四个方向移动到相邻的单元格。移动次数表示路程。
 *
 * 输入 1:
 *
 * 输入:
 * 高度 : 5
 * 宽度 : 7
 * 树的位置 : [2,2]
 * 松鼠 : [4,4]
 * 坚果 : [[3,0], [2,5]]
 * 输出: 12
 * 解释:
 * ​​​​​
 * 注意:
 *
 * 所有给定的位置不会重叠。
 * 松鼠一次最多只能携带一颗坚果。
 * 给定的坚果位置没有顺序。
 * 高度和宽度是正整数。 3 <= 高度 * 宽度 <= 10,000。
 * 给定的网格至少包含一颗坚果，唯一的一棵树和一只松鼠。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/squirrel-simulation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int minDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
        // 先边遍历 nuts 获取往返位置 计算总的sum
        int sum = 0;
        for (int[] nut : nuts) {
            sum += getDistance(nut, tree) * 2;
        }
        // 再次遍历 nuts 此时 减去 nut 到tree 距离 + 松鼠 到nut距离 求最小值
        int min = Integer.MAX_VALUE;
        for (int[] nut : nuts) {
            min = Math.min(min, sum - getDistance(tree, nut) + getDistance(squirrel, nut));
        }
        return min;
    }

    private int getDistance(int[] from, int[] to) {
        return Math.abs(from[0] - to[0]) + Math.abs(from[1] - to[1]);
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int height = 1;
        int width = 3;
        int[] tree = new int[] {
                0,1
        };
        int[] squirrel = new int[] {
                0,0
        };
        int[][] nuts = new int[][] {
                {0,2}
        };
        int distance = solution.minDistance(height, width, tree, squirrel, nuts);
        System.out.println(distance);
        Assert.assertEquals(3, distance);
    }
}
