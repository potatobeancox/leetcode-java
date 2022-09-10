package com.potato.study.leetcodecn.p00761.t001;

import org.junit.Assert;

import java.util.*;

/**
 * 761. 特殊的二进制序列
 *
 * 特殊的二进制序列是具有以下两个性质的二进制序列：
 *
 * 0 的数量与 1 的数量相等。
 * 二进制序列的每一个前缀码中 1 的数量要大于等于 0 的数量。
 * 给定一个特殊的二进制序列 S，以字符串形式表示。定义一个操作 为首先选择 S 的两个连续且非空的特殊的子串，然后将它们交换。（两个子串为连续的当且仅当第一个子串的最后一个字符恰好为第二个子串的第一个字符的前一个字符。)
 *
 * 在任意次数的操作之后，交换后的字符串按照字典序排列的最大的结果是什么？
 *
 * 示例 1:
 *
 * 输入: S = "11011000"
 * 输出: "11100100"
 * 解释:
 * 将子串 "10" （在S[1]出现） 和 "1100" （在S[3]出现）进行交换。
 * 这是在进行若干次操作后按字典序排列最大的结果。
 * 说明:
 *
 * S 的长度不超过 50。
 * S 保证为一个满足上述定义的特殊 的二进制序列。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/special-binary-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode.cn/problems/special-binary-string/solution/by-ac_oier-cz6h/
     * @param s
     * @return
     */
    public String makeLargestSpecial(String s) {
        // 使用 一个 k 记录 当前子串的得分，
        int status = 0;
        // 遍历 s 记录 当前没有改动的开始位置 和结束位置， 对于之间的位置 地柜进行比较
        int start = 0;
        char[] chars = s.toCharArray();
        // 用list 生成一个 每次 k等于 0生成的子串
        List<String> list = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '0') {
                status--;
            } else {
                // 1
                status++;
            }
            // 当前是否满足等于 0
            if (status == 0) {
                String subString = s.substring(start + 1, i+1);
                String temp = "1" + makeLargestSpecial(subString) + "0";
                list.add(temp);
                // 重新计算 开始位置
                start = i + 1;
            }
        }
        // 对于 list 进行排序 字典序升序
        Collections.sort(list, (o1, o2) -> {
            String tmp = o1 + o2;
            return tmp.compareTo(o2 + o1);
        });
        // 构造结果
        StringBuilder builder = new StringBuilder();
        for (int i = list.size() - 1; i >= 0; i--) {
            builder.append(list.get(i));
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "11011000";
        String str = solution.makeLargestSpecial(s);
        System.out.println(str);
        Assert.assertEquals("11100100", str);
    }
}
