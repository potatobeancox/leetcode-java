package com.potato.study.leetcodecn.p01882.t001;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import org.junit.Assert;

/**
 * 1882. 使用服务器处理任务
 *
 * 给你两个 下标从 0 开始 的整数数组 servers 和 tasks ，长度分别为 n​​​​​​ 和 m​​​​​​ 。servers[i] 是第 i​​​​​​​​​​ 台服务器的 权重 ，而 tasks[j] 是处理第
 * j​​​​​​ 项任务 所需要的时间（单位：秒）。
 *
 * 你正在运行一个仿真系统，在处理完所有任务后，该系统将会关闭。每台服务器只能同时处理一项任务。第 0 项任务在第 0 秒可以开始处理，相应地，第 j 项任务在第 j 秒可以开始处理。处理第 j 项任务时，你需要为它分配一台
 * 权重最小 的空闲服务器。如果存在多台相同权重的空闲服务器，请选择 下标最小 的服务器。如果一台空闲服务器在第 t 秒分配到第 j 项任务，那么在 t + tasks[j] 时它将恢复空闲状态。
 *
 * 如果没有空闲服务器，则必须等待，直到出现一台空闲服务器，并 尽可能早 地处理剩余任务。 如果有多项任务等待分配，则按照 下标递增 的顺序完成分配。
 *
 * 如果同一时刻存在多台空闲服务器，可以同时将多项任务分别分配给它们。
 *
 * 构建长度为 m 的答案数组 ans ，其中 ans[j] 是第 j 项任务分配的服务器的下标。
 *
 * 返回答案数组 ans​​​​ 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：servers = [3,3,2], tasks = [1,2,3,2,1,2]
 * 输出：[2,2,0,2,1,2]
 * 解释：事件按时间顺序如下：
 * - 0 秒时，第 0 项任务加入到任务队列，使用第 2 台服务器处理到 1 秒。
 * - 1 秒时，第 2 台服务器空闲，第 1 项任务加入到任务队列，使用第 2 台服务器处理到 3 秒。
 * - 2 秒时，第 2 项任务加入到任务队列，使用第 0 台服务器处理到 5 秒。
 * - 3 秒时，第 2 台服务器空闲，第 3 项任务加入到任务队列，使用第 2 台服务器处理到 5 秒。
 * - 4 秒时，第 4 项任务加入到任务队列，使用第 1 台服务器处理到 5 秒。
 * - 5 秒时，所有服务器都空闲，第 5 项任务加入到任务队列，使用第 2 台服务器处理到 7 秒。
 * 示例 2：
 *
 * 输入：servers = [5,1,4,3,2], tasks = [2,1,2,4,5,2,1]
 * 输出：[1,4,1,4,1,3,2]
 * 解释：事件按时间顺序如下：
 * - 0 秒时，第 0 项任务加入到任务队列，使用第 1 台服务器处理到 2 秒。
 * - 1 秒时，第 1 项任务加入到任务队列，使用第 4 台服务器处理到 2 秒。
 * - 2 秒时，第 1 台和第 4 台服务器空闲，第 2 项任务加入到任务队列，使用第 1 台服务器处理到 4 秒。
 * - 3 秒时，第 3 项任务加入到任务队列，使用第 4 台服务器处理到 7 秒。
 * - 4 秒时，第 1 台服务器空闲，第 4 项任务加入到任务队列，使用第 1 台服务器处理到 9 秒。
 * - 5 秒时，第 5 项任务加入到任务队列，使用第 3 台服务器处理到 7 秒。
 * - 6 秒时，第 6 项任务加入到任务队列，使用第 2 台服务器处理到 7 秒。
 *  
 *
 * 提示：
 *
 * servers.length == n
 * tasks.length == m
 * 1 <= n, m <= 2 * 105
 * 1 <= servers[i], tasks[j] <= 2 * 105
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/process-tasks-using-servers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * server 按照权重排序
     * @param servers
     * @param tasks
     * @return
     */
    public int[] assignTasks(int[] servers, int[] tasks) {
        // 构造 arr Server 按照 weight 升序 id 升序排序
        Server[] serverArray = new Server[servers.length];
        // 两个队列了 一个 是空闲的 一个是满的
        PriorityQueue<Server> idlePriorityQueue = new PriorityQueue<>(new Comparator<Server>() {
            @Override
            public int compare(Server o1, Server o2) {
                int compare = Integer.compare(o1.weight, o2.weight);
                if (compare != 0) {
                    return compare;
                }
                compare = Integer.compare(o1.id, o2.id);
                return compare;
            }
        });


        PriorityQueue<Server> fullPriorityQueue = new PriorityQueue<>(new Comparator<Server>() {
            @Override
            public int compare(Server o1, Server o2) {
                // 先按照空闲时间排序
                int compareTime = Integer.compare(o1.idleFromTime, o2.idleFromTime);
                return compareTime;
            }
        });


        for (int i = 0; i < servers.length; i++) {
            serverArray[i] = new Server();
            serverArray[i].id = i;
            serverArray[i].weight = servers[i];
            serverArray[i].idleFromTime = 0;

            idlePriorityQueue.add(serverArray[i]);
        }

        // 遍历 task 对于 并记录时间 也就是 index 从 arr 中 从头开始 遍历得到 第一个当前秒空闲的服务器 ，然后就用这个生成结果
        int[] res = new int[tasks.length];
        // 当前时间
        int time = 0;
        for (int i = 0; i < tasks.length; i++) {
            // 对于当前时间 full 之前的 pop 出来 放到idle里边
            while (!fullPriorityQueue.isEmpty() && fullPriorityQueue.peek().idleFromTime <= time) {
                Server poll = fullPriorityQueue.poll();
                idlePriorityQueue.add(poll);
            }
            // 如果当前还没到时间 那么将time 推移到 fullPriorityQueue .peek
            if (idlePriorityQueue.isEmpty()) {
                time = fullPriorityQueue.peek().idleFromTime;
                while (!fullPriorityQueue.isEmpty() && fullPriorityQueue.peek().idleFromTime <= time) {
                    Server poll = fullPriorityQueue.poll();
                    idlePriorityQueue.add(poll);
                }
            }
            // idlePriorityQueue 肯定有了
            Server poll = idlePriorityQueue.poll();
            res[i] = poll.id;
            poll.idleFromTime = tasks[i] + time;
            fullPriorityQueue.add(poll);

            time = Math.max(time, i + 1);
        }
        return res;
    }

    class Server {
        // 下标
        public int id;
        // 权重
        public int weight;
        // 空闲时间
        public int idleFromTime;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] servers = new int[] {
                3,3,2
        };
        int[] tasks = new int[] {
                1,2,3,2,1,2
        };
        int[] ints = solution.assignTasks(servers, tasks);
        // [2,2,0,2,1,2]
        System.out.println(Arrays.toString(ints));
        Assert.assertArrayEquals(new int[] {
                2,2,0,2,1,2
        }, ints);

        servers = new int[] {
                5,1,4,3,2
        };
        tasks = new int[] {
                2,1,2,4,5,2,1
        };
        ints = solution.assignTasks(servers, tasks);
        // [2,2,0,2,1,2]
        System.out.println(Arrays.toString(ints));
        Assert.assertArrayEquals(new int[] {
                1,4,1,4,1,3,2
        }, ints);
    }
}
