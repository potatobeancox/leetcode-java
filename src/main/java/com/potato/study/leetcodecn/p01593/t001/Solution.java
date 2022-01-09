package com.potato.study.leetcodecn.p01593.t001;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 1593. 拆分字符串使唯一子字符串的数目最大
 *
 * 给你一个字符串 s ，请你拆分该字符串，并返回拆分后唯一子字符串的最大数目。

 字符串 s 拆分后可以得到若干 非空子字符串 ，这些子字符串连接后应当能够还原为原字符串。但是拆分出来的每个子字符串都必须是 唯一的 。

 注意：子字符串 是字符串中的一个连续字符序列。

  

 示例 1：

 输入：s = "ababccc"
 输出：5
 解释：一种最大拆分方法为 ['a', 'b', 'ab', 'c', 'cc'] 。
 像 ['a', 'b', 'a', 'b', 'c', 'cc'] 这样拆分不满足题目要求，因为其中的 'a' 和 'b' 都出现了不止一次。

 示例 2：

 输入：s = "aba"
 输出：2
 解释：一种最大拆分方法为 ['a', 'ba'] 。

 示例 3：

 输入：s = "aa"
 输出：1
 解释：无法进一步拆分字符串。
  

 提示：

 1 <= s.length <= 16

 s 仅包含小写英文字母

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/split-a-string-into-the-max-number-of-unique-substrings
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    private int max;


    public int maxUniqueSplit(String s) {
        // dfs 拆分 使用 max 记录最大值  记录 当前 开始 搞的位置 按照 当前位置 往后 开始 找 记录 过程过程中的 set
        Set<String> hasSeen = new HashSet<>();
        this.max = 0;
        dfs(s, 0, hasSeen, 0);
        return this.max;
    }

    private void dfs(String s, int startIndex, Set<String> hasSeen, int currentCount) {
        // 找到最后一个位置 开始 比较最多的
        if (startIndex == s.length()) {
            this.max = Math.max(max, currentCount);
            return;
        }
        // 遍历 startIndex 开始的每个终止位置 ，对于每个终止位置 判断是够出现过，如果没有出现过 放入 set 记录 找
        for (int i = startIndex + 1; i <= s.length(); i++) {
            String currentWord = s.substring(startIndex, i);
            if (hasSeen.contains(currentWord)) {
                continue;
            }
            Set<String> nextHasSeen = new HashSet<>(hasSeen);
            nextHasSeen.add(currentWord);
            dfs(s, i, nextHasSeen, currentCount + 1);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "ababccc";
        int i = solution.maxUniqueSplit(s);
        System.out.println(i);
        Assert.assertEquals(5, i);


        s = "aba";
        i = solution.maxUniqueSplit(s);
        System.out.println(i);
        Assert.assertEquals(2, i);
    }

}
