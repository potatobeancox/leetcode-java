package com.potato.study.leetcodecn.p00397.t001;

import java.util.*;

/**
 * 397. 整数替换
 *
 * 给定一个正整数 n ，你可以做如下操作：

 如果 n 是偶数，则用 n / 2替换 n 。
 如果 n 是奇数，则可以用 n + 1或n - 1替换 n 。
 n 变为 1 所需的最小替换次数是多少？

  

 示例 1：

 输入：n = 8
 输出：3
 解释：8 -> 4 -> 2 -> 1
 示例 2：

 输入：n = 7
 输出：4
 解释：7 -> 8 -> 4 -> 2 -> 1
 或 7 -> 6 -> 3 -> 2 -> 1
 示例 3：

 输入：n = 4
 输出：2
  

 提示：

 1 <= n <= 231 - 1

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/integer-replacement
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    private Map<Long, Integer> stepMap = new HashMap<>();

    public int integerReplacement(int n) {
        return integerReplacement((long) n);
    }


    private int integerReplacement(long n) {
        if (stepMap.containsKey(n)) {
            return stepMap.get(n);
        }

        if (n == 1) {
            stepMap.put(1L, 0);
            return 0;
        }

        long tmp = n;
        int step;
        if (tmp % 2 == 1) {
            step = Math.min(integerReplacement(tmp + 1), integerReplacement(tmp - 1)) + 1;
        } else {
            step = 1 + integerReplacement(tmp/2);
        }
        stepMap.put(n, step);
        return step;

    }
}

