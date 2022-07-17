package com.potato.study.leetcodecn.p01134.t001;


/**
 * 1134. 阿姆斯特朗数
 *
 * 给你一个整数 n ，让你来判定他是否是 阿姆斯特朗数，是则返回 true，不是则返回 false。

 假设存在一个 k 位数 n ，其每一位上的数字的 k 次幂的总和也是 n ，那么这个数是阿姆斯特朗数 。

  

 示例 1：

 输入：n = 153
 输出：true
 示例：
 153 是一个 3 位数，且 153 = 13 + 53 + 33。
 示例 2：

 输入：n = 123
 输出：false
 解释：123 是一个 3 位数，且 123 != 13 + 23 + 33 = 36。
  

 提示：

 1 <= n <= 108

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/armstrong-number
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public boolean isArmstrong(int n) {
        int tmp = n;
        int total = 0;
        String s = String.valueOf(n);
        int bitCount = s.length();
        while (tmp > 0) {
            int i = tmp % 10;
            total += Math.pow(i, bitCount);
            tmp /= 10;
        }
        return total == n;
    }
}
