package com.potato.study.leetcodecn.p02315.t001;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

/**
 * 2315. 统计星号
 *
 * 给你一个字符串 s ，每 两个 连续竖线 '|' 为 一对 。换言之，第一个和第二个 '|' 为一对，第三个和第四个 '|' 为一对，以此类推。

 请你返回 不在 竖线对之间，s 中 '*' 的数目。

 注意，每个竖线 '|' 都会 恰好 属于一个对。

  

 示例 1：

 输入：s = "l|*e*et|c**o|*de|"
 输出：2
 解释：不在竖线对之间的字符加粗加斜体后，得到字符串："l|*e*et|c**o|*de|" 。
 第一和第二条竖线 '|' 之间的字符不计入答案。
 同时，第三条和第四条竖线 '|' 之间的字符也不计入答案。
 不在竖线对之间总共有 2 个星号，所以我们返回 2 。
 示例 2：

 输入：s = "iamprogrammer"
 输出：0
 解释：在这个例子中，s 中没有星号。所以返回 0 。
 示例 3：

 输入：s = "yo|uar|e**|b|e***au|tifu|l"
 输出：5
 解释：需要考虑的字符加粗加斜体后："yo|uar|e**|b|e***au|tifu|l" 。不在竖线对之间总共有 5 个星号。所以我们返回 5 。
  

 提示：

 1 <= s.length <= 1000
 s 只包含小写英文字母，竖线 '|' 和星号 '*' 。
 s 包含 偶数 个竖线 '|' 。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/count-asterisks
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {




    // 2315
    public int countAsterisks(String s) {
        // 遍历 s 往后找 ||
        int index = 0;
        int innerStarCount = 0;
        char[] chars = s.toCharArray();
        while (index < s.length()) {
            // 找到第一个 |
            while (index < s.length() && chars[index] != '|') {
                if ('*' == chars[index]) {
                    innerStarCount++;
                }
                index++;
            }
            if (index >= s.length()) {
                break;
            }
            index++;
            while (index < s.length() && chars[index] != '|') {
                index++;
            }
            if (index >= s.length()) {
                break;
            }
            //  往后下一组
            index++;
        }
        return innerStarCount;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "l|*e*et|c**o|*de|";
        int i = solution.countAsterisks(s);
        System.out.println(i);
        Assert.assertEquals(2, i);

        s = "iamprogrammer";
        i = solution.countAsterisks(s);
        System.out.println(i);
        Assert.assertEquals(0, i);

        s = "yo|uar|e**|b|e***au|tifu|l";
        i = solution.countAsterisks(s);
        System.out.println(i);
        Assert.assertEquals(5, i);
    }

}
