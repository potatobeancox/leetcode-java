package com.potato.study.leetcodecn.p01016.t001;

import org.junit.Assert;

/**
 * 1016. 子串能表示从 1 到 N 数字的二进制串
 *
 * 给定一个二进制字符串 S（一个仅由若干 '0' 和 '1' 构成的字符串）和一个正整数 N，如果对于从 1 到 N 的每个整数 X，其二进制表示都是 S 的子串，就返回 true，否则返回 false。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：S = "0110", N = 3
 * 输出：true
 * 示例 2：
 *
 * 输入：S = "0110", N = 4
 * 输出：false
 *  
 *
 * 提示：
 *
 * 1 <= S.length <= 1000
 * 1 <= N <= 10^9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-string-with-substrings-representing-1-to-n
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 1016
    public boolean queryString(String s, int n) {
        for (int i = 1; i <= n; i++) {
            String ss = Integer.toBinaryString(i);
            if (!s.contains(ss)) {
                return false;
            }
        }
        return true;
    }
}
