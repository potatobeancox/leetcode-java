package com.potato.study.leetcodecn.p01649.t001;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;

/**
 * 1649. 通过指令创建有序数组
 *
 * 给你一个整数数组 instructions ，你需要根据 instructions 中的元素创建一个有序数组。一开始你有一个空的数组 nums ，
 * 你需要 从左到右 遍历 instructions
 *  中的元素，将它们依次插入 nums 数组中。每一次插入操作的 代价 是以下两者的 较小值 ：
 *
 * nums 中 严格小于  instructions[i] 的数字数目。
 * nums 中 严格大于  instructions[i] 的数字数目。
 * 比方说，如果要将 3 插入到 nums = [1,2,3,5] ，那么插入操作的 代价 为 min(2, 1) (元素 1 和  2 小于 3 ，元素 5 大于 3 ），
 * 插入后 nums 变成 [1,2,3,3,5] 。
 *
 * 请你返回将 instructions 中所有元素依次插入 nums 后的 总最小代价 。由于答案会很大，请将它对 109 + 7 取余 后返回。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：instructions = [1,5,6,2]
 * 输出：1
 * 解释：一开始 nums = [] 。
 * 插入 1 ，代价为 min(0, 0) = 0 ，现在 nums = [1] 。
 * 插入 5 ，代价为 min(1, 0) = 0 ，现在 nums = [1,5] 。
 * 插入 6 ，代价为 min(2, 0) = 0 ，现在 nums = [1,5,6] 。
 * 插入 2 ，代价为 min(1, 2) = 1 ，现在 nums = [1,2,5,6] 。
 * 总代价为 0 + 0 + 0 + 1 = 1 。
 * 示例 2:
 *
 * 输入：instructions = [1,2,3,6,5,4]
 * 输出：3
 * 解释：一开始 nums = [] 。
 * 插入 1 ，代价为 min(0, 0) = 0 ，现在 nums = [1] 。
 * 插入 2 ，代价为 min(1, 0) = 0 ，现在 nums = [1,2] 。
 * 插入 3 ，代价为 min(2, 0) = 0 ，现在 nums = [1,2,3] 。
 * 插入 6 ，代价为 min(3, 0) = 0 ，现在 nums = [1,2,3,6] 。
 * 插入 5 ，代价为 min(3, 1) = 1 ，现在 nums = [1,2,3,5,6] 。
 * 插入 4 ，代价为 min(3, 2) = 2 ，现在 nums = [1,2,3,4,5,6] 。
 * 总代价为 0 + 0 + 0 + 0 + 1 + 2 = 3 。
 * 示例 3：
 *
 * 输入：instructions = [1,3,3,3,2,4,2,1,2]
 * 输出：4
 * 解释：一开始 nums = [] 。
 * 插入 1 ，代价为 min(0, 0) = 0 ，现在 nums = [1] 。
 * 插入 3 ，代价为 min(1, 0) = 0 ，现在 nums = [1,3] 。
 * 插入 3 ，代价为 min(1, 0) = 0 ，现在 nums = [1,3,3] 。
 * 插入 3 ，代价为 min(1, 0) = 0 ，现在 nums = [1,3,3,3] 。
 * 插入 2 ，代价为 min(1, 3) = 1 ，现在 nums = [1,2,3,3,3] 。
 * 插入 4 ，代价为 min(5, 0) = 0 ，现在 nums = [1,2,3,3,3,4] 。
 * ​​​​​插入 2 ，代价为 min(1, 4) = 1 ，现在 nums = [1,2,2,3,3,3,4] 。
 * 插入 1 ，代价为 min(0, 6) = 0 ，现在 nums = [1,1,2,2,3,3,3,4] 。
 * 插入 2 ，代价为 min(2, 4) = 2 ，现在 nums = [1,1,2,2,2,3,3,3,4] 。
 * 总代价为 0 + 0 + 0 + 0 + 1 + 0 + 1 + 0 + 2 = 4 。
 *  
 *
 * 提示：
 *
 * 1 <= instructions.length <= 105
 * 1 <= instructions[i] <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/create-sorted-array-through-instructions
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode.cn/problems/create-sorted-array-through-instructions/solution/by-stormsunshine-prxx/
     * 还是按照
     * @param instructions
     * @return
     */
    public int createSortedArray(int[] instructions) {
        // 进行归并，归并过程中记录 index
        int length = instructions.length;
        // 记录 indexes
        int[] indexes = new int[length];
        int[] newInstructions = new int[length];
        // 再用一个 map 单独记录每个元素出现次数
        Map<Integer, Long> sameCountMap = new HashMap<>();
        for (int i = 0; i < length; i++) {
            indexes[i] = i;
            newInstructions[i] = instructions[i];
        }
        // 记录每个位置左边比他小的数量
        long[] smallerCount = new long[length];
        // 合并的时候 按照降序排序 当 后面的点 大于前面点时 计算有多少个点 小雨 使用一个数组记录 多少 ，
        mergeAndSort(smallerCount, newInstructions, indexes, 0, length - 1);

        // 便利每个元素 计算总划分
        long totalCost = 0;
        for (int i = 0; i < instructions.length; i++) {
            long count = sameCountMap.getOrDefault(instructions[i], 0L);
            count++;
            sameCountMap.put(instructions[i], count);
            long cost = Math.min(smallerCount[i], i+1 - smallerCount[i] - count);

            totalCost += cost;
        }
        int mod = 1_000_000_000 + 7;
        return (int) (totalCost % mod);
    }

    private void mergeAndSort(long[] smallerCount, int[] newInstructions, int[] indexes, int left, int right) {
        // 将数组 倒序排列，并找到 左边小于 indexes right 的个数
        if (left == right) {
            return;
        }
        int mid = (left + right) / 2;
        mergeAndSort(smallerCount, newInstructions, indexes, left, mid);
        mergeAndSort(smallerCount, newInstructions, indexes, mid+1, right);

        // 剪枝 如果 mid已经比 mid+1 大了 说明 已然有序
        if (newInstructions[mid] >= newInstructions[mid + 1]) {
            return;
        }
        // 合并
        mergeAndGetSmaller(smallerCount, newInstructions, indexes, left, right, mid);
    }

    /**
     * 合并排序 数组
     * @param smallerCount
     * @param newInstructions
     * @param left
     * @param right
     * @param mid
     */
    private void mergeAndGetSmaller(long[] smallerCount, int[] newInstructions, int[] indexes,
            int left, int right, int mid) {
        // 先做 排序逻辑
        int length = right - left + 1;
        int[] tempInstructions = new int[length];
        int[] tempIndexes = new int[length];
        // copy
        System.arraycopy(newInstructions, left, tempInstructions, 0, length);
        System.arraycopy(indexes, left, tempIndexes, 0, length);

        int i = left;
        int j = mid + 1;
        int index = left;
        while (i <= mid || j <= right) {
            // i 或者 j 用完了
            if (i > mid) {
                newInstructions[index] = tempInstructions[j-left];
                indexes[index] = tempIndexes[j-left];
                j++;
            } else if (j > right) {
                newInstructions[index] = tempInstructions[i-left];
                indexes[index] = tempIndexes[i-left];
                i++;
            } else {
                // 都没用完
                if (tempInstructions[i-left] >= tempInstructions[j-left]) {
                    // i相同 或者大
                    newInstructions[index] = tempInstructions[i-left];
                    indexes[index] = tempIndexes[i-left];
                    i++;
                } else {
                    // j大
                    newInstructions[index] = tempInstructions[j-left];
                    indexes[index] = tempIndexes[j-left];
                    // i 之后的元素都比较小 包括当前i
                    smallerCount[indexes[index]] += (mid - i + 1);
                    j++;
                }
            }
            index++;
        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] ints = {
                1, 5, 6, 2
        };
        int sortedArray = solution.createSortedArray(ints);
        System.out.println(sortedArray);
        Assert.assertEquals(1, sortedArray);

        ints = new int[]{
                1,2,3,6,5,4
        };
        sortedArray = solution.createSortedArray(ints);
        System.out.println(sortedArray);
        Assert.assertEquals(3, sortedArray);
    }
}
