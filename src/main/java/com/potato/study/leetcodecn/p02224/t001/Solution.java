package com.potato.study.leetcodecn.p02224.t001;

/**
 * 2224. 转化时间需要的最少操作数
 *
 * 给你两个字符串 current 和 correct ，表示两个 24 小时制时间 。

 24 小时制时间 按 "HH:MM" 进行格式化，其中 HH 在 00 和 23 之间，而 MM 在 00 和 59 之间。最早的 24 小时制时间为 00:00 ，最晚的是 23:59 。

 在一步操作中，你可以将 current 这个时间增加 1、5、15 或 60 分钟。你可以执行这一操作 任意 次数。

 返回将 current 转化为 correct 需要的 最少操作数 。

  

 示例 1：

 输入：current = "02:30", correct = "04:35"
 输出：3
 解释：
 可以按下述 3 步操作将 current 转换为 correct ：
 - 为 current 加 60 分钟，current 变为 "03:30" 。
 - 为 current 加 60 分钟，current 变为 "04:30" 。
 - 为 current 加 5 分钟，current 变为 "04:35" 。
 可以证明，无法用少于 3 步操作将 current 转化为 correct 。
 示例 2：

 输入：current = "11:00", correct = "11:01"
 输出：1
 解释：只需要为 current 加一分钟，所以最小操作数是 1 。
  

 提示：

 current 和 correct 都符合 "HH:MM" 格式
 current <= correct

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/minimum-number-of-operations-to-convert-time
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int convertTime(String current, String correct) {
        String[] split1 = current.split(":");
        String[] split2 = correct.split(":");

        int minuteNum = 0;
        minuteNum += (Integer.parseInt(split2[0]) - Integer.parseInt(split1[0])) * 60;
        minuteNum += (Integer.parseInt(split2[1]) - Integer.parseInt(split1[1]));

        int count = 0;
        count += minuteNum / 60;
        minuteNum %= 60;
        count += minuteNum / 15;
        minuteNum %= 15;
        count += minuteNum / 5;
        minuteNum %= 5;
        count += minuteNum / 1;
        return count;
    }
}
