package com.potato.study.leetcodecn.p01234.t001;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1234. 替换子串得到平衡字符串
 *
 * 有一个只含有 'Q', 'W', 'E', 'R' 四种字符，且长度为 n 的字符串。

 假如在该字符串中，这四个字符都恰好出现 n/4 次，那么它就是一个「平衡字符串」。

  

 给你一个这样的字符串 s，请通过「替换一个子串」的方式，使原字符串 s 变成一个「平衡字符串」。

 你可以用和「待替换子串」长度相同的 任何 其他字符串来完成替换。

 请返回待替换子串的最小可能长度。

 如果原字符串自身就是一个平衡字符串，则返回 0。

  

 示例 1：

 输入：s = "QWER"
 输出：0
 解释：s 已经是平衡的了。
 示例 2：

 输入：s = "QQWE"
 输出：1
 解释：我们需要把一个 'Q' 替换成 'R'，这样得到的 "RQWE" (或 "QRWE") 是平衡的。
 示例 3：

 输入：s = "QQQW"
 输出：2
 解释：我们可以把前面的 "QQ" 替换成 "ER"。
 示例 4：

 输入：s = "QQQQ"
 输出：3
 解释：我们可以替换后 3 个 'Q'，使 s = "QWER"。
  

 提示：

 1 <= s.length <= 10^5
 s.length 是 4 的倍数
 s 中只含有 'Q', 'W', 'E', 'R' 四种字符

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/replace-the-substring-for-balanced-string
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 1234
     * @param s
     * @return
     */
    public int balancedString(String s) {
        // 统计出现次数 每个 每次 滑动窗口 记录最小值 'Q', 'W', 'E', 'R'
        int[] count = new int[4];
        char[] chars = s.toCharArray();
        for (char ch : chars) {
            if (ch == 'Q') {
                count[0]++;
            } else if (ch == 'W') {
                count[1]++;
            } else if (ch == 'E') {
                count[2]++;
            } else if (ch == 'R') {
                count[3]++;
            }
        }
        int limit = s.length() / 4;
        int[] miss = new int[4];
        for (int i = 0; i < 4; i++) {
            if (count[i] > limit) {
                miss[i] = count[i] - limit;
            }
        }
        // 滑动窗口记录最小值
        if (checkMiss(miss)) {
            return 0;
        }
        int minLen = s.length();
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            char ch = chars[right];
            if (ch == 'Q') {
                miss[0]--;
            } else if (ch == 'W') {
                miss[1]--;
            } else if (ch == 'E') {
                miss[2]--;
            } else if (ch == 'R') {
                miss[3]--;
            }
            if (checkMiss(miss)) {
                minLen = Math.min(minLen, right - left + 1);
                ch = chars[left];
                if (ch == 'Q') {
                    miss[0]++;
                } else if (ch == 'W') {
                    miss[1]++;
                } else if (ch == 'E') {
                    miss[2]++;
                } else if (ch == 'R') {
                    miss[3]++;
                }
                left++;
            }
        }
        return minLen;
    }

    private boolean checkMiss(int[] miss) {
        return miss[0] == 0 && miss[1] == 0
                && miss[2] == 0 && miss[3] == 0;
    }


}
