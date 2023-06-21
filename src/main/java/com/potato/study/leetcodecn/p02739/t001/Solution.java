package com.potato.study.leetcodecn.p02739.t001;


/**
 *
 * 2739. 总行驶距离
 *
 * 卡车有两个油箱。给你两个整数，mainTank 表示主油箱中的燃料（以升为单位），additionalTank 表示副油箱中的燃料（以升为单位）。

 该卡车每耗费 1 升燃料都可以行驶 10 km。每当主油箱使用了 5 升燃料时，如果副油箱至少有 1 升燃料，则会将 1 升燃料从副油箱转移到主油箱。

 返回卡车可以行驶的最大距离。

 注意：从副油箱向主油箱注入燃料不是连续行为。这一事件会在每消耗 5 升燃料时突然且立即发生。

  

 示例 1：

 输入：mainTank = 5, additionalTank = 10
 输出：60
 解释：
 在用掉 5 升燃料后，主油箱中燃料还剩下 (5 - 5 + 1) = 1 升，行驶距离为 50km 。
 在用掉剩下的 1 升燃料后，没有新的燃料注入到主油箱中，主油箱变为空。
 总行驶距离为 60km 。
 示例 2：

 输入：mainTank = 1, additionalTank = 2
 输出：10
 解释：
 在用掉 1 升燃料后，主油箱变为空。
 总行驶距离为 10km 。
  

 提示：

 1 <= mainTank, additionalTank <= 100

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/total-distance-traveled
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int distanceTraveled(int mainTank, int additionalTank) {
        int total = 0;
        while (mainTank >= 5) {
            total += (mainTank / 5 * 5);
            int add = mainTank/5;
            mainTank %= 5;

            if (additionalTank <= add) {
                mainTank += additionalTank;
                additionalTank = 0;
            } else {
                mainTank += add;
                additionalTank -= add;
            }
        }
        if (mainTank > 0) {
            total += mainTank;
        }
        return total * 10;
    }

}
