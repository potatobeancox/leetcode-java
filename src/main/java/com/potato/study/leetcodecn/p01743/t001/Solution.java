package com.potato.study.leetcodecn.p01743.t001;

import org.junit.Assert;

import java.util.*;

/**
 * 1743. 从相邻元素对还原数组

 *
 * 存在一个由 n 个不同元素组成的整数数组 nums ，但你已经记不清具体内容。好在你还记得 nums 中的每一对相邻元素。

 给你一个二维整数数组 adjacentPairs ，大小为 n - 1 ，其中每个 adjacentPairs[i] = [ui, vi] 表示元素 ui 和 vi 在 nums 中相邻。

 题目数据保证所有由元素 nums[i] 和 nums[i+1] 组成的相邻元素对都存在于 adjacentPairs 中，存在形式可能是 [nums[i], nums[i+1]] ，也可能是 [nums[i+1], nums[i]] 。这些相邻元素对可以 按任意顺序 出现。

 返回 原始数组 nums 。如果存在多种解答，返回 其中任意一个 即可。

  

 示例 1：

 输入：adjacentPairs = [[2,1],[3,4],[3,2]]
 输出：[1,2,3,4]
 解释：数组的所有相邻元素对都在 adjacentPairs 中。
 特别要注意的是，adjacentPairs[i] 只表示两个元素相邻，并不保证其 左-右 顺序。
 示例 2：

 输入：adjacentPairs = [[4,-2],[1,4],[-3,1]]
 输出：[-2,4,1,-3]
 解释：数组中可能存在负数。
 另一种解答是 [-3,1,4,-2] ，也会被视作正确答案。
 示例 3：

 输入：adjacentPairs = [[100000,-100000]]
 输出：[100000,-100000]
  

 提示：

 nums.length == n
 adjacentPairs.length == n - 1
 adjacentPairs[i].length == 2
 2 <= n <= 105
 -105 <= nums[i], ui, vi <= 105
 题目数据保证存在一些以 adjacentPairs 作为元素对的数组 nums

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/restore-the-array-from-adjacent-pairs
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {



    public int[] restoreArray(int[][] adjacentPairs) {
        // 使用 map 记录 相邻的节点 ，因为对应节点不同，所以相邻的节点 肯定少于2个
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] adj : adjacentPairs) {
            List<Integer> orDefault1 = map.getOrDefault(adj[0], new ArrayList<>());
            orDefault1.add(adj[1]);
            map.put(adj[0], orDefault1);

            List<Integer> orDefault2 = map.getOrDefault(adj[1], new ArrayList<>());
            orDefault2.add(adj[0]);
            map.put(adj[1], orDefault2);
        }
        // 遍历 map 找到只有一个点 那么它就是 第一个节点
        int[] result = new int[map.size()];
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            if (entry.getValue().size() == 1) {
                result[0] = entry.getKey();
                break;
            }
        }
        // 从第二个节点开始 找其相邻的点，看每次看之前有没有用到
        result[1] = map.get(result[0]).get(0);
        for (int i = 2; i < result.length; i++) {
            // 因为每个元素都不同 找到i位置的元素
            List<Integer> list = map.get(result[i-1]);
            int target = 0;
            for (int num : list) {
                // 之前用过了
                if (num == result[i-2]) {
                    continue;
                }
                target = num;
                break;
            }
            result[i] = target;
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] adjacentPairs = new int[][] {
                {2,1},
                {3,4},
                {3,2}
        };
        int[] ints = solution.restoreArray(adjacentPairs);
        System.out.println(Arrays.toString(ints));
        Assert.assertArrayEquals(new int[] {
                1,2,3,4
        }, ints);

        adjacentPairs = new int[][] {
                {4,-2},
                {1,4},
                {-3,1}
        };
        ints = solution.restoreArray(adjacentPairs);
        System.out.println(Arrays.toString(ints));
        Assert.assertArrayEquals(new int[] {
                -2,4,1,-3
        }, ints);
    }
}
