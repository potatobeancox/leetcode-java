package com.potato.study.leetcodecn.other.Interview.p0016.p0025;


import java.util.*;

/**
 * 面试题 16.25. LRU 缓存
 *
 *
 *
 * 设计和构建一个“最近最少使用”缓存，该缓存会删除最近最少使用的项目。缓存应该从键映射到值(允许你插入和检索特定键对应的值)，并在初始化时指定最大容量。当缓存被填满时，它应该删除最近最少使用的项目。

 它应该支持以下操作： 获取数据 get 和 写入数据 put 。

 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。

 示例:

 LRUCache cache = new LRUCache( 2  缓存容量  );

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // 返回  1
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        cache.get(2);       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        cache.get(1);       // 返回 -1 (未找到)
        cache.get(3);       // 返回  3
        cache.get(4);       // 返回  4

        来源：力扣（LeetCode）
        链接：https://leetcode.cn/problems/lru-cache-lcci
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class LRUCache {

    private int capacity;

    private Map<Integer, LRUCacheNode> map;
    private LRUCacheNode head;
    private LRUCacheNode tail;

    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.capacity = capacity;
        this.head = new LRUCacheNode();
        this.tail = new LRUCacheNode();

        head.next = tail;
        tail.prev = head;
    }


    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        LRUCacheNode lruCacheNode = map.get(key);
        // 将 lruCacheNode 移动到head next
        moveNodeToHeadNext(lruCacheNode);
        return lruCacheNode.val;
    }

    /**
     * lruCacheNode 存在的时候 将 lruCacheNode 移动到head位置
     * @param lruCacheNode
     */
    private void moveNodeToHeadNext(LRUCacheNode lruCacheNode) {
        LRUCacheNode prev = lruCacheNode.prev;
        LRUCacheNode next = lruCacheNode.next;
        // 前后直接连接了 只要在节点中 就一定有前后的 因为双哨兵了
        prev.next = next;
        next.prev = prev;
        // 将节点移动到head.next
        if (head.next == lruCacheNode) {
            return;
        }
        LRUCacheNode headNext = head.next;
        head.next = lruCacheNode;
        lruCacheNode.prev = head;
        lruCacheNode.next = headNext;
        headNext.prev = lruCacheNode;
    }

    public void put(int key, int value) {
        LRUCacheNode lruCacheNode = map.get(key);
        // 新创建一个
        if (lruCacheNode == null) {
            lruCacheNode = new LRUCacheNode();
            lruCacheNode.val = value;
            lruCacheNode.key = key;
            map.put(key, lruCacheNode);

            // 放到头部
            LRUCacheNode headNext = head.next;
            head.next = lruCacheNode;
            lruCacheNode.prev = head;
            lruCacheNode.next = headNext;
            headNext.prev = lruCacheNode;

        } else {
            // 以前就有
            lruCacheNode.val = value;
            moveNodeToHeadNext(lruCacheNode);
        }
        // 判断是不是超过了
        if (map.size() > capacity) {
            // 要删除 的tail
            LRUCacheNode realTail = tail.prev;
            LRUCacheNode prev = realTail.prev;
            tail.prev = prev;
            prev.next = tail;

            // 删除map
            map.remove(realTail.key);
        }

    }


    class LRUCacheNode {
        public int val;
        public int key;
        public LRUCacheNode next;
        public LRUCacheNode prev;
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));// 返回  1
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        System.out.println(cache.get(2)); // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        System.out.println(cache.get(1)); // 返回 -1 (未找到)
        System.out.println(cache.get(3)); // 返回  3
        System.out.println(cache.get(4)); // 返回  4

    }
}