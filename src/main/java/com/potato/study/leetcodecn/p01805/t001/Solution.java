package com.potato.study.leetcodecn.p01805.t001;

import org.junit.Assert;

import java.util.HashSet;
import java.util.Set;

/**
 * 1805. 字符串中不同整数的数目
 *
 * 给你一个字符串 word ，该字符串由数字和小写英文字母组成。

 请你用空格替换每个不是数字的字符。例如，"a123bc34d8ef34" 将会变成 " 123  34 8  34" 。注意，剩下的这些整数为（相邻彼此至少有一个空格隔开）："123"、"34"、"8" 和 "34" 。

 返回对 word 完成替换后形成的 不同 整数的数目。

 只有当两个整数的 不含前导零 的十进制表示不同， 才认为这两个整数也不同。

  

 示例 1：

 输入：word = "a123bc34d8ef34"
 输出：3
 解释：不同的整数有 "123"、"34" 和 "8" 。注意，"34" 只计数一次。
 示例 2：

 输入：word = "leet1234code234"
 输出：2
 示例 3：

 输入：word = "a1b01c001"
 输出：1
 解释："1"、"01" 和 "001" 视为同一个整数的十进制表示，因为在比较十进制值时会忽略前导零的存在。
  

 提示：

 1 <= word.length <= 1000
 word 由数字和小写英文字母组成

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/number-of-different-integers-in-a-string
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     *
     * @param word
     * @return
     */
    public int numDifferentIntegers(String word) {
        if ("".equals(word)) {
            return 0;
        }
        Set<String> set = new HashSet<>();
        int startIndex = Character.isDigit(word.charAt(0)) ? 0 : -1;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (Character.isDigit(ch)) {
                if (startIndex == -1) {
                    startIndex = i;
                }
            } else {
                // end = i-1
                if (startIndex != -1) {
                    String str = deleteZero(word.substring(startIndex, i));
                    set.add(str);
                }
                startIndex = -1;
            }
        }
        // last
        if (startIndex != -1) {
            String str = deleteZero(word.substring(startIndex, word.length()));
            set.add(str);
        }
        return set.size();
    }


    /**
     *
     * @param numStr
     * @return
     */
    private String deleteZero(String numStr) {
        int index = 0;
        while (index < numStr.length() && '0' == numStr.charAt(index)) {
            index++;
        }
        if (index == numStr.length()) {
            return "0";
        }
        return numStr.substring(index);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String str = "a123bc34d8ef34";
        int i = solution.numDifferentIntegers(str);
        System.out.println(i);
        Assert.assertEquals(3, i);


        str = "leet1234code234";
        i = solution.numDifferentIntegers(str);
        System.out.println(i);
        Assert.assertEquals(2, i);
    }
}
