package com.potato.study.leetcodecn.p02673.t001;

import com.potato.study.leetcode.domain.ListNode;
import com.potato.study.leetcode.util.LeetcodeInputUtils;
import com.potato.study.leetcode.util.LeetcodeUtils;
import org.junit.Assert;

/**
 *
 * 2673. 使二叉树所有路径值相等的最小代价
 *
 * 给你一个整数 n 表示一棵 满二叉树 里面节点的数目，节点编号从 1 到 n 。根节点编号为 1 ，树中每个非叶子节点 i 都有两个孩子，分别是左孩子 2 * i 和右孩子 2 * i + 1 。

 树中每个节点都有一个值，用下标从 0 开始、长度为 n 的整数数组 cost 表示，其中 cost[i] 是第 i + 1 个节点的值。每次操作，你可以将树中 任意 节点的值 增加 1 。你可以执行操作 任意 次。

 你的目标是让根到每一个 叶子结点 的路径值相等。请你返回 最少 需要执行增加操作多少次。

 注意：

 满二叉树 指的是一棵树，它满足树中除了叶子节点外每个节点都恰好有 2 个节点，且所有叶子节点距离根节点距离相同。
 路径值 指的是路径上所有节点的值之和。
  

 示例 1：



 输入：n = 7, cost = [1,5,2,2,3,3,1]
 输出：6
 解释：我们执行以下的增加操作：
 - 将节点 4 的值增加一次。
 - 将节点 3 的值增加三次。
 - 将节点 7 的值增加两次。
 从根到叶子的每一条路径值都为 9 。
 总共增加次数为 1 + 3 + 2 = 6 。
 这是最小的答案。
 示例 2：



 输入：n = 3, cost = [5,3,3]
 输出：0
 解释：两条路径已经有相等的路径值，所以不需要执行任何增加操作。
  

 提示：

 3 <= n <= 105
 n + 1 是 2 的幂
 cost.length == n
 1 <= cost[i] <= 104

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/make-costs-of-paths-equal-in-a-binary-tree
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int minIncrements(int n, int[] cost) {
        // 从叶子节点开始算 没两个 当前父亲对应孩子的差 差值就是 需要增加的值
        int res = 0;
        for (int i = n / 2; i >= 1; i--) {
            int c1 = 2 * i;
            int c2 = 2 * i + 1;

            int diff = Math.abs(cost[c1-1] - cost[c2-1]);
            int max = Math.max(cost[c1-1], cost[c2-1]);

            res += diff;
            cost[i-1] += max;
        }
        // 计算之后 将较大的 加到父亲上面
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 7;
        int[] cost = LeetcodeInputUtils.inputString2IntArray("[1,5,2,2,3,3,1]");
        int i = solution.minIncrements(n, cost);
        System.out.println(i);
        Assert.assertEquals(6, i);
    }
}
