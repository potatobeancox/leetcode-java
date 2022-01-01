package com.potato.study.leetcodecn.p01239.t001;


import java.util.ArrayList;
import java.util.List;

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

    private int maxLen;
    // 1239
    public int maxLength(List<String> arr) {
        // 将 字段串转成 状态
        List<Long> statusList = new ArrayList<>();
        for (String word : arr) {
            long status = getStatus(word);
            statusList.add(status);
        }
        // 回溯找
        this.maxLen = 0;
        backtrack(arr, statusList, 0, 0, 0);
        return maxLen;
    }

    private void backtrack(List<String> wordList, List<Long> statusList, int currentMaxLength, long currentStatus,
            int currentIndex) {
        // 判定 当前 currentIndex 是否可以选择
        long status = statusList.get(currentIndex);
        boolean hasSameChar = hasSameChar(status, currentStatus);
        // 不能选择 往后 回溯
        if (hasSameChar) {
            backtrack(wordList, statusList, currentMaxLength, currentStatus, currentIndex + 1);
            return;
        }
        // 可以选择 不选 往后回溯
        backtrack(wordList, statusList, currentMaxLength, currentStatus, currentIndex + 1);
        // 可以选择，选择 更新状态，往后回溯
        String currentWord = wordList.get(currentIndex);
        this.maxLen = Math.max(maxLen, currentMaxLength + currentWord.length());
        backtrack(wordList, statusList, currentMaxLength + currentWord.length(),
                (currentStatus | statusList.get(currentIndex)), currentIndex + 1);
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
            if (count[i] > 0) {
                status |= (1 << i);
            }
        }
        return status;
    }

    private boolean hasSameChar(long status1, long status2) {
        for (int i = 0; i < 26; i++) {
            long bit1 = status1 & 1;
            long bit2 = status2 & 1;
            if (bit1 + bit2 > 1) {
                return false;
            }

            status1 >>>= 1;
            status2 >>>= 1;
        }
        return true;
    }


}

