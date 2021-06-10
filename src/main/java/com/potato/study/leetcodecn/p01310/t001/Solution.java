package com.potato.study.leetcodecn.p01310.t001;


import java.util.Arrays;

import org.junit.Assert;

/**
 * 1310. 子数组异或查询
 *
 * 有一个正整数数组 arr，现给你一个对应的查询数组 queries，其中 queries[i] = [Li, Ri]。
 *
 * 对于每个查询 i，请你计算从 Li 到 Ri 的 XOR 值（即 arr[Li] xor arr[Li+1] xor ... xor arr[Ri]）作为本次查询的结果。
 *
 * 并返回一个包含给定查询 queries 所有结果的数组。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：arr = [1,3,4,8], queries = [[0,1],[1,2],[0,3],[3,3]]
 * 输出：[2,7,14,8]
 * 解释：
 * 数组中元素的二进制表示形式是：
 * 1 = 0001
 * 3 = 0011
 * 4 = 0100
 * 8 = 1000
 * 查询的 XOR 值为：
 * [0,1] = 1 xor 3 = 2
 * [1,2] = 3 xor 4 = 7
 * [0,3] = 1 xor 3 xor 4 xor 8 = 14
 * [3,3] = 8
 * 示例 2：
 *
 * 输入：arr = [4,8,2,10], queries = [[2,3],[1,3],[0,0],[0,3]]
 * 输出：[8,0,4,4]
 *  
 *
 * 提示：
 *
 * 1 <= arr.length <= 3 * 10^4
 * 1 <= arr[i] <= 10^9
 * 1 <= queries.length <= 3 * 10^4
 * queries[i].length == 2
 * 0 <= queries[i][0] <= queries[i][1] < arr.length
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/xor-queries-of-a-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     *
     * @param arr
     * @param queries
     * @return
     */
    public int[] xorQueries(int[] arr, int[][] queries) {
        int[] res = new int[arr.length];
        int bit = 0;
        for (int i = 0; i < arr.length; i++) {
            bit ^= arr[i];
            res[i] = bit;
        }
        // 遍历 queries 通过异或
        int[] resultArr = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            if (query[0] == 0) {
                resultArr[i] = res[query[1]];
            } else {
                resultArr[i] = res[query[1]] ^ res[query[0] - 1];
            }
        }
        return resultArr;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[] {
                1,3,4,8
        };
        int[][] queries = new int[][] {
                {0,1},{1,2},{0,3},{3,3}
        };
        // 2,7,14,8
        int[] res = solution.xorQueries(arr, queries);
        System.out.println(Arrays.toString(res));
        Assert.assertArrayEquals(new int[] {
                2, 7, 14, 8
        }, res);

        // arr = [4,8,2,10], queries = [[2,3],[1,3],[0,0],[0,3]]
        arr = new int[] {
                4,8,2,10
        };
        queries = new int[][] {
                {2,3},{1,3},{0,0},{0,3}
        };
        res = solution.xorQueries(arr, queries);
        System.out.println(Arrays.toString(res));
        Assert.assertArrayEquals(new int[] {
                8,0,4,4
        }, res);
    }
}
