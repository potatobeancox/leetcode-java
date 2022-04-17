package com.potato.study.leetcodecn.p01292.t001;


import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 1292. 元素和小于等于阈值的正方形的最大边长
 *
 * 给你一个大小为 m x n 的矩阵 mat 和一个整数阈值 threshold。
 *
 * 请你返回元素总和小于或等于阈值的正方形区域的最大边长；如果没有这样的正方形区域，则返回 0 。
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：mat = [[1,1,3,2,4,3,2],[1,1,3,2,4,3,2],[1,1,3,2,4,3,2]], threshold = 4
 * 输出：2
 * 解释：总和小于或等于 4 的正方形的最大边长为 2，如图所示。
 * 示例 2：
 *
 * 输入：mat = [[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2]], threshold = 1
 * 输出：0
 * 示例 3：
 *
 * 输入：mat = [[1,1,1,1],[1,0,0,0],[1,0,0,0],[1,0,0,0]], threshold = 6
 * 输出：3
 * 示例 4：
 *
 * 输入：mat = [[18,70],[61,1],[25,85],[14,40],[11,96],[97,96],[63,45]], threshold = 40184
 * 输出：2
 *  
 *
 * 提示：
 *
 * 1 <= m, n <= 300
 * m == mat.length
 * n == mat[i].length
 * 0 <= mat[i][j] <= 10000
 * 0 <= threshold <= 10^5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-side-length-of-a-square-with-sum-less-than-or-equal-to-threshold
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int maxSideLength(int[][] mat, int threshold) {
        // 求2位前缀和 先求 行 再求列
        int[][] newMat = new int[mat.length + 1][mat[0].length + 1];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                newMat[i+1][j+1] = newMat[i+1][j] + mat[i][j];
            }
        }
        // 求列
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                newMat[i+1][j+1] += (newMat[i][j+1]);
            }
        }
        // 枚举每个 ij 作为右下角
        int maxLength = 0;
        for (int i = newMat.length - 1; i >= 0; i--) {
            for (int j = newMat[0].length - 1; j >= 0; j--) {
                // 长度 从大的开始
                int length = Math.min(i, j);
                for (int k = length; k > 0; k--) {
                    int area = newMat[i][j] + newMat[i-k][j-k]
                            - newMat[i][j-k] - newMat[i-k][j];
                    if (area > threshold) {
                        continue;
                    } else {
                        maxLength = Math.max(maxLength, k);
                        break;
                    }
                }
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String str = "[[1,1,3,2,4,3,2],[1,1,3,2,4,3,2],[1,1,3,2,4,3,2]]";
        int[][] arrayTwoDimensional = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(str);
        int i = solution.maxSideLength(arrayTwoDimensional, 4);
        System.out.println(i);
        Assert.assertEquals(2, i);


        str = "[[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2]]";
        arrayTwoDimensional = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(str);
        i = solution.maxSideLength(arrayTwoDimensional, 1);
        System.out.println(i);
        Assert.assertEquals(0, i);
//

        str = "[[1,1,1,1],[1,0,0,0],[1,0,0,0],[1,0,0,0]]";
        arrayTwoDimensional = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(str);
        i = solution.maxSideLength(arrayTwoDimensional, 6);
        System.out.println(i);
        Assert.assertEquals(3, i);


    }
}
