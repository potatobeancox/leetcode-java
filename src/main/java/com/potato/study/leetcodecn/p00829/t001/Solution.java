package com.potato.study.leetcodecn.p00829.t001;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 829. 连续整数求和
 *
 * 给定一个正整数 n，返回 连续正整数满足所有数字之和为 n 的组数 。 
 *
 *  
 *
 * 示例 1:
 *
 * 输入: n = 5
 * 输出: 2
 * 解释: 5 = 2 + 3，共有两组连续整数([5],[2,3])求和后为 5。
 * 示例 2:
 *
 * 输入: n = 9
 * 输出: 3
 * 解释: 9 = 4 + 5 = 2 + 3 + 4
 * 示例 3:
 *
 * 输入: n = 15
 * 输出: 4
 * 解释: 15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5
 *  
 *
 * 提示:
 *
 * 1 <= n <= 109​​​​​​​
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/consecutive-numbers-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int consecutiveNumbersSum(int n) {
        // 829
        int count = 0;
        for (int i = 1; (i * i+1) <= 2 * n ; i++) {
            if (canConsist(n, i)) {
                count++;
            }
        }
        return count;
    }

    private boolean canConsist(int n, int k) {
        if (k % 2 == 1) {
            return n % k == 0;
        } else {
            return n % k != 0 && 2 * n % k == 0;
        }
    }
}
