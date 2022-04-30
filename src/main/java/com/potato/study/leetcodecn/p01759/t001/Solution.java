package com.potato.study.leetcodecn.p01759.t001;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 1759. 统计同构子字符串的数目
 *
 *
 * 给你一个字符串 s ，返回 s 中 同构子字符串 的数目。由于答案可能很大，只需返回对 109 + 7 取余 后的结果。
 *
 * 同构字符串 的定义为：如果一个字符串中的所有字符都相同，那么该字符串就是同构字符串。
 *
 * 子字符串 是字符串中的一个连续字符序列。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "abbcccaa"
 * 输出：13
 * 解释：同构子字符串如下所列：
 * "a"   出现 3 次。
 * "aa"  出现 1 次。
 * "b"   出现 2 次。
 * "bb"  出现 1 次。
 * "c"   出现 3 次。
 * "cc"  出现 2 次。
 * "ccc" 出现 1 次。
 * 3 + 1 + 2 + 1 + 3 + 2 + 1 = 13
 * 示例 2：
 *
 * 输入：s = "xy"
 * 输出：2
 * 解释：同构子字符串是 "x" 和 "y" 。
 * 示例 3：
 *
 * 输入：s = "zzzzz"
 * 输出：15
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 105
 * s 由小写字符串组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-number-of-homogenous-substrings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int countHomogenous(String s) {
        // 遍历 s 每次往后找到 相同的字母 每次找到一个 往后加上当前连续的字母数量 （以当前位置结尾的字母数量）
        int index = 0;
        int totalCount = 0;
        int mod = 1_000_000_000 + 7;
        while (index < s.length()) {
            // 找到与当前位置相同的字母数量
            char ch = s.charAt(index);
            int sameCount = 0;
            while (index < s.length() && s.charAt(index) == ch) {
                sameCount++;
                // 以当前为结尾的数量
                totalCount += sameCount;
                totalCount %= mod;
                index++;
            }
        }
        return totalCount;
    }


}
