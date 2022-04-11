package com.potato.study.leetcodecn.p01718.t001;

import java.util.*;

/**
 * 1718. 构建字典序最大的可行序列
 *
 * 给你一个整数 n ，请你找到满足下面条件的一个序列：

 整数 1 在序列中只出现一次。
 2 到 n 之间每个整数都恰好出现两次。
 对于每个 2 到 n 之间的整数 i ，两个 i 之间出现的距离恰好为 i 。
 序列里面两个数 a[i] 和 a[j] 之间的 距离 ，我们定义为它们下标绝对值之差 |j - i| 。

 请你返回满足上述条件中 字典序最大 的序列。题目保证在给定限制条件下，一定存在解。

 一个序列 a 被认为比序列 b （两者长度相同）字典序更大的条件是： a 和 b 中第一个不一样的数字处，a 序列的数字比 b 序列的数字大。比方说，[0,1,9,0] 比 [0,1,5,6] 字典序更大，因为第一个不同的位置是第三个数字，且 9 比 5 大。

  

 示例 1：

 输入：n = 3
 输出：[3,1,2,3,2]
 解释：[2,3,2,1,3] 也是一个可行的序列，但是 [3,1,2,3,2] 是字典序最大的序列。
 示例 2：

 输入：n = 5
 输出：[5,3,1,4,3,5,2,4,2]
  

 提示：

 1 <= n <= 20

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/construct-the-lexicographically-largest-valid-sequence
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    private int[] result;

    /**
     * https://leetcode-cn.com/problems/construct-the-lexicographically-largest-valid-sequence/solution/tan-xin-hui-su-by-chenchuxin-jcy5/
     *
     * dfs 回溯
     * @param n
     * @return
     */
    public int[] constructDistancedSequence(int n) {
        this.result = new int[n * 2 - 1];
        dfs(n, 0, new HashSet<>());
        return result;
    }


    /**
     *
     * @param n
     * @param resultIndex    当前填充到的index
     * @param visited        当前已经使用的数字
     * @return
     */
    private boolean dfs(int n, int resultIndex, Set<Integer> visited) {
        // 终止条件 所有数字都被使用
        if (visited.size() == n) {
            return true;
        }
        // 每次 从最大的 往后找 直到 找到 visited 没有的 看看 这样放置能不能行
        for (int i = n; i > 0; i--) {
            if (visited.contains(i)) {
                continue;
            }
            // 当前选中 元素 位置
            int nextResultIndex = resultIndex + i;
            // 当前位置用过 continue
            if (i != 1 && nextResultIndex >= result.length) {
                continue;
            }
            if (i != 1 && result[nextResultIndex] != 0) {
                continue;
            }
            // 放置
            visited.add(i);
            result[resultIndex] = i;
            if (i != 1) {
                result[nextResultIndex] = i;
            }
            // 找到下一个 位置
            int nextIndex = resultIndex + 1;
            while (nextIndex < result.length && result[nextIndex] > 0) {
                nextIndex++;
            }
            // dfs
            boolean nextLoopResult = dfs(n, nextIndex, visited);
            if (nextLoopResult) {
                return true;
            }
            visited.remove(i);
            result[resultIndex] = 0;
            if (i != 1) {
                result[nextResultIndex] = 0;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 3;
        int[] ints = solution.constructDistancedSequence(n);
        // [3,1,2,3,2]
        System.out.println(Arrays.toString(ints));


        n = 5;
        ints = solution.constructDistancedSequence(n);
        System.out.println(Arrays.toString(ints));
        // [5,3,1,4,3,5,2,4,2]
        // [5, 3, 1, 4, 3, 5, 2, 4, 2]
    }
}
