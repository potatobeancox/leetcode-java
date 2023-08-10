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
        // 遍历 arr 计算 1的个数 并看看能不能分成3份
        int oneCount = 0;
        for (int element : arr) {
            if (element == 1) {
                oneCount++;
            }
        }
        if (oneCount % 3 != 0) {
            return new int[] {-1, -1};
        }
        if (oneCount == 0) {
            return new int[] {0, 2};
        }
        // 分割后部分的个数
        int partOneCount = oneCount / 3;
        // 找到 index1 = partOneCount index2 = partOneCount * 2的1的index index0 就是第0个1的index
        int index0 = -1;
        int index1 = -1;
        int index2 = -1;
        int currentOneCount = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                if (currentOneCount == 0) {
                    index0 = i;
                }
                if (currentOneCount == partOneCount) {
                    index1 = i;
                }
                if (currentOneCount == partOneCount * 2) {
                    index2 = i;
                }
                currentOneCount++;
            }
        }
        // 分成之后 找到 最后一个  从每个位置的开始位置开始往后比较 先比较长度
        int minLen = arr.length - index2;
        // 判断从开始位置到最终是不是比这个大
        if (index1 < minLen) {
            return new int[] {-1, -1};
        }
        // 长度就是找到最后一段的最小长度 判断 第一个位置之前的长度是不是大于等于 最小长度 中间那短长度是不是大于等于最小长度
        if (index2 - index1 + 1 < minLen) {
            return new int[] {-1, -1};
        }
        // 后来比较各个位置是否相同
        for (int i = 0; i < minLen; i++) {
            int target3 = arr[index2+i];
            int target1 = arr[index0+i];
            int target2 = arr[index1+i];

            if (target1 != target3 || target2 != target3) {
                return new int[] {-1, -1};
            }
        }
        // 找到了 推算一下 从index1开始推  minLen个长度
        int res1 = minLen + index0 - 1;
        int res2 = index1 + minLen;
        return new int[] {res1, res2};
    }



    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = LeetcodeInputUtils.inputString2IntArray("[1,0,1,0,1]");
        int[] ints = solution.threeEqualParts(arr);
        System.out.println(Arrays.toString(ints));
        Assert.assertArrayEquals(new int[] {0, 3}, ints);
    }


}
