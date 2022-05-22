package com.potato.study.leetcodecn.p01834.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 1834. 单线程 CPU
 *
 * 给你一个二维数组 tasks ，用于表示 n​​​​​​ 项从 0 到 n - 1 编号的任务。其中 tasks[i] = [enqueueTimei, processingTimei] 意味着第 i​​​​​​​​​​
 * 项任务将会于 enqueueTimei 时进入任务队列，需要 processingTimei 的时长完成执行。
 *
 * 现有一个单线程 CPU ，同一时间只能执行 最多一项 任务，该 CPU 将会按照下述方式运行：
 *
 * 如果 CPU 空闲，且任务队列中没有需要执行的任务，则 CPU 保持空闲状态。
 * 如果 CPU 空闲，但任务队列中有需要执行的任务，则 CPU 将会选择 执行时间最短 的任务开始执行。如果多个任务具有同样的最短执行时间，则选择下标最小的任务开始执行。
 * 一旦某项任务开始执行，CPU 在 执行完整个任务 前都不会停止。
 * CPU 可以在完成一项任务后，立即开始执行一项新任务。
 * 返回 CPU 处理任务的顺序。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：tasks = [[1,2],[2,4],[3,2],[4,1]]
 * 输出：[0,2,3,1]
 * 解释：事件按下述流程运行：
 * - time = 1 ，任务 0 进入任务队列，可执行任务项 = {0}
 * - 同样在 time = 1 ，空闲状态的 CPU 开始执行任务 0 ，可执行任务项 = {}
 * - time = 2 ，任务 1 进入任务队列，可执行任务项 = {1}
 * - time = 3 ，任务 2 进入任务队列，可执行任务项 = {1, 2}
 * - 同样在 time = 3 ，CPU 完成任务 0 并开始执行队列中用时最短的任务 2 ，可执行任务项 = {1}
 * - time = 4 ，任务 3 进入任务队列，可执行任务项 = {1, 3}
 * - time = 5 ，CPU 完成任务 2 并开始执行队列中用时最短的任务 3 ，可执行任务项 = {1}
 * - time = 6 ，CPU 完成任务 3 并开始执行任务 1 ，可执行任务项 = {}
 * - time = 10 ，CPU 完成任务 1 并进入空闲状态
 * 示例 2：
 *
 * 输入：tasks = [[7,10],[7,12],[7,5],[7,4],[7,2]]
 * 输出：[4,3,2,0,1]
 * 解释：事件按下述流程运行：
 * - time = 7 ，所有任务同时进入任务队列，可执行任务项  = {0,1,2,3,4}
 * - 同样在 time = 7 ，空闲状态的 CPU 开始执行任务 4 ，可执行任务项 = {0,1,2,3}
 * - time = 9 ，CPU 完成任务 4 并开始执行任务 3 ，可执行任务项 = {0,1,2}
 * - time = 13 ，CPU 完成任务 3 并开始执行任务 2 ，可执行任务项 = {0,1}
 * - time = 18 ，CPU 完成任务 2 并开始执行任务 0 ，可执行任务项 = {1}
 * - time = 28 ，CPU 完成任务 0 并开始执行任务 1 ，可执行任务项 = {}
 * - time = 40 ，CPU 完成任务 1 并进入空闲状态
 *  
 *
 * 提示：
 *
 * tasks.length == n
 * 1 <= n <= 105
 * 1 <= enqueueTimei, processingTimei <= 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/single-threaded-cpu
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int[] getOrder(int[][] tasks) {
        // 对 tasks 按照达到时间 升序 排序
        Integer[] indexArr = new Integer[tasks.length];
        for (int i = 0; i < indexArr.length; i++) {
            indexArr[i] = i;
        }
        Arrays.sort(indexArr, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(tasks[o1][0], tasks[o2][0]);
            }
        });
        // 当前时间从 1开始往后 每次 先吧 task 时间小于等于 time 入 优先级队列
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((o1, o2) -> {
            // 0->执行时间, 1->index
            int compare = Integer.compare(o1[0], o2[0]);
            if (compare != 0) {
                return compare;
            }
            return Integer.compare(o1[1], o2[1]);
        });
        // 队列 排序优先级： 执行时间升序， index 升序
        int time = 1;
        int taskIndex = 0;
        int[] result = new int[indexArr.length];
        int resultIndex = 0;
        while (taskIndex < indexArr.length || !priorityQueue.isEmpty()) {
            // 当前时间 比task 时间小
            if (taskIndex < indexArr.length && time < tasks[indexArr[taskIndex]][0]) {
                time = tasks[indexArr[taskIndex]][0];
            }
            // 先往里遍 放已经到达的任务
            while (taskIndex < indexArr.length && tasks[indexArr[taskIndex]][0] <= time) {
                priorityQueue.add(new int[] {
                        tasks[indexArr[taskIndex]][1], indexArr[taskIndex]
                });
                taskIndex++;
            }
            // 执行一个任务 修改 time 时间
            if (!priorityQueue.isEmpty()) {
                int[] poll = priorityQueue.poll();
                result[resultIndex] = poll[1];
                resultIndex++;
                time += poll[0];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "[[1,2],[2,4],[3,2],[4,1]]";
        int[][] ints = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        int[] order = solution.getOrder(ints);
        System.out.println(Arrays.toString(order));
        Assert.assertArrayEquals(new int[] {
                0,2,3,1
        }, order);


        input = "[[7,10],[7,12],[7,5],[7,4],[7,2]]";
        ints = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        order = solution.getOrder(ints);
        System.out.println(Arrays.toString(order));
        Assert.assertArrayEquals(new int[] {
                4,3,2,0,1
        }, order);

        // [[19,13],[16,9],[21,10],[32,25],[37,4],[49,24],[2,15],[38,41],[37,34],[33,6],[45,4],[18,18],[46,39],[12,24]]
        input = "[[19,13],[16,9],[21,10],[32,25],[37,4],[49,24],[2,15],[38,41],[37,34],[33,6],[45,4],[18,18],[46,39],[12,24]]";
        ints = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        order = solution.getOrder(ints);
        System.out.println(Arrays.toString(order));
        Assert.assertArrayEquals(new int[] {
                6,1,2,9,4,10,0,11,5,13,3,8,12,7
        }, order);


        input = "[[35,36],[11,7],[15,47],[34,2],[47,19],[16,14],[19,8],[7,34],[38,15],[16,18],[27,22],[7,15],[43,2],[10,5],[5,4],[3,11]]";
        ints = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        order = solution.getOrder(ints);
        System.out.println(Arrays.toString(order));
        Assert.assertArrayEquals(new int[] {
                15,14,13,1,6,3,5,12,8,11,9,4,10,7,0,2
        }, order);
    }
}
