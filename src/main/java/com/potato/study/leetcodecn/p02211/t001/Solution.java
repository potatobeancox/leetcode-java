package com.potato.study.leetcodecn.p02211.t001;

import org.junit.Assert;

import java.util.Stack;

/**
 * 2211. 统计道路上的碰撞次数
 *
 * 在一条无限长的公路上有 n 辆汽车正在行驶。汽车按从左到右的顺序按从 0 到 n - 1 编号，每辆车都在一个 独特的 位置。

 给你一个下标从 0 开始的字符串 directions ，长度为 n 。directions[i] 可以是 'L'、'R' 或 'S' 分别表示第 i 辆车是向 左 、向 右 或者 停留 在当前位置。每辆车移动时 速度相同 。

 碰撞次数可以按下述方式计算：

 当两辆移动方向 相反 的车相撞时，碰撞次数加 2 。
 当一辆移动的车和一辆静止的车相撞时，碰撞次数加 1 。
 碰撞发生后，涉及的车辆将无法继续移动并停留在碰撞位置。除此之外，汽车不能改变它们的状态或移动方向。

 返回在这条道路上发生的 碰撞总次数 。

  

 示例 1：

 输入：directions = "RLRSLL"
 输出：5
 解释：
 将会在道路上发生的碰撞列出如下：
 - 车 0 和车 1 会互相碰撞。由于它们按相反方向移动，碰撞数量变为 0 + 2 = 2 。
 - 车 2 和车 3 会互相碰撞。由于 3 是静止的，碰撞数量变为 2 + 1 = 3 。
 - 车 3 和车 4 会互相碰撞。由于 3 是静止的，碰撞数量变为 3 + 1 = 4 。
 - 车 4 和车 5 会互相碰撞。在车 4 和车 3 碰撞之后，车 4 会待在碰撞位置，接着和车 5 碰撞。碰撞数量变为 4 + 1 = 5 。
 因此，将会在道路上发生的碰撞总次数是 5 。
 示例 2：

 输入：directions = "LLRR"
 输出：0
 解释：
 不存在会发生碰撞的车辆。因此，将会在道路上发生的碰撞总次数是 0 。
  

 提示：

 1 <= directions.length <= 105
 directions[i] 的值为 'L'、'R' 或 'S'

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/count-collisions-on-a-road
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int countCollisions(String directions) {
        // 从左往右遍历 如果遇到 s 和 R 记录 入栈 如果遇到 l 且栈非空结算 如果 s 入栈 stack 是 r 还需要循环结算
        Stack<Character> stack = new Stack<>();
        int count = 0;
        for (int i = 0; i < directions.length(); i++) {
            char ch = directions.charAt(i);
            if (ch == 'L') {
                // 如果栈空 就这样了 如果栈顶是 s 要相撞 生成两个 s 入栈
                if (stack.isEmpty()) {
                    continue;
                }
                if (stack.peek() == 'S') {
                    stack.add('S');
                    count++;
                    continue;
                }
                if (stack.peek() == 'R') {
                    count += 2;
                    // r 输出 生成2个 s
                    stack.pop();
                    while (!stack.isEmpty() && stack.peek() == 'R') {
                        stack.pop();
                        count += 1;
                    }
                    stack.push('S');
                }

            } else if (ch == 'S') {
                if (stack.isEmpty()) {
                    stack.push('S');
                    continue;
                }
                while (!stack.isEmpty() && stack.peek() == 'R') {
                    stack.pop();
                    count += 1;
                }
                stack.push('S');
            } else {
                // r
                if (stack.isEmpty()) {
                    stack.push('R');
                    continue;
                }
                if (stack.peek() == 'S' || stack.peek() == 'R') {
                    stack.push('R');
                }
            }
        }
        // 结算时 如果
        return count;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String str = "SSRSSRLLRSLLRSRSSRLRRRRLLRRLSSRR";
        int i = solution.countCollisions(str);
        System.out.println(i);
        Assert.assertEquals(20, i);
    }
}
