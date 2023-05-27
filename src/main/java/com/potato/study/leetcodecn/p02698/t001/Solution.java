package com.potato.study.leetcodecn.p02698.t001;

import org.junit.Assert;

/**
 *
 * 2698. 求一个整数的惩罚数
 *
 * 给你一个正整数 n ，请你返回 n 的 惩罚数 。
 *
 * n 的 惩罚数 定义为所有满足以下条件 i 的数的平方和：
 *
 * 1 <= i <= n
 * i * i 的十进制表示的字符串可以分割成若干连续子字符串，且这些子字符串对应的整数值之和等于 i 。
 *  
 *
 * 示例 1：
 *
 * 输入：n = 10
 * 输出：182
 * 解释：总共有 3 个整数 i 满足要求：
 * - 1 ，因为 1 * 1 = 1
 * - 9 ，因为 9 * 9 = 81 ，且 81 可以分割成 8 + 1 。
 * - 10 ，因为 10 * 10 = 100 ，且 100 可以分割成 10 + 0 。
 * 因此，10 的惩罚数为 1 + 81 + 100 = 182
 * 示例 2：
 *
 * 输入：n = 37
 * 输出：1478
 * 解释：总共有 4 个整数 i 满足要求：
 * - 1 ，因为 1 * 1 = 1
 * - 9 ，因为 9 * 9 = 81 ，且 81 可以分割成 8 + 1 。
 * - 10 ，因为 10 * 10 = 100 ，且 100 可以分割成 10 + 0 。
 * - 36 ，因为 36 * 36 = 1296 ，且 1296 可以分割成 1 + 29 + 6 。
 * 因此，37 的惩罚数为 1 + 81 + 100 + 1296 = 1478
 *  
 *
 * 提示：
 *
 * 1 <= n <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-the-punishment-number-of-an-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {




    // 2698
    public int punishmentNumber(int n) {
        // 枚举从 [1-n] 每个值 求平方看看 平方能不能分割组成
        int res = 0;
        for (int i = 1; i <= n; i++) {
            if (isPunishmentNumber(i)) {
                res += i * i;
            }
        }
        return res;
    }


    private boolean isPunishmentNumber(long target) {
        long temp = target * target;
        if (temp == target) {
            return true;
        }
        String s = String.valueOf(temp);
        // 按照每个位置分割
        for (int i = 1; i < s.length(); i++) {
            String sub1 = s.substring(0, i);
            String sub2 = s.substring(i);
            if (target == Long.parseLong(sub1) + Long.parseLong(sub2)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.punishmentNumber(10);
        System.out.println(i);
        Assert.assertEquals(182, i);


        i = solution.punishmentNumber(37);
        System.out.println(i);
        Assert.assertEquals(182, i);
    }

}
