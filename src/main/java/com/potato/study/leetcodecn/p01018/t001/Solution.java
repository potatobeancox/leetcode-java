package com.potato.study.leetcodecn.p01018.t001;

import com.potato.study.leetcode.domain.ListNode;
import com.potato.study.leetcode.util.ListNodeUtil;

import java.util.*;

/**
 * 1018. 可被 5 整除的二进制前缀
 *
 * 给定由若干 0 和 1 组成的数组 A。我们定义 N_i：从 A[0] 到 A[i] 的第 i 个子数组被解释为一个二进制数（从最高有效位到最低有效位）。

 返回布尔值列表 answer，只有当 N_i 可以被 5 整除时，答案 answer[i] 为 true，否则为 false。

  

 示例 1：

 输入：[0,1,1]
 输出：[true,false,false]
 解释：
 输入数字为 0, 01, 011；也就是十进制中的 0, 1, 3 。只有第一个数可以被 5 整除，因此 answer[0] 为真。
 示例 2：

 输入：[1,1,1]
 输出：[false,false,false]
 示例 3：

 输入：[0,1,1,1,1,1]
 输出：[true,false,false,false,true,false]
 示例 4：

 输入：[1,1,1,0,1]
 输出：[false,false,false,false,false]
  

 提示：

 1 <= A.length <= 30000
 A[i] 为 0 或 1

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/binary-prefix-divisible-by-5
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 前缀比较大怎么办 每次对100 取余数 不会影响结果
     *
     * @param nums
     * @return
     */
    public List<Boolean> prefixesDivBy5(int[] nums) {
        int num = 0;
        List<Boolean> resultList = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            num *= 2;
            num += nums[i];
            num %= 100;

            if (num % 5 == 0) {
                resultList.add(true);
            } else {
                resultList.add(false);
            }
        }
        return resultList;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {
                0,1,1
        };
        List<Boolean> booleans = solution.prefixesDivBy5(nums);
        // [true,false,false]
        System.out.println(booleans);
    }
}
