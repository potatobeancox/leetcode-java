package com.potato.study.leetcodecn.p01461.t001;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;

/**
 * 1461. 检查一个字符串是否包含所有长度为 K 的二进制子串
 *
 * 给你一个二进制字符串 s 和一个整数 k 。
 *
 * 如果所有长度为 k 的二进制字符串都是 s 的子串，请返回 true ，否则请返回 false 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "00110110", k = 2
 * 输出：true
 * 解释：长度为 2 的二进制串包括 "00"，"01"，"10" 和 "11"。它们分别是 s 中下标为 0，1，3，2 开始的长度为 2 的子串。
 * 示例 2：
 *
 * 输入：s = "00110", k = 2
 * 输出：true
 * 示例 3：
 *
 * 输入：s = "0110", k = 1
 * 输出：true
 * 解释：长度为 1 的二进制串包括 "0" 和 "1"，显然它们都是 s 的子串。
 * 示例 4：
 *
 * 输入：s = "0110", k = 2
 * 输出：false
 * 解释：长度为 2 的二进制串 "00" 没有出现在 s 中。
 * 示例 5：
 *
 * 输入：s = "0000000001011100", k = 4
 * 输出：false
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 5 * 105
 * s[i] 不是'0' 就是 '1'
 * 1 <= k <= 20
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/check-if-a-string-contains-all-binary-codes-of-size-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 1461
    public boolean hasAllCodes(String s, int k) {
        // 用一个 set 记录 出现的字符串 k 个长度 最后 判断下 是否有 2k大小
        Set<String> set = new HashSet<>();
        for (int i = 0; i + k <= s.length(); i++) {
            String substring = s.substring(i, i + k);
            set.add(substring);
        }
        return set.size() == (1 << k);
    }

    public static void main(String[] args) {
        String s = "00110110";
        int k = 2;
        Solution solution = new Solution();
        boolean b = solution.hasAllCodes(s, k);
        System.out.println(b);
        Assert.assertEquals(true, b);
    }
}
