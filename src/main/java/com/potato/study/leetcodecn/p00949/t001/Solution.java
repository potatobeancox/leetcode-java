package com.potato.study.leetcodecn.p00949.t001;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;

import com.potato.study.leetcode.util.LeetcodeInputUtils;

/**
 * 949. 给定数字能组成的最大时间
 *
 * 给定一个由 4 位数字组成的数组，返回可以设置的符合 24 小时制的最大时间。
 *
 * 24 小时格式为 "HH:MM" ，其中 HH 在 00 到 23 之间，MM 在 00 到 59 之间。最小的 24 小时制时间是 00:00 ，而最大的是 23:59 。从 00:00 （午夜）开始算起，过得越久，时间越大。
 *
 * 以长度为 5 的字符串，按 "HH:MM" 格式返回答案。如果不能确定有效时间，则返回空字符串。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：arr = [1,2,3,4]
 * 输出："23:41"
 * 解释：有效的 24 小时制时间是 "12:34"，"12:43"，"13:24"，"13:42"，"14:23"，"14:32"，"21:34"，"21:43"，"23:14" 和 "23:41" 。这些时间中，"23:41"
 * 是最大时间。
 * 示例 2：
 *
 * 输入：arr = [5,5,5,5]
 * 输出：""
 * 解释：不存在有效的 24 小时制时间，因为 "55:55" 无效。
 * 示例 3：
 *
 * 输入：arr = [0,0,0,0]
 * 输出："00:00"
 * 示例 4：
 *
 * 输入：arr = [0,0,1,0]
 * 输出："10:00"
 *  
 *
 * 提示：
 *
 * arr.length == 4
 * 0 <= arr[i] <= 9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/largest-time-for-given-digits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public String largestTimeFromDigits(int[] arr) {
        // 按照 arr的 四个位置 倒序排序 判断每次生成的是否合法
        Set<String> digitSet = new HashSet<>();
        boolean[] used = new boolean[4];
        buildAllDigitSet(digitSet, arr, used);
        // 将digitSet 排序
        List<String> list = new ArrayList<>(digitSet);
        Collections.sort(list, Collections.reverseOrder());
        // 检查字符串是否正确 时间
        for (String timeStr : list) {
            boolean checkResult = checkValid(timeStr);
            if (checkResult) {
                return timeStr;
            }
        }
        return "";
    }

    private void buildAllDigitSet(Set<String> digitSet, int[] arr, boolean[] used) {

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    for (int l = 0; l < 4; l++) {
                        if (i == j || i == k || i == l || j == k || j == l
                                || k == l) {
                            continue;
                        }
                        StringBuilder builder = new StringBuilder();
                        builder.append(arr[i]).append(arr[j])
                                .append(":")
                                .append(arr[k]).append(arr[l]);
                        digitSet.add(builder.toString());
                    }
                }
            }
        }
        return;
    }


    private boolean checkValid(String timeStr) {
        if (timeStr.length() != 5) {
            return false;
        }
        String[] split = timeStr.split(":");

        String hour = split[0];
        int h = Integer.parseInt(hour);
        if (h > 23) {
            return false;
        }
        String minute = split[1];
        int m = Integer.parseInt(minute);
        if (m > 59) {
            return false;
        }
        return true;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[]{1,2,3,4};
        String s = solution.largestTimeFromDigits(arr);
        System.out.println(s);
        Assert.assertEquals("23:41", s);
    }

}
