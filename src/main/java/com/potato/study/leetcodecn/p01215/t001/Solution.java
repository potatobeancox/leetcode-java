package com.potato.study.leetcodecn.p01215.t001;


import com.potato.study.leetcode.domain.TreeNode;

import java.util.*;

/**
 * 1215. 步进数
 *
 * 如果一个整数上的每一位数字与其相邻位上的数字的绝对差都是 1，那么这个数就是一个「步进数」。
 *
 * 例如，321 是一个步进数，而 421 不是。
 *
 * 给你两个整数，low 和 high，请你找出在 [low, high] 范围内的所有步进数，并返回 排序后 的结果。
 *
 *  
 *
 * 示例：
 *
 * 输入：low = 0, high = 21
 * 输出：[0,1,2,3,4,5,6,7,8,9,10,12,21]
 *  
 *
 * 提示：
 *
 * 0 <= low <= high <= 2 * 10^9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/stepping-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    //
    public List<Integer> countSteppingNumbers(int low, int high) {
        // 生成 步进数
        Queue<Long> queue = new LinkedList<>();
        for (int i = 1; i <= 9; i++) {
            queue.add((long)i);
        }
//        dfs(list);
        // 选取 low 和 high 之间的 并排序
        List<Integer> result = new ArrayList<>();
        if (low == 0) {
            result.add(0);
        }
        while (!queue.isEmpty()) {
            long current = queue.poll();
            if (current > high) {
                return result;
            }
            if (low <= current && current <= high) {
                result.add((int) current);
            }
            // 下一个数字
            int lastBit = (int) (current % 10);
            if (lastBit > 0) {
                long temp = current * 10 + (lastBit - 1);
                queue.add(temp);
            }
            if (lastBit < 9) {
                long temp = current * 10 + (lastBit + 1);
                queue.add(temp);
            }
        }
        return result;
    }

}
