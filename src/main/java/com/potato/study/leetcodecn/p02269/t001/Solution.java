package com.potato.study.leetcodecn.p02269.t001;

import com.potato.study.leetcode.domain.TreeNode;
import com.potato.study.leetcode.util.TreeNodeUtil;
import org.junit.Assert;

/**
 * 2269. 找到一个数字的 K 美丽值
 *
 * 一个整数 num 的 k 美丽值定义为 num 中符合以下条件的 子字符串 数目：

 子字符串长度为 k 。
 子字符串能整除 num 。
 给你整数 num 和 k ，请你返回 num 的 k 美丽值。

 注意：

 允许有 前缀 0 。
 0 不能整除任何值。
 一个 子字符串 是一个字符串里的连续一段字符序列。

  

 示例 1：

 输入：num = 240, k = 2
 输出：2
 解释：以下是 num 里长度为 k 的子字符串：
 - "240" 中的 "24" ：24 能整除 240 。
 - "240" 中的 "40" ：40 能整除 240 。
 所以，k 美丽值为 2 。
 示例 2：

 输入：num = 430043, k = 2
 输出：2
 解释：以下是 num 里长度为 k 的子字符串：
 - "430043" 中的 "43" ：43 能整除 430043 。
 - "430043" 中的 "30" ：30 不能整除 430043 。
 - "430043" 中的 "00" ：0 不能整除 430043 。
 - "430043" 中的 "04" ：4 不能整除 430043 。
 - "430043" 中的 "43" ：43 能整除 430043 。
 所以，k 美丽值为 2 。
  

 提示：

 1 <= num <= 109
 1 <= k <= num.length （将 num 视为字符串）

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/find-the-k-beauty-of-a-number
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int divisorSubstrings(int num, int k) {
        // num 转换成字符串 求余数
        String s = String.valueOf(num);
        int count = 0;
        for (int i = 0; i <= s.length() - k; i++) {
            String substring = s.substring(i, i + k);
            int sub = Integer.parseInt(substring);
            if (sub != 0 && num % sub == 0) {
                count++;
            }
        }
        return count;
    }
}
