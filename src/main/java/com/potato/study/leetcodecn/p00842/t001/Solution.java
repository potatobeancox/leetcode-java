package com.potato.study.leetcodecn.p00842.t001;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.Assert;

import com.google.common.collect.Lists;

/**
 * 842. 将数组拆分成斐波那契序列
 *
 * 给定一个数字字符串 S，比如 S = "123456579"，我们可以将它分成斐波那契式的序列 [123, 456, 579]。
 *
 * 形式上，斐波那契式序列是一个非负整数列表 F，且满足：
 *
 * 0 <= F[i] <= 2^31 - 1，（也就是说，每个整数都符合 32 位有符号整数类型）；
 * F.length >= 3；
 * 对于所有的0 <= i < F.length - 2，都有 F[i] + F[i+1] = F[i+2] 成立。
 * 另外，请注意，将字符串拆分成小块时，每个块的数字一定不要以零开头，除非这个块是数字 0 本身。
 *
 * 返回从 S 拆分出来的任意一组斐波那契式的序列块，如果不能拆分则返回 []。
 *
 *  
 *
 * 示例 1：
 *
 * 输入："123456579"
 * 输出：[123,456,579]
 * 示例 2：
 *
 * 输入: "11235813"
 * 输出: [1,1,2,3,5,8,13]
 * 示例 3：
 *
 * 输入: "112358130"
 * 输出: []
 * 解释: 这项任务无法完成。
 * 示例 4：
 *
 * 输入："0123"
 * 输出：[]
 * 解释：每个块的数字不能以零开头，因此 "01"，"2"，"3" 不是有效答案。
 * 示例 5：
 *
 * 输入: "1101111"
 * 输出: [110, 1, 111]
 * 解释: 输出 [11,0,11,11] 也同样被接受。
 *  
 *
 * 提示：
 *
 * 1 <= S.length <= 200
 * 字符串 S 中只含有数字。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/split-array-into-fibonacci-sequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    private List<Integer> result;

    // 842
    public List<Integer> splitIntoFibonacci(String num) {
        boolean flag = dfs(num, 0, 0, 0, 0, new ArrayList<>());
        if (flag) {
            return result;
        }
        return new ArrayList<>();
    }


    /**
     *
     * @param num     目标字符串
     * @param index   当前index 到了多少
     * @param num1    之前的数字1
     * @param num2    之前的数字2
     * @param numCount  数字 count
     * @return
     */
    private boolean dfs(String num, int index, int num1, int num2, int numCount, List<Integer> list) {
        // 已经到了末尾
        if (index == num.length()) {
            if (numCount > 2) {
                this.result = list;
                return true;
            } else {
                return false;
            }
        }
        // 从 index 开始往后面遍历
        for (int i = index; i < num.length(); i++) {
            String substring = num.substring(index, i + 1);
            // 判断是够有先导0
            if (substring.charAt(0) == '0' && substring.length() != 1) {
                continue;
            }
            int current = Integer.parseInt(substring);
            if (numCount >= 2 && num1 + num2 != current) {
                continue;
            }
            List<Integer> nextList = new ArrayList<>(list);
            nextList.add(current);
            boolean flag = dfs(num, i+1, num2, current, numCount + 1, nextList);
            if (flag) {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String num = "123456579";
        List<Integer> list = solution.splitIntoFibonacci(num);
        // [123,456,579]
        System.out.println(list);

        num = "0123";
        list = solution.splitIntoFibonacci(num);
        // [123,456,579]
        System.out.println(list);

    }
}
