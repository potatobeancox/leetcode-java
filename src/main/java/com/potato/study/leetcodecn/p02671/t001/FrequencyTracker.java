package com.potato.study.leetcodecn.p02671.t001;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 2671. 频率跟踪器
 *
 * 请你设计并实现一个能够对其中的值进行跟踪的数据结构，并支持对频率相关查询进行应答。
 *
 * 实现 FrequencyTracker 类：
 *
 * FrequencyTracker()：使用一个空数组初始化 FrequencyTracker 对象。
 * void add(int number)：添加一个 number 到数据结构中。
 * void deleteOne(int number)：从数据结构中删除一个 number 。数据结构 可能不包含 number ，在这种情况下不删除任何内容。
 * bool hasFrequency(int frequency): 如果数据结构中存在出现 frequency 次的数字，则返回 true，否则返回 false。
 *  
 *
 * 示例 1：
 *
 * 输入
 * ["FrequencyTracker", "add", "add", "hasFrequency"]
 * [[], [3], [3], [2]]
 * 输出
 * [null, null, null, true]
 *
 * 解释
 * FrequencyTracker frequencyTracker = new FrequencyTracker();
 * frequencyTracker.add(3); // 数据结构现在包含 [3]
 * frequencyTracker.add(3); // 数据结构现在包含 [3, 3]
 * frequencyTracker.hasFrequency(2); // 返回 true ，因为 3 出现 2 次
 * 示例 2：
 *
 * 输入
 * ["FrequencyTracker", "add", "deleteOne", "hasFrequency"]
 * [[], [1], [1], [1]]
 * 输出
 * [null, null, null, false]
 *
 * 解释
 * FrequencyTracker frequencyTracker = new FrequencyTracker();
 * frequencyTracker.add(1); // 数据结构现在包含 [1]
 * frequencyTracker.deleteOne(1); // 数据结构现在为空 []
 * frequencyTracker.hasFrequency(1); // 返回 false ，因为数据结构为空
 * 示例 3：
 *
 * 输入
 * ["FrequencyTracker", "hasFrequency", "add", "hasFrequency"]
 * [[], [2], [3], [1]]
 * 输出
 * [null, false, null, true]
 *
 * 解释
 * FrequencyTracker frequencyTracker = new FrequencyTracker();
 * frequencyTracker.hasFrequency(2); // 返回 false ，因为数据结构为空
 * frequencyTracker.add(3); // 数据结构现在包含 [3]
 * frequencyTracker.hasFrequency(1); // 返回 true ，因为 3 出现 1 次
 *  
 *
 * 提示：
 *
 * 1 <= number <= 105
 * 1 <= frequency <= 105
 * 最多调用 add、deleteOne 和 hasFrequency 共计 2 * 105 次
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/frequency-tracker
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class FrequencyTracker {



    private Map<Integer, Integer> numberTimesMap;
    private Map<Integer, Integer> frequencyTimeMap;

    /**
     * 初始化 一个map 用来记录 number 出现次数 之后add 和 delete 都是修改出现次数
     * 再用一个 map 记录出现次数出现的个数 每次 add delete后都修改这个
     */
    public FrequencyTracker() {
        this.numberTimesMap = new HashMap<>();
        this.frequencyTimeMap = new HashMap<>();
    }

    public void add(int number) {
        // 都是 2步
        Integer count = numberTimesMap.getOrDefault(number, 0);
        count++;
        numberTimesMap.put(number, count);
        // 将count -1 减去1次 count 加上一次
        Integer frequency = frequencyTimeMap.getOrDefault(count, 0);
        frequency++;
        frequencyTimeMap.put(count, frequency);


        frequency = frequencyTimeMap.getOrDefault(count-1, 0);
        frequency--;
        frequencyTimeMap.put(count-1, frequency);
    }

    public void deleteOne(int number) {
        // 都是 2步
        Integer count = numberTimesMap.getOrDefault(number, 0);
        if (count <= 0) {
            // 已经没有了 直接返回吧
            return;
        }
        count--;
        numberTimesMap.put(number, count);
        // 将count -1 减去1次 count 加上一次
        Integer frequency = frequencyTimeMap.getOrDefault(count, 0);
        frequency++;
        frequencyTimeMap.put(count, frequency);


        frequency = frequencyTimeMap.getOrDefault(count+1, 0);
        frequency--;
        frequencyTimeMap.put(count+1, frequency);

    }

    public boolean hasFrequency(int frequency) {
        int appearTime = frequencyTimeMap.getOrDefault(frequency, 0);
        return appearTime > 0;
    }

    public static void main(String[] args) {
        FrequencyTracker frequencyTracker = new FrequencyTracker();
        //["FrequencyTracker","deleteOne","deleteOne","add","hasFrequency","hasFrequency","hasFrequency"]
        //[[],[2],[1],[1],[1],[1],[1]]
        frequencyTracker.deleteOne(2);
        frequencyTracker.deleteOne(1);
        frequencyTracker.add(1);
        System.out.println(frequencyTracker.hasFrequency(1)); // true
        System.out.println(frequencyTracker.hasFrequency(1)); // true
        System.out.println(frequencyTracker.hasFrequency(1)); // true


    }
}
