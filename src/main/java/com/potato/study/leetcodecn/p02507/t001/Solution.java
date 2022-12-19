package com.potato.study.leetcodecn.p02507.t001;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * 2507. 使用质因数之和替换后可以取到的最小值
 *
 * 给你一个正整数 n 。

 请你将 n 的值替换为 n 的 质因数 之和，重复这一过程。

 注意，如果 n 能够被某个质因数多次整除，则在求和时，应当包含这个质因数同样次数。
 返回 n 可以取到的最小值。

  

 示例 1：

 输入：n = 15
 输出：5
 解释：最开始，n = 15 。
 15 = 3 * 5 ，所以 n 替换为 3 + 5 = 8 。
 8 = 2 * 2 * 2 ，所以 n 替换为 2 + 2 + 2 = 6 。
 6 = 2 * 3 ，所以 n 替换为 2 + 3 = 5 。
 5 是 n 可以取到的最小值。
 示例 2：

 输入：n = 3
 输出：3
 解释：最开始，n = 3 。
 3 是 n 可以取到的最小值。
  

 提示：

 2 <= n <= 105

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/smallest-value-after-replacing-with-sum-of-prime-factors
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int smallestValue(int n) {
        // 求质因数的和
        int temp = n;
        int sum = 0;
        for (int i = 2; i <= n; i++) {
            if (temp < i) {
                break;
            }
            while (temp % i == 0) {
                temp /= i;
                sum += i;
            }
        }
        // 没有变
        if (sum == n) {
            return n;
        }
        // 变了 递归找
        return smallestValue(sum);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.smallestValue(15);
        System.out.println(i);
        Assert.assertEquals(5, i);
    }

}
