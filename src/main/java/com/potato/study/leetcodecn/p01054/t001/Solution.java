package com.potato.study.leetcodecn.p01054.t001;

import java.util.PriorityQueue;

/**
 * 1054. 距离相等的条形码
 *
 * 在一个仓库里，有一排条形码，其中第 i 个条形码为 barcodes[i]。
 *
 * 请你重新排列这些条形码，使其中任意两个相邻的条形码不能相等。 你可以返回任何满足该要求的答案，此题保证存在答案。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：barcodes = [1,1,1,2,2,2]
 * 输出：[2,1,2,1,2,1]
 * 示例 2：
 *
 * 输入：barcodes = [1,1,1,1,2,2,3,3]
 * 输出：[1,3,1,3,2,1,2,1]
 *  
 *
 * 提示：
 *
 * 1 <= barcodes.length <= 10000
 * 1 <= barcodes[i] <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/distant-barcodes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int[] rearrangeBarcodes(int[] barcodes) {
        // 统计数量
        int[] count = new int[10001];
        for (int i = 0; i < barcodes.length; i++) {
            count[barcodes[i]]++;
        }
        // 按照数量多少排序 0-> num 1-> count
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((o1, o2) -> {
            return Integer.compare(o2[1], o1[1]);
        });
        // 放到数组里边
        for (int i = 0; i < count.length; i++) {
            if (count[i] == 0) {
                continue;
            }
            priorityQueue.add(new int[]{
                i, count[i]
            });
        }
        // 奇偶 获取 生成结果
        int[] tmp = new int[barcodes.length];
        int index = 0;
        while (!priorityQueue.isEmpty()) {
            int[] poll = priorityQueue.poll();
            int length = poll[1];
            int value = poll[0];
            for (int i = 0; i < length; i++) {
                tmp[index] = value;
                index++;
            }
        }
        index = 0;
        int[] result = new int[barcodes.length];
        for (int i = 0; i < barcodes.length; i+=2) {
            result[i] = tmp[index];
            index++;
        }
        for (int i = 1; i < barcodes.length; i+=2) {
            result[i] = tmp[index];
            index++;
        }
        return result;
    }
}
