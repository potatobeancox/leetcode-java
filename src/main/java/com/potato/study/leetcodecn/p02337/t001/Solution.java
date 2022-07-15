package com.potato.study.leetcodecn.p02337.t001;

import java.util.TreeSet;

/**
 * 2337. 移动片段得到字符串
 *
 * 给你两个字符串 start 和 target ，长度均为 n 。每个字符串 仅 由字符 'L'、'R' 和 '_' 组成，其中：

 字符 'L' 和 'R' 表示片段，其中片段 'L' 只有在其左侧直接存在一个 空位 时才能向 左 移动，而片段 'R' 只有在其右侧直接存在一个 空位 时才能向 右 移动。
 字符 '_' 表示可以被 任意 'L' 或 'R' 片段占据的空位。
 如果在移动字符串 start 中的片段任意次之后可以得到字符串 target ，返回 true ；否则，返回 false 。

  

 示例 1：

 输入：start = "_L__R__R_", target = "L______RR"
 输出：true
 解释：可以从字符串 start 获得 target ，需要进行下面的移动：
 - 将第一个片段向左移动一步，字符串现在变为 "L___R__R_" 。
 - 将最后一个片段向右移动一步，字符串现在变为 "L___R___R" 。
 - 将第二个片段向右移动散步，字符串现在变为 "L______RR" 。
 可以从字符串 start 得到 target ，所以返回 true 。
 示例 2：

 输入：start = "R_L_", target = "__LR"
 输出：false
 解释：字符串 start 中的 'R' 片段可以向右移动一步得到 "_RL_" 。
 但是，在这一步之后，不存在可以移动的片段，所以无法从字符串 start 得到 target 。
 示例 3：

 输入：start = "_R", target = "R_"
 输出：false
 解释：字符串 start 中的片段只能向右移动，所以无法从字符串 start 得到 target 。
  

 提示：

 n == start.length == target.length
 1 <= n <= 105
 start 和 target 由字符 'L'、'R' 和 '_' 组成

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/move-pieces-to-obtain-a-string
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode.cn/problems/move-pieces-to-obtain-a-string/solution/nao-jin-ji-zhuan-wan-pythonjavacgo-by-en-9sqt/
     * @param start
     * @param target
     * @return
     */
    public boolean canChange(String start, String target) {
        // 先通过去掉 - 判断是不是 排列是对着的 不是直接返回
        String startSeq = start.replace("_", "");
        String targetSeq = target.replace("_", "");
        if (!startSeq.equals(targetSeq)) {
            return false;
        }
        // 分别遍历 start 和 target 如果当前字幕是 L 那么 ij 分别代表 start 和 end 位置， i如果出现在 j之后 就返回false 同理 R
        int i = 0;
        int j = 0;
        while (i < start.length() && j < target.length()) {
            while (i < start.length() && start.charAt(i) == '_') {
                i++;
            }
            while (j < target.length() && target.charAt(j) == '_') {
                j++;
            }
            if (i == start.length() || j == target.length()) {
                break;
            }
            // 判断 ij ij一定相同 L只能往左 R 只能往右
            if (start.charAt(i) == 'L' && i < j) {
                return false;
            } else if (start.charAt(i) == 'R' && i > j){
                return false;
            }
            i++;
            j++;

        }
        return true;
    }
}
