package com.potato.study.leetcodecn.p02571.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.SimpleTimeZone;

/**
 * 2571. 将整数减少到零需要的最少操作数
 给你一个正整数 n ，你可以执行下述操作 任意 次：

 n 加上或减去 2 的某个 幂
 返回使 n 等于 0 需要执行的 最少 操作数。

 如果 x == 2i 且其中 i >= 0 ，则数字 x 是 2 的幂。

  

 示例 1：

 输入：n = 39
 输出：3
 解释：我们可以执行下述操作：
 - n 加上 20 = 1 ，得到 n = 40 。
 - n 减去 23 = 8 ，得到 n = 32 。
 - n 减去 25 = 32 ，得到 n = 0 。
 可以证明使 n 等于 0 需要执行的最少操作数是 3 。
 示例 2：

 输入：n = 54
 输出：3
 解释：我们可以执行下述操作：
 - n 加上 21 = 2 ，得到 n = 56 。
 - n 加上 23 = 8 ，得到 n = 64 。
 - n 减去 26 = 64 ，得到 n = 0 。
 使 n 等于 0 需要执行的最少操作数是 3 。
  

 提示：

 1 <= n <= 105

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/minimum-operations-to-reduce-an-integer-to-0
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {



    // 2571
    public int minOperations(int n) {
        int temp = n;
        int count = 0;
        int i = 1;
        while (temp > 0) {
            int op = (1 << (i-1));
            // 如果当前位置是0 直接continue掉
            if ((op & temp) == 0) {
                i++;
                continue;
            }
            int tempBitCount = Integer.bitCount(temp);
            int targetBitCount = Integer.bitCount(temp + op);
            if (tempBitCount == targetBitCount) {
                temp -= op;
            } else {
                temp += op;
            }
            count++;
            i++;
        }
        return count;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.minOperations(39);
        System.out.println(i);
        Assert.assertEquals(3, i);
    }

}
