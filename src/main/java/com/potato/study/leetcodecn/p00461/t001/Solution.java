package com.potato.study.leetcodecn.p00461.t001;

import org.junit.Assert;

/**
 * 461. 汉明距离
 *
 * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。

 给出两个整数 x 和 y，计算它们之间的汉明距离。

 注意：
 0 ≤ x, y < 231.

 示例:

 输入: x = 1, y = 4

 输出: 2

 解释:
 1   (0 0 0 1)
 4   (0 1 0 0)
 ↑   ↑

 上面的箭头指出了对应二进制位不同的位置。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/hamming-distance
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 异或 求二进制 1的个数
     * @param x
     * @param y
     * @return
     */
    public int hammingDistance(int x, int y) {
        int tmp = x ^ y;
        int count = 0;
        while (tmp > 0) {
            int bit = tmp & 1;
            if (bit == 1) {
                count++;
            }
            tmp /= 2;
        }
        return count;
    }


    public static void main(String[] args) {

        Solution solution = new Solution();

        int x = 1;
        int y = 4;
        int distance = solution.hammingDistance(x, y);
        System.out.println(distance);
        Assert.assertEquals(2, distance);

//        g = new int[]{1,2};
//        s = new int[]{1,2,3};
//        contentChildren = solution.findContentChildren(g, s);
//        System.out.println(contentChildren);
//        Assert.assertEquals(2, contentChildren);
    }
}
