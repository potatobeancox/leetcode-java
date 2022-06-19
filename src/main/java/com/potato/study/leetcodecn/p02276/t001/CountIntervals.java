package com.potato.study.leetcodecn.p02276.t001;

import java.util.TreeMap;

/**
 * 2276. 统计区间中的整数数目
 *
 * 给你区间的 空 集，请你设计并实现满足要求的数据结构：

 新增：添加一个区间到这个区间集合中。
 统计：计算出现在 至少一个 区间中的整数个数。
 实现 CountIntervals 类：

 CountIntervals() 使用区间的空集初始化对象
 void add(int left, int right) 添加区间 [left, right] 到区间集合之中。
 int count() 返回出现在 至少一个 区间中的整数个数。
 注意：区间 [left, right] 表示满足 left <= x <= right 的所有整数 x 。

  

 示例 1：

 输入
 ["CountIntervals", "add", "add", "count", "add", "count"]
 [[], [2, 3], [7, 10], [], [5, 8], []]
 输出
 [null, null, null, 6, null, 8]

 解释
 CountIntervals countIntervals = new CountIntervals(); // 用一个区间空集初始化对象
 countIntervals.add(2, 3);  // 将 [2, 3] 添加到区间集合中
 countIntervals.add(7, 10); // 将 [7, 10] 添加到区间集合中
 countIntervals.count();    // 返回 6
 // 整数 2 和 3 出现在区间 [2, 3] 中
 // 整数 7、8、9、10 出现在区间 [7, 10] 中
 countIntervals.add(5, 8);  // 将 [5, 8] 添加到区间集合中
 countIntervals.count();    // 返回 8
 // 整数 2 和 3 出现在区间 [2, 3] 中
 // 整数 5 和 6 出现在区间 [5, 8] 中
 // 整数 7 和 8 出现在区间 [5, 8] 和区间 [7, 10] 中
 // 整数 9 和 10 出现在区间 [7, 10] 中
  

 提示：

 1 <= left <= right <= 109
 最多调用  add 和 count 方法 总计 105 次
 调用 count 方法至少一次


 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/count-integers-in-intervals
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class CountIntervals {


    private TreeMap<Integer, Integer> treeMap;
    private int count;

    /**
     * https://leetcode.cn/problems/count-integers-in-intervals/solution/by-endlesscheng-clk2/
     */
    public CountIntervals() {
        this.treeMap = new TreeMap<>();
        this.count = 0;
    }

    public void add(int left, int right) {
        // treeMap key right value left 找到第一个大于 left 的right
        Integer nextRight = treeMap.ceilingKey(left);
        // 需要合并 判断是否有交集
        while (nextRight != null && treeMap.get(nextRight) <= right) {
            // 减去这个区间
            Integer nextLeft = treeMap.remove(nextRight);
            this.count -= (nextRight - nextLeft + 1);
            left = Math.min(left, nextLeft);
            right = Math.max(right, nextRight);
            // 往下一个 继续寻找
            nextRight = treeMap.ceilingKey(left);
        }
        // 最终加入总的区间
        this.count += (right - left + 1);
        treeMap.put(right, left);
    }

    public int count() {
        return this.count;
    }

    public static void main(String[] args) {
        CountIntervals countIntervals = new CountIntervals();
        countIntervals.add(2, 3);
        countIntervals.add(7, 10);
        int count1 = countIntervals.count();
        // 6
        System.out.println(count1);

        countIntervals.add(5, 8);
        // 8
        int count2 = countIntervals.count();
        System.out.println(count2);
    }
}