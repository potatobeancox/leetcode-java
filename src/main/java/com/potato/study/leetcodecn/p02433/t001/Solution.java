package com.potato.study.leetcodecn.p02433.t001;

/**
 * 2433. 找出前缀异或的原始数组
 *
 * 给你一个长度为 n 的 整数 数组 pref 。找出并返回满足下述条件且长度为 n 的数组 arr ：
 *
 * pref[i] = arr[0] ^ arr[1] ^ ... ^ arr[i].
 * 注意 ^ 表示 按位异或（bitwise-xor）运算。
 *
 * 可以证明答案是 唯一 的。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：pref = [5,2,0,3,1]
 * 输出：[5,7,2,3,2]
 * 解释：从数组 [5,7,2,3,2] 可以得到如下结果：
 * - pref[0] = 5
 * - pref[1] = 5 ^ 7 = 2
 * - pref[2] = 5 ^ 7 ^ 2 = 0
 * - pref[3] = 5 ^ 7 ^ 2 ^ 3 = 3
 * - pref[4] = 5 ^ 7 ^ 2 ^ 3 ^ 2 = 1
 * 示例 2：
 *
 * 输入：pref = [13]
 * 输出：[13]
 * 解释：pref[0] = arr[0] = 13
 *  
 *
 * 提示：
 *
 * 1 <= pref.length <= 105
 * 0 <= pref[i] <= 106
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-the-original-array-of-prefix-xor
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {





















    /**
     * 从后往前一直生成
     * @param pref
     * @return
     */
    public int[] findArray(int[] pref) {
        int[] res = new int[pref.length];
        for (int i = pref.length - 1; i > 0; i--) {
            res[i] = (pref[i] ^ pref[i-1]);
        }
        res[0] = pref[0];
        return res;
    }

}
