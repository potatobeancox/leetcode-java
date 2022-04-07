package com.potato.study.leetcodecn.p02049.t001;

import java.util.ArrayList;
import java.util.List;

/**
 * 2049. 统计最高分的节点数目
 *
 * 给你一棵根节点为 0 的 二叉树 ，它总共有 n 个节点，节点编号为 0 到 n - 1 。同时给你一个下标从 0 开始的整数数组 parents 表示这棵树，其中 parents[i] 是节点 i 的父节点。由于节点
 * 0 是根，所以 parents[0] == -1 。
 *
 * 一个子树的 大小 为这个子树内节点的数目。每个节点都有一个与之关联的 分数 。求出某个节点分数的方法是，将这个节点和与它相连的边全部 删除 ，剩余部分是若干个 非空 子树，这个节点的 分数 为所有这些子树 大小的乘积 。
 *
 * 请你返回有 最高得分 节点的 数目 。
 *
 *  
 *
 * 示例 1:
 *
 *
 *
 * 输入：parents = [-1,2,0,2,0]
 * 输出：3
 * 解释：
 * - 节点 0 的分数为：3 * 1 = 3
 * - 节点 1 的分数为：4 = 4
 * - 节点 2 的分数为：1 * 1 * 2 = 2
 * - 节点 3 的分数为：4 = 4
 * - 节点 4 的分数为：4 = 4
 * 最高得分为 4 ，有三个节点得分为 4 （分别是节点 1，3 和 4 ）。
 * 示例 2：
 *
 *
 *
 * 输入：parents = [-1,2,0]
 * 输出：2
 * 解释：
 * - 节点 0 的分数为：2 = 2
 * - 节点 1 的分数为：2 = 2
 * - 节点 2 的分数为：1 * 1 = 1
 * 最高分数为 2 ，有两个节点分数为 2 （分别为节点 0 和 1 ）。
 *  
 *
 * 提示：
 *
 * n == parents.length
 * 2 <= n <= 105
 * parents[0] == -1
 * 对于 i != 0 ，有 0 <= parents[i] <= n - 1
 * parents 表示一棵二叉树。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-nodes-with-the-highest-score
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    private long max;
    private long maxNodeCount;
    /**
     *
     * @param parents
     * @return
     */
    public int countHighestScoreNodes(int[] parents) {
        // 将 parents 转换成 children 数组
        int n = parents.length;
        List<Integer> [] children = new List[n];
        // init
        for (int i = 0; i < children.length; i++) {
            children[i] = new ArrayList<>();
        }
        for (int i = 0; i < parents.length; i++) {
            int parent = parents[i];
            int child = i;
            if (parent == -1) {
                continue;
            }
            children[parent].add(child);
        }
        // dfs 从 0 号节点开始
        this.max = 0;
        this.maxNodeCount = 0;
        dfs(0, children, n);
        return (int) maxNodeCount;
    }

    private long dfs(int noteIndex, List<Integer>[] children, int totalNodeCount) {
        // 找到孩子 对于每个孩子 计算 孩子数量 求和和乘积，
        List<Integer> child = children[noteIndex];
        long score = 1;
        long childNodeCount = 0;
        // 有孩子
        for (int childNodeIndex : child) {
            long eachChildNodeCount = dfs(childNodeIndex, children, totalNodeCount);
            childNodeCount += eachChildNodeCount;
            score *= eachChildNodeCount;
        }
        // 计算上 除去当前节点子树
        if (totalNodeCount - childNodeCount - 1 > 1) {
            score *= (totalNodeCount - childNodeCount - 1);
        }

        // 判断得分是否是最大
        if (score > this.max) {
            this.max = score;
            this.maxNodeCount = 1;
        } else if (score == this.max) {
            this.maxNodeCount++;
        }
        // 返回有多少个节点 包括这个节点
        return childNodeCount + 1;
    }
}
