package com.potato.study.leetcodecn.other.Interview.p0016.p0018;


import org.junit.Assert;

/**
 * 面试题 16.18. 模式匹配
 *
 * 你有两个字符串，即pattern和value。 pattern字符串由字母"a"和"b"组成，用于描述字符串中的模式。例如，字符串"catcatgocatgo"匹配模式"aabab"（其中"cat"是"a"，"go"是"b"），该字符串也匹配像"a"、"ab"和"b"这样的模式。但需注意"a"和"b"不能同时表示相同的字符串。编写一个方法判断value字符串是否匹配pattern字符串。

 示例 1：

 输入： pattern = "abba", value = "dogcatcatdog"
 输出： true
 示例 2：

 输入： pattern = "abba", value = "dogcatcatfish"
 输出： false
 示例 3：

 输入： pattern = "aaaa", value = "dogcatcatdog"
 输出： false
 示例 4：

 输入： pattern = "abba", value = "dogdogdogdog"
 输出： true
 解释： "a"="dogdog",b=""，反之也符合规则
 提示：

 1 <= len(pattern) <= 1000
 0 <= len(value) <= 1000
 你可以假设pattern只包含字母"a"和"b"，value仅包含小写字母。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/pattern-matching-lcci
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public boolean patternMatching(String pattern, String value) {
        // 0 表示 a对应单词 1表示b对应单词
        String[] select = new String[2];
        // dfs
        int index1 = 0;
        int index2 = 0;
        return dfsPatternMatching(pattern, value, index1, index2, select);
    }

    /**
     *
     * @param pattern
     * @param value
     * @param index1
     * @param index2
     * @param select
     * @return
     */
    private boolean dfsPatternMatching(String pattern, String value,
                                       int index1, int index2, String[] select) {
        // 到了最后一个节点 都是
        if (index1 == pattern.length() && index2 == value.length()) {
            return true;
        } else if (index1 == pattern.length() && index2 != value.length()) {
            return false;
        }
        // 都没有到终点 看看
        char pat = pattern.charAt(index1);
        int indexPat = pat - 'a';
        // 是不是已经存在了
        if (select[indexPat] == null) {
            // 没有的话 就需要从 index2 中 分别找一下了
            for (int i = index2-1; i < value.length(); i++) {
                String selectPattern = value.substring(index2, i + 1);
                // 如果当前 selectPattern 重复了
                if (select[1-indexPat] != null && select[1-indexPat].equals(selectPattern)) {
                    continue;
                }
                select[indexPat] = selectPattern;
                // dfs
                boolean res = dfsPatternMatching(pattern, value, index1 + 1, i + 1, select);
                if (res) {
                    return true;
                }
                select[indexPat] = null;
            }

        } else {
            // 已经有了 看看之后是不是相同
            String patternString = select[indexPat];
            if (index2 + patternString.length() > value.length()) {
                // index2 已经匹配不了
                return false;
            }
            String substring = value.substring(index2, index2 + patternString.length());
            if (!substring.equals(patternString)) {
                // 跟之前的不一致
                return false;
            }
            // 一致 往后dfs看看
            return dfsPatternMatching(pattern, value, index1 + 1,
                    index2 + patternString.length(), select);
        }
        return false;
    }

    public static void main(String[] args) {

        Solution solution = new Solution();
        String pattern = "abba";
        String value = "dogdogdogdog";
        boolean b = solution.patternMatching(pattern, value);
        System.out.println(b);
        Assert.assertEquals(true, b);


        pattern = "a";
        value = "";
        b = solution.patternMatching(pattern, value);
        System.out.println(b);
        Assert.assertEquals(true, b);


        pattern = "aaaaab";
        value = "xahnxdxyaahnxdxyaahnxdxyaahnxdxyaauxuhuo";
        b = solution.patternMatching(pattern, value);
        System.out.println(b);
        Assert.assertEquals(true, b);
    }
}
