package com.potato.study.leetcodecn.other.swordoffer.p0009.p1.t001;

import java.util.Stack;

/**
 * 剑指 Offer 09. 用两个栈实现队列
 *
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )

  

 示例 1：

 输入：
 ["CQueue","appendTail","deleteHead","deleteHead"]
 [[],[3],[],[]]
 输出：[null,null,3,-1]
 示例 2：

 输入：
 ["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
 [[],[],[5],[2],[],[]]
 输出：[null,-1,null,null,5,2]
 提示：

 1 <= values <= 10000
 最多会对 appendTail、deleteHead 进行 10000 次调用

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class CQueue {


    private Stack<Integer> stackIn;

    private Stack<Integer> stackOut;

    public CQueue() {
        this.stackIn = new Stack<>();
        this.stackOut = new Stack<>();
    }

    /**
     * 入队 直接往in 中添加元素 in的top 元素 永远是 最后一个输出的元素
     * @param value
     */
    public void appendTail(int value) {
        stackIn.push(value);
    }

    /**
     * 1. 判断 out 是否非空 非空，直接pop
     * 2. out 空的话 将 in 全部倒入 out 然后pop
     * @return
     */
    public int deleteHead() {
        // 如果有直接pop
        if (!stackOut.isEmpty()) {
            return stackOut.pop();
        }
        // out 空的话 将 in 全部倒入 out 然后pop
        while (!stackIn.isEmpty()) {
            stackOut.push(stackIn.pop());
        }
        // in全到out中 再看有没有
        if (!stackOut.isEmpty()) {
            return stackOut.pop();
        }
        return -1;
    }
}
