package com.potato.study.leetcodecn.p00564.t001;


import com.potato.study.leetcode.domain.TreeNode;

/**
 * 564. 寻找最近的回文数
 *
 * 给定一个整数 n ，你需要找到与它最近的回文数（不包括自身）。

 “最近的”定义为两个整数差的绝对值最小。

 示例 1:

 输入: "123"
 输出: "121"
 注意:

 n 是由字符串表示的正整数，其长度不超过18。
 如果有多个结果，返回最小的那个。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/find-the-closest-palindrome
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 找到最近的3个数字 n 奇数还是偶数
     * @param n
     * @return
     */
    public String nearestPalindromic(String n) {
        // 对n 进行判定
        return null;
    }


    public int[] getNearest3Num(String n) {
        // 奇数和偶数
        if (n.length() % 2 == 1) {
            // 奇数
            String prefix = n.substring(0, n.length() / 2 + 1);
            int currentPrefixNum = Integer.parseInt(prefix);
            int plus = currentPrefixNum + 1;
            int minus = currentPrefixNum - 1;

            return null;
        } else {
            // 偶数
            String prefix = n.substring(0, n.length() / 2);
            int currentPrefixNum = Integer.parseInt(prefix);
            int plus = currentPrefixNum + 1;
            int minus = currentPrefixNum - 1;
            return new int[] {getPalindromicEven(currentPrefixNum), getPalindromicEven(plus), getPalindromicEven(minus)};
        }
    }


    private int getPalindromicEven(int num) {
        StringBuilder builder = new StringBuilder(num);
        builder.append(builder.reverse().toString());
        return Integer.parseInt(builder.toString());
    }

    private int getPalindromicOdd(int num) {
        StringBuilder builder = new StringBuilder(num);
        builder.append(builder.reverse().toString().substring(1));
        return Integer.parseInt(builder.toString());
    }

}
