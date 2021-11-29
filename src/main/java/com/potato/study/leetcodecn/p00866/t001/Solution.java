package com.potato.study.leetcodecn.p00866.t001;

import com.potato.study.leetcode.domain.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 866. 回文素数
 *
 * 求出大于或等于 N 的最小回文素数。

 回顾一下，如果一个数大于 1，且其因数只有 1 和它自身，那么这个数是素数。

 例如，2，3，5，7，11 以及 13 是素数。

 回顾一下，如果一个数从左往右读与从右往左读是一样的，那么这个数是回文数。

 例如，12321 是回文数。

  

 示例 1：

 输入：6
 输出：7
 示例 2：

 输入：8
 输出：11
 示例 3：

 输入：13
 输出：101
  

 提示：

 1 <= N <= 10^8
 答案肯定存在，且小于 2 * 10^8。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/prime-palindrome
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int primePalindrome(int n) {
        if (n < 2) {
            return 2;
        }
        for (int i = n; i < 1_000_000_000; i++) {
            String word = String.valueOf(i);

            if (word.length() == 8) {
                i = 9999_9999;
                continue;
            }

            if (isPalindrome(word) && isPrime(i)) {
                return i;
            }
        }
        return -1;

    }

    private boolean isPalindrome(String word) {
        int left = 0;
        int right = word.length() - 1;
        char[] chars = word.toCharArray();
        while (left < right) {

            if (chars[left] != chars[right]) {
                return false;
            }

            left++;
            right--;
        }
        return true;
    }


    private boolean isPrime(int num) {

        String s = String.valueOf(num);
        if (s.length() == 8) {
            return false;
        }

        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }



}
