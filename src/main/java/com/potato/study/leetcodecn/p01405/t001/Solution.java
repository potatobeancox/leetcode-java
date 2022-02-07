package com.potato.study.leetcodecn.p01405.t001;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.Assert;

/**
 * 1405. 最长快乐字符串
 *
 * 如果字符串中不含有任何 'aaa'，'bbb' 或 'ccc' 这样的字符串作为子串，那么该字符串就是一个「快乐字符串」。
 *
 * 给你三个整数 a，b ，c，请你返回 任意一个 满足下列全部条件的字符串 s：
 *
 * s 是一个尽可能长的快乐字符串。
 * s 中 最多 有a 个字母 'a'、b 个字母 'b'、c 个字母 'c' 。
 * s 中只含有 'a'、'b' 、'c' 三种字母。
 * 如果不存在这样的字符串 s ，请返回一个空字符串 ""。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：a = 1, b = 1, c = 7
 * 输出："ccaccbcc"
 * 解释："ccbccacc" 也是一种正确答案。
 * 示例 2：
 *
 * 输入：a = 2, b = 2, c = 1
 * 输出："aabbc"
 * 示例 3：
 *
 * 输入：a = 7, b = 1, c = 0
 * 输出："aabaa"
 * 解释：这是该测试用例的唯一正确答案。
 *  
 *
 * 提示：
 *
 * 0 <= a, b, c <= 100
 * a + b + c > 0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-happy-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode-cn.com/problems/longest-happy-string/solution/zui-chang-kuai-le-zi-fu-chuan-by-leetcod-5nde/
     * @param a
     * @param b
     * @param c
     * @return
     */
    public String longestDiverseString(int a, int b, int c) {
        // 使用堆 存储 按照 当前字符出现多少 倒序遍历 选择 每次选择 最多的
        Count[] countArray = new Count[3];
        countArray[0] = new Count('a', a);
        countArray[1] = new Count('b', b);
        countArray[2] = new Count('c', c);
        // 一直排序直到找到不能用的字符串了
        StringBuilder builder = new StringBuilder();
        while (true) {
            // 排序
            Arrays.sort(countArray, new Comparator<Count>() {
                @Override
                public int compare(Count o1, Count o2) {
                    return Integer.compare(o2.count, o1.count);
                }
            });
            // 一次选择最多的
            boolean hasSelect = false;
            for (int i = 0; i < 3; i++) {
                Count count = countArray[i];
                // 没有了
                if (count.count <= 0) {
                    continue;
                }
                // 如果连续出现了2次 那么就换下一个
                if (builder.length() >= 2
                        && builder.charAt(builder.length() - 1) == count.ch
                        && builder.charAt(builder.length() - 2) == count.ch) {
                    continue;
                }
                // 就用这个
                hasSelect = true;
                builder.append(count.ch);
                count.count--;
                break;
            }
            if (a + b + c == builder.length()) {
                break;
            }
            // 没有课选的的了直接结束吧
            if (!hasSelect) {
                break;
            }
        }
        return builder.toString();
    }

    class Count {
        public char ch;
        public int count;

        public Count(char ch, int count) {
            this.ch = ch;
            this.count = count;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int a = 1;
        int b = 1;
        int c = 7;
        String s = solution.longestDiverseString(a, b, c);
        System.out.println(s);
        Assert.assertEquals("ccaccbcc", s);
    }
}
