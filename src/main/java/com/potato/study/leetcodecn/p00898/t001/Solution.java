package com.potato.study.leetcodecn.p00898.t001;

import com.potato.study.leetcode.domain.TreeNode;
import com.potato.study.leetcode.util.TreePrintUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 898. 子数组按位或操作
 *
 * 我们有一个非负整数数组 arr 。

 对于每个（连续的）子数组 sub = [arr[i], arr[i + 1], ..., arr[j]] （ i <= j），我们对 sub 中的每个元素进行按位或操作，获得结果 arr[i] | arr[i + 1] | ... | arr[j] 。

 返回可能结果的数量。 多次出现的结果在最终答案中仅计算一次。

  

 示例 1：

 输入：arr = [0]
 输出：1
 解释：
 只有一个可能的结果 0 。
 示例 2：

 输入：arr = [1,1,2]
 输出：3
 解释：
 可能的子数组为 [1]，[1]，[2]，[1, 1]，[1, 2]，[1, 1, 2]。
 产生的结果为 1，1，2，1，3，3 。
 有三个唯一值，所以答案是 3 。
 示例 3：

 输入：arr = [1,2,4]
 输出：6
 解释：
 可能的结果是 1，2，3，4，6，以及 7 。
  

 提示：

 1 <= nums.length <= 5 * 104
 0 <= nums[i] <= 109​​​​​​​

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/bitwise-ors-of-subarrays
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 因为或就是一直网上堆积1 使用一个 set 记录 即可
     * https://leetcode.cn/problems/bitwise-ors-of-subarrays/solution/zi-shu-zu-an-wei-huo-cao-zuo-by-leetcode/
     * @param arr
     * @return
     */
    public int subarrayBitwiseORs(int[] arr) {
        Set<Integer> total = new HashSet<>();
        Set<Integer> current = new HashSet<>();
        current.add(0);
        for (int element : arr) {
            Set<Integer> temp = new HashSet<>();
            for (int num : current) {
                temp.add(num | element);
            }
            // 只有自己的时候
            temp.add(element);

            current = temp;
            total.addAll(current);
        }
        return total.size();
    }
}
