package com.potato.study.leetcodecn.p00625.t001;


import com.potato.study.leetcode.domain.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 625. 最小因式分解
 *
 * 给定一个正整数 a，找出最小的正整数 b 使得 b 的所有数位相乘恰好等于 a。
 *
 * 如果不存在这样的结果或者结果不是 32 位有符号整数，返回 0。
 *
 *  
 *
 * 样例 1
 *
 * 输入：
 *
 * 48
 * 输出：
 *
 * 68
 *  
 *
 * 样例 2
 *
 * 输入：
 *
 * 15
 * 输出：
 *
 * 35
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-factorization
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {



    public int smallestFactorization(int num) {
        if (1 == num) {
            return 1;
        }
        StringBuilder builder = new StringBuilder();
        int tmp = num;
        // 9 往2 一次找 能够整除的 能找多少就找多少 不行就gg
        for (int i = 9; i >= 2; i--) {
            // 一次
            while (tmp >= i && tmp % i == 0) {
                builder.append(i);
                tmp /= i;
            }

            if (tmp == 1) {
                break;
            }
        }

        if (builder.length() > 10 || builder.length() == 0 || tmp != 1) {
            return 0;
        }
        long l = Long.parseLong(builder.reverse().toString());
        if (l > Integer.MAX_VALUE) {
            return 0;
        }
        return (int) l;
    }

}
