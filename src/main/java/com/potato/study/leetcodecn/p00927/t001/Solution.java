package com.potato.study.leetcodecn.p00927.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 927. 三等分
 *
 * 给定一个由 0 和 1 组成的数组 arr ，将数组分成  3 个非空的部分 ，使得所有这些部分表示相同的二进制值。

 如果可以做到，请返回任何 [i, j]，其中 i+1 < j，这样一来：

 arr[0], arr[1], ..., arr[i] 为第一部分；
 arr[i + 1], arr[i + 2], ..., arr[j - 1] 为第二部分；
 arr[j], arr[j + 1], ..., arr[arr.length - 1] 为第三部分。
 这三个部分所表示的二进制值相等。
 如果无法做到，就返回 [-1, -1]。

 注意，在考虑每个部分所表示的二进制时，应当将其看作一个整体。例如，[1,1,0] 表示十进制中的 6，而不会是 3。此外，前导零也是被允许的，所以 [0,1,1] 和 [1,1] 表示相同的值。

  

 示例 1：

 输入：arr = [1,0,1,0,1]
 输出：[0,3]
 示例 2：

 输入：arr = [1,1,0,1,1]
 输出：[-1,-1]
 示例 3:

 输入：arr = [1,1,0,0,1]
 输出：[0,2]
  

 提示：

 3 <= arr.length <= 3 * 104
 arr[i] 是 0 或 1

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/three-equal-parts
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int[] threeEqualParts(int[] arr) {
        // dfs 当前要比较的位置 当前的分段结果 当前段的值 每一段的值
        int index = 0;
        List<Integer> splitIndexList = new ArrayList<>();
        int splitPartSum = 0;
        return dfs(arr, index, 0, splitIndexList, splitPartSum);
    }

    private int[] dfs(int[] arr, int index, int currentSum, List<Integer> splitIndexList,
                      int splitPartSum) {
        // 如果当前已经走到最后一个节点结算
        if (index == arr.length) {
            // 没找到分割点
            if (splitIndexList.size() != 2) {
                return null;
            }
            // 当前的分割方法最后一个点不是 split
            if (currentSum != splitPartSum) {
                return null;
            }
            int[] res = new int[2];
            res[0] = splitIndexList.get(0);
            res[1] = splitIndexList.get(1);
            return res;
        }
        // 第二个需要根据 splitPartSum 确定分割
        if (splitIndexList.size() == 2) {
            currentSum <<= 1;
            if (currentSum > splitPartSum) {
                return null;
            }
            // 搞下一个点
            return dfs(arr, index + 1, currentSum, splitIndexList, splitPartSum);
        }
        // 之前已经有一个已经确定了
        if (splitIndexList.size() == 1) {
            currentSum <<= 1;
            if (currentSum > splitPartSum) {
                return null;
            }
            // 小于等于的时候 等于的时候 可以继续往下走 或者直接终结
            if (currentSum < splitPartSum) {
                return dfs(arr, index + 1, currentSum, splitIndexList, splitPartSum);
            }
            // 等于的时候可以先终结 再往下走
            splitIndexList.add(index);
            int[] dfs = dfs(arr, index + 1, 0, splitIndexList, splitPartSum);
            splitIndexList.remove(splitIndexList.size() - 1);
            if (dfs != null) {
                return dfs;
            }
            return dfs(arr, index + 1, currentSum, splitIndexList, splitPartSum);
        }
        // 没走到最后一个节点 如果当前是第一个点 需要确定 splitPartSum
        if (splitIndexList.size() == 0) {
            currentSum <<= 1;
            // 使用这个作为第一个点
            splitIndexList.add(index);
            int[] dfs = dfs(arr, index + 1, 0, splitIndexList, currentSum);
            splitIndexList.remove(splitIndexList.size() - 1);
            if (dfs != null) {
                return dfs;
            }
            return dfs(arr, index + 1, currentSum, splitIndexList, splitPartSum);
        }
        return new int[0];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = LeetcodeInputUtils.inputString2IntArray("[1,0,1,0,1]");
        int[] ints = solution.threeEqualParts(arr);
        System.out.println(Arrays.toString(ints));
        Assert.assertArrayEquals(new int[] {0, 3}, ints);
    }


}
