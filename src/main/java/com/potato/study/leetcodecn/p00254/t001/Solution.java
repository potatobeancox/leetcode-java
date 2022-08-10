package com.potato.study.leetcodecn.p00254.t001;

import java.util.*;

/**
 * 254. 因子的组合
 *
 * 整数可以被看作是其因子的乘积。

 例如：

 8 = 2 x 2 x 2;
 = 2 x 4.
 请实现一个函数，该函数接收一个整数 n 并返回该整数所有的因子组合。

 注意：

 你可以假定 n 为永远为正数。
 因子必须大于 1 并且小于 n。
 示例 1：

 输入: 1
 输出: []
 示例 2：

 输入: 37
 输出: []
 示例 3：

 输入: 12
 输出:
 [
 [2, 6],
 [2, 2, 3],
 [3, 4]
 ]
 示例 4:

 输入: 32
 输出:
 [
 [2, 16],
 [2, 2, 8],
 [2, 2, 2, 4],
 [2, 2, 2, 2, 2],
 [2, 4, 4],
 [4, 8]
 ]

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/factor-combinations
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public List<List<Integer>> getFactors(int n) {
        return getFactors(n, 2);
    }

    private List<List<Integer>> getFactors(int n, int startIndex) {
        List<List<Integer>> result = new ArrayList<>();
        if (n == 1) {
            return result;
        }
        // n > 2
        for (int i = startIndex; i <= n / 2; i++) {
            if (n % i != 0) {
                continue;
            }
            int temp = n / i;
            if (temp < i) {
                continue;
            }
            // 找到 temp 的可能
            List<List<Integer>> factors = getFactors(temp, i);
            List<Integer> objects = new ArrayList<>();
            objects.add(temp);
            factors.add(objects);

            for (List<Integer> factor : factors) {
                List<Integer> res = new ArrayList<>(factor);
                res.add(i);

                result.add(res);
            }
        }
        return result;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<Integer>> factors = solution.getFactors(37);
        System.out.println(factors);

        factors = solution.getFactors(12);
        System.out.println(factors);
    }


}
