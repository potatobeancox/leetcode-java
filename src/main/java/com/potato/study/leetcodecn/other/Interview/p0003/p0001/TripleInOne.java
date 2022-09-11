package com.potato.study.leetcodecn.other.Interview.p0003.p0001;


import java.util.Stack;

/**
 * 面试题 03.01. 三合一
 *
 * 三合一。描述如何只用一个数组来实现三个栈。
 *
 * 你应该实现push(stackNum, value)、pop(stackNum)、isEmpty(stackNum)、peek(stackNum)方法。stackNum表示栈下标，value表示压入的值。
 *
 * 构造函数会传入一个stackSize参数，代表每个栈的大小。
 *
 * 示例1:
 *
 *  输入：
 * ["TripleInOne", "push", "push", "pop", "pop", "pop", "isEmpty"]
 * [[1], [0, 1], [0, 2], [0], [0], [0], [0]]
 *  输出：
 * [null, null, null, 1, -1, -1, true]
 * 说明：当栈为空时`pop, peek`返回-1，当栈满时`push`不压入元素。
 * 示例2:
 *
 *  输入：
 * ["TripleInOne", "push", "push", "push", "pop", "pop", "pop", "peek"]
 * [[2], [0, 1], [0, 2], [0, 3], [0], [0], [0], [0]]
 *  输出：
 * [null, null, null, null, 2, 1, -1, -1]
 *  
 *
 * 提示：
 *
 * 0 <= stackNum <= 2
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/three-in-one-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class TripleInOne {

    private int[][] arr;
    private int[] index;


    public TripleInOne(int stackSize) {
        this.arr = new int[3][stackSize];
        // 初始都是 0
        this.index = new int[3];
    }

    public void push(int stackNum, int value) {
        int[] stack = arr[stackNum];
        // 满了
        if (stack.length == index[stackNum]) {
            return;
        }
        stack[index[stackNum]] = value;
        index[stackNum]++;
    }

    public int pop(int stackNum) {
        if (isEmpty(stackNum)) {
            return -1;
        }
        int[] stack = arr[stackNum];
        int value = stack[index[stackNum]-1];
        index[stackNum]--;
        return value;
    }

    public int peek(int stackNum) {
        if (isEmpty(stackNum)) {
            return -1;
        }
        int[] stack = arr[stackNum];
        return stack[index[stackNum]-1];
    }

    public boolean isEmpty(int stackNum) {
        return index[stackNum] == 0;
    }
}
