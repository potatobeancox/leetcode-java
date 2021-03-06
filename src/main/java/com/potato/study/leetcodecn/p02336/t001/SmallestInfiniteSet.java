package com.potato.study.leetcodecn.p02336.t001;

import java.util.TreeSet;

/**
 * 2336. 无限集中的最小数字
 *
 * 现有一个包含所有正整数的集合 [1, 2, 3, 4, 5, ...] 。
 *
 * 实现 SmallestInfiniteSet 类：
 *
 * SmallestInfiniteSet() 初始化 SmallestInfiniteSet 对象以包含 所有 正整数。
 * int popSmallest() 移除 并返回该无限集中的最小整数。
 * void addBack(int num) 如果正整数 num 不 存在于无限集中，则将一个 num 添加 到该无限集中。
 *  
 *
 * 示例：
 *
 * 输入
 * ["SmallestInfiniteSet", "addBack", "popSmallest", "popSmallest", "popSmallest", "addBack", "popSmallest",
 * "popSmallest", "popSmallest"]
 * [[], [2], [], [], [], [1], [], [], []]
 * 输出
 * [null, null, 1, 2, 3, null, 1, 4, 5]
 *
 * 解释
 * SmallestInfiniteSet smallestInfiniteSet = new SmallestInfiniteSet();
 * smallestInfiniteSet.addBack(2);    // 2 已经在集合中，所以不做任何变更。
 * smallestInfiniteSet.popSmallest(); // 返回 1 ，因为 1 是最小的整数，并将其从集合中移除。
 * smallestInfiniteSet.popSmallest(); // 返回 2 ，并将其从集合中移除。
 * smallestInfiniteSet.popSmallest(); // 返回 3 ，并将其从集合中移除。
 * smallestInfiniteSet.addBack(1);    // 将 1 添加到该集合中。
 * smallestInfiniteSet.popSmallest(); // 返回 1 ，因为 1 在上一步中被添加到集合中，
 *                                    // 且 1 是最小的整数，并将其从集合中移除。
 * smallestInfiniteSet.popSmallest(); // 返回 4 ，并将其从集合中移除。
 * smallestInfiniteSet.popSmallest(); // 返回 5 ，并将其从集合中移除。
 *  
 *
 * 提示：
 *
 * 1 <= num <= 1000
 * 最多调用 popSmallest 和 addBack 方法 共计 1000 次
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/smallest-number-in-infinite-set
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class SmallestInfiniteSet {

    // 如果没有召回的数据 使用limit 返回
    private int limit;

    private TreeSet<Integer> treeSet;

    public SmallestInfiniteSet() {
        this.limit = 1;
        this.treeSet = new TreeSet<>();
    }

    public int popSmallest() {
        if (treeSet.isEmpty()) {
            int res = this.limit;
            limit++;
            return res;
        }
        return treeSet.pollFirst();
    }

    public void addBack(int num) {
        if (limit <= num) {
            return;
        }
        // limit 已经移动到 num之后了 判断下 priorityQueue 里边有没有
        treeSet.add(num);
    }
}
