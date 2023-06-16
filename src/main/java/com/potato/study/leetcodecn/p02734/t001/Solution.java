package com.potato.study.leetcodecn.p02734.t001;


/**
 *
 * 2734. 执行子串操作后的字典序最小字符串
 *
 * 给你一个仅由小写英文字母组成的字符串 s 。在一步操作中，你可以完成以下行为：

 选则 s 的任一非空子字符串，可能是整个字符串，接着将字符串中的每一个字符替换为英文字母表中的前一个字符。例如，'b' 用 'a' 替换，'a' 用 'z' 替换。
 返回执行上述操作 恰好一次 后可以获得的 字典序最小 的字符串。

 子字符串 是字符串中的一个连续字符序列。

 现有长度相同的两个字符串 x 和 字符串 y ，在满足 x[i] != y[i] 的第一个位置 i 上，如果  x[i] 在字母表中先于 y[i] 出现，则认为字符串 x 比字符串 y 字典序更小 。
  

 示例 1：

 输入：s = "cbabc"
 输出："baabc"
 解释：我们选择从下标 0 开始、到下标 1 结束的子字符串执行操作。
 可以证明最终得到的字符串是字典序最小的。
 示例 2：

 输入：s = "acbbc"
 输出："abaab"
 解释：我们选择从下标 1 开始、到下标 4 结束的子字符串执行操作。
 可以证明最终得到的字符串是字典序最小的。
 示例 3：

 输入：s = "leetcode"
 输出："kddsbncd"
 解释：我们选择整个字符串执行操作。
 可以证明最终得到的字符串是字典序最小的。
  

 提示：

 1 <= s.length <= 3 * 105
 s 仅由小写英文字母组成

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/lexicographically-smallest-string-after-substring-operation
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public String smallestString(String s) {
        // 找到第一个不为a的位置 依次减1 直到a
        char[] chars = s.toCharArray();
        int i = 0;
        // 前缀a 省略
        while (i < s.length() && chars[i] == 'a') {
            i++;
        }
        // 全是a 第一个字母转成z
        if (i >= s.length()) {
            chars[s.length()-1] = 'z';
            return new String(chars);
        }
        while (i < s.length() && chars[i] != 'a') {
            chars[i] -= 1;
            i++;
        }
        return new String(chars);
    }

}
