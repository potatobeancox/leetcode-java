package com.potato.study.leetcodecn.p02080.t001;

import org.junit.Assert;

import java.util.*;

/**
 * 2080. 区间内查询数字的频率
 *
 * 请你设计一个数据结构，它能求出给定子数组内一个给定值的 频率 。

 子数组中一个值的 频率 指的是这个子数组中这个值的出现次数。

 请你实现 RangeFreqQuery 类：

 RangeFreqQuery(int[] arr) 用下标从 0 开始的整数数组 arr 构造一个类的实例。
 int query(int left, int right, int value) 返回子数组 arr[left...right] 中 value 的 频率 。
 一个 子数组 指的是数组中一段连续的元素。arr[left...right] 指的是 nums 中包含下标 left 和 right 在内 的中间一段连续元素。

  

 示例 1：

 输入：
 ["RangeFreqQuery", "query", "query"]
 [[[12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]], [1, 2, 4], [0, 11, 33]]
 输出：
 [null, 1, 2]

 解释：
 RangeFreqQuery rangeFreqQuery = new RangeFreqQuery([12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]);
 rangeFreqQuery.query(1, 2, 4); // 返回 1 。4 在子数组 [33, 4] 中出现 1 次。
 rangeFreqQuery.query(0, 11, 33); // 返回 2 。33 在整个子数组中出现 2 次。
  

 提示：

 1 <= arr.length <= 105
 1 <= arr[i], value <= 104
 0 <= left <= right < arr.length
 调用 query 不超过 105 次。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/range-frequency-queries
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class RangeFreqQuery {

    private Map<Integer, List<Integer>> index2FreqIndexMap;

    public RangeFreqQuery(int[] arr) {
        this.index2FreqIndexMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            List<Integer> list = index2FreqIndexMap.getOrDefault(arr[i], new ArrayList<>());
            list.add(i);
            index2FreqIndexMap.put(arr[i], list);
        }
    }


    /**
     * https://leetcode-cn.com/problems/range-frequency-queries/solution/java-er-fen-by-merickbao-2-phux/
     * 使用二分查找
     * @param left
     * @param right
     * @param value
     * @return
     */
    public int query(int left, int right, int value) {
        List<Integer> list = index2FreqIndexMap.get(value);
        if (list == null || list.size() == 0) {
            return 0;
        }
        int rightIndex = getCeilIndex(right, list);
        int leftIndex = getCeilIndex(left, list);
        if (leftIndex == -1) {
            return 0;
        }
        return rightIndex - leftIndex + 1;


    }


    /**
     * 查找
     * @param target target 并返回第一个下标
     * @param list
     * @return
     */
    private int getCeilIndex(int target, List<Integer> list) {
        if (target < list.get(0)) {
            return -1;
        }
        int left = 0;
        int right = list.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (list.get(mid) < target) {
                left = mid + 1;
            } else {
                // mid 位置 大于等于 target
                if (mid - 1 < 0 || list.get(mid - 1) < target) {
                    return mid;
                }
                right = mid - 1;
            }
        }
        return right;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {
                12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56
        };
        RangeFreqQuery rangeFreqQuery = new RangeFreqQuery(arr);
        int query = rangeFreqQuery.query(1, 2, 4);
        System.out.println(query);
        Assert.assertEquals(1, query);
        query = rangeFreqQuery.query(0, 11, 33);
        System.out.println(query);
        Assert.assertEquals(2, query);


        arr = new int[] {
                1,1,1,2,2
        };
        rangeFreqQuery = new RangeFreqQuery(arr);
        query = rangeFreqQuery.query(0,1,2);
        System.out.println(query);
        Assert.assertEquals(0, query);
    }
}
