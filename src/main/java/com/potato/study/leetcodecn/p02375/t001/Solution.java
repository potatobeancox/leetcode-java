package com.potato.study.leetcodecn.p02375.t001;

import org.junit.Assert;

import java.util.HashSet;
import java.util.Set;

/**
 * 2375. 根据模式串构造最小数字
 *
 * 给你下标从 0 开始、长度为 n 的字符串 pattern ，它包含两种字符，'I' 表示 上升 ，'D' 表示 下降 。
 *
 * 你需要构造一个下标从 0 开始长度为 n + 1 的字符串，且它要满足以下条件：
 *
 * num 包含数字 '1' 到 '9' ，其中每个数字 至多 使用一次。
 * 如果 pattern[i] == 'I' ，那么 num[i] < num[i + 1] 。
 * 如果 pattern[i] == 'D' ，那么 num[i] > num[i + 1] 。
 * 请你返回满足上述条件字典序 最小 的字符串 num。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：pattern = "IIIDIDDD"
 * 输出："123549876"
 * 解释：
 * 下标 0 ，1 ，2 和 4 处，我们需要使 num[i] < num[i+1] 。
 * 下标 3 ，5 ，6 和 7 处，我们需要使 num[i] > num[i+1] 。
 * 一些可能的 num 的值为 "245639871" ，"135749862" 和 "123849765" 。
 * "123549876" 是满足条件最小的数字。
 * 注意，"123414321" 不是可行解因为数字 '1' 使用次数超过 1 次。
 * 示例 2：
 *
 * 输入：pattern = "DDD"
 * 输出："4321"
 * 解释：
 * 一些可能的 num 的值为 "9876" ，"7321" 和 "8742" 。
 * "4321" 是满足条件最小的数字。
 *  
 *
 * 提示：
 *
 * 1 <= pattern.length <= 8
 * pattern 只包含字符 'I' 和 'D' 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/construct-smallest-number-from-di-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public String smallestNumber(String pattern) {
        // 先遍历 i 后遍历 d 每次选择最小的进行使用
        int cur = 1;
        int length = pattern.length();
        int index = 0;
        StringBuilder builder = new StringBuilder();
        while (index < length) {
            // 找i最终能有多少个
            int count1 = 0;
            while (index < length && pattern.charAt(index) == 'I') {
                count1++;
                index++;
            }
            // 找了了 i
            if (count1 > 0) {
                int limit = count1 - 1;
                if (builder.length() == 0) {
                    limit++;
                }
                // 计算当前能用到多少
                for (int i = 0; i < limit; i++) {
                    builder.append(cur);
                    cur++;
                }
            }
            // 找d
            int count2 = 0;
            while (index < length && pattern.charAt(index) == 'D') {
                index++;
                count2++;
            }
            // 找了了 i
            if (count2 >= 0) {
                for (int i = 0; i <= count2; i++) {
                    builder.append(cur + count2 - i);
                }
                cur += (count2 + 1);
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = solution.smallestNumber("IIIDIDDD");
        System.out.println(s);
        Assert.assertEquals("123549876", s);

    }


}
