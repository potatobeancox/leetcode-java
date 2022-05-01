package com.potato.study.leetcodecn.p01138.t001;


/**
 * 1138. 字母板上的路径
 *
 * 我们从一块字母板上的位置 (0, 0) 出发，该坐标对应的字符为 board[0][0]。
 *
 * 在本题里，字母板为board = ["abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"]，如下所示。
 *
 *
 *
 * 我们可以按下面的指令规则行动：
 *
 * 如果方格存在，'U' 意味着将我们的位置上移一行；
 * 如果方格存在，'D' 意味着将我们的位置下移一行；
 * 如果方格存在，'L' 意味着将我们的位置左移一列；
 * 如果方格存在，'R' 意味着将我们的位置右移一列；
 * '!' 会把在我们当前位置 (r, c) 的字符 board[r][c] 添加到答案中。
 * （注意，字母板上只存在有字母的位置。）
 *
 * 返回指令序列，用最小的行动次数让答案和目标 target 相同。你可以返回任何达成目标的路径。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：target = "leet"
 * 输出："DDR!UURRR!!DDD!"
 * 示例 2：
 *
 * 输入：target = "code"
 * 输出："RR!DDRR!UUL!R!"
 *  
 *
 * 提示：
 *
 * 1 <= target.length <= 100
 * target 仅含有小写英文字母。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/alphabet-board-path
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public String alphabetBoardPath(String target) {
        // 因为z的原因 先往上在往左  先往右再往下
        int x = 0;
        int y = 0;
        // 每个字母 计算与下个字母之前的 xy 左边差 直到往哪走
        StringBuilder builder = new StringBuilder();
        char[] chars = target.toCharArray();
        for (int i = 0; i < target.length(); i++) {
            char ch = chars[i];
            // 计算当前ch 应该在的位置
            int dx = (ch - 'a') / 5;
            int dy = (ch - 'a') % 5;

            int moveX = dx - x;
            int moveY = dy - y;

            // 先上 再左 再右 在下
            while (moveX < 0) {
                builder.append("U");
                moveX++;
            }
            while (moveY < 0) {
                builder.append("L");
                moveY++;
            }
            while (moveY > 0) {
                builder.append("R");
                moveY--;
            }
            while (moveX > 0) {
                builder.append("D");
                moveX--;
            }
            builder.append("!");
            x = dx;
            y = dy;
        }
        return builder.toString();
    }


}
