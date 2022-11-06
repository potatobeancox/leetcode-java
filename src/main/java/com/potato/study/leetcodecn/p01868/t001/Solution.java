package com.potato.study.leetcodecn.p01868.t001;

import com.potato.study.leetcode.util.ArrayUtil;
import com.potato.study.leetcode.util.LeetcodeInputUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 1868. 两个行程编码数组的积
 *
 * 行程编码（Run-length encoding）是一种压缩算法，能让一个含有许多段连续重复数字的整数类型数组 nums 以一个（通常更小的）二维数组 encoded 表示。每个 encoded[i] = [vali, freqi] 表示 nums 中第 i 段重复数字，其中 vali 是该段重复数字，重复了 freqi 次。
 *
 * 例如， nums = [1,1,1,2,2,2,2,2] 可表示称行程编码数组 encoded = [[1,3],[2,5]] 。对此数组的另一种读法是“三个 1 ，后面有五个 2 ”。
 * 两个行程编码数组 encoded1 和 encoded2 的积可以按下列步骤计算：
 *
 * 将 encoded1 和 encoded2 分别扩展成完整数组 nums1 和 nums2 。
 * 创建一个新的数组 prodNums ，长度为 nums1.length 并设 prodNums[i] = nums1[i] * nums2[i] 。
 * 将 prodNums 压缩成一个行程编码数组并返回之。
 * 给定两个行程编码数组 encoded1 和 encoded2 ，分别表示完整数组 nums1 和 nums2 。nums1 和 nums2 的长度相同。 每一个 encoded1[i] = [vali, freqi] 表示 nums1 中的第 i 段，每一个 encoded2[j] = [valj, freqj] 表示 nums2 中的第 j 段。
 *
 * 返回 encoded1 和 encoded2 的乘积。
 *
 * 注：行程编码数组需压缩成可能的最小长度。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: encoded1 = [[1,3],[2,3]], encoded2 = [[6,3],[3,3]]
 * 输出: [[6,6]]
 * 解释n: encoded1 扩展为 [1,1,1,2,2,2] ，encoded2 扩展为 [6,6,6,3,3,3]。
 * prodNums = [6,6,6,6,6,6]，压缩成行程编码数组 [[6,6]]。
 * 示例 2:
 *
 * 输入: encoded1 = [[1,3],[2,1],[3,2]], encoded2 = [[2,3],[3,3]]
 * 输出: [[2,3],[6,1],[9,2]]
 * 解释: encoded1 扩展为 [1,1,1,2,3,3] ，encoded2 扩展为 [2,2,2,3,3,3]。
 * prodNums = [2,2,2,6,9,9]，压缩成行程编码数组 [[2,3],[6,1],[9,2]]。
 *  
 *
 * 提示：
 *
 * 1 <= encoded1.length, encoded2.length <= 105
 * encoded1[i].length == 2
 * encoded2[j].length == 2
 * 对于每一个 encoded1[i]， 1 <= vali, freqi <= 104  
 * 对于每一个 encoded2[j]， 1 <= valj, freqj <= 104
 * encoded1 和 encoded2 表示的完整数组长度相同。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/product-of-two-run-length-encoded-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public List<List<Integer>> findRLEArray(int[][] encoded1, int[][] encoded2) {
        List<List<Integer>> result = new ArrayList<>();
        // 两个指针 指上去
        int index1 = 0;
        int index2 = 0;
        // 依次滑动 直到 都到最后了 [vali, freqi]
        int[] remind1 = new int[] {
                encoded1[0][0],
                encoded1[0][1]
        };
        int[] remind2 = new int[] {
                encoded2[0][0],
                encoded2[0][1]
        };
        while (index1 < encoded1.length && index2 < encoded2.length) {
            // 当前还有多少 剩余 如果没有 往后滑动
            if (remind1 == null || remind1[1] == 0) {
                index1++;
                if (index1 < encoded1.length) {
                    remind1[0] = encoded1[index1][0];
                    remind1[1] = encoded1[index1][1];
                }
            }
            if (remind2 == null || remind2[1] == 0) {
                index2++;
                if (index2 < encoded2.length) {
                    remind2[0] = encoded2[index1][0];
                    remind2[1] = encoded2[index1][1];
                }
            }
            // 找到长度
            int len = Math.min(remind1[1], remind2[1]);
            int targetVal = remind1[0] * remind2[0];
            // 更改老数组
            remind1[1] -= len;
            remind2[1] -= len;
            // 判断下跟上面一个是否可以合并
            if (result.size() > 0 && result.get(result.size() - 1).get(0) == targetVal) {
                List<Integer> removeInterval = result.remove(result.size() - 1);
                List<Integer> newInterval = new ArrayList<>();
                newInterval.add(targetVal);
                newInterval.add(len + removeInterval.get(1));

                result.add(newInterval);
            } else {
                // 按照
                List<Integer> newInterval = new ArrayList<>();
                newInterval.add(targetVal);
                newInterval.add(len);

                result.add(newInterval);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] encoded1 = LeetcodeInputUtils.inputString2IntArrayTwoDimensional("[[1,3],[2,3]]");
        int[][] encoded2 = LeetcodeInputUtils.inputString2IntArrayTwoDimensional("[[6,3],[3,3]]");
        List<List<Integer>> rleArray = solution.findRLEArray(encoded1, encoded2);
        System.out.println(rleArray);
    }


}
