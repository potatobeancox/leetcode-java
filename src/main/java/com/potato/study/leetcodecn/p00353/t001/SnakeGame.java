package com.potato.study.leetcodecn.p00353.t001;

import org.junit.Assert;

import java.util.*;

/**
 * 353. 贪吃蛇
 *
 * 请你设计一个 贪吃蛇游戏，该游戏将会在一个 屏幕尺寸 = 宽度 x 高度 的屏幕上运行。如果你不熟悉这个游戏，可以 点击这里 在线试玩。

 起初时，蛇在左上角的 (0, 0) 位置，身体长度为 1 个单位。

 你将会被给出一个数组形式的食物位置序列 food ，其中 food[i] = (ri, ci) 。当蛇吃到食物时，身子的长度会增加 1 个单位，得分也会 +1 。

 食物不会同时出现，会按列表的顺序逐一显示在屏幕上。比方讲，第一个食物被蛇吃掉后，第二个食物才会出现。

 当一个食物在屏幕上出现时，保证 不会 出现在被蛇身体占据的格子里。

 如果蛇越界（与边界相撞）或者头与 移动后 的身体相撞（即，身长为 4 的蛇无法与自己相撞），游戏结束。

 实现 SnakeGame 类：

 SnakeGame(int width, int height, int[][] food) 初始化对象，屏幕大小为 height x width ，食物位置序列为 food
 int move(String direction) 返回蛇在方向 direction 上移动后的得分。如果游戏结束，返回 -1 。
  
 示例 1：


 输入：
 ["SnakeGame", "move", "move", "move", "move", "move", "move"]
 [[3, 2, [[1, 2], [0, 1]]], ["R"], ["D"], ["R"], ["U"], ["L"], ["U"]]
 输出：
 [null, 0, 0, 1, 1, 2, -1]

 解释：
 SnakeGame snakeGame = new SnakeGame(3, 2, [[1, 2], [0, 1]]);
 snakeGame.move("R"); // 返回 0
 snakeGame.move("D"); // 返回 0
 snakeGame.move("R"); // 返回 1 ，蛇吃掉了第一个食物，同时第二个食物出现在 (0, 1)
 snakeGame.move("U"); // 返回 1
 snakeGame.move("L"); // 返回 2 ，蛇吃掉了第二个食物，没有出现更多食物
 snakeGame.move("U"); // 返回 -1 ，蛇与边界相撞，游戏结束
  

 提示：

 1 <= width, height <= 104
 1 <= food.length <= 50
 food[i].length == 2
 0 <= ri < height
 0 <= ci < width
 direction.length == 1
 direction is 'U', 'D', 'L', or 'R'.
 最多调用 104 次 move 方法

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/design-snake-game
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class SnakeGame {



    private int width;
    private int height;

    private int x;
    private int y;

    // 食物
//    private Set<String> foodPosition;
    private int[][] food;
    private int foodIndex;

    // snake
    private Set<String> snakeSet;
    private Queue<String> snakeQueue;


    // 得分
    private int score;


    /**
     *
     * @param width
     * @param height
     * @param food
     */
    public SnakeGame(int width, int height, int[][] food) {
        // 需要记录 当前蛇尾位 当前蛇头位置  蛇头用坐标 蛇尾记录在queue中
        this.width = width;
        this.height = height;
        // 当前位置
        this.x = 0;
        this.y = 0;
        // set string 存food位置
        this.food = food;
        this.foodIndex = 0;
        // queue 代表蛇的身子
        this.snakeSet = new HashSet<>();
        snakeSet.add(getKey(0,0));
        this.snakeQueue = new LinkedList<>();
        snakeQueue.add(getKey(0, 0));

        this.score = 0;
    }


    private String getKey(int x, int y) {
        return x + "_" + y;
    }

    public int move(String direction) {
        // 走完之后 蛇头位置
        switch (direction) {
            case "U" :
                x--;
                break;
            case "D" :
                x++;
                break;
            case "L" :
                y--;
                break;
            case "R" :
                y++;
                break;
        }
        // 判断待增加蛇头 是否已经越界
        if (x < 0 || y < 0 || x >= this.height | y >= this.width) {
            return -1;
        }
        // 当前位置 是否是 食物
        String currentPosition = getKey(x, y);

        if (foodIndex < food.length) {
            String foodPosition = getKey(food[foodIndex][0], food[foodIndex][1]);
            if (foodPosition.equals(currentPosition)) {
                score++;
                foodIndex++;
            } else {
                // 没有食物 尾巴修改
                String poll = snakeQueue.poll();
                snakeSet.remove(poll);
            }
        } else {
            // 没有食物 尾巴修改
            String poll = snakeQueue.poll();
            snakeSet.remove(poll);
        }
        // 直接判断 是不是撞上了 是否已经碰到蛇身子 使用 set
        if (snakeSet.contains(currentPosition)) {
            return -1;
        }
        // 弹出蛇尾 (如果没有吃的) 增加蛇头
        snakeQueue.add(currentPosition);
        snakeSet.add(currentPosition);
        return this.score;
    }

    public static void main(String[] args) {
        int width = 3;
        int height = 2;

        int[][] food = new int[][] {
                {1,2},
                {0,1}
        };
        SnakeGame snakeGame = new SnakeGame(width, height, food);
        // [null,0,0,1,1,2,-1]
        System.out.println(snakeGame.move("R"));
        System.out.println(snakeGame.move("D"));
        System.out.println(snakeGame.move("R"));
        System.out.println(snakeGame.move("U"));
        System.out.println(snakeGame.move("L"));
        System.out.println(snakeGame.move("U"));

    }
}