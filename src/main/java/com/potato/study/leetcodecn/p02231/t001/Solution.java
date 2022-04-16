package com.potato.study.leetcodecn.p02231.t001;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 2231. 按奇偶性交换后的最大数字
 *
 * 给你一个正整数 num 。你可以交换 num 中 奇偶性 相同的任意两位数字（即，都是奇数或者偶数）。

 返回交换 任意 次之后 num 的 最大 可能值。

  

 示例 1：

 输入：num = 1234
 输出：3412
 解释：交换数字 3 和数字 1 ，结果得到 3214 。
 交换数字 2 和数字 4 ，结果得到 3412 。
 注意，可能存在其他交换序列，但是可以证明 3412 是最大可能值。
 注意，不能交换数字 4 和数字 1 ，因为它们奇偶性不同。
 示例 2：

 输入：num = 65875
 输出：87655
 解释：交换数字 8 和数字 6 ，结果得到 85675 。
 交换数字 5 和数字 7 ，结果得到 87655 。
 注意，可能存在其他交换序列，但是可以证明 87655 是最大可能值。
  

 提示：

 1 <= num <= 109

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/largest-number-after-digit-swaps-by-parity
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int largestInteger(int num) {
        if (num == 0) {
            return 0;
        }
        PriorityQueue<Integer> odd = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> even = new PriorityQueue<>(Comparator.reverseOrder());
        char[] chars = String.valueOf(num).toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int bit = chars[i] - '0';
            if (bit % 2 == 0) {
                even.add(bit);
            } else {
                odd.add(bit);
            }
        }

        for (int i = 0; i < chars.length; i++) {
            int bit = chars[i] - '0';
            if (bit % 2 == 0) {
                chars[i] = (char) ('0' + even.poll());
            } else {
                chars[i] = (char) ('0' + odd.poll());
            }
        }
        int i = Integer.parseInt(new String(chars));
        return i;

    }
}
