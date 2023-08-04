package com.potato.study.leetcodecn.other.lcr.p0093.t001;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer II 093. 最长斐波那契数列
 *
 * 如果序列 X_1, X_2, ..., X_n 满足下列条件，就说它是 斐波那契式 的：
 *
 * n >= 3
 * 对于所有 i + 2 <= n，都有 X_i + X_{i+1} = X_{i+2}
 * 给定一个严格递增的正整数数组形成序列 arr ，找到 arr 中最长的斐波那契式的子序列的长度。如果一个不存在，返回  0 。
 *
 * （回想一下，子序列是从原序列  arr 中派生出来的，它从 arr 中删掉任意数量的元素（也可以不删），而不改变其余元素的顺序。例如， [3, 5, 8] 是 [3, 4, 5, 6, 7, 8] 的一个子序列）
 *
 *  
 *
 * 示例 1：
 *
 * 输入: arr = [1,2,3,4,5,6,7,8]
 * 输出: 5
 * 解释: 最长的斐波那契式子序列为 [1,2,3,5,8] 。
 * 示例 2：
 *
 * 输入: arr = [1,3,7,11,12,14,18]
 * 输出: 3
 * 解释: 最长的斐波那契式子序列有 [1,11,12]、[3,11,14] 以及 [7,11,18] 。
 *  
 *
 * 提示：
 *
 * 3 <= arr.length <= 1000
 * 1 <= arr[i] < arr[i + 1] <= 10^9
 *
 *  
 *
 * 注意：本题与主站 873 题相同： https://leetcode-cn.com/problems/length-of-longest-fibonacci-subsequence/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/Q91FMA
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // ii 093
    public int lenLongestFibSubseq(int[] arr) {
        // arr 严格递增 说明 其中每个数字都不同 使用 map 找到每个值对应的index
        Map<Integer, Integer> valueIndexMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            valueIndexMap.put(arr[i], i);
        }
        // dp ij 已ij作为 最后两个 fib数 能组成的最大数量
        int n = arr.length;
        int[][] dp = new int[n][n];
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                // 看看 ij 之前是不是在 数字里边
                int before = arr[j] - arr[i];
                if (!valueIndexMap.containsKey(before)) {
                    // 没有之前的点 说明 当前就是最后的两个点
                    dp[i][j] = 2;
                } else {
                    int beforeIndex = valueIndexMap.get(before);
                    // before 有 但是已经被使用了
                    if (beforeIndex < i) {
                        dp[i][j] = Math.max(dp[i][j], dp[beforeIndex][i] + 1);
                    } else {
                        dp[i][j] = 2;
                    }
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        if (max == 2) {
            return 0;
        }
        return max;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[] {
                1,2,3,4,5,6,7,8
        };
        // 1 2 3 5 8
        int i = solution.lenLongestFibSubseq(arr);
        System.out.println(i);
        Assert.assertEquals(5, i);


        arr = new int[] {
                2,4,7,8,9,10,14,15,18,23,32,50
        };
        // 4 14 18 32 50
        i = solution.lenLongestFibSubseq(arr);
        System.out.println(i);
        Assert.assertEquals(5, i);
    }
}
