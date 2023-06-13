package com.potato.study.leetcodecn.p02729.t001;


import java.util.HashSet;
import java.util.Set;

/**
 *
 * 2729. 判断一个数是否迷人
 *
 * 给你一个三位数整数 n 。
 *
 * 如果经过以下修改得到的数字 恰好 包含数字 1 到 9 各一次且不包含任何 0 ，那么我们称数字 n 是 迷人的 ：
 *
 * 将 n 与数字 2 * n 和 3 * n 连接 。
 * 如果 n 是迷人的，返回 true，否则返回 false 。
 *
 * 连接 两个数字表示把它们首尾相接连在一起。比方说 121 和 371 连接得到 121371 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 192
 * 输出：true
 * 解释：我们将数字 n = 192 ，2 * n = 384 和 3 * n = 576 连接，得到 192384576 。这个数字包含 1 到 9 恰好各一次。
 * 示例 2：
 *
 * 输入：n = 100
 * 输出：false
 * 解释：我们将数字 n = 100 ，2 * n = 200 和 3 * n = 300 连接，得到 100200300 。这个数字不符合上述条件。
 *  
 *
 * 提示：
 *
 * 100 <= n <= 999
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/check-if-the-number-is-fascinating
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public boolean isFascinating(int n) {
        // n 2n 3n 链接
        StringBuilder builder = new StringBuilder();
        // 变成字符串 计数
        builder.append(n);
        builder.append(n * 2);
        builder.append(n * 3);

        if (builder.length() != 9) {
            return false;
        }
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < builder.length(); i++) {
            char c = builder.charAt(i);
            if (c == '0') {
                return false;
            }
            if (set.contains(c)) {
                return false;
            }
            set.add(c);
        }
        return set.size() == 9;
    }

}
