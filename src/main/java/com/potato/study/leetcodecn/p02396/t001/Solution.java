package com.potato.study.leetcodecn.p02396.t001;

import java.util.HashSet;
import java.util.Set;

/**
 * 2396. 严格回文的数字
 *
 * 如果一个整数 n 在 b 进制下（b 为 2 到 n - 2 之间的所有整数）对应的字符串 全部 都是 回文的 ，那么我们称这个数 n 是 严格回文 的。
 *
 * 给你一个整数 n ，如果 n 是 严格回文 的，请返回 true ，否则返回 false 。
 *
 * 如果一个字符串从前往后读和从后往前读完全相同，那么这个字符串是 回文的 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 9
 * 输出：false
 * 解释：在 2 进制下：9 = 1001 ，是回文的。
 * 在 3 进制下：9 = 100 ，不是回文的。
 * 所以，9 不是严格回文数字，我们返回 false 。
 * 注意在 4, 5, 6 和 7 进制下，n = 9 都不是回文的。
 * 示例 2：
 *
 * 输入：n = 4
 * 输出：false
 * 解释：我们只考虑 2 进制：4 = 100 ，不是回文的。
 * 所以我们返回 false 。
 *  
 *
 * 提示：
 *
 * 4 <= n <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/strictly-palindromic-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     *
     * @param n
     * @return
     */
    public boolean isStrictlyPalindromic(int n) {
        // 12(n-2) 都是这样 说明 根本不可能 唯一可能得 4 -> 100
        return false;
    }

}
