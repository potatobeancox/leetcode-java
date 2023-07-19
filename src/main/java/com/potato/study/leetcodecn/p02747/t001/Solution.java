package com.potato.study.leetcodecn.p02747.t001;


import org.junit.Assert;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * 2747. 统计没有收到请求的服务器数目
 *
 * 给你一个整数 n ，表示服务器的总数目，再给你一个下标从 0 开始的 二维 整数数组 logs ，其中 logs[i] = [server_id, time] 表示 id 为 server_id 的服务器在 time 时收到了一个请求。
 *
 * 同时给你一个整数 x 和一个下标从 0 开始的整数数组 queries  。
 *
 * 请你返回一个长度等于 queries.length 的数组 arr ，其中 arr[i] 表示在时间区间 [queries[i] - x, queries[i]] 内没有收到请求的服务器数目。
 *
 * 注意时间区间是个闭区间。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 3, logs = [[1,3],[2,6],[1,5]], x = 5, queries = [10,11]
 * 输出：[1,2]
 * 解释：
 * 对于 queries[0]：id 为 1 和 2 的服务器在区间 [5, 10] 内收到了请求，所以只有服务器 3 没有收到请求。
 * 对于 queries[1]：id 为 2 的服务器在区间 [6,11] 内收到了请求，所以 id 为 1 和 3 的服务器在这个时间段内没有收到请求。
 * 示例 2：
 *
 * 输入：n = 3, logs = [[2,4],[2,1],[1,2],[3,1]], x = 2, queries = [3,4]
 * 输出：[0,1]
 * 解释：
 * 对于 queries[0]：区间 [1, 3] 内所有服务器都收到了请求。
 * 对于 queries[1]：只有 id 为 3 的服务器在区间 [2,4] 内没有收到请求。
 *  
 *
 * 提示：
 *
 * 1 <= n <= 105
 * 1 <= logs.length <= 105
 * 1 <= queries.length <= 105
 * logs[i].length == 2
 * 1 <= logs[i][0] <= n
 * 1 <= logs[i][1] <= 106
 * 1 <= x <= 105
 * x < queries[i] <= 106
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/count-zero-request-servers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        // 分别对 logs 按照时间 升序排序 query 升序排序
        Arrays.sort(logs, Comparator.comparingInt(o -> o[1]));
        // 对 query 生成一个 index 数组
        Integer[] indexes = new Integer[queries.length];
        for (int i = 0; i < queries.length; i++) {
            indexes[i] = i;
        }
        Arrays.sort(indexes, Comparator.comparingInt(i -> queries[i]));
        // 遍历 query 计算两个端点的距离 中间维护 当前已经找到的窗口中 log的开始和结束位置
        int[] res = new int[queries.length];
        int left = -1;
        int right = -1;
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int targetIndex : indexes) {
            // 闭区间
            int queryLeft = queries[targetIndex] - x;
            int queryRight = queries[targetIndex];
            // 窗口内部使用一个 map计数 每次都修改 窗口区间的左右端点 index
            // 当前左边 是否落在闭区间里边 落在外面
            while (left != -1 && left <= right && logs[left][1] < queryLeft) {
                int serverId = logs[left][0];
                int count = countMap.get(serverId);
                count--;
                if (count == 0) {
                    countMap.remove(serverId);
                } else {
                    countMap.put(serverId, count);
                }
                left++;
            }
            // 当前右边是不是在闭区间里边
            while (right+1 < logs.length && logs[right+1][1] <= queryRight) {
                if (left == -1) {
                    left = right+1;
                }
                int serverId = logs[right+1][0];
                int count = countMap.getOrDefault(serverId, 0);
                count++;
                countMap.put(serverId, count);
                right++;
            }
            // 统计个数
            res[targetIndex] = n - countMap.size();

        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 6;
        // logs[i] = [server_id, time]
        int[][] logs = new int[][] {
                {1,21}
        };
        int x = 10;
        int[] queries = new int[] {
                24,35,28,26,20,25,16,31,12
        };
        int[] ints = solution.countServers(n, logs, x, queries);
        System.out.println(Arrays.toString(ints));
        Assert.assertArrayEquals(new int[] {
                5,6,5,5,6,5,6,5,6
        }, ints);


        n = 3;
        // logs[i] = [server_id, time]
        logs = new int[][] {
                {1,3}, {2, 6}, {1, 5}
        };
        x = 5;
        queries = new int[] {
                10,11
        };
        ints = solution.countServers(n, logs, x, queries);
        System.out.println(Arrays.toString(ints));
//        Assert.assertArrayEquals(new int[] {
//                5,6,5,5,6,5,6,5,6
//        }, ints);
    }
}
