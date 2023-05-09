package com.potato.study.leetcodecn.p01183.t001;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1183. 矩阵中 1 的最大数量
 *
 * 现在有一个尺寸为 width * height 的矩阵 M，矩阵中的每个单元格的值不是 0 就是 1。

 而且矩阵 M 中每个大小为 sideLength * sideLength 的 正方形 子阵中，1 的数量不得超过 maxOnes。

 请你设计一个算法，计算矩阵中最多可以有多少个 1。

  

 示例 1：

 输入：width = 3, height = 3, sideLength = 2, maxOnes = 1
 输出：4
 解释：
 题目要求：在一个 3*3 的矩阵中，每一个 2*2 的子阵中的 1 的数目不超过 1 个。
 最好的解决方案中，矩阵 M 里最多可以有 4 个 1，如下所示：
 [1,0,1]
 [0,0,0]
 [1,0,1]
 示例 2：

 输入：width = 3, height = 3, sideLength = 2, maxOnes = 2
 输出：6
 解释：
 [1,0,1]
 [1,0,1]
 [1,0,1]
  

 提示：

 1 <= width, height <= 100
 1 <= sideLength <= width, height
 0 <= maxOnes <= sideLength * sideLength

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/maximum-number-of-ones
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class Solution {

    public int maximumNumberOfOnes(int width, int height, int sideLength, int maxOnes) {
        // 交换 保证 height 大
        if (height < width) {
            int tmp = height;
            height = width;
            width = tmp;
        }
        int count1 = (width / sideLength) * (height / sideLength);
        // wid 还剩多少和剩余多少个
        int remindWith = width % sideLength;
        int count2 = height / sideLength;
        // 计算有多少组完全的 sideLength* sideLength 计算有多少剩下的和剩下多少
        int remindHeight = height % sideLength;
        int count3 = width / sideLength;


        int area4 = remindWith * remindHeight;
        // 最小的面积都填补满 那么多有都可以用 maxOnes 填满
        if (maxOnes <= area4) {
            // 能满足直接乘法返回
            return maxOnes * (count1 + count2 + count3 + 1);
        }
        // 比剩下的2个都小
        int count = count1 * maxOnes;
        // 剩下 count2 比较多的都用了
        int tmp = Math.min(remindWith * sideLength, maxOnes);
        count += tmp * count2;
        // 当前 sideLength * remindHeight 和剩下的哪个小一些
        count += Math.min(maxOnes - tmp + area4, sideLength * remindHeight) * count3;
        // 剩下的最小的 都填充了
        count += area4;
        return count;
    }


}

