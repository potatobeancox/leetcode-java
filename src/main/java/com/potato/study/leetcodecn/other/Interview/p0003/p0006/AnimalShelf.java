package com.potato.study.leetcodecn.other.Interview.p0003.p0006;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 面试题 03.06. 动物收容所
 *
 * 动物收容所。有家动物收容所只收容狗与猫，且严格遵守“先进先出”的原则。在收养该收容所的动物时，收养人只能收养所有动物中“最老”（由其进入收容所的时间长短而定）的动物，或者可以挑选猫或狗（同时必须收养此类动物中“最老”的）。换言之，收养人不能自由挑选想收养的对象。请创建适用于这个系统的数据结构，实现各种操作方法，比如enqueue、dequeueAny、dequeueDog和dequeueCat。允许使用Java内置的LinkedList数据结构。

 enqueue方法有一个animal参数，animal[0]代表动物编号，animal[1]代表动物种类，其中 0 代表猫，1 代表狗。

 dequeue*方法返回一个列表[动物编号, 动物种类]，若没有可以收养的动物，则返回[-1,-1]。

 示例1:

 输入：
 ["AnimalShelf", "enqueue", "enqueue", "dequeueCat", "dequeueDog", "dequeueAny"]
 [[], [[0, 0]], [[1, 0]], [], [], []]
 输出：
 [null,null,null,[0,0],[-1,-1],[1,0]]
 示例2:

 输入：
 ["AnimalShelf", "enqueue", "enqueue", "enqueue", "dequeueDog", "dequeueCat", "dequeueAny"]
 [[], [[0, 0]], [[1, 0]], [[2, 1]], [], [], []]
 输出：
 [null,null,null,null,[2,1],[0,0],[1,0]]
 说明:

 收纳所的最大容量为20000

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/animal-shelter-lcci
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class AnimalShelf {

    private Queue<Integer> catIndexQueue;
    private Queue<Integer> dogIndexQueue;

    public AnimalShelf() {
        this.catIndexQueue = new LinkedList<>();
        this.dogIndexQueue = new LinkedList<>();
    }

    public void enqueue(int[] animal) {
        int type = animal[1];
        int index = animal[0];
        //  0 代表猫，1 代表狗。
        if (type == 0) {
            catIndexQueue.add(index);
        } else {
            dogIndexQueue.add(index);
        }
    }

    public int[] dequeueAny() {
        if (dogIndexQueue.isEmpty() && catIndexQueue.isEmpty()) {
            return new int[] {-1, -1};
        } else if (dogIndexQueue.isEmpty()) {
            Integer poll = catIndexQueue.poll();
            return new int[] {poll, 0};
        } else if (catIndexQueue.isEmpty()) {
            Integer poll = dogIndexQueue.poll();
            return new int[] {poll, 1};
        } else {
            Integer peek1 = dogIndexQueue.peek();
            Integer peek2 = catIndexQueue.peek();
            if (peek1 < peek2) {
                dogIndexQueue.poll();
                return new int[] {peek1, 1};
            } else {
                catIndexQueue.poll();
                return new int[] {peek2, 0};
            }
        }
    }

    public int[] dequeueDog() {
        if (!dogIndexQueue.isEmpty()) {
            Integer poll = dogIndexQueue.poll();
            return new int[] {poll, 1};
        }
        return new int[] {-1, -1};
    }

    public int[] dequeueCat() {
        if (!catIndexQueue.isEmpty()) {
            Integer poll = catIndexQueue.poll();
            return new int[] {poll, 0};
        }
        return new int[] {-1, -1};
    }
}
