package com.potato.study.leetcodecn.other.swordoffer2.p0038.t001;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * 剑指 Offer II 038. 每日温度
 *
 * 请根据每日 气温 列表 temperatures ，重新生成一个列表，要求其对应位置的输出为：
 * 要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出: [1,1,4,2,1,1,0,0]
 * 示例 2:
 *
 * 输入: temperatures = [30,40,50,60]
 * 输出: [1,1,1,0]
 * 示例 3:
 *
 * 输入: temperatures = [30,60,90]
 * 输出: [1,1,0]
 *  
 *
 * 提示：
 *
 * 1 <= temperatures.length <= 105
 * 30 <= temperatures[i] <= 100
 *  
 *
 * 注意：本题与主站 739 题相同： https://leetcode-cn.com/problems/daily-temperatures/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/iIQa4I
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     *
     * @param temperatures
     * @return
     */
    public int[] dailyTemperatures(int[] temperatures) {
        // 单调栈 从后往前遍历 如果 stack 为空 或者可以出栈到空 当前位置返回0 改成存下标
        Stack<Integer> stack = new Stack<>();
        int[] day = new int[temperatures.length];
        for (int i = temperatures.length - 1; i >= 0; i--) {
            // 如果当前 cur 小于 栈顶 那么计数 = 1 否则循环pop 计数
            while (!stack.isEmpty() && temperatures[stack.peek()] <= temperatures[i]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                day[i] = 0;
            } else {
                day[i] = stack.peek() - i;
            }
            // 当前 温度 入栈
            stack.push(i);
        }
        return day;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] temperatures = new int[] {
                73,74,75,71,69,72,76,73
        };
        //[1,1,4,2,1,1,0,0]
        int[] ints = solution.dailyTemperatures(temperatures);
        System.out.println(Arrays.toString(ints));
    }
}
