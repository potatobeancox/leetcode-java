package com.potato.study.leetcodecn.p02615.t001;

import org.junit.Assert;

import java.util.*;

/**
 *
 * 2615. 等值距离和
 *
 * 给你一个下标从 0 开始的整数数组 nums 。现有一个长度等于 nums.length 的数组 arr 。对于满足 nums[j] == nums[i] 且 j != i 的所有 j ，arr[i] 等于所有 |i - j| 之和。如果不存在这样的 j ，则令 arr[i] 等于 0 。

 返回数组 arr 。

  

 示例 1：

 输入：nums = [1,3,1,1,2]
 输出：[5,0,3,4,0]
 解释：
 i = 0 ，nums[0] == nums[2] 且 nums[0] == nums[3] 。因此，arr[0] = |0 - 2| + |0 - 3| = 5 。
 i = 1 ，arr[1] = 0 因为不存在值等于 3 的其他下标。
 i = 2 ，nums[2] == nums[0] 且 nums[2] == nums[3] 。因此，arr[2] = |2 - 0| + |2 - 3| = 3 。
 i = 3 ，nums[3] == nums[0] 且 nums[3] == nums[2] 。因此，arr[3] = |3 - 0| + |3 - 2| = 4 。
 i = 4 ，arr[4] = 0 因为不存在值等于 2 的其他下标。
 示例 2：

 输入：nums = [0,5,3]
 输出：[0,0,0]
 解释：因为 nums 中的元素互不相同，对于所有 i ，都有 arr[i] = 0 。
  

 提示：

 1 <= nums.length <= 105
 0 <= nums[i] <= 109


 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/sum-of-distances
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 2615
    public long[] distance(int[] nums) {
        Map<Integer, List<Integer>> appearMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            List<Integer> indexList = appearMap.getOrDefault(nums[i], new ArrayList<>());
            indexList.add(i);

            appearMap.put(nums[i], indexList);
        }
        // 遍历 每个 map 的每个value 计算每个list的前缀和 对于当前 i 就可以得到结果了
        long[] result = new long[nums.length];
        for (List<Integer> sameIndexList : appearMap.values()) {
            // 对于每个位置
            int size = sameIndexList.size();
            if (size <= 1) {
                continue;
            }
            long allSum = 0;
            for (int i : sameIndexList) {
                allSum += i;
            }

            long prefixSum = 0;
            for (int i = 0; i < sameIndexList.size(); i++) {
                int index = sameIndexList.get(i);
                result[index] = (long)i * index - prefixSum +
                        (allSum - prefixSum - index) - ((long)size - i - 1) * index;

                prefixSum += index;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {
                1,3,1,1,2
        };
        long[] distance = solution.distance(nums);
        System.out.println(Arrays.toString(distance));
        Assert.assertArrayEquals(new long[] {
                5,0,3,4,0
        }, distance);
    }




}
