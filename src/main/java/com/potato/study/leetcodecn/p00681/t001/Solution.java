package com.potato.study.leetcodecn.p00681.t001;

import org.junit.Assert;

import java.util.*;

/**
 * 681. 最近时刻
 *
 * 给定一个形如 "HH:MM" 表示的时刻 time ，利用当前出现过的数字构造下一个距离当前时间最近的时刻。每个出现数字都可以被无限次使用。
 *
 * 你可以认为给定的字符串一定是合法的。例如， "01:34" 和  "12:09" 是合法的，“1:34” 和 “12:9” 是不合法的。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: "19:34"
 * 输出: "19:39"
 * 解释: 利用数字 1, 9, 3, 4 构造出来的最近时刻是 19:39，是 5 分钟之后。
 * 结果不是 19:33 因为这个时刻是 23 小时 59 分钟之后。
 * 示例 2:
 *
 * 输入: "23:59"
 * 输出: "22:22"
 * 解释: 利用数字 2, 3, 5, 9 构造出来的最近时刻是 22:22。
 * 答案一定是第二天的某一时刻，所以选择可构造的最小时刻。
 *  
 *
 * 提示：
 *
 * time.length == 5
 * time 为有效时间，格式为 "HH:MM".
 * 0 <= HH < 24
 * 0 <= MM < 60
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/next-closest-time
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public String nextClosestTime(String time) {
        // 收集数据
        Set<Character> charSet = new HashSet<>();
        for (char ch : time.toCharArray()) {
            if (':' == ch) {
                continue;
            }
            charSet.add(ch);
        }
        // 两次遍历 生成 小时和 分钟
        Set<String> hourSet = new HashSet<>();
        Set<String> minuteSet = new HashSet<>();

        // 将生成结果 组合
        for (char ch1 : charSet) {
            for (char ch2 : charSet) {
                StringBuilder builder = new StringBuilder();
                builder.append(ch1);
                builder.append(ch2);
                String s = builder.toString();

                if (checkValid(s, 1)) {
                    minuteSet.add(s);
                }

                if (checkValid(s, 2)) {
                    hourSet.add(s);
                }
            }
        }
        // 组装 minute 和 hour
        List<String> list = new ArrayList<>();
        for (String hour : hourSet) {
            for (String minute : minuteSet) {
                list.add(hour + ":" + minute);
            }
        }
        // 升序
        Collections.sort(list);
        // 找打 target 为主 之后就是
        int i = list.indexOf(time);
        if (i == list.size() - 1) {
            return list.get(0);
        }
        return list.get(i + 1);
    }

    /**
     *
     * @param num
     * @param type 1-minute 2-hour
     * @return
     */
    private boolean checkValid(String num, int type) {
        int i = Integer.parseInt(num);
        if (type == 1) {
            return 0 <= i && i <= 59;
        } else if (type == 2) {
            return 0 <= i && i <= 23;
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String time = "19:34";
        String b = solution.nextClosestTime(time);
        System.out.println(b);
        Assert.assertEquals("19:39", b);
    }
}
