package com.potato.study.leetcodecn.p02266.t001;

import org.junit.Assert;

import com.potato.study.leetcode.domain.TreeNode;
import com.potato.study.leetcode.util.TreeNodeUtil;

/**
 * 2266. 统计打字方案数
 *
 * Alice 在给 Bob 用手机打字。数字到字母的 对应 如下图所示。
 *
 *
 *
 * 为了 打出 一个字母，Alice 需要 按 对应字母 i 次，i 是该字母在这个按键上所处的位置。
 *
 * 比方说，为了按出字母 's' ，Alice 需要按 '7' 四次。类似的， Alice 需要按 '5' 两次得到字母  'k' 。
 * 注意，数字 '0' 和 '1' 不映射到任何字母，所以 Alice 不 使用它们。
 * 但是，由于传输的错误，Bob 没有收到 Alice 打字的字母信息，反而收到了 按键的字符串信息 。
 *
 * 比方说，Alice 发出的信息为 "bob" ，Bob 将收到字符串 "2266622" 。
 * 给你一个字符串 pressedKeys ，表示 Bob 收到的字符串，请你返回 Alice 总共可能发出多少种文字信息 。
 *
 * 由于答案可能很大，将它对 109 + 7 取余 后返回。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：pressedKeys = "22233"
 * 输出：8
 * 解释：
 * Alice 可能发出的文字信息包括：
 * "aaadd", "abdd", "badd", "cdd", "aaae", "abe", "bae" 和 "ce" 。
 * 由于总共有 8 种可能的信息，所以我们返回 8 。
 * 示例 2：
 *
 * 输入：pressedKeys = "222222222222222222222222222222222222"
 * 输出：82876089
 * 解释：
 * 总共有 2082876103 种 Alice 可能发出的文字信息。
 * 由于我们需要将答案对 109 + 7 取余，所以我们返回 2082876103 % (109 + 7) = 82876089 。
 *  
 *
 * 提示：
 *
 * 1 <= pressedKeys.length <= 105
 * pressedKeys 只包含数字 '2' 到 '9' 。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/count-number-of-texts
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode.cn/problems/count-number-of-texts/solution/dao-ge-shua-ti-by-lcfgrn-z2cp/
     * @param pressedKeys
     * @return
     */
    public int countTexts(String pressedKeys) {
        // dp i 以第i 个字符为结尾的可能数量
        int n = pressedKeys.length();
        int[] dp = new int[n];
        dp[0] = 1;
        char[] chars = pressedKeys.toCharArray();
        int mod = 1_000_000_000 + 7;
        for (int i = 1; i < pressedKeys.length(); i++) {
            if (chars[i] != chars[i-1]) {
                dp[i] = dp[i-1];
                continue;
            }
            // i-1 == i
            if (i == 1) {
                dp[i] = dp[i-1] + 1;
                dp[i] %= mod;
                continue;
            }
            if (i == 2) {
                if (chars[i-2] != chars[i]) {
                    dp[i] = dp[i-1] + dp[i-2];
                } else {
                    // 都相同
                }
            }


        }
        // 如果是 非 79 dpi = dp i-1 + dpi-2 + dpi-3
        // 如果是 79 dpi = dp i-1 + dpi-2 + dpi-3 + dp i-4
        return dp[n-1];
    }
}
