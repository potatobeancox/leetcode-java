package com.potato.study.leetcodecn.p02595.t001;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 2595. 奇偶位数

 给你一个 正 整数 n 。

 用 even 表示在 n 的二进制形式（下标从 0 开始）中值为 1 的偶数下标的个数。

 用 odd 表示在 n 的二进制形式（下标从 0 开始）中值为 1 的奇数下标的个数。

 返回整数数组 answer ，其中 answer = [even, odd] 。

  

 示例 1：

 输入：n = 17
 输出：[2,0]
 解释：17 的二进制形式是 10001 。
 下标 0 和 下标 4 对应的值为 1 。
 共有 2 个偶数下标，0 个奇数下标。
 示例 2：

 输入：n = 2
 输出：[0,1]
 解释：2 的二进制形式是 10 。
 下标 1 对应的值为 1 。
 共有 0 个偶数下标，1 个奇数下标。
  

 提示：

 1 <= n <= 1000

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/number-of-even-and-odd-bits
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 2595
    public int[] evenOddBit(int n) {
        String s = Integer.toBinaryString(n);
        int even = 0;
        int odd = 0;
        for (int i = 0; i < s.length(); i++) {
            // 计算下标对应的位置
            int index = s.length() - 1 - i;
            if ('0' == s.charAt(index)) {
                continue;
            }
            // 按照 i的奇偶性计数
            if (i % 2 == 0) {
                even++;
            } else {
                odd++;
            }
        }
        return new int[] {even, odd};
    }

}
