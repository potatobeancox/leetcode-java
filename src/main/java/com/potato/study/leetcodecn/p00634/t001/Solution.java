package com.potato.study.leetcodecn.p00634.t001;


import org.junit.Assert;

import java.util.HashSet;
import java.util.Set;

/**
 * 634. 寻找数组的错位排列
 *
 * 在组合数学中，如果一个排列中所有元素都不在原先的位置上，那么这个排列就被称为 错位排列 。
 *
 * 给定一个从 1 到 n 升序排列的数组，返回 不同的错位排列 的数量 。由于答案可能非常大，你只需要将答案对 109+7 取余 输出即可。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: n = 3
 * 输出: 2
 * 解释: 原始的数组为 [1,2,3]。两个错位排列的数组为 [2,3,1] 和 [3,1,2]。
 * 示例 2:
 *
 * 输入: n = 2
 * 输出: 1
 *  
 *
 * 提示：
 *
 * 1 <= n <= 106
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-the-derangement-of-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int findDerangement(int n) {
        // 只有一个位置 有什么错位
        if (n == 1) {
            return 0;
        }
        // 2个位置
        if (n == 2) {
            return 1;
        }
        long target = -1;
        long n1 = 0;
        long n2 = 1;
        int mod = 1_000_000_000 + 7;
        for (int i = 3; i <= n; i++) {
            target = ((i-1) % mod) * ((n1 + n2) % mod);

            n1 = n2;
            n2 = target;

            target %= mod;
        }
        return (int)target;
    }

}
