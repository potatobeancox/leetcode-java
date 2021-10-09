package com.potato.study.leetcodecn.p01415.t001;

import org.junit.Assert;

/**
 * 1415. 长度为 n 的开心字符串中字典序第 k 小的字符串
 *
 * 一个 「开心字符串」定义为：
 *
 * 仅包含小写字母 ['a', 'b', 'c'].
 * 对所有在 1 到 s.length - 1 之间的 i ，满足 s[i] != s[i + 1] （字符串的下标从 1 开始）。
 * 比方说，字符串 "abc"，"ac"，"b" 和 "abcbabcbcb" 都是开心字符串，但是 "aa"，"baa" 和 "ababbc" 都不是开心字符串。
 *
 * 给你两个整数 n 和 k ，你需要将长度为 n 的所有开心字符串按字典序排序。
 *
 * 请你返回排序后的第 k 个开心字符串，如果长度为 n 的开心字符串少于 k 个，那么请你返回 空字符串 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 1, k = 3
 * 输出："c"
 * 解释：列表 ["a", "b", "c"] 包含了所有长度为 1 的开心字符串。按照字典序排序后第三个字符串为 "c" 。
 * 示例 2：
 *
 * 输入：n = 1, k = 4
 * 输出：""
 * 解释：长度为 1 的开心字符串只有 3 个。
 * 示例 3：
 *
 * 输入：n = 3, k = 9
 * 输出："cab"
 * 解释：长度为 3 的开心字符串总共有 12 个 ["aba", "abc", "aca", "acb", "bab", "bac", "bca", "bcb", "cab", "cac", "cba", "cbc"] 。第 9
 * 个字符串为 "cab"
 * 示例 4：
 *
 * 输入：n = 2, k = 7
 * 输出：""
 * 示例 5：
 *
 * 输入：n = 10, k = 100
 * 输出："abacbabacb"
 *  
 *
 * 提示：
 *
 * 1 <= n <= 10
 * 1 <= k <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/the-k-th-lexicographical-string-of-all-happy-strings-of-length-n
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     *
     * @param n
     * @param k
     * @return
     */
    public String getHappyString(int n, int k) {
        // 先判断 k 在哪棵树上 每棵树节点树
        int leafNum = (1 << (n-1));
        // k 比 n层 的3棵树都大 取不到 返回
        if (k > leafNum * 3) {
            return "";
        }
        int treeIndex = (k-1) / leafNum;
        // 初始化 每个字母的一下个树字母对应值
        char[][] nextChar = new char[][] {
                {'b','c'}, // a对应
                {'a','c'},   // b对应
                {'a','b'}    // c对应
        };
        // 计算出 k 对应在树的哪个坐标 从0 开始 k-1 / 叶子数量

        int treeNodeIndex = (k-1) % leafNum;
        // 利用 位置 对应2进制 就是哪个点 生成字符串 2进制，取后n个，通过 遍历 字符串知道应该挑什么样的字符串
        int lastCharIndex = treeIndex;
        StringBuilder builder = new StringBuilder();
        builder.append((char)(treeIndex + 'a'));
        if (n == 1) {
            return builder.toString();
        }
        // n-1 就是 上面的 abc
        for (int i = n-2; i >= 0; i--) {
            int bit = (1 << i) & treeNodeIndex;
            char ch;
            if (bit > 0) {
                ch = nextChar[lastCharIndex][1];
                lastCharIndex = (ch - 'a');
            } else {
                ch = nextChar[lastCharIndex][0];
                lastCharIndex = (ch - 'a');
            }
            builder.append(ch);
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 1;
        int k = 3;
        String happyString = solution.getHappyString(n, k);
        System.out.println(happyString);
        Assert.assertEquals("c", happyString);


        n = 1;
        k = 4;
        happyString = solution.getHappyString(n, k);
        System.out.println(happyString);
        Assert.assertEquals("", happyString);


        n = 3;
        k = 9;
        happyString = solution.getHappyString(n, k);
        System.out.println(happyString);
        Assert.assertEquals("cab", happyString);

        n = 2;
        k = 7;
        happyString = solution.getHappyString(n, k);
        System.out.println(happyString);
        Assert.assertEquals("", happyString);

        System.out.println(Integer.toBinaryString(99));

        n = 10;
        k = 100;
        happyString = solution.getHappyString(n, k);
        System.out.println(happyString);
        Assert.assertEquals("abacbabacb", happyString);
    }
}
