package com.potato.study.leetcodecn.p01130.t001;


import org.junit.Assert;

import java.util.Stack;

/**
 * 1130. 叶值的最小代价生成树
 *
 * 给你一个正整数数组 arr，考虑所有满足以下条件的二叉树：
 *
 * 每个节点都有 0 个或是 2 个子节点。
 * 数组 arr 中的值与树的中序遍历中每个叶节点的值一一对应。（知识回顾：如果一个节点有 0 个子节点，那么该节点为叶节点。）
 * 每个非叶节点的值等于其左子树和右子树中叶节点的最大值的乘积。
 * 在所有这样的二叉树中，返回每个非叶节点的值的最小可能总和。这个和的值是一个 32 位整数。
 *
 *  
 *
 * 示例：
 *
 * 输入：arr = [6,2,4]
 * 输出：32
 * 解释：
 * 有两种可能的树，第一种的非叶节点的总和为 36，第二种非叶节点的总和为 32。
 *
 *     24            24
 *    /  \          /  \
 *   12   4        6    8
 *  /  \               / \
 * 6    2             2   4
 *  
 *
 * 提示：
 *
 * 2 <= arr.length <= 40
 * 1 <= arr[i] <= 15
 * 答案保证是一个 32 位带符号整数，即小于 2^31。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-cost-tree-from-leaf-values
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     *
     https://leetcode-cn.com/problems/minimum-cost-tree-from-leaf-values/solution/xiang-xi-jie-shi-dong-tai-gui-hua-dan-diao-zhan-ji/
     * @param arr
     * @return
     */
    public int mctFromLeafValues(int[] arr) {
        // 单调栈 从top到 botton 单调递减 每次找到 最小的2个进行合并 累加结果
        Stack<Integer> stack = new Stack<>();
        // 放进去一个最大值 便于处理
        stack.add(Integer.MAX_VALUE);
        // 第一个元素进去
        stack.add(arr[0]);
        // 遍历 每个元素  对于每个位置 比较下看
        int total = 0;
        for (int i = 1; i < arr.length; i++) {
            int current = arr[i];
            // 如果当前 current 大于 stack 那么 stack 顶需要被消耗
            while (current > stack.peek()) {
                Integer smallest = stack.pop();
                total += (smallest * Math.min(stack.peek(), current));
            }
            // 如果当前 current 小于 stack 先不进行消耗
            stack.push(current);
        }

        // 遍历 stack 每次消耗一个叶子 记得头部 还有一个int 最大值
        while (stack.size() > 2) {
            Integer pop = stack.pop();
            total += (pop * stack.peek());
        }

        return total;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[] {
                6,2,4
        };
        int i = solution.mctFromLeafValues(arr);
        System.out.println(i);
        Assert.assertEquals(32, i);
    }
}
