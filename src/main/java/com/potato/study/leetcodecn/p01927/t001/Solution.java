package com.potato.study.leetcodecn.p01927.t001;

/**
 * 1927. 求和游戏
 *
 * Alice 和 Bob 玩一个游戏，两人轮流行动，Alice 先手 。
 *
 * 给你一个 偶数长度 的字符串 num ，每一个字符为数字字符或者 '?' 。每一次操作中，如果 num 中至少有一个 '?' ，那么玩家可以执行以下操作：
 *
 * 选择一个下标 i 满足 num[i] == '?' 。
 * 将 num[i] 用 '0' 到 '9' 之间的一个数字字符替代。
 * 当 num 中没有 '?' 时，游戏结束。
 *
 * Bob 获胜的条件是 num 中前一半数字的和 等于 后一半数字的和。Alice 获胜的条件是前一半的和与后一半的和 不相等 。
 *
 * 比方说，游戏结束时 num = "243801" ，那么 Bob 获胜，因为 2+4+3 = 8+0+1 。如果游戏结束时 num = "243803" ，那么 Alice 获胜，因为 2+4+3 != 8+0+3 。
 * 在 Alice 和 Bob 都采取 最优 策略的前提下，如果 Alice 获胜，请返回 true ，如果 Bob 获胜，请返回 false 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：num = "5023"
 * 输出：false
 * 解释：num 中没有 '?' ，没法进行任何操作。
 * 前一半的和等于后一半的和：5 + 0 = 2 + 3 。
 * 示例 2：
 *
 * 输入：num = "25??"
 * 输出：true
 * 解释：Alice 可以将两个 '?' 中的一个替换为 '9' ，Bob 无论如何都无法使前一半的和等于后一半的和。
 * 示例 3：
 *
 * 输入：num = "?3295???"
 * 输出：false
 * 解释：Bob 总是能赢。一种可能的结果是：
 * - Alice 将第一个 '?' 用 '9' 替换。num = "93295???" 。
 * - Bob 将后面一半中的一个 '?' 替换为 '9' 。num = "932959??" 。
 * - Alice 将后面一半中的一个 '?' 替换为 '2' 。num = "9329592?" 。
 * - Bob 将后面一半中最后一个 '?' 替换为 '7' 。num = "93295927" 。
 * Bob 获胜，因为 9 + 3 + 2 + 9 = 5 + 9 + 2 + 7 。
 *  
 *
 * 提示：
 *
 * 2 <= num.length <= 105
 * num.length 是 偶数 。
 * num 只包含数字字符和 '?' 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode.cn/problems/sum-game/solution/1927-qiu-he-you-xi-java-by-wa-pian-d-cgy8/
     * @param num
     * @return
     */
    public boolean sumGame(String num) {
        // 遍历 num 统计 左边 ？ 个数 和左边的和 右边个数和右边的和
        int leftNum = 0;
        int rightNum = 0;
        int leftSum = 0;
        int rightSum = 0;
        for (int i = 0; i < num.length() / 2; i++) {
            char ch = num.charAt(i);
            if ('?' == ch) {
                leftNum++;
            } else {
                leftSum += (ch - '0');
            }
        }

        for (int i = num.length() / 2; i < num.length(); i++) {
            char ch = num.charAt(i);
            if ('?' == ch) {
                rightNum++;
            } else {
                rightSum += (ch - '0');
            }
        }

//    System.out.println(leftNum + "_" + rightNum + "_" + leftSum + "_" + rightSum);
        // 如果 个数相等 且 判断 和是否相同  相等 b总能找平，不相同 a就取9 b一定输了
        if (leftNum == rightNum) {
            return leftSum != rightSum;
        }
        // 个数不同 判断多的那边 是否还大，是的话 a肯定赢 否则计算 差值 两个的差值 a 找到多的那边一直放9 一直无法相同
        int numDiff = 0;
        int sumDiff = 0;
        if (leftNum > rightNum) {
            if (leftSum >= rightSum) {
                return true;
            }
            numDiff = leftNum - rightNum;
            sumDiff = rightSum - leftSum;
        } else {
            // leftnum < right num
            if (leftSum <= rightSum) {
                return true;
            }
            numDiff = rightNum - leftNum;
            sumDiff = leftSum - rightSum;

        }
        // 判断是否为奇数 奇数一定 a获胜
        if (numDiff % 2 == 1) {
            // a最后一手不配平
            return true;
        }
        // 判断 个数差值 / 2 * 9 是否等于 sum差值 是的话 b赢 不是 a赢  最后b操作
        return (numDiff / 2) * 9 != sumDiff;
    }



    public static void main(String[] args) {
        Solution solution = new Solution();
        String num = "?0?3105????1834??7382?997?3?????7?63116?566?701?065?13?3??38?7?488?????9";
        boolean b = solution.sumGame(num);
        System.out.println(b);
    }


}
