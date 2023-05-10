package com.potato.study.leetcodecn.p00564.t001;


import com.potato.study.leetcode.domain.TreeNode;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

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
        long target = Long.parseLong(n);
        List<Long> list = getPalindromicList(n);
        long nearest = -1;
        for (long num : list) {

            if (num == target) {
                continue;
            }

            if (nearest == -1) {
                nearest = num;
                continue;
            }


            if (Math.abs(target - num) < Math.abs(target - nearest)
                    || (Math.abs(target - num) == Math.abs(target - nearest) && num < nearest)) {
                nearest = num;
            }
        }
        return String.valueOf(nearest);
    }

    private List<Long> getPalindromicList(String n) {
        // 之前数位
        int size = n.length();
        // 977 -> 1001   100-> 99
        List<Long> res = new ArrayList<>();
        long num = (long) Math.pow(10, size) + 1;
        res.add(num);

        num = (long) Math.pow(10, size - 1) - 1;
        res.add(num);

        // 找到substring
        String substring = n.substring(0, (n.length() + 1) / 2);
        long l = Long.parseLong(substring);
        long big = l + 1;
        long small = l - 1;

        res.add(getPalindromicLength(l, size));
        if (String.valueOf(big).length() == substring.length()) {
            res.add(getPalindromicLength(big, size));
        }
        if (String.valueOf(small).length() == substring.length()) {
            res.add(getPalindromicLength(small, size));
        }

        return res;
    }

    private Long getPalindromicLength(long target, int size) {
        StringBuilder builder = new StringBuilder();
        builder.append(target);

        String substring = builder.substring(0, size - builder.length());

        StringBuilder sub = new StringBuilder(substring);
        builder.append(sub.reverse().toString());

        return Long.parseLong(builder.toString());
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = solution.nearestPalindromic("123");
        System.out.println(s);
        Assert.assertEquals("121", s);

        s = solution.nearestPalindromic("1");
        System.out.println(s);
        Assert.assertEquals("0", s);
    }

}
