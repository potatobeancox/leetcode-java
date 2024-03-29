package com.potato.study.leetcodecn.p01933.t001;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;

/**
 * 1933. 判断字符串是否可分解为值均等的子串
 *
 * 一个字符串的所有字符都是一样的，被称作等值字符串。
 *
 * 举例，"1111" 和 "33" 就是等值字符串。
 * 相比之下，"123"就不是等值字符串。
 * 规则：给出一个数字字符串s，将字符串分解成一些等值字符串，如果有且仅有一个等值子字符串长度为2，其他的等值子字符串的长度都是3.
 *
 * 如果能够按照上面的规则分解字符串s，就返回真，否则返回假。
 *
 * 子串就是原字符串中连续的字符序列。
 *
 *  
 *
 * 示例 1：
 *
 * 输入: s = "000111000"
 * 输出: false
 * 解释:  s只能被分解长度为3的等值子字符串。
 * 示例 2：
 *
 * 输入: s = "00011111222"
 * 输出: true
 * 解释: s 能被分解为 ["000","111","11","222"].
 * 示例 3：
 *
 * 输入: s = "01110002223300"
 * 输出: false
 * 解释: 一个不能被分解的原因是在开头有一个0.
 *  
 *
 * 提示:
 *
 * 1 <= s.length <= 1000
 * s 仅包含数字。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/check-if-string-is-decomposable-into-value-equal-substrings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public boolean isDecomposable(String s) {
        // 子字符串
        int sameCount = 0;
        char lastCh = '#';
        char[] chars = s.toCharArray();
        int twoCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i == 0) {
                sameCount = 1;
                lastCh = chars[i];
                continue;
            }
            // 需要 进行计算
            if (lastCh != chars[i]) {
                if (sameCount % 3 == 1) {
                    return false;
                } else if (sameCount % 3 == 2) {
                    twoCount++;
                }
                // 如果是 3的倍数 是ok的
                sameCount = 1;
                lastCh = chars[i];
            } else {
                sameCount++;
            }

            if (twoCount > 1) {
                return false;
            }

        }
        if (sameCount % 3 == 1) {
            return false;
        } else if (sameCount % 3 == 2) {
            twoCount++;
        }

        return twoCount == 1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "000111000";
        boolean decomposable = solution.isDecomposable(s);
        System.out.println(decomposable);
        Assert.assertEquals(false, decomposable);


        s = "00011111222";
        decomposable = solution.isDecomposable(s);
        System.out.println(decomposable);
        Assert.assertEquals(true, decomposable);


        s = "01110002223300";
        decomposable = solution.isDecomposable(s);
        System.out.println(decomposable);
        Assert.assertEquals(false, decomposable);
    }


}
