package com.potato.study.leetcodecn.p00850.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.*;

/**
 * 850. 矩形面积 II
 *
 * 给你一个轴对齐的二维数组 rectangles 。 对于 rectangle[i] = [x1, y1, x2, y2]，其中（x1，y1）是矩形 i 左下角的坐标， (xi1, yi1) 是该矩形 左下角 的坐标， (xi2, yi2) 是该矩形 右上角 的坐标。
 *
 * 计算平面中所有 rectangles 所覆盖的 总面积 。任何被两个或多个矩形覆盖的区域应只计算 一次 。
 *
 * 返回 总面积 。因为答案可能太大，返回 109 + 7 的 模 。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：rectangles = [[0,0,2,2],[1,0,2,3],[1,0,3,1]]
 * 输出：6
 * 解释：如图所示，三个矩形覆盖了总面积为 6 的区域。
 * 从(1,1)到(2,2)，绿色矩形和红色矩形重叠。
 * 从(1,0)到(2,3)，三个矩形都重叠。
 * 示例 2：
 *
 * 输入：rectangles = [[0,0,1000000000,1000000000]]
 * 输出：49
 * 解释：答案是 1018 对 (109 + 7) 取模的结果， 即 49 。
 *  
 *
 * 提示：
 *
 * 1 <= rectangles.length <= 200
 * rectanges[i].length = 4
 * 0 <= xi1, yi1, xi2, yi2 <= 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/rectangle-area-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {





    public int rectangleArea(int[][] rectangles) {
        // 遍历 rectangles 将轴坐标保存起来 按照从小到大
        List<Integer> lineIndexList = new ArrayList<>();
        for (int[] rec : rectangles) {
            int x1 = rec[0];
            int x2 = rec[2];

            lineIndexList.add(x1);
            lineIndexList.add(x2);
        }
        // 按照生序对 list 排序 维护之前的区间
        Collections.sort(lineIndexList);
        // 记录总面积
        long area = 0;
        int mod = 1_000_000_000 + 7;
        for (int i = 1; i < lineIndexList.size(); i++) {
            // 从index1开始，找到当前 x的上下界 内部遍历 rectangles 找到 x坐标 能够包含上下界限 将 y轴记录在list中
            int startX = lineIndexList.get(i-1);
            int endX = lineIndexList.get(i);

            if (startX == endX) {
                continue;
            }

            List<int[]> yList = new ArrayList<>();

            for (int[] rec : rectangles) {
                int x1 = rec[0];
                int x2 = rec[2];
                // 判定在不在里边
                if (x1 > startX || endX > x2) {
                    continue;
                }
                int y1 = rec[1];
                int y2 = rec[3];
                yList.add(new int[] {
                        y1, y2
                });
            }
            // 对 y进行排序 先按照小的 再按大的
            Collections.sort(yList, Comparator.comparingInt((int[] y) -> y[0]).thenComparingInt(y -> y[1]));
            long up = -1;
            long down = -1;

            long len = endX - startX;
            for (int[] element : yList) {
                int currentUp = element[0];
                int currentDown = element[1];
                // 当前 块跟之前有间距 需要结算下之前的
                if (currentUp > down) {
                    area += (down - up) * len;
                    // 遍历 yList 计算总的面积
                    area %= mod;
                    down = currentDown;
                    up = currentUp;
                } else {
                    down = Math.max(down, currentDown);
                }
            }
            area += (down - up) * len;
            // 遍历 yList 计算总的面积
            area %= mod;
        }
        return (int) area;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "[[0,0,1000000000,1000000000]]";
        int[][] rectangles = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        int i = solution.rectangleArea(rectangles);
        System.out.println(i);
        Assert.assertEquals(49, i);
    }

}
