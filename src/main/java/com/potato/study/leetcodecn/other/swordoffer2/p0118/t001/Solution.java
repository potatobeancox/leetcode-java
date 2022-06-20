package com.potato.study.leetcodecn.other.swordoffer2.p0118.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.Arrays;

/**
 * 剑指 Offer II 118. 多余的边
 *
 * 树可以看成是一个连通且 无环 的 无向 图。

 给定往一棵 n 个节点 (节点值 1～n) 的树中添加一条边后的图。添加的边的两个顶点包含在 1 到 n 中间，且这条附加的边不属于树中已存在的边。图的信息记录于长度为 n 的二维数组 edges ，edges[i] = [ai, bi] 表示图中在 ai 和 bi 之间存在一条边。

 请找出一条可以删去的边，删除后可使得剩余部分是一个有着 n 个节点的树。如果有多个答案，则返回数组 edges 中最后出现的边。

  

 示例 1：



 输入: edges = [[1,2],[1,3],[2,3]]
 输出: [2,3]
 示例 2：



 输入: edges = [[1,2],[2,3],[3,4],[1,4],[1,5]]
 输出: [1,4]
  

 提示:

 n == edges.length
 3 <= n <= 1000
 edges[i].length == 2
 1 <= ai < bi <= edges.length
 ai != bi
 edges 中无重复元素
 给定的图是连通的 
  

 注意：本题与主站 684 题相同： https://leetcode-cn.com/problems/redundant-connection/

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/7LpjUW
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 并查集合
     * @param edges
     * @return
     */
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        UnionFind unionFind = new UnionFind(n);
        for (int[] edge : edges) {
            // 是否已经联通了
            if (unionFind.find(edge[0]) == unionFind.find(edge[1])) {
                return edge;
            } else {
                unionFind.union(edge[0], edge[1]);
            }
        }
        return null;
    }


    class UnionFind {
        private int[] parent;

        public UnionFind(int n) {
            this.parent = new int[n + 1];
            for (int i = 0; i < parent.length; i++) {
                parent[i] = i;
            }
        }

        public void union(int target1, int target2) {
            int p1 = find(target1);
            int p2 = find(target2);

            if (p1 != p2) {
                parent[p1] = p2;
            }
        }

        public int find(int target) {
            while (target != parent[target]) {
                target = parent[target];
            }
            return target;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "[[1,2],[1,3],[2,3]]";
        int[][] ints = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        int[] arr = solution.findRedundantConnection(ints);
        System.out.println(Arrays.toString(arr));
        Assert.assertArrayEquals(arr, new int[] {
                2,3
        });
    }
}
