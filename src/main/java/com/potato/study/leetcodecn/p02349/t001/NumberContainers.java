package com.potato.study.leetcodecn.p02349.t001;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 2349. 设计数字容器系统
 *
 * 设计一个数字容器系统，可以实现以下功能：

 在系统中给定下标处 插入 或者 替换 一个数字。
 返回 系统中给定数字的最小下标。
 请你实现一个 NumberContainers 类：

 NumberContainers() 初始化数字容器系统。
 void change(int index, int number) 在下标 index 处填入 number 。如果该下标 index 处已经有数字了，那么用 number 替换该数字。
 int find(int number) 返回给定数字 number 在系统中的最小下标。如果系统中没有 number ，那么返回 -1 。
  

 示例：

 输入：
 ["NumberContainers", "find", "change", "change", "change", "change", "find", "change", "find"]
 [[], [10], [2, 10], [1, 10], [3, 10], [5, 10], [10], [1, 20], [10]]
 输出：
 [null, -1, null, null, null, null, 1, null, 2]

 解释：
 NumberContainers nc = new NumberContainers();
 nc.find(10); // 没有数字 10 ，所以返回 -1 。
 nc.change(2, 10); // 容器中下标为 2 处填入数字 10 。
 nc.change(1, 10); // 容器中下标为 1 处填入数字 10 。
 nc.change(3, 10); // 容器中下标为 3 处填入数字 10 。
 nc.change(5, 10); // 容器中下标为 5 处填入数字 10 。
 nc.find(10); // 数字 10 所在的下标为 1 ，2 ，3 和 5 。因为最小下标为 1 ，所以返回 1 。
 nc.change(1, 20); // 容器中下标为 1 处填入数字 20 。注意，下标 1 处之前为 10 ，现在被替换为 20 。
 nc.find(10); // 数字 10 所在下标为 2 ，3 和 5 。最小下标为 2 ，所以返回 2 。
  

 提示：

 1 <= index, number <= 109
 调用 change 和 find 的 总次数 不超过 105 次。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/design-a-number-container-system
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class NumberContainers {

    private Map<Integer, TreeSet<Integer>> number2IndexSetMap;
    private Map<Integer, Integer> indexNumberMap;

    public NumberContainers() {
        this.number2IndexSetMap = new HashMap<>();
        this.indexNumberMap = new HashMap<>();
    }

    public void change(int index, int number) {
        // 找到index 对应number 原来的
        if (indexNumberMap.containsKey(index)) {
            // 在 treeset中 删除 这个index
            int originNumber = indexNumberMap.get(index);
            TreeSet<Integer> treeSet = number2IndexSetMap.getOrDefault(originNumber, new TreeSet<>());
            treeSet.remove(index);
            // 对新的 number 设置 map tresset
            indexNumberMap.put(index, number);
            TreeSet<Integer> replaceSet = number2IndexSetMap.getOrDefault(number, new TreeSet<>());
            replaceSet.add(index);
            number2IndexSetMap.put(number, replaceSet);
        } else {
            // 没有就直接写
            indexNumberMap.put(index, number);
            TreeSet<Integer> treeSet = number2IndexSetMap.getOrDefault(number, new TreeSet<>());
            treeSet.add(index);
            number2IndexSetMap.put(number, treeSet);
        }

    }

    public int find(int number) {
        // 找到第一个 treeset 对应的值
        if (!number2IndexSetMap.containsKey(number)) {
            return -1;
        }
        TreeSet<Integer> treeSet = number2IndexSetMap.get(number);
        if (treeSet.isEmpty()) {
            return -1;
        }
        return treeSet.first();
    }
}
