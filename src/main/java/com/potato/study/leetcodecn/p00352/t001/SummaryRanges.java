package com.potato.study.leetcodecn.p00352.t001;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Assert;
import org.omg.PortableInterceptor.INACTIVE;

/**
 * 352. 将数据流变为多个不相交区间
 *
 * 给你一个由非负整数 a1, a2, ..., an 组成的数据流输入，请你将到目前为止看到的数字总结为不相交的区间列表。
 *
 * 实现 SummaryRanges 类：
 *
 * SummaryRanges() 使用一个空数据流初始化对象。
 * void addNum(int val) 向数据流中加入整数 val 。
 * int[][] getIntervals() 以不相交区间 [starti, endi] 的列表形式返回对数据流中整数的总结。
 *  
 *
 * 示例：
 *
 * 输入：
 * ["SummaryRanges", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum",
 * "getIntervals", "addNum", "getIntervals"]
 * [[], [1], [], [3], [], [7], [], [2], [], [6], []]
 * 输出：
 * [null, null, [[1, 1]], null, [[1, 1], [3, 3]], null, [[1, 1], [3, 3], [7, 7]], null, [[1, 3], [7, 7]], null, [[1,
 * 3], [6, 7]]]
 *
 * 解释：
 * SummaryRanges summaryRanges = new SummaryRanges();
 * summaryRanges.addNum(1);      // arr = [1]
 * summaryRanges.getIntervals(); // 返回 [[1, 1]]
 * summaryRanges.addNum(3);      // arr = [1, 3]
 * summaryRanges.getIntervals(); // 返回 [[1, 1], [3, 3]]
 * summaryRanges.addNum(7);      // arr = [1, 3, 7]
 * summaryRanges.getIntervals(); // 返回 [[1, 1], [3, 3], [7, 7]]
 * summaryRanges.addNum(2);      // arr = [1, 2, 3, 7]
 * summaryRanges.getIntervals(); // 返回 [[1, 3], [7, 7]]
 * summaryRanges.addNum(6);      // arr = [1, 2, 3, 6, 7]
 * summaryRanges.getIntervals(); // 返回 [[1, 3], [6, 7]]
 *  
 *
 * 提示：
 *
 * 0 <= val <= 104
 * 最多调用 addNum 和 getIntervals 方法 3 * 104 次
 *  
 *
 * 进阶：如果存在大量合并，并且与数据流的大小相比，不相交区间的数量很小，该怎么办?
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/data-stream-as-disjoint-intervals
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class SummaryRanges {

    private TreeMap<Integer, Integer> map;

    /**
     * 使用 treemap 记录底层结构
     * key 区间开始位置
     * value 区间的结束位置
     * https://leetcode-cn.com/problems/data-stream-as-disjoint-intervals/solution/jiang-shu-ju-liu-bian-wei-duo-ge-bu-xian-hm1r/
     */
    public SummaryRanges() {
        this.map = new TreeMap<>();
    }

    /**
     * 使用 cieling 和 floor 函数
     * ceiling 函数 找到 比 当前给的只 val + 1 还大的区间的看是
     * floor 找到 小于等于 val 的开始位置
     * @param val
     */
    public void addNum(int val) {
        // 找到 floor 和 ceil val + 1位置的区间
        java.util.Map.Entry<Integer, Integer> floorEntry = map.floorEntry(val);
        java.util.Map.Entry<Integer, Integer> ceilingEntry = map.ceilingEntry(val + 1);
        // 如果当前val 属于 floor（开始小于等于） val 的区间 不用变化
        if (floorEntry != null && floorEntry.getKey() <= val && floorEntry.getValue() >= val) {
            return;
        }
        // 如果 val 位于 floor 末尾 且 是 ceil头 连上两个区间
        if (ceilingEntry != null && floorEntry != null
                && floorEntry.getValue() + 1 == val
                && ceilingEntry.getKey() - 1 == val) {
            map.remove(floorEntry.getKey());
            map.remove(ceilingEntry.getKey());
            map.put(floorEntry.getKey(), ceilingEntry.getValue());
            return;
        }
        // 如果 val 位于 floor 末尾 延长 floor
        if (floorEntry != null && floorEntry.getValue() + 1 == val) {
            map.remove(floorEntry.getKey());
            map.put(floorEntry.getKey(), val);
            return;
        }
        // 如果 val 位于 ceil 头部 延长
        if (ceilingEntry != null && ceilingEntry.getKey() -  1 == val) {
            map.remove(ceilingEntry.getKey());
            map.put(val, ceilingEntry.getValue());
            return;
        }
        // 增加 【value, value】
        map.put(val, val);
    }

    public int[][] getIntervals() {
        // 遍历 返回
        int[][] interval = new int[map.size()][2];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            interval[index++] = new int[] {
                    entry.getKey(), entry.getValue()
            };
        }
        return interval;
    }
}
