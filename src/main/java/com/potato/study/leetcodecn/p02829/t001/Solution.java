package com.potato.study.leetcodecn.p02829.t001;


import java.util.List;

/**
 *
 * 2829. k-avoiding 数组的最小总和
 *
 * 给你两个整数 n 和 k 。

 对于一个由 不同 正整数组成的数组，如果其中不存在任何求和等于 k 的不同元素对，则称其为 k-avoiding 数组。

 返回长度为 n 的 k-avoiding 数组的可能的最小总和。



 示例 1：

 输入：n = 5, k = 4
 输出：18
 解释：设若 k-avoiding 数组为 [1,2,4,5,6] ，其元素总和为 18 。
 可以证明不存在总和小于 18 的 k-avoiding 数组。
 示例 2：

 输入：n = 2, k = 6
 输出：3
 解释：可以构造数组 [1,2] ，其元素总和为 3 。
 可以证明不存在总和小于 3 的 k-avoiding 数组。


 提示：

 1 <= n, k <= 50
 *
 */
public class Solution {



    public int minimumSum(int n, int k) {
        long minSum = 0;
        // k 之前 有多少个可以使用的数字 【1， k/2】
        int beforeCount = k / 2;
        if (beforeCount >= n) {
            minSum += (1 + n) * n / 2;
            return (int) minSum;
        }
        minSum += (1 + beforeCount) * beforeCount / 2;
        // [k 之后也要找
        int afterCount = n - beforeCount;
        minSum += (k + k + afterCount - 1) * afterCount / 2;

        return (int) minSum;
    }


}
