package com.potato.study.leetcodecn.p00858.t001;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 858. 镜面反射
 *
 * 有一个特殊的正方形房间，每面墙上都有一面镜子。除西南角以外，每个角落都放有一个接受器，编号为 0， 1，以及 2。

 正方形房间的墙壁长度为 p，一束激光从西南角射出，首先会与东墙相遇，入射点到接收器 0 的距离为 q 。

 返回光线最先遇到的接收器的编号（保证光线最终会遇到一个接收器）。

  

 示例：

 输入： p = 2, q = 1
 输出： 2
 解释： 这条光线在第一次被反射回左边的墙时就遇到了接收器 2 。

  

 提示：

 1 <= p <= 1000
 0 <= q <= p

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/mirror-reflection
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode-cn.com/problems/mirror-reflection/solution/guang-hui-fan-she-ma-na-wo-men-jia-she-guang-bu-fa/
     * @param p
     * @param q
     * @return
     */
    public int mirrorReflection(int p, int q) {
        // 保证分子 分母不全是偶数
        while (p % 2 == 0 && q % 2 == 0) {
            p /= 2;
            q /= 2;
        }
        if (p % 2 == 0) {
            return 2;
        }
        if (q % 2 == 0) {
            return 0;
        }
        // 全是奇数
        return 1;
    }
}
