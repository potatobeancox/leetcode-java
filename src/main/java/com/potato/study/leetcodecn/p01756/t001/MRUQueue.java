package com.potato.study.leetcodecn.p01756.t001;

import java.util.LinkedList;

/**
 * 1756. 设计最近使用（MRU）队列

 *
 * 设计一种类似队列的数据结构，该数据结构将最近使用的元素移到队列尾部。

 实现 MRUQueue 类：

 MRUQueue(int n)  使用 n 个元素： [1,2,3,...,n] 构造 MRUQueue 。
 fetch(int k) 将第 k 个元素（从 1 开始索引）移到队尾，并返回该元素。
  

 示例 1：

 输入：
 ["MRUQueue", "fetch", "fetch", "fetch", "fetch"]
 [[8], [3], [5], [2], [8]]
 输出：
 [null, 3, 6, 2, 2]

 解释：
 MRUQueue mRUQueue = new MRUQueue(8); // 初始化队列为 [1,2,3,4,5,6,7,8]。
 mRUQueue.fetch(3); // 将第 3 个元素 (3) 移到队尾，使队列变为 [1,2,4,5,6,7,8,3] 并返回该元素。
 mRUQueue.fetch(5); // 将第 5 个元素 (6) 移到队尾，使队列变为 [1,2,4,5,7,8,3,6] 并返回该元素。
 mRUQueue.fetch(2); // 将第 2 个元素 (2) 移到队尾，使队列变为 [1,4,5,7,8,3,6,2] 并返回该元素。
 mRUQueue.fetch(8); // 第 8 个元素 (2) 已经在队列尾部了，所以直接返回该元素即可。
  

 提示：

 1 <= n <= 2000
 1 <= k <= n
 最多调用 2000 次 fetch
  

 进阶：找到每次 fetch 的复杂度为 O(n) 的算法比较简单。你可以找到每次 fetch 的复杂度更佳的算法吗？

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/design-most-recently-used-queue
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class MRUQueue {

    private LinkedList<Integer> list;

    public MRUQueue(int n) {
        this.list = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
    }

    public int fetch(int k) {
        int target = list.remove(k - 1);
        list.add(target);
        return target;
    }
}
