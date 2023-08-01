package com.potato.study.leetcodecn.p02753.t001;


/**
 *
 * 2753. 计算一个环形街道上的房屋数量 II

 *
 * 给定一个代表 环形 街道的类 Street 和一个正整数 k，表示街道上房屋的最大数量（也就是说房屋数量不超过 k）。每个房屋的门初始时可以是开着的也可以是关着的（至少有一个房屋的门是开着的）。

 刚开始，你站在一座房子的门前。你的任务是计算街道上的房屋数量。

 Street 类包含以下函数：

 void closeDoor()：关闭当前房屋的门。
 boolean isDoorOpen()：如果当前房屋的门是开着的返回 true，否则返回 false。
 void moveRight()：向右移动到下一座房屋。
 注意：在 环形 街道内，如果将房屋从 1 到 n 编号，则当 i < n 时 housei 右边的房子是 housei+1，housen 右边的房子是 house1。

 返回 ans，它表示街道上的房屋数量。

  

 示例 1：

 输入：street = [1,1,1,1], k = 10
 输出：4
 解释：街道上有 4 座房屋，它们的门都是关着的。
 房屋数量小于 k，即 10。
 示例 2：

 输入：street = [1,0,1,1,0], k = 5
 输出：5
 解释：街道上有 5 座房屋，向右移动时第 1、3 和 4 座房屋的门是开着的，其余的门都是关着的。
 房屋数量等于 k，即 5。
  

 提示：

 n 是房屋数量
 1 <= n <= k <= 105
 street 是环形的
 输入数据中至少有一扇门是开着的

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/count-houses-in-a-circular-street-ii
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {



    public int houseCount(Street street, int k) {

        return -1;
    }


}

class Street {
    public Street(int[] doors) {

    }
    public void closeDoor() {

    }
    public boolean isDoorOpen() {
        return false;
    }
    public void moveRight() {}
}