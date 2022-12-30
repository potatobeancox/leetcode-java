package com.potato.study.leetcodecn.p01993.t001;

import java.util.*;

/**
 * 1993. 树上的操作
 *
 * 给你一棵 n 个节点的树，编号从 0 到 n - 1 ，以父节点数组 parent 的形式给出，其中 parent[i] 是第 i 个节点的父节点。树的根节点为 0 号节点，所以 parent[0] = -1 ，因为它没有父节点。你想要设计一个数据结构实现树里面对节点的加锁，解锁和升级操作。

 数据结构需要支持如下函数：

 Lock：指定用户给指定节点 上锁 ，上锁后其他用户将无法给同一节点上锁。只有当节点处于未上锁的状态下，才能进行上锁操作。
 Unlock：指定用户给指定节点 解锁 ，只有当指定节点当前正被指定用户锁住时，才能执行该解锁操作。
 Upgrade：指定用户给指定节点 上锁 ，并且将该节点的所有子孙节点 解锁 。只有如下 3 个条件 全部 满足时才能执行升级操作：
 指定节点当前状态为未上锁。
 指定节点至少有一个上锁状态的子孙节点（可以是 任意 用户上锁的）。
 指定节点没有任何上锁的祖先节点。
 请你实现 LockingTree 类：

 LockingTree(int[] parent) 用父节点数组初始化数据结构。
 lock(int num, int user) 如果 id 为 user 的用户可以给节点 num 上锁，那么返回 true ，否则返回 false 。如果可以执行此操作，节点 num 会被 id 为 user 的用户 上锁 。
 unlock(int num, int user) 如果 id 为 user 的用户可以给节点 num 解锁，那么返回 true ，否则返回 false 。如果可以执行此操作，节点 num 变为 未上锁 状态。
 upgrade(int num, int user) 如果 id 为 user 的用户可以给节点 num 升级，那么返回 true ，否则返回 false 。如果可以执行此操作，节点 num 会被 升级 。
  

 示例 1：



 输入：
 ["LockingTree", "lock", "unlock", "unlock", "lock", "upgrade", "lock"]
 [[[-1, 0, 0, 1, 1, 2, 2]], [2, 2], [2, 3], [2, 2], [4, 5], [0, 1], [0, 1]]
 输出：
 [null, true, false, true, true, true, false]

 解释：
 LockingTree lockingTree = new LockingTree([-1, 0, 0, 1, 1, 2, 2]);
 lockingTree.lock(2, 2);    // 返回 true ，因为节点 2 未上锁。
 // 节点 2 被用户 2 上锁。
 lockingTree.unlock(2, 3);  // 返回 false ，因为用户 3 无法解锁被用户 2 上锁的节点。
 lockingTree.unlock(2, 2);  // 返回 true ，因为节点 2 之前被用户 2 上锁。
 // 节点 2 现在变为未上锁状态。
 lockingTree.lock(4, 5);    // 返回 true ，因为节点 4 未上锁。
 // 节点 4 被用户 5 上锁。
 lockingTree.upgrade(0, 1); // 返回 true ，因为节点 0 未上锁且至少有一个被上锁的子孙节点（节点 4）。
 // 节点 0 被用户 1 上锁，节点 4 变为未上锁。
 lockingTree.lock(0, 1);    // 返回 false ，因为节点 0 已经被上锁了。
  

 提示：

 n == parent.length
 2 <= n <= 2000
 对于 i != 0 ，满足 0 <= parent[i] <= n - 1
 parent[0] == -1
 0 <= num <= n - 1
 1 <= user <= 104
 parent 表示一棵合法的树。
 lock ，unlock 和 upgrade 的调用 总共 不超过 2000 次。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/operations-on-tree
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class LockingTree {

    // key 是父亲index value是孩子 index集合
    private Map<Integer, Set<Integer>> map;
    // 每个位置谁的锁
    private int[] lock;

    private int[] parent;

    public LockingTree(int[] parent) {
        this.parent = parent;
        // 将parent 转换成 map key是父亲，value是孩子
        int length = parent.length;
        // user 从 1 开始 lock i == 0 代表没有上锁
        this.lock = new int[length];
        // 构造 通过 父亲找孩子的map
        this.map = new HashMap<>();
        for (int i = 0; i < length; i++) {
            map.put(i, new HashSet<>());
        }
        for (int i = 0; i < parent.length; i++) {
            int p = parent[i];
            if (p == -1) {
                continue;
            }
            map.get(p).add(i);
        }
    }

    public boolean lock(int num, int user) {
        if (lock[num] == 0) {
            lock[num] = user;
            return true;
        }
        // user
        return false;
    }

    public boolean unlock(int num, int user) {
        if (lock[num] == user) {
            lock[num] = 0;
            return true;
        }
        return false;
    }

    public boolean upgrade(int num, int user) {
        // 指定节点当前状态为未上锁。
        if (lock[num] != 0) {
            return false;
        }
        // 指定节点至少有一个上锁状态的子孙节点（可以是 任意 用户上锁的）。
        List<Integer> childLockIndexList = new ArrayList<>();
        // queue 遍历
        Queue<Integer> queue = new LinkedList<>();
        queue.add(num);

        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            // poll 的孩子有没有
            if (!map.containsKey(poll)) {
                continue;
            }
            Set<Integer> childSet = map.get(poll);
            for (int child : childSet) {
                if (lock[child] != 0) {
                    childLockIndexList.add(child);
                }
                queue.add(child);
            }
        }
        if (childLockIndexList.size() == 0) {
            return false;
        }
        // 指定节点没有任何上锁的祖先节点。
        Queue<Integer> parentQueue = new LinkedList<>();
        parentQueue.add(num);
        while (!parentQueue.isEmpty()) {
            Integer poll = parentQueue.poll();
            int p = parent[poll];
            if (p == -1) {
                continue;
            }
            // 祖先上有锁
            if (lock[p] != 0) {
                return false;
            }
            parentQueue.add(p);
        }

        // 当前节点上锁
        lock[num] = user;
        // 孩子们解锁
        for (int child : childLockIndexList) {
            lock[child] = 0;
        }
        return true;
    }

    public static void main(String[] args) {
        LockingTree lockingTree = new LockingTree(new int[] {
                -1,0,8,0,7,4,2,3,3,1
        });
        // [8,39],[5,28],[6,33],[9,24],[5,22],[1,3],[5,20],[0,38],[5,14],[6,34],[6,28],[3,23],[4,45],[8,7],[2,18],[3,35],[2,16],[3,21],[1,41],[5,22]]
        lockingTree.upgrade(8,39); // false
        lockingTree.upgrade(5,28); // false
        lockingTree.upgrade(6,33); // false
        lockingTree.upgrade(9,24); // false
        lockingTree.lock(5,22); // true
        lockingTree.upgrade(1,3); // false
        lockingTree.lock(5,20); // false
        lockingTree.upgrade(0,38); // true
        lockingTree.lock(5,14); // true
        lockingTree.lock(6,34); // true
        lockingTree.lock(6,28); // false
        lockingTree.upgrade(3,23); // false
        boolean upgrade = lockingTree.upgrade(4, 45);// false
        System.out.println(upgrade);

    }
}
