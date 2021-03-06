package com.potato.study.leetcodecn.p01291.t001;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 1291. 顺次数
 *
 * 我们定义「顺次数」为：每一位上的数字都比前一位上的数字大 1 的整数。
 *
 * 请你返回由 [low, high] 范围内所有顺次数组成的 有序 列表（从小到大排序）。
 *
 *  
 *
 * 示例 1：
 *
 * 输出：low = 100, high = 300
 * 输出：[123,234]
 * 示例 2：
 *
 * 输出：low = 1000, high = 13000
 * 输出：[1234,2345,3456,4567,5678,6789,12345]
 *  
 *
 * 提示：
 *
 * 10 <= low <= high <= 10^9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sequential-digits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> list = new ArrayList<>();
        // 开始位置
        for (int i = 1; i <= 9; i++) {
            // 结束位置
            StringBuilder builder = new StringBuilder();
            builder.append(i);
            for (int j = i + 1; j <= 9; j++) {
                builder.append(j);
                int num = Integer.valueOf(builder.toString());
                if (low <= num && num <= high) {
                    list.add(num);
                } else if (num > high) {
                    break;
                }
            }
        }
        Collections.sort(list);
        return list;
    }
}
