package com.potato.study.leetcodecn.p02578.t001;

/**
 * 2578. 最小和分割

 给你一个正整数 num ，请你将它分割成两个非负整数 num1 和 num2 ，满足：

 num1 和 num2 直接连起来，得到 num 各数位的一个排列。
 换句话说，num1 和 num2 中所有数字出现的次数之和等于 num 中所有数字出现的次数。
 num1 和 num2 可以包含前导 0 。
 请你返回 num1 和 num2 可以得到的和的 最小 值。

 注意：

 num 保证没有前导 0 。
 num1 和 num2 中数位顺序可以与 num 中数位顺序不同。
  

 示例 1：

 输入：num = 4325
 输出：59
 解释：我们可以将 4325 分割成 num1 = 24 和 num2 = 35 ，和为 59 ，59 是最小和。
 示例 2：

 输入：num = 687
 输出：75
 解释：我们可以将 687 分割成 num1 = 68 和 num2 = 7 ，和为最优值 75 。
  

 提示：

 10 <= num <= 109


 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/split-with-minimum-sum
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 2578
    public int splitNum(int num) {
        // 统计没和 num 位置 出现的数字个数
        int[] count = new int[10];
        while (num > 0) {
            int i = num % 10;
            count[i]++;
            num /= 10;
        }
        // 从小到大 依次生成 数组
        int num1 = 0;
        int num2 = 0;
        // 设置num1
        boolean flag = true;
        for (int i = 0; i < 10; i++) {
            if (count[i] == 0) {
                continue;
            }
            for (int j = 0; j < count[i]; j++) {
                if (flag) {
                    num1 *= 10;
                    num1 += i;
                    flag = false;
                } else {
                    num2 *= 10;
                    num2 += i;
                    flag = true;
                }
            }
        }
        return num1 + num2;
    }

}
