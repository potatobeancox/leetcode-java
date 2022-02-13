package com.potato.study.leetcodecn.p00214.t001;

import org.junit.Assert;

/**
 * 214. 最短回文串
 *
 * 给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。找到并返回可以用这种方式转换的最短回文串。

  

 示例 1：

 输入：s = "aacecaaa"
 输出："aaacecaaa"
 示例 2：

 输入：s = "abcd"
 输出："dcbabcd"
  

 提示：

 0 <= s.length <= 5 * 104
 s 仅由小写英文字母组成

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/shortest-palindrome
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode-cn.com/problems/shortest-palindrome/solution/java-kmp-by-lyl-36-zrfx/
     * @param s
     * @return
     */
    public String shortestPalindrome(String s) {
        // 将s 翻转 拼到s 之后
        StringBuilder builder = new StringBuilder(s);
        String target = s + "#" + builder.reverse().toString();
        // 使用next 数组 表示 如果当前字符串匹配不上的话 下一个字符从哪个字符开始匹配
        int[] next = new int[target.length()];
        // 如果开始位置就匹配不上 那么从 -1 + 1开始匹配
        next[0] = -1;
        // next 存的是 这个位置之前匹配上的位置
        int j = -1;
        for (int i = 1; i < target.length(); i++) {
            // 找到应该匹配的位置
            while (j != -1 &&
                    target.charAt(i) != target.charAt(j+1)) {
                j = next[j];
            }
            // 判断能不能匹配上 匹配上的话 往后移动一下记录
            if (target.charAt(i) == target.charAt(j+1)) {
                j += 1;
            }
            next[i] = j;
        }
        // 找到最后一个字符 匹配不上 从哪个开始匹配的位置  也就是 s 之前是可以公用的 往后生成 字符串
        int pos = next[target.length() - 1] + 1;
        // 拼接到s 上
        if (pos == -1) {
            return builder.reverse().toString() + s;
        }
        String substring = s.substring(pos);
        return new StringBuilder(substring).reverse().toString() + s;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String str = "aacecaaa";
        String s = solution.shortestPalindrome(str);
        System.out.println(s);
        Assert.assertEquals("aaacecaaa", s);


        str = "abcd";
        s = solution.shortestPalindrome(str);
        System.out.println(s);
        Assert.assertEquals("dcbabcd", s);
    }


}
