package com.potato.study.leetcodecn.p00432.t001;

import com.potato.study.leetcode.domain.multistage.Node;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 432. 全 O(1) 的数据结构
 *
 * 请你设计一个用于存储字符串计数的数据结构，并能够返回计数最小和最大的字符串。

 实现 AllOne 类：

 AllOne() 初始化数据结构的对象。
 inc(String key) 字符串 key 的计数增加 1 。如果数据结构中尚不存在 key ，那么插入计数为 1 的 key 。
 dec(String key) 字符串 key 的计数减少 1 。如果 key 的计数在减少后为 0 ，那么需要将这个 key 从数据结构中删除。测试用例保证：在减少计数前，key 存在于数据结构中。
 getMaxKey() 返回任意一个计数最大的字符串。如果没有元素存在，返回一个空字符串 "" 。
 getMinKey() 返回任意一个计数最小的字符串。如果没有元素存在，返回一个空字符串 "" 。
 注意：每个函数都应当满足 O(1) 平均时间复杂度。

  

 示例：

 输入
 ["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey"]
 [[], ["hello"], ["hello"], [], [], ["leet"], [], []]
 输出
 [null, null, null, "hello", "hello", null, "hello", "leet"]

 解释
 AllOne allOne = new AllOne();
 allOne.inc("hello");
 allOne.inc("hello");
 allOne.getMaxKey(); // 返回 "hello"
 allOne.getMinKey(); // 返回 "hello"
 allOne.inc("leet");
 allOne.getMaxKey(); // 返回 "hello"
 allOne.getMinKey(); // 返回 "leet"
  

 提示：

 1 <= key.length <= 10
 key 由小写英文字母组成
 测试用例保证：在每次调用 dec 时，数据结构中总存在 key
 最多调用 inc、dec、getMaxKey 和 getMinKey 方法 5 * 104 次

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/all-oone-data-structure
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class AllOne {
    private Map<String, Node> keyNodeMap;
    private Node head;
    private Node tail;


    /**
     * map 记录对应key 对应的点
     * 双向链表记录 有序链表 升序
     * 头指针 尾指针
     */
    public AllOne() {
        this.keyNodeMap = new HashMap<>();
        // head tail 哨兵
        head = new Node(-1);
        //  5 * 104 次
        tail = new Node(1_0000_0);

        head.next = tail;
        tail.prev = head;

    }


    /**
     * https://leetcode.cn/problems/all-oone-data-structure/solution/quan-o1-de-shu-ju-jie-gou-by-leetcode-so-7gdv/
     * @param key
     */
    public void inc(String key) {
        // 获取这个 key
        Node node = keyNodeMap.get(key);
        // 没有的话
        if (node == null) {
            Node newNode = new Node(1);
            newNode.key = key;
            // 插入头部
            Node headNext = head.next;

            head.next = newNode;
            newNode.prev = head;
            newNode.next = headNext;
            headNext.prev = newNode;

            keyNodeMap.put(key, newNode);
        } else {
            // 存在的话 往后移动类似 先修改
            node.count++;
            // 不用移动
            if (node.next.count >= node.count) {
                return;
            }
            // 往后移动 找到第一个 大于的位置
            Node nextBigger = node.next;
            while (nextBigger.count <= node.count) {
                nextBigger = nextBigger.next;
            }
            Node nextBiggerPrev = nextBigger.prev;
            // 先将 node 的前后相连
            Node nodePrev = node.prev;
            Node nodeNext = node.next;

            nodePrev.next = nodeNext;
            nodeNext.prev = nodePrev;


            // node 放在 nextBigger 与 nextBiggerPrev 之间
            node.next = nextBigger;
            node.prev = nextBiggerPrev;
            nextBigger.prev = node;
            nextBiggerPrev.next = node;
        }
    }

    public void dec(String key) {
        // 获取这个 key
        Node node = keyNodeMap.get(key);
        node.count--;
        if (node.count == 0) {
            // 需要删除
            Node nodePrev = node.prev;
            Node nodeNext = node.next;

            nodePrev.next = nodeNext;
            nodeNext.prev = nodePrev;

            // 删除map
            node.prev = null;
            node.next = null;
            keyNodeMap.remove(key);
        } else {
            // 需要移动 找到位置
            if (node.prev.count <= node.count) {
                return;
            }
            // 找到第一个小于 node count的位置
            Node nodeSmallerPrev = node.prev;
            while (nodeSmallerPrev.count >= node.count) {
                nodeSmallerPrev = nodeSmallerPrev.prev;
            }
            Node nodeSmallerPrevNext = nodeSmallerPrev.next;


            Node prev = node.prev;
            Node next = node.next;
            prev.next = next;
            next.prev = prev;

            // next 放在 nodeSmallerPrevNext nodeSmallerPrev 之间
            nodeSmallerPrev.next = node;
            nodeSmallerPrevNext.prev = node;

            node.prev = nodeSmallerPrev;
            node.next = nodeSmallerPrevNext;

        }
    }

    public String getMaxKey() {
        if (tail.prev == head) {
            return "";
        }
        return tail.prev.key;
    }

    public String getMinKey() {
        if (head.next == tail) {
            return "";
        }
        return head.next.key;
    }


    class Node {
        public Node prev;
        public Node next;

        public String key;
        public int count;

        public Node(int count) {
            this.count = count;
        }
    }

    public static void main(String[] args) {
        AllOne allOne = new AllOne();
        allOne.inc("a");
        allOne.inc("b");
        allOne.inc("b");
        allOne.inc("b");
        allOne.inc("b");
        // ["AllOne","inc","inc","inc","inc","inc","dec","dec","getMaxKey","getMinKey"]
        // [[],       ["a"],["b"],["b"],["b"],["b"],["b"],["b"],  [],        []]

        allOne.dec("b");
        allOne.dec("b");

        System.out.println(allOne.getMaxKey());
        System.out.println(allOne.getMinKey());
    }
}
