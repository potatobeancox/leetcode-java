package com.potato.study.leetcodecn.p00381.t001;

import java.util.*;

import com.potato.study.leetcodecn.p02121.t001.Solution;

/**
 * 381. O(1) 时间插入、删除和获取随机元素 - 允许重复
 *
 * RandomizedCollection 是一种包含数字集合(可能是重复的)的数据结构。它应该支持插入和删除特定元素，以及删除随机元素。

 实现 RandomizedCollection 类:

 RandomizedCollection()初始化空的 RandomizedCollection 对象。
 bool insert(int val) 将一个 val 项插入到集合中，即使该项已经存在。如果该项不存在，则返回 true ，否则返回 false 。
 bool remove(int val) 如果存在，从集合中移除一个 val 项。如果该项存在，则返回 true ，否则返回 false 。注意，如果 val 在集合中出现多次，我们只删除其中一个。
 int getRandom() 从当前的多个元素集合中返回一个随机元素。每个元素被返回的概率与集合中包含的相同值的数量 线性相关 。
 您必须实现类的函数，使每个函数的 平均 时间复杂度为 O(1) 。

 注意：生成测试用例时，只有在 RandomizedCollection 中 至少有一项 时，才会调用 getRandom 。

  

 示例 1:

 输入
 ["RandomizedCollection", "insert", "insert", "insert", "getRandom", "remove", "getRandom"]
 [[], [1], [1], [2], [], [1], []]
 输出
 [null, true, false, true, 2, true, 1]

 解释
 RandomizedCollection collection = new RandomizedCollection();// 初始化一个空的集合。
 collection.insert(1);// 向集合中插入 1 。返回 true 表示集合不包含 1 。
 collection.insert(1);// 向集合中插入另一个 1 。返回 false 表示集合包含 1 。集合现在包含 [1,1] 。
 collection.insert(2);// 向集合中插入 2 ，返回 true 。集合现在包含 [1,1,2] 。
 collection.getRandom();// getRandom 应当有 2/3 的概率返回 1 ，1/3 的概率返回 2 。
 collection.remove(1);// 从集合中删除 1 ，返回 true 。集合现在包含 [1,2] 。
 collection.getRandom();// getRandom 应有相同概率返回 1 和 2 。
  

 提示:

 -231 <= val <= 231 - 1
 insert, remove 和 getRandom 最多 总共 被调用 2 * 105 次
 当调用 getRandom 时，数据结构中 至少有一个 元素

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/insert-delete-getrandom-o1-duplicates-allowed
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class RandomizedCollection {

    // 存当前有的数字
    private List<Integer> numList;

    private Random random;

    private Map<Integer, Set<Integer>> valueIndexMap;

    /** Initialize your data structure here. */
    public RandomizedCollection() {
        this.numList = new ArrayList<>();
        this.random = new Random();
        this.valueIndexMap = new HashMap<>();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        // 将 val 放到 num最后
        this.numList.add(val);
        int index = numList.size() - 1;
        // 将 val 对应 index 放入 map 中的位置
        Set<Integer> indexSet = valueIndexMap.getOrDefault(val, new HashSet<>());
        indexSet.add(index);
        valueIndexMap.put(val, indexSet);
        // 等于 1 说明刚刚 插入的
        return indexSet.size() == 1;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        // 看下 val 是否存在
        if (!valueIndexMap.containsKey(val) || valueIndexMap.get(val).size() <= 0) {
            return false;
        }
        // 找到 val 出现的第一个位置 和 最后一个元素 出现的位置
        Set<Integer> set = valueIndexMap.get(val);
        Iterator<Integer> iterator = set.iterator();
        Integer removeIndex = iterator.next();

        Integer lastNum = numList.get(numList.size() - 1);
        numList.set(removeIndex, lastNum);

        set.remove(removeIndex);
        Set<Integer> lastNumIndexSet = valueIndexMap.get(lastNum);
        lastNumIndexSet.remove(numList.size() - 1);

        if (removeIndex < numList.size() - 1) {
            lastNumIndexSet.add(removeIndex);
        }

        if (set.size() == 0) {
            valueIndexMap.remove(val);
        }

        numList.remove(numList.size() - 1);
        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        // 随机数
        int index = random.nextInt(numList.size());
        return numList.get(index);
    }

    public static void main(String[] args) {

//        ["RandomizedCollection","insert","remove","insert","remove","getRandom","getRandom","getRandom","getRandom","getRandom","getRandom","getRandom","getRandom","getRandom","getRandom"]
//           [[],                    [0],     [0],     [-1],    [0],     [],[],[],[],[],[],[],[],[],[]]

        RandomizedCollection randomizedCollection = new RandomizedCollection();
        randomizedCollection.insert(0);
        randomizedCollection.remove(0);
        randomizedCollection.insert(-1);
        randomizedCollection.remove(0);

        randomizedCollection.getRandom();
    }
}
