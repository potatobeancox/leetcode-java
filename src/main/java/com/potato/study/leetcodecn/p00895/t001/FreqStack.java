package com.potato.study.leetcodecn.p00895.t001;

import com.potato.study.leetcode.domain.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 895. 最大频率栈
 *
 * 设计一个类似堆栈的数据结构，将元素推入堆栈，并从堆栈中弹出出现频率最高的元素。
 *
 * 实现 FreqStack 类:
 *
 * FreqStack() 构造一个空的堆栈。
 * void push(int val) 将一个整数 val 压入栈顶。
 * int pop() 删除并返回堆栈中出现频率最高的元素。
 * 如果出现频率最高的元素不只一个，则移除并返回最接近栈顶的元素。
 *  
 *
 * 示例 1：
 *
 * 输入：
 * ["FreqStack","push","push","push","push","push","push","pop","pop","pop","pop"],
 * [[],[5],[7],[5],[7],[4],[5],[],[],[],[]]
 * 输出：[null,null,null,null,null,null,null,5,7,5,4]
 * 解释：
 * FreqStack = new FreqStack();
 * freqStack.push (5);//堆栈为 [5]
 * freqStack.push (7);//堆栈是 [5,7]
 * freqStack.push (5);//堆栈是 [5,7,5]
 * freqStack.push (7);//堆栈是 [5,7,5,7]
 * freqStack.push (4);//堆栈是 [5,7,5,7,4]
 * freqStack.push (5);//堆栈是 [5,7,5,7,4,5]
 * freqStack.pop ();//返回 5 ，因为 5 出现频率最高。堆栈变成 [5,7,5,7,4]。
 * freqStack.pop ();//返回 7 ，因为 5 和 7 出现频率最高，但7最接近顶部。堆栈变成 [5,7,5,4]。
 * freqStack.pop ();//返回 5 ，因为 5 出现频率最高。堆栈变成 [5,7,4]。
 * freqStack.pop ();//返回 4 ，因为 4, 5 和 7 出现频率最高，但 4 是最接近顶部的。堆栈变成 [5,7]。
 *  
 *
 * 提示：
 *
 * 0 <= val <= 109
 * push 和 pop 的操作数不大于 2 * 104。
 * 输入保证在调用 pop 之前堆栈中至少有一个元素。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-frequency-stack
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class FreqStack {

    private Map<Integer, Integer> countMap;
    private int maxCount;

    private Map<Integer, Deque<Integer>> countStackMap;


    public FreqStack() {
        // 一个记录每个 value 出现的次数
        this.countMap = new HashMap<>();
        // 每个出现的次数 对应的栈 每个数字按照新老 老的先进栈 新的后入栈
        countStackMap = new HashMap<>();
        // 记录当前出现次数最多是多少次 就是 从哪里开始 pop
        this.maxCount = 0;
    }


    public void push(int val) {
        // 获取当前的次数
        int count = countMap.getOrDefault(val, 0);
        count++;
        countMap.put(val, count);
        if (count > maxCount) {
            maxCount = count;
        }
        // 放入指定位置
        Deque<Integer> orDefault = countStackMap.getOrDefault(count, new LinkedList<>());
        orDefault.add(val);
        countStackMap.put(count, orDefault);
    }

    public int pop() {
        // 找到max位置 pop i一下 如果是空的 max要往下走了 pop之后 记得改数字
        int maxCount = this.maxCount;
        Deque<Integer> integerDeque = countStackMap.get(maxCount);
        if (integerDeque == null) {
            // 出错了
            return -1;
        }
        int target = integerDeque.pop();
        // 判断是否修改max
        if (integerDeque.isEmpty()) {
            this.maxCount--;
        }
        // 修改 target值
        int count = countMap.get(target);
        countMap.put(target, count-1);
        return target;
    }
}

