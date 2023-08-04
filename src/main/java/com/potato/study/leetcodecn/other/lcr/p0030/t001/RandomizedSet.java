package com.potato.study.leetcodecn.other.lcr.p0030.t001;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 剑指 Offer II 030. 插入、删除和随机访问都是 O(1) 的容器
 *
 * 设计一个支持在平均 时间复杂度 O(1) 下，执行以下操作的数据结构：
 *
 * insert(val)：当元素 val 不存在时返回 true ，并向集合中插入该项，否则返回 false 。
 * remove(val)：当元素 val 存在时返回 true ，并从集合中移除该项，否则返回 false 。
 * getRandom：随机返回现有集合中的一项。每个元素应该有 相同的概率 被返回。
 *  
 *
 * 示例 :
 *
 * 输入: inputs = ["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
 * [[], [1], [2], [2], [], [1], [2], []]
 * 输出: [null, true, false, true, 2, true, false, 2]
 * 解释:
 * RandomizedSet randomSet = new RandomizedSet();  // 初始化一个空的集合
 * randomSet.insert(1); // 向集合中插入 1 ， 返回 true 表示 1 被成功地插入
 *
 * randomSet.remove(2); // 返回 false，表示集合中不存在 2
 *
 * randomSet.insert(2); // 向集合中插入 2 返回 true ，集合现在包含 [1,2]
 *
 * randomSet.getRandom(); // getRandom 应随机返回 1 或 2
 *
 * randomSet.remove(1); // 从集合中移除 1 返回 true 。集合现在包含 [2]
 *
 * randomSet.insert(2); // 2 已在集合中，所以返回 false
 *
 * randomSet.getRandom(); // 由于 2 是集合中唯一的数字，getRandom 总是返回 2
 *  
 *
 * 提示：
 *
 * -231 <= val <= 231 - 1
 * 最多进行 2 * 105 次 insert ， remove 和 getRandom 方法调用
 * 当调用 getRandom 方法时，集合中至少有一个元素
 *  
 *
 * 注意：本题与主站 380 题相同：https://leetcode-cn.com/problems/insert-delete-getrandom-o1/
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/FortPu
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class RandomizedSet {

    // ii 030
    private Random random;

    /**
     * 通过 value 找到map
     */
    private Map<Integer, Integer> valueIndexMap;

    /**
     * 当前
     */
    private int[] array;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        this.random = new Random();
        this.valueIndexMap = new HashMap<>();
        this.array = new int[2 * 1_00000 + 1];
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        // 如果存在
        if (valueIndexMap.containsKey(val)) {
            return false;
        }
        // 当前插入的index
        int insertIndex = valueIndexMap.size();
        array[insertIndex] = val;
        valueIndexMap.put(val, insertIndex);

        insertIndex++;
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        // 不存在
        if (!valueIndexMap.containsKey(val)) {
            return false;
        }
        // 存在 找到 index 删除（使用最后一个位置替换）
        int removeIndex = valueIndexMap.remove(val);
        // 最后一个位置不用处理
        if (removeIndex == valueIndexMap.size()) {
            return true;
        }
        // 不是最后一个位置使用最后u一个位置填充
        array[removeIndex] = array[valueIndexMap.size()];
        // 更新位置
        valueIndexMap.put(array[removeIndex], removeIndex);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        int index = random.nextInt(valueIndexMap.size());
        return array[index];
    }
}
