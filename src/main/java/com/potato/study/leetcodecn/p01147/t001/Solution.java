package com.potato.study.leetcodecn.p01147.t001;


import com.potato.study.leetcode.domain.TreeNode;
import org.junit.Assert;

/**
 * 1147. 段式回文
 *
 * 你会得到一个字符串 text 。你应该把它分成 k 个子字符串 (subtext1, subtext2，…， subtextk) ，要求满足:
 *
 * subtexti 是 非空 字符串
 * 所有子字符串的连接等于 text ( 即subtext1 + subtext2 + ... + subtextk == text )
 * 对于所有 i 的有效值( 即 1 <= i <= k ) ，subtexti == subtextk - i + 1 均成立
 * 返回k可能最大值。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：text = "ghiabcdefhelloadamhelloabcdefghi"
 * 输出：7
 * 解释：我们可以把字符串拆分成 "(ghi)(abcdef)(hello)(adam)(hello)(abcdef)(ghi)"。
 * 示例 2：
 *
 * 输入：text = "merchant"
 * 输出：1
 * 解释：我们可以把字符串拆分成 "(merchant)"。
 * 示例 3：
 *
 * 输入：text = "antaprezatepzapreanta"
 * 输出：11
 * 解释：我们可以把字符串拆分成 "(a)(nt)(a)(pre)(za)(tpe)(za)(pre)(a)(nt)(a)"。
 *  
 *
 * 提示：
 *
 * 1 <= text.length <= 1000
 * text 仅由小写英文字符组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/longest-chunked-palindrome-decomposition
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int longestDecomposition(String text) {
        // 终止条件 s位空
        if (text.isEmpty()) {
            return 0;
        }
        // s 不空 枚举每个前缀大小 1 只要找到 就往里走
        for (int i = 1; i <= text.length() / 2; i++) {
            String left = text.substring(0, i);
            String right = text.substring(text.length() - i);

            if (left.equals(right)) {
                return 2 + longestDecomposition(text.substring(i, text.length() - i));
            }
        }
        // 都枚举不到返回
        return 1;
    }
}
