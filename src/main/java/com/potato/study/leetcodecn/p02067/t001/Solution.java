package com.potato.study.leetcodecn.p02067.t001;

/**
 * 2067. 等计数子串的数量
 *
 * 给你一个下标从 0 开始的字符串 s，只包含小写英文字母和一个整数 count。如果 s 的 子串 中的每种字母在子串中恰好出现 count 次，这个子串就被称为 等计数子串。

 返回 s 中 等计数子串 的个数。

 子串 是字符串中连续的非空字符序列。

  

 示例 1:

 输入: s = "aaabcbbcc", count = 3
 输出: 3
 解释:
 从下标 0 开始到下标 2 结束的子串是 "aaa"。
 字母 “a” 在子串中恰好出现了 3 次。
 从下标 3 开始到下标 8 结束的子串是 "bcbbcc"。
 字母 “b” 和 “c” 在子串中恰好出现了 3 次。
 从下标 0 开始到下标 8 结束的子串是 "aaabcbbcc"。
 字母 “a”、“b” 和 “c” 在子串中恰好出现了 3 次。
 示例 2:

 输入: s = "abcd", count = 2
 输出: 0
 解释:
 每种字母在 s 中出现的次数小于 count。
 因此，s 中没有子串是等计数子串，返回 0。
 示例 3:

 输入: s = "a", count = 5
 输出: 0
 解释:
 每种字母在 s 中出现的次数小于 count。
 因此，s 中没有子串是等计数子串，返回 0。
  

 提示:

 1 <= s.length <= 3 * 104
 1 <= count <= 3 * 104
 s 只由小写英文字母组成。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/number-of-equal-count-substrings
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode.cn/problems/number-of-equal-count-substrings/solution/qian-zhui-ge-by-qing-bi-ning-shuang-88bc/
     * @param s
     * @param count
     * @return
     */
    public int equalCountSubstrings(String s, int count) {
        // 针对1-26 种字母长度 每次对 s进行子串统计，窗口大小每次传入
        int totalCount = 0;
        for (int i = 1; i <= 26; i++) {
            int len = i * count;
            totalCount += countEqualCountSubstrings(s, count, len);
        }
        return totalCount;
    }


    /**
     *
     * @param s     原始串
     * @param count 每种字符要有的个数
     * @param len   子串长度
     * @return
     */
    private int countEqualCountSubstrings(String s, int count, int len) {
        // 统计窗口内部的字符个数
        int[] num = new int[26];
        int left = 0;
        int validCount = 0;
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            num[index]++;
            // 判断是否需要修改 window 左边
            if (i - left + 1 > len) {
                num[s.charAt(left) - 'a']--;
                left++;
            }
            // 结算当前子串是否合法
            if (i >= len - 1) {
                boolean isValid = true;
                for (int j = 0; j < 26; j++) {
                    if (num[j] != 0 && num[j] != count) {
                        isValid = false;
                        break;
                    }
                }
                if (isValid) {
                    validCount++;
                }
            }
        }
        return validCount;
    }


}
