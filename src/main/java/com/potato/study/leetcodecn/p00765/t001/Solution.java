package com.potato.study.leetcodecn.p00765.t001;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * 765. 情侣牵手
 *
 * N 对情侣坐在连续排列的 2N 个座位上，想要牵到对方的手。 计算最少交换座位的次数，以便每对情侣可以并肩坐在一起。 一次交换可选择任意两人，让他们站起来交换座位。

 人和座位用 0 到 2N-1 的整数表示，情侣们按顺序编号，第一对是 (0, 1)，第二对是 (2, 3)，以此类推，最后一对是 (2N-2, 2N-1)。

 这些情侣的初始座位  row[i] 是由最初始坐在第 i 个座位上的人决定的。

 示例 1:

 输入: row = [0, 2, 1, 3]
 输出: 1
 解释: 我们只需要交换row[1]和row[2]的位置即可。
 示例 2:

 输入: row = [3, 2, 0, 1]
 输出: 0
 解释: 无需交换座位，所有的情侣都已经可以手牵手了。
 说明:

 len(row) 是偶数且数值在 [4, 60]范围内。
 可以保证row 是序列 0...len(row)-1 的一个全排列。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/couples-holding-hands
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     *
     * @param row
     * @return
     */
    public int minSwapsCouples(int[] row) {
        UnionFind unionFind = new UnionFind(row.length / 2);
        // 每次 从 row 取出两个点 如果对了 不用换了 否则就是做错了，按照并查集统计 并查集需要统计每个联通分量元素个数
        for (int i = 0; i < row.length; i+=2) {
            int target1 = row[i] / 2;
            int target2 = row[i+1] / 2;
            if (target1 == target2) {
                continue;
            }
            unionFind.union(target1, target2);
        }
        // 找到并查集中元素个数，每个 联通分量个数 就是 联通分量元素数 -1
        return row.length / 2 - unionFind.count;
    }



    // 并查集数据结构
    class UnionFind {
        // 节点的父亲节点
        public int[] parent;
        // 联通分量个数
        public int count;

        public UnionFind(int n) {
            this.count = n;
            this.parent = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        /**
         * 找到 target 的父亲节点
         * @param target
         * @return
         */
        public int find(int target) {
            while (parent[target] != target) {
                target = parent[target];
            }
            return target;
        }

        public void union(int target1, int target2) {
            int parent1 = find(target1);
            int parent2 = find(target2);
            if (parent1 == parent2) {
                return;
            }
            // union
            parent[parent1] = parent2;
            count--;
        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[] {
                0, 2, 1, 3
        };
        int i = solution.minSwapsCouples(arr);
        System.out.println(i);
        Assert.assertEquals(1, i);

        arr = new int[] {
                3, 2, 0, 1
        };
        i = solution.minSwapsCouples(arr);
        System.out.println(i);
        Assert.assertEquals(0, i);

        arr = new int[] {
                5,4,2,6,3,1,0,7
        };
        i = solution.minSwapsCouples(arr);
        System.out.println(i);
        Assert.assertEquals(2, i);
    }

}



