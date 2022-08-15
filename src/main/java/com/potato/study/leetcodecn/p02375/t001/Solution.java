package com.potato.study.leetcodecn.p02375.t001;

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
        // 遍历 pattern 每次找到 I开头 D结尾的组
        int index = 0;
        StringBuilder builder = new StringBuilder();
        int startNum = 1;
        while (index < pattern.length()) {
            int iCount = 0;
            while (index < pattern.length() && pattern.charAt(index) == 'I') {
                iCount++;
                index++;
            }
            int dCount = 0;
            while (index < pattern.length() && pattern.charAt(index) == 'D') {
                dCount++;
                index++;
            }
            // 将最小的数字给到i个 对于 d+1 个按照从大到小给出
            for (int i = 0; i < iCount; i++) {
                builder.append(startNum);
                startNum++;
            }
            for (int i = 0; i < dCount + 1; i++) {
                builder.append(startNum + dCount - i);
            }
            startNum += (dCount + 2);
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        solution.smallestNumber();
    }


}
