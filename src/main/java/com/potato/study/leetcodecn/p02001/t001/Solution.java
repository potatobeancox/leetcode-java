package com.potato.study.leetcodecn.p02001.t001;

import java.util.HashMap;
import java.util.Map;


/**
 * 2001. 可互换矩形的组数
 *
 * 用一个下标从 0 开始的二维整数数组 rectangles 来表示 n 个矩形，其中 rectangles[i] = [widthi, heighti] 表示第 i 个矩形的宽度和高度。
 *
 * 如果两个矩形 i 和 j（i < j）的宽高比相同，则认为这两个矩形 可互换 。更规范的说法是，两个矩形满足 widthi/heighti == widthj/heightj（使用实数除法而非整数除法），则认为这两个矩形 可互换 。
 *
 * 计算并返回 rectangles 中有多少对 可互换 矩形。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：rectangles = [[4,8],[3,6],[10,20],[15,30]]
 * 输出：6
 * 解释：下面按下标（从 0 开始）列出可互换矩形的配对情况：
 * - 矩形 0 和矩形 1 ：4/8 == 3/6
 * - 矩形 0 和矩形 2 ：4/8 == 10/20
 * - 矩形 0 和矩形 3 ：4/8 == 15/30
 * - 矩形 1 和矩形 2 ：3/6 == 10/20
 * - 矩形 1 和矩形 3 ：3/6 == 15/30
 * - 矩形 2 和矩形 3 ：10/20 == 15/30
 * 示例 2：
 *
 * 输入：rectangles = [[4,5],[7,8]]
 * 输出：0
 * 解释：不存在成对的可互换矩形。
 *  
 *
 * 提示：
 *
 * n == rectangles.length
 * 1 <= n <= 105
 * rectangles[i].length == 2
 * 1 <= widthi, heighti <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-pairs-of-interchangeable-rectangles
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public long interchangeableRectangles(int[][] rectangles) {
        Map<String, Integer> countMap = new HashMap<>();
        for (int[] edge : rectangles) {
            int a = edge[0];
            int b = edge[1];
            int gcd = gcd(a, b);
            a /= gcd;
            b /= gcd;
            String key = a + "_" + b;
            Integer count = countMap.getOrDefault(key, 0);
            count++;
            countMap.put(key, count);
        }
        // 计算总的组数
        long totalCount = 0;
        for (int count : countMap.values()) {
            if (count <= 1) {
                continue;
            }
            totalCount += ((long)count * (count - 1) / 2);
        }
        return totalCount;
    }

    /**
     * 最大公约数
     * @param a
     * @param b
     * @return
     */
    private int gcd (int a, int b) {
        if (a % b == 0) {
            return b;
        }
        return gcd(b, a % b);
    }

}
