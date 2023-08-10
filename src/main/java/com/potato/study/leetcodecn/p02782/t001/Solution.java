package com.potato.study.leetcodecn.p02782.t001;


import java.util.Arrays;

/**
 *
 * 2782. 唯一类别的数量
 *
 * 现给定一个整数 n 和一个 CategoryHandler 类的对象 categoryHandler 。
 *
 * 有 n 个元素，编号从 0 到 n - 1。每个元素都有一个类别，你的任务是找出唯一类别的数量。
 *
 * CategoryHandler 类包含以下方法，可能对你有帮助：
 *
 * boolean haveSameCategory(integer a, integer b)：如果 a 和 b 属于相同的类别，则返回 true，否则返回 false。同时，如果 a 或 b 不是有效的数字（即大于等于 n 或小于 0），它也会返回 false。
 * 返回唯一类别的数量。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 6, catagoryHandler = [1,1,2,2,3,3]
 * 输出：3
 * 解释：这个示例中有 6 个元素。前两个元素属于类别 1，接下来两个属于类别 2，最后两个元素属于类别 3。所以有 3 个唯一类别。
 * 示例 2：
 *
 * 输入：n = 5, catagoryHandler = [1,2,3,4,5]
 * 输出：5
 * 解释：这个示例中有 5 个元素。每个元素属于一个唯一的类别。所以有 5 个唯一类别。
 * 示例 3：
 *
 * 输入：n = 3, catagoryHandler = [1,1,1]
 * 输出：1
 * 解释：这个示例中有 3 个元素。它们全部属于同一个类别。所以只有 1 个唯一类别。
 *
 *
 * 提示：
 *
 * 1 <= n <= 100
 *
 */
public class Solution {


    public int numberOfCategories(int n, CategoryHandler categoryHandler) {
        // 并查集 使用一个 n计算部分
        UnionFind unionFind = new UnionFind(n);
        // 两重循环 利用 union 进行合并
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if (categoryHandler.haveSameCategory(i, j)) {
                    unionFind.union(i, j);
                }
            }
        }
        // 返回个数
        return unionFind.partCount;
    }

    class UnionFind {
        public int[] parent;
        // 并查集分量的个数
        public int partCount;

        public UnionFind(int n) {
            this.partCount = n;
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public void union(int target1, int target2) {
            int p1 = find(target1);
            int p2 = find(target2);

            if (p1 == p2) {
                return;
            }
            parent[p1] = parent[p2];
            this.partCount--;
        }

        public int find(int target) {
            while (target != parent[target]) {
                target = parent[target];
            }
            return target;
        }
    }

}

class CategoryHandler {
    public CategoryHandler(int[] categories) {
    }
    public boolean haveSameCategory(int a, int b) {
        return false;
    }
};
