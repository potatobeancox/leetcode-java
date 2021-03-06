package com.potato.study.leetcodecn.p01131.t001;


import org.junit.Assert;

import java.util.Stack;

/**
 * 1131. 绝对值表达式的最大值
 *
 * 给你两个长度相等的整数数组，返回下面表达式的最大值：

 |arr1[i] - arr1[j]| + |arr2[i] - arr2[j]| + |i - j|

 其中下标 i，j 满足 0 <= i, j < arr1.length。

  

 示例 1：

 输入：arr1 = [1,2,3,4], arr2 = [-1,4,5,6]
 输出：13
 示例 2：

 输入：arr1 = [1,-2,-5,0,10], arr2 = [0,-2,-1,-7,-4]
 输出：20
  

 提示：

 2 <= arr1.length == arr2.length <= 40000
 -10^6 <= arr1[i], arr2[i] <= 10^6

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/maximum-of-absolute-value-expression
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int maxAbsValExpr(int[] arr1, int[] arr2) {
        // |arr1[i] - arr1[j]| + |arr2[i] - arr2[j]| + |i - j|
        int max1 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE;
        // arr1 + arr2 + i  最大值 - 最小值
        int max2 = Integer.MIN_VALUE;
        int min2 = Integer.MAX_VALUE;
        // arr1 + arr2 - i  最大值 - 最小值
        int max3 = Integer.MIN_VALUE;
        int min3 = Integer.MAX_VALUE;
        // arr1 - arr2 + i
        int max4 = Integer.MIN_VALUE;
        int min4 = Integer.MAX_VALUE;

        for (int i = 0; i < arr1.length; i++) {
            max1 = Math.max(max1, arr1[i] + arr2[i] + i);
            min1 = Math.min(min1, arr1[i] + arr2[i] + i);

            max2 = Math.max(max2, arr1[i] + arr2[i] - i);
            min2 = Math.min(min2, arr1[i] + arr2[i] - i);

            max3 = Math.max(max3, arr1[i] - arr2[i] + i);
            min3 = Math.min(min3, arr1[i] - arr2[i] + i);

            max4 = Math.max(max4, arr1[i] - arr2[i] - i);
            min4 = Math.min(min4, arr1[i] - arr2[i] - i);
        }

        // arr1 - arr2 - i
        int max = Math.max((max1 - min1), (max2 - min2));
        max = Math.max(max, (max3 - min3));
        max = Math.max(max, (max4 - min4));
        return max;
    }
}
