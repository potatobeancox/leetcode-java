package com.potato.study.leetcodecn.p01483.t001;


import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

/**
 * 1483. 树节点的第 K 个祖先

 *
 * 给你一棵树，树上有 n 个节点，按从 0 到 n-1 编号。树以父节点数组的形式给出，其中 parent[i] 是节点 i 的父节点。树的根节点是编号为 0 的节点。

 树节点的第 k 个祖先节点是从该节点到根节点路径上的第 k 个节点。

 实现 TreeAncestor 类：

 TreeAncestor（int n， int[] parent） 对树和父数组中的节点数初始化对象。
 getKthAncestor(int node, int k) 返回节点 node 的第 k 个祖先节点。如果不存在这样的祖先节点，返回 -1 。


 示例 1：



 输入：
 ["TreeAncestor","getKthAncestor","getKthAncestor","getKthAncestor"]
 [[7,[-1,0,0,1,1,2,2]],[3,1],[5,2],[6,3]]

 输出：
 [null,1,0,-1]

 解释：
 TreeAncestor treeAncestor = new TreeAncestor(7, [-1, 0, 0, 1, 1, 2, 2]);

 treeAncestor.getKthAncestor(3, 1);  // 返回 1 ，它是 3 的父节点
 treeAncestor.getKthAncestor(5, 2);  // 返回 0 ，它是 5 的祖父节点
 treeAncestor.getKthAncestor(6, 3);  // 返回 -1 因为不存在满足要求的祖先节点


 提示：

 1 <= k <= n <= 5 * 104
 parent[0] == -1 表示编号为 0 的节点是根节点。
 对于所有的 0 < i < n ，0 <= parent[i] < n 总成立
 0 <= node < n
 至多查询 5 * 104 次
 *
 */
public class TreeAncestor {

    private int[][] p;

    public TreeAncestor(int n, int[] parent) {
        // i的 第 2^j 的父亲
        this.p = new int[n][32];
        // 设置 0
        for (int i = 0; i < n; i++) {
            p[i][0] = parent[i];
        }
        // 从1开始设置
        for (int j = 0; j < 31; j++) {
            for (int i = 0; i < n; i++) {
                int target = p[i][j];
                // 之前已经没有父亲了 这个也没有
                if (target < 0) {
                    p[i][j+1] = -1;
                } else {
                    p[i][j+1] = p[target][j];
                }
            }
        }
    }

    public int getKthAncestor(int node, int k) {
        int target = node;
        for (int i = 0; i < 32; i++) {
            if (((1 << i)&k) == 0) {
                continue;
            }
            // 找到当前值 i
            target = this.p[target][i];
            if (target < 0) {
                return -1;
            }
        }
        return target;
    }

    public static void main(String[] args) {
        int n = 10;
        int[] parent = LeetcodeInputUtils.inputString2IntArray("[-1,0,1,2,0,1,0,4,7,1]");
        TreeAncestor treeAncestor = new TreeAncestor(n, parent);

        int kthAncestor = treeAncestor.getKthAncestor(3,2);
        System.out.println(kthAncestor);
        Assert.assertEquals(1, kthAncestor);
    }
}