package com.potato.study.leetcodecn.p02457.t001;

/**
 * 2457. 美丽整数的最小增量
 *
 * 给你两个正整数 n 和 target 。
 *
 * 如果某个整数每一位上的数字相加小于或等于 target ，则认为这个整数是一个 美丽整数 。
 *
 * 找出并返回满足 n + x 是 美丽整数 的最小非负整数 x 。生成的输入保证总可以使 n 变成一个美丽整数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 16, target = 6
 * 输出：4
 * 解释：最初，n 是 16 ，且其每一位数字的和是 1 + 6 = 7 。在加 4 之后，n 变为 20 且每一位数字的和变成 2 + 0 = 2 。可以证明无法加上一个小于 4 的非负整数使 n 变成一个美丽整数。
 * 示例 2：
 *
 * 输入：n = 467, target = 6
 * 输出：33
 * 解释：最初，n 是 467 ，且其每一位数字的和是 4 + 6 + 7 = 17 。在加 33 之后，n 变为 500 且每一位数字的和变成 5 + 0 + 0 = 5 。可以证明无法加上一个小于 33 的非负整数使 n 变成一个美丽整数。
 * 示例 3：
 *
 * 输入：n = 1, target = 1
 * 输出：0
 * 解释：最初，n 是 1 ，且其每一位数字的和是 1 ，已经小于等于 target 。
 *  
 *
 * 提示：
 *
 * 1 <= n <= 1012
 * 1 <= target <= 150
 * 生成的输入保证总可以使 n 变成一个美丽整数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-addition-to-make-integer-beautiful
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * 参考：
     * https://leetcode.cn/problems/minimum-addition-to-make-integer-beautiful/solution/by-xiang-yun-h5-ekec/
     * @param n
     * @param target
     * @return
     */
    public long makeIntegerBeautiful(long n, int target) {
        // 依次对 n 进行进位 ，因为只有进位才能导致 数字和减小
        long current = n;
        // 判断是否数字和 小于 target
        long bit = 10;
        while (!check(current, target)) {
            // 不满足要求 求进位
            current = ((current / bit) + 1 ) * bit;
            bit *= 10;
        }
        // 第一个 能达到要求 的值 比较下差值 是不是 超过 target
        return current - n;
    }


    private boolean check(long num, long target) {
        long numSum = 0;
        while (num > 0) {
            numSum += (num % 10);
            num /= 10;
        }
        return numSum <= target;
    }

}
