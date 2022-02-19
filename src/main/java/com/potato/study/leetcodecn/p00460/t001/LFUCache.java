package com.potato.study.leetcodecn.p00460.t001;

import com.potato.study.leetcode.domain.TreeNode;
import com.potato.study.leetcodecn.p00402.t001.Solution;
import org.junit.Assert;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * 460. LFU 缓存
 *
 * 请你为 最不经常使用（LFU）缓存算法设计并实现数据结构。

 实现 LFUCache 类：

 LFUCache(int capacity) - 用数据结构的容量 capacity 初始化对象
 int get(int key) - 如果键 key 存在于缓存中，则获取键的值，否则返回 -1 。
 void put(int key, int value) - 如果键 key 已存在，则变更其值；如果键不存在，请插入键值对。当缓存达到其容量 capacity 时，则应该在插入新项之前，移除最不经常使用的项。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 最近最久未使用 的键。
 为了确定最不常使用的键，可以为缓存中的每个键维护一个 使用计数器 。使用计数最小的键是最久未使用的键。

 当一个键首次插入到缓存中时，它的使用计数器被设置为 1 (由于 put 操作)。对缓存中的键执行 get 或 put 操作，使用计数器的值将会递增。

 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。

  

 示例：

 输入：
 ["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
 [[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
 输出：
 [null, null, null, 1, null, -1, 3, null, -1, 3, 4]

 解释：
 // cnt(x) = 键 x 的使用计数
 // cache=[] 将显示最后一次使用的顺序（最左边的元素是最近的）
 LFUCache lfu = new LFUCache(2);
 lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
 lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
 lfu.get(1);      // 返回 1
 // cache=[1,2], cnt(2)=1, cnt(1)=2
 lfu.put(3, 3);   // 去除键 2 ，因为 cnt(2)=1 ，使用计数最小
 // cache=[3,1], cnt(3)=1, cnt(1)=2
 lfu.get(2);      // 返回 -1（未找到）
 lfu.get(3);      // 返回 3
 // cache=[3,1], cnt(3)=2, cnt(1)=2
 lfu.put(4, 4);   // 去除键 1 ，1 和 3 的 cnt 相同，但 1 最久未使用
 // cache=[4,3], cnt(4)=1, cnt(3)=2
 lfu.get(1);      // 返回 -1（未找到）
 lfu.get(3);      // 返回 3
 // cache=[3,4], cnt(4)=1, cnt(3)=3
 lfu.get(4);      // 返回 4
 // cache=[3,4], cnt(4)=2, cnt(3)=3
  

 提示：

 0 <= capacity <= 104
 0 <= key <= 105
 0 <= value <= 109
 最多调用 2 * 105 次 get 和 put 方法

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/lfu-cache
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class LFUCache {

    class LFUCacheNode {
        public int key;
        public int value;
        public int time;
        public int count;
    }

    // 当前时刻
    private int time;
    // capacity 容量
    private int capacity;
    // 当前数量
    private int currentCount;

    private Map<Integer, LFUCacheNode> map;

    private TreeSet<LFUCacheNode> set;


    // 用一个数据结构 和treemap 记录超过 的时候 从treemap中移除first
    public LFUCache(int capacity) {
        this.time = 0;
        this.capacity = capacity;
        this.currentCount = 0;
        this.map = new HashMap<>();
        this.set = new TreeSet<LFUCacheNode>((o1, o2) -> {
            int compare = Integer.compare(o1.count, o2.count);
            if (compare != 0) {
                return compare;
            }
            compare = Integer.compare(o1.time, o2.time);
            return compare;
        });
    }

    public int get(int key) {
        if (capacity == 0) {
            return -1;
        }
        // 不存在
        if (!map.containsKey(key)) {
            return -1;
        }
        // 存在
        LFUCacheNode node = map.get(key);
        set.remove(node);
        node.count++;
        node.time = ++this.time;
        set.add(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        // 插入当前key
        if (map.containsKey(key)) {
            LFUCacheNode node = map.get(key);
            set.remove(node);
            node.count++;
            node.time = ++this.time;
            node.value = value;
            set.add(node);
            return;
        }
        // 判断是够大于 capacity 大于需要删除 第一个点
        if (currentCount == capacity) {
            // 弹出
            LFUCacheNode popNode = set.first();
            set.remove(popNode);
            map.remove(popNode.key);
            this.currentCount--;
        }
        LFUCacheNode node = new LFUCacheNode();
        node.key = key;
        node.value = value;
        node.count = 1;
        node.time = ++this.time;
        set.add(node);
        map.put(key, node);
        this.currentCount++;
    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1)); // 1
        cache.put(3, 3);
        System.out.println(cache.get(2)); // -1
        System.out.println(cache.get(3)); // 3
        cache.put(4, 4);
        System.out.println(cache.get(1)); // -1
        System.out.println(cache.get(3)); // 3
        System.out.println(cache.get(4)); // 4


        cache = new LFUCache(2);
        cache.put(3, 1);
        cache.put(2, 1);
        cache.put(2, 2);
        cache.put(4, 4);
        System.out.println(cache.get(2)); // 2



        cache = new LFUCache(2);
        int num = cache.get(2);
        System.out.println(num);
        Assert.assertEquals(-1, num);
        cache.put(2, 6);
        num = cache.get(1);
        System.out.println(num);
        Assert.assertEquals(-1, num);
        cache.put(1, 5);
        cache.put(1, 2);

        num = cache.get(1);
        System.out.println(num); // 2
        Assert.assertEquals(2, num);

        num = cache.get(2);
        System.out.println(num); // 2
        Assert.assertEquals(6, num);

    }
}
