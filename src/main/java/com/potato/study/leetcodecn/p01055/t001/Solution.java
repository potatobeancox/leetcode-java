package com.potato.study.leetcodecn.p01055.t001;

import java.util.PriorityQueue;

import org.junit.Assert;

/**
 * 1055. 形成字符串的最短路径
 *
 * 对于任何字符串，我们可以通过删除其中一些字符（也可能不删除）来构造该字符串的 子序列 。(例如，“ace” 是 “abcde” 的子序列，而 “aec” 不是)。
 *
 * 给定源字符串 source 和目标字符串 target，返回 源字符串 source 中能通过串联形成目标字符串 target 的 子序列 的最小数量 。如果无法通过串联源字符串中的子序列来构造目标字符串，则返回 -1。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：source = "abc", target = "abcbc"
 * 输出：2
 * 解释：目标字符串 "abcbc" 可以由 "abc" 和 "bc" 形成，它们都是源字符串 "abc" 的子序列。
 * 示例 2：
 *
 * 输入：source = "abc", target = "acdbc"
 * 输出：-1
 * 解释：由于目标字符串中包含字符 "d"，所以无法由源字符串的子序列构建目标字符串。
 * 示例 3：
 *
 * 输入：source = "xyz", target = "xzyxz"
 * 输出：3
 * 解释：目标字符串可以按如下方式构建： "xz" + "y" + "xz"。
 *  
 *
 * 提示：
 *
 * 1 <= source.length, target.length <= 1000
 * source 和 target 仅包含英文小写字母。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/shortest-way-to-form-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {













    public int shortestWay(String source, String target) {
        int useCount = 0;
        // 从每个 字母开始 往后 按照 source 能够匹配的最多情况进行匹配
        int targetIndex = 0;
        while (targetIndex < target.length()) {
            int sourceIndex = 0;
            // 依次比较 全部没有匹配中
            boolean isAllMisMatch = true;
            while (targetIndex < target.length()
                    && sourceIndex < source.length()) {
                // 相等
                if (target.charAt(targetIndex) == source.charAt(sourceIndex)) {
                    targetIndex++;
                    sourceIndex++;
                    isAllMisMatch = false;
                } else {
                    // 不相同 往后移动source
                    sourceIndex++;
                }
            }
            // 如果 这困一个 sourece 位置都没有用 那么就是 匹配不上了
            if (isAllMisMatch) {
                return -1;
            }
            // 用完了一个单词
            useCount++;
        }
        // 过程中 统计 最大个数
        return useCount;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String source = "abc";
        String target = "abcbc";
        int i = solution.shortestWay(source, target);
        System.out.println(i);
        Assert.assertEquals(2, i);


        source = "abc";
        target = "acdbc";
        i = solution.shortestWay(source, target);
        System.out.println(i);
        Assert.assertEquals(-1, i);
    }
}
