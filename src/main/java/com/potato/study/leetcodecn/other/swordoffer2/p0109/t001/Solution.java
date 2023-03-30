package com.potato.study.leetcodecn.other.swordoffer2.p0109.t001;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * 剑指 Offer II 109. 开密码锁
 *
 * 一个密码锁由 4 个环形拨轮组成，每个拨轮都有 10 个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。每个拨轮可以自由旋转：例如把 '9' 变为 '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
 *
 * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
 *
 * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
 *
 * 字符串 target 代表可以解锁的数字，请给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 -1 。
 *
 *  
 *
 * 示例 1:
 *
 * 输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * 输出：6
 * 解释：
 * 可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
 * 注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，因为当拨动到 "0102" 时这个锁就会被锁定。
 * 示例 2:
 *
 * 输入: deadends = ["8888"], target = "0009"
 * 输出：1
 * 解释：
 * 把最后一位反向旋转一次即可 "0000" -> "0009"。
 * 示例 3:
 *
 * 输入: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
 * 输出：-1
 * 解释：
 * 无法旋转到目标数字且不被锁定。
 * 示例 4:
 *
 * 输入: deadends = ["0000"], target = "8888"
 * 输出：-1
 *  
 *
 * 提示：
 *
 * 1 <= deadends.length <= 500
 * deadends[i].length == 4
 * target.length == 4
 * target 不在 deadends 之中
 * target 和 deadends[i] 仅由若干位数字组成
 *  
 *
 * 注意：本题与主站 752 题相同： https://leetcode-cn.com/problems/open-the-lock/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/zlDJc7
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // ii 109
    public int openLock(String[] deadends, String target) {
        // 将死锁 换成 Set
        Set<String> deadSet = new HashSet<>();
        for (String dead : deadends) {
            deadSet.add(dead);
        }
        if (deadSet.contains(target) || deadSet.contains("0000")) {
            return -1;
        }
        // 从 0000 开始 bfs
        Queue<String> queue = new LinkedList<>();
        queue.add("0000");

        Set<String> hasAppearSet = new HashSet<>();
        hasAppearSet.add("0000");
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String poll = queue.poll();
                // 对于poll 看看是不是 所求
                if (target.equals(poll)) {
                    return step;
                }
                List<String> nextList = getNextListOfPoll(poll);
                for (String next : nextList) {
                    if (hasAppearSet.contains(next)) {
                        continue;
                    }
                    if (deadSet.contains(next)) {
                        continue;
                    }
                    // 看看是不是 target
                    if (target.equals(next)) {
                        return step + 1;
                    }
                    queue.add(next);
                    hasAppearSet.add(next);
                }
            }
            step++;
        }
        return -1;
    }

    /**
     * poll 是4个数字 字符构成的字符串 对其进行只有一个距离字符修改
     * @param poll
     * @return
     */
    private List<String> getNextListOfPoll(String poll) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            char[] chars = poll.toCharArray();
            if (chars[i] == '0') {
                chars[i] = '9';
                result.add(new String(chars));
                chars[i] = '1';
                result.add(new String(chars));
            } else if (chars[i] == '9') {
                chars[i] = '8';
                result.add(new String(chars));
                chars[i] = '0';
                result.add(new String(chars));
            } else {
                // 前后都有
                chars[i]++;
                result.add(new String(chars));
                chars[i]--;
                result.add(new String(chars));
            }
        }
        return result;
    }
}
