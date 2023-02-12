package com.potato.study.leetcodecn.p02417.t001;


/**
 * 2417. 最近的公平整数
 *
 * 给定一个 正整数 n。

 如果一个整数 k 中的 偶数 位数与 奇数 位数相等，那么我们称 k 为公平整数。

 返回 大于或等于 n 的 最小 的公平整数。

  

 示例 1:

 输入: n = 2
 输出: 10
 解释: 大于等于 2 的最小的公平整数是 10。
 10是公平整数，因为它的偶数和奇数个数相等 (一个奇数和一个偶数)。
 示例 2:

 输入: n = 403
 输出: 1001
 解释: 大于或等于 403 的最小的公平整数是 1001。
 1001 是公平整数，因为它有相等数量的偶数和奇数 (两个奇数和两个偶数)。
  

 提示:

 1 <= n <= 109

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/closest-fair-integer
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int closestFair(int n) {
        String s = String.valueOf(n);
        int length = s.length();
        if (length % 2 == 1) {
            return getClosestFairEven(length+1);
        }
        // 如果 n有偶数个位置的话
        for (int i = n; i < Math.pow(10, length); i++) {
            if (isFair(i)) {
                return i;
            }
        }
        return getClosestFairEven(length+2);
    }

    private int getClosestFairEven(int i) {
        StringBuilder builder = new StringBuilder();
        builder.append(1);
        for (int j = 0; j < i / 2; j++) {
            builder.append(0);
        }
        for (int j = 0; j < i / 2 - 1; j++) {
            builder.append(1);
        }
        return Integer.parseInt(builder.toString());
    }

    private boolean isFair(int num) {
        if (num == 0) {
            return false;
        }
        int odd = 0;
        int even = 0;
        while (num > 0) {
            int n = num % 10;
            if (n % 2 == 1) {
                odd++;
            } else {
                even++;
            }
            num /= 10;
        }
        return odd == even;
    }

}
