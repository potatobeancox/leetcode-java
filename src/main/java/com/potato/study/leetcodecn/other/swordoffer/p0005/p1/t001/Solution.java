package com.potato.study.leetcodecn.other.swordoffer.p0005.p1.t001;

import org.junit.Assert;

/**
 * 剑指 Offer 05. 替换空格
 *
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。

  

 示例 1：

 输入：s = "We are happy."
 输出："We%20are%20happy."
  

 限制：

 0 <= s 的长度 <= 10000

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public String replaceSpace(String s) {
        if (null == s || s.length() == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (char ch : s.toCharArray()) {
            if (Character.isSpaceChar(ch)) {
                builder.append("%20");
            } else {
                builder.append(ch);
            }
        }
        return builder.toString();
    }


    public static void main(String[] args) {

        Solution solution = new Solution();
        String str = "We are happy.";
        String s = solution.replaceSpace(str);
        System.out.println(s);
        Assert.assertEquals("We%20are%20happy.", s);
    }

}
