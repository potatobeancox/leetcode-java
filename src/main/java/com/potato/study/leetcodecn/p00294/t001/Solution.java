package com.potato.study.leetcodecn.p00294.t001;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 294. 翻转游戏 II
 *
 * 你和朋友玩一个叫做「翻转游戏」的游戏。游戏规则如下：

 给你一个字符串 currentState ，其中只含 '+' 和 '-' 。你和朋友轮流将 连续 的两个 "++" 反转成 "--" 。当一方无法进行有效的翻转时便意味着游戏结束，则另一方获胜。默认每个人都会采取最优策略。

 请你写出一个函数来判定起始玩家 是否存在必胜的方案 ：如果存在，返回 true ；否则，返回 false 。

  
 示例 1：

 输入：currentState = "++++"
 输出：true
 解释：起始玩家可将中间的 "++" 翻转变为 "+--+" 从而得胜。
 示例 2：

 输入：currentState = "+"
 输出：false
  

 提示：

 1 <= currentState.length <= 60
 currentState[i] 不是 '+' 就是 '-'

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/flip-game-ii
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 使用一个 map 缓存先手 对应某种局面结果
    private Map<String, Boolean> keyResultMap = new HashMap<>();

    public boolean canWin(String currentState) {
        if (keyResultMap.containsKey(currentState)) {
            return keyResultMap.get(currentState);
        }
        // 如果本次没有遇到过 替换一个++ 依次选择 递归看看 对方能不能输
        for (int i = 0; i < currentState.length() - 1; i++) {
            if (currentState.charAt(i) == '+'
                    && currentState.charAt(i+1) == '+') {
                // 替换一下试试
                String newState = "";
                if (i > 0) {
                    newState += currentState.substring(0, i);
                }
                newState += "--";
                if (i + 2 <= currentState.length()) {
                    newState += currentState.substring(i+2);
                }
                if (!canWin(newState)) {
                    keyResultMap.put(currentState, true);
                    return true;
                }
            }
        }
        keyResultMap.put(currentState, false);
        return false;
    }


}
