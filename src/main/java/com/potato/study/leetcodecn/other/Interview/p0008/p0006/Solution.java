package com.potato.study.leetcodecn.other.Interview.p0008.p0006;


import java.util.*;

/**
 * 面试题 08.06. 汉诺塔问题
 *
 * 在经典汉诺塔问题中，有 3 根柱子及 N 个不同大小的穿孔圆盘，盘子可以滑入任意一根柱子。一开始，所有盘子自上而下按升序依次套在第一根柱子上(即每一个盘子只能放在更大的盘子上面)。移动圆盘时受到以下限制:
 (1) 每次只能移动一个盘子;
 (2) 盘子只能从柱子顶端滑出移到下一根柱子;
 (3) 盘子只能叠在比它大的盘子上。

 请编写程序，用栈将所有盘子从第一根柱子移到最后一根柱子。

 你需要原地修改栈。

 示例1:

 输入：A = [2, 1, 0], B = [], C = []
 输出：C = [2, 1, 0]
 示例2:

 输入：A = [1, 0], B = [], C = []
 输出：C = [1, 0]
 提示:

 A中盘子的数目不大于14个。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/hanota-lcci
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode.cn/problems/hanota-lcci/solution/java-di-gui-jie-jue-lao-sheng-chang-tan-de-yi-nuo-/
     * @param a
     * @param b
     * @param c
     */
    public void hanota(List<Integer> a, List<Integer> b, List<Integer> c) {
        int n = a.size();
        place(n, a, b, c);
    }

    private void place(int i, List<Integer> a, List<Integer> b, List<Integer> c) {
        // 只有一个 直接从a 放到c
        if (i == 1) {
            c.add(a.remove(a.size() - 1));
            return;
        }
        // 递归 将n-1 从 a 经过c 放到b
        place(i-1, a, c, b);
        // 将 a 上剩下的 放到c
        c.add(a.remove(a.size() - 1));
        // 将b 上的 经过a 放到c
        place(i-1, b, a, c);
        return;
    }

}
