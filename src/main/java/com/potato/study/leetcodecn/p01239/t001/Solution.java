package com.potato.study.leetcodecn.p01239.t001;


import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.potato.study.leetcode.util.LeetcodeInputUtils;

/**
 * 1239. 串联字符串的最大长度
 *
 * 给定一个字符串数组 arr，字符串 s 是将 arr 某一子序列字符串连接所得的字符串，如果 s 中的每一个字符都只出现过一次，那么它就是一个可行解。
 *
 * 请返回所有可行解 s 中最长长度。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：arr = ["un","iq","ue"]
 * 输出：4
 * 解释：所有可能的串联组合是 "","un","iq","ue","uniq" 和 "ique"，最大长度为 4。
 * 示例 2：
 *
 * 输入：arr = ["cha","r","act","ers"]
 * 输出：6
 * 解释：可能的解答有 "chaers" 和 "acters"。
 * 示例 3：
 *
 * 输入：arr = ["abcdefghijklmnopqrstuvwxyz"]
 * 输出：26
 *  
 *
 * 提示：
 *
 * 1 <= arr.length <= 16
 * 1 <= arr[i].length <= 26
 * arr[i] 中只含有小写英文字母
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    private long maxLen;
    // 1239
    public int maxLength(List<String> arr) {
        // 将 字段串转成 状态 数字
        List<Long> statusList = new ArrayList<>();
        // 遍历 word 如果出现 重复字符返回 0
        for (String word : arr) {
            long status = getStatus(word);
            if (status > 0) {
                statusList.add(status);
            }
        }
        // 回溯找
        this.maxLen = 0;
        backtrack(statusList, 0,  0);
        return (int) maxLen;
    }

    /**
     *
     * @param statusList        原始字符串对应的占位符
     * @param currentStatus     当前拼接后字符串的状态
     * @param currentIndex      当前使用的index
     */
    private void backtrack(List<Long> statusList, long currentStatus, int currentIndex) {

        // 找下一个 字符串看 能不能递归找下去
        for (int i = currentIndex; i < statusList.size(); i++) {
            // 判定 当前 currentIndex 是否可以选择
            long status = statusList.get(i);
            // 有相同的 往后找
            if (hasSameChar(status, currentStatus)) {
                continue;
            }
            // 当前没有相同的 修改最大设定等
            long nextStatus = (currentStatus | status);
            long nextLength = Long.bitCount(nextStatus);
            maxLen = Math.max(maxLen, nextLength);
            // 用这个点
            backtrack(statusList, nextStatus, i + 1);
            // 也可以不用这个节点
            backtrack(statusList, currentStatus, i + 1);
        }
    }

    private long getStatus(String word) {
        int[] count = new int[26];
        char[] chars = word.toCharArray();
        for (char ch : chars) {
            count[ch - 'a']++;
        }
        // count 数组变成 字符串
        int status = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i] >= 2) {
                return 0;
            } else if (count[i] == 1) {
                status |= (1 << i);
            }
        }
        return status;
    }

    /**
     * 两个状态是否有相同的字符
     * @param status1
     * @param status2
     * @return
     */
    private boolean hasSameChar(long status1, long status2) {
        long bit = status1 & status2;
        return bit > 0;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "[\"un\",\"iq\",\"ue\"]";
        List<String> list = LeetcodeInputUtils.inputString2StringList(input);

        int i = solution.maxLength(list);
        System.out.println(i);
        Assert.assertEquals(4, i);
    }


}

