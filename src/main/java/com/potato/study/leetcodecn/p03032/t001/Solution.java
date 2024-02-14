package com.potato.study.leetcodecn.p03032.t001;


/**
 *
 * 3032. 统计各位数字都不同的数字个数 II
 *
 * 给你两个 正整数 a 和 b ，返回 闭区间 [a, b] 内各位数字都不同的数字个数。
 *
 *
 * 示例 1：
 *
 * 输入：a = 1, b = 20
 * 输出：19
 * 解释：除 11 以外，区间 [1, 20] 内的所有数字的各位数字都不同。因此，答案为 19 。
 * 示例 2：
 *
 * 输入：a = 9, b = 19
 * 输出：10
 * 解释：除 11 以外，区间 [1, 20] 内的所有数字的各位数字都不同。因此，答案为 10 。
 * 示例 3：
 *
 * 输入：a = 80, b = 120
 * 输出：27
 * 解释：区间 [80, 120] 内共有 41 个整数，其中 27 个数字的各位数字都不同。
 *
 *
 * 提示：
 *
 * 1 <= a <= b <= 1000
 *
 */

public class Solution {


    private boolean hasGenerated = false;
    private int[] count;

    public int numberCount(int a, int b) {
        // 静态数组 首次生成标记
        if (this.hasGenerated) {
            return count[b] - count[a-1];
        }
        // 生成
        this.hasGenerated = true;
        // 从 1 <=  <= 1000 依次生成结果
        this.count = new int[1001];
        count[0] = 1;
        for (int i = 1; i <= 1000; i++) {
            boolean diffDigit = isDiffDigit(i);
            if (diffDigit) {
                count[i] = count[i-1] + 1;
            } else {
                count[i] = count[i-1];
            }
        }
        return count[b] - count[a-1];
    }


    /**
     * 判断数字中，是否有重复的数位
     * @param target
     * @return true 全是不同的
     */
    private boolean isDiffDigit(int target) {
        // false 没有出现
        boolean[] appear = new boolean[10];
        while (target > 0) {
            int digit = target % 10;
            if (appear[digit]) {
                return false;
            }
            appear[digit] = true;
            target /= 10;

        }
        return true;
    }


}
