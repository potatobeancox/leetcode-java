package com.potato.study.leetcodecn.other.lcr.p0031.t001;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer II 031. 最近最少使用缓存
 *
 * 运用所掌握的数据结构，设计和实现一个  LRU (Least Recently Used，最近最少使用) 缓存机制 。
 *
 * 实现 LRUCache 类：
 *
 * LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 *  
 *
 * 示例：
 *
 * 输入
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * 输出
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 *
 * 解释
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // 缓存是 {1=1}
 * lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
 * lRUCache.get(1);    // 返回 1
 * lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
 * lRUCache.get(2);    // 返回 -1 (未找到)
 * lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
 * lRUCache.get(1);    // 返回 -1 (未找到)
 * lRUCache.get(3);    // 返回 3
 * lRUCache.get(4);    // 返回 4
 *  
 *
 * 提示：
 *
 * 1 <= capacity <= 3000
 * 0 <= key <= 10000
 * 0 <= value <= 105
 * 最多调用 2 * 105 次 get 和 put
 *  
 *
 * 进阶：是否可以在 O(1) 时间复杂度内完成这两种操作？
 *
 *  
 *
 * 注意：本题与主站 146 题相同：https://leetcode-cn.com/problems/lru-cache/ 
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/OrIXps
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class LRUCache {
    // 031

    private Map<Integer, LRUCacheNode> keyValueMap;
    private LRUCacheNode head;
    private LRUCacheNode tail;

    // 当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
    public LRUCache(int capacity) {
        this.keyValueMap = new HashMap<>();
    }

    public int get(int key) {
        // 返回值 更新使用状态
        if (!keyValueMap.containsKey(key)) {
            return -1;
        }
        LRUCacheNode current = keyValueMap.get(key);
        LRUCacheNode prev = current.prev;
        LRUCacheNode next = current.next;

        moveToTail(current, prev, next);

        return current.value;
    }

    private void moveToTail(LRUCacheNode current, LRUCacheNode prev, LRUCacheNode next) {
        if (prev == null && next == null) {
            // 只有这一个节点
        } else if (prev != null && next != null) {
            // 两边都有 直接连上
            prev.next = next;
            next.prev = prev;

            // 当前节点移动到最后
            tail.next = current;
            current.next = null;
            current.prev = tail;
            // 注意移动 tail
            tail = current;
            // 只有这一个节点
        } else if (prev == null) {
            // head 就是这个点
            head = head.next;
            // 当前节点移动到最后
            tail.next = current;
            current.next = null;
            current.prev = tail;
            // 注意移动 tail
            tail = current;
            // 只有这一个节点
        } else {
            // next == null  这个点已经是末尾了
        }
    }

    public void put(int key, int value) {
        // 更新值 并将其改到 链表尾
        if (!keyValueMap.containsKey(key)) {
            LRUCacheNode lruCacheNode = new LRUCacheNode();
            lruCacheNode.value = value;
            keyValueMap.put(key, lruCacheNode);
            if (tail != null) {
                tail.next = lruCacheNode;
                lruCacheNode.prev = tail;
            }
            tail = lruCacheNode;
            // 已经在末尾了
            // 只有一个点的时候 要处理
            if (head == null) {
                // 当前就是第一个点
                head = null;
            }

            return;
        }
        //  存在的时候
        LRUCacheNode current = keyValueMap.get(key);
        current.value = value;
        LRUCacheNode prev = current.prev;
        LRUCacheNode next = current.next;

        moveToTail(current, prev, next);
    }

    // 节点 记录 前后
    class LRUCacheNode {
        private LRUCacheNode prev;
        private LRUCacheNode next;

        private int value;
    }
}
