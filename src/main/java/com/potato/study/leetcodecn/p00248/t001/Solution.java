package com.potato.study.leetcodecn.p00248.t001;

import java.util.ArrayList;
import java.util.List;

/**
 * 248. 中心对称数 III
 *
 * 给定两个字符串 low 和 high 表示两个整数 low 和 high ，其中 low <= high ，返回 范围 [low, high] 内的 「中心对称数」总数  。
 *
 * 中心对称数 是一个数字在旋转了 180 度之后看起来依旧相同的数字（或者上下颠倒地看）。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: low = "50", high = "100"
 * 输出: 3
 * 示例 2:
 *
 * 输入: low = "0", high = "0"
 * 输出: 1
 *  
 *
 * 提示:
 *
 * 1 <= low.length, high.length <= 15
 * low 和 high 只包含数字
 * low <= high
 * low and high 不包含任何前导零，除了零本身。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/strobogrammatic-number-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int strobogrammaticInRange(String low, String high) {
        // 上限和限制
        int lowSize = low.length();
        int highSize = high.length();
        // 从开始长度向终止长度计算
        int count = 0;
        for (int i = lowSize; i <= highSize; i++) {
            // 每次得到的 值进行比较
            List<String> dfs = dfs(i, i);

            for (String target : dfs) {
                if (betweenAnd(low, high, target)) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean betweenAnd(String low, String high, String target) {
        long lowNum = Long.parseLong(low);
        long highNum = Long.parseLong(high);
        long targetNum = Long.parseLong(target);
        return lowNum <= targetNum && targetNum <= highNum;
    }

    private List<String> dfs(int n, int total) {
        List<String> result = new ArrayList<>();
        if (n == 0) {
            return result;
        }
        if (n == 1) {
            result.add("1");
            result.add("8");
            result.add("0");
            return result;
        }
        if (n == 2) {
            result.add("11");
            result.add("88");
            result.add("69");
            result.add("96");
            if (n != total) {
                result.add("00");
            }
            return result;
        }
        List<String> innerList = dfs(n - 2, total);
        for (String innerNum : innerList) {
            result.add("1" + innerNum + "1");
            result.add("8" + innerNum + "8");
            result.add("6" + innerNum + "9");
            result.add("9" + innerNum + "6");

            if (total != n) {
                result.add("0" + innerNum + "0");
            }
        }
        return result;
    }


}
