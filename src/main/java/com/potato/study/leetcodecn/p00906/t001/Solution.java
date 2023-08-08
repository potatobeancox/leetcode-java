package com.potato.study.leetcodecn.p00906.t001;

import org.junit.Assert;

import java.util.Arrays;

/**
 * 906. 超级回文数
 *
 * 如果一个正整数自身是回文数，而且它也是一个回文数的平方，那么我们称这个数为超级回文数。
 *
 * 现在，给定两个正整数 L 和 R （以字符串形式表示），返回包含在范围 [L, R] 中的超级回文数的数目。
 *
 *  
 *
 * 示例：
 *
 * 输入：L = "4", R = "1000"
 * 输出：4
 * 解释：
 * 4，9，121，以及 484 是超级回文数。
 * 注意 676 不是一个超级回文数： 26 * 26 = 676，但是 26 不是回文数。
 *  
 *
 * 提示：
 *
 * 1 <= len(L) <= 18
 * 1 <= len(R) <= 18
 * L 和 R 是表示 [1, 10^18) 范围的整数的字符串。
 * int(L) <= int(R)
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/super-palindromes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {



    public int superpalindromesInRange(String left, String right) {
        // 从1-1_00000 生成 小的回文  生成 double len or double-1 分别计算平方是不是回文
        long leftLimit = Long.parseLong(left);
        long rightLimit = Long.parseLong(right);

        int totalCount = 0;
        for (int i = 1; i < 1_000_00; i++) {
            // 完全翻倍生成
            StringBuilder builder = new StringBuilder();
            builder.append(i);
            String reverse = builder.reverse().toString();
            String targetStr1 = i + reverse.substring(1);
            String targetStr2 = i + reverse;
            long target1 = Long.parseLong(targetStr1);
            long target = target1 * target1;
            // 小的比 right大就没必要了
            if (target > rightLimit) {
                break;
            }
            // 判断生成的数字是不是在 范围之内，判断生成的数字是不是回文
            if (leftLimit <= target && target <= rightLimit && isPalindrome(target)) {
                totalCount++;
            }
            long target2 = Long.parseLong(targetStr2);
            target = target2 * target2;
            // 判断生成的数字是不是在 范围之内，判断生成的数字是不是回文
            if (leftLimit <= target && target <= rightLimit && isPalindrome(target)) {
                totalCount++;
            }
        }
        return totalCount;
    }

    private boolean isPalindrome(long val) {
        long tmp = val;
        long target = 0;
        while (tmp != 0) {
            target *= 10;
            target += (tmp % 10);
            tmp /= 10;
        }
        return target == val;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.superpalindromesInRange("4", "1000");
        System.out.println(i);
        Assert.assertEquals(i, 4);
    }

}
