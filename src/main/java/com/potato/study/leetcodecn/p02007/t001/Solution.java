package com.potato.study.leetcodecn.p02007.t001;

import java.util.*;

/**
 * 2007. 从双倍数组中还原原数组
 *
 * 一个整数数组 original 可以转变成一个 双倍 数组 changed ，转变方式为将 original 中每个元素 值乘以 2 加入数组中，然后将所有元素 随机打乱 。

 给你一个数组 changed ，如果 change 是 双倍 数组，那么请你返回 original数组，否则请返回空数组。original 的元素可以以 任意 顺序返回。

  

 示例 1：

 输入：changed = [1,3,4,2,6,8]
 输出：[1,3,4]
 解释：一个可能的 original 数组为 [1,3,4] :
 - 将 1 乘以 2 ，得到 1 * 2 = 2 。
 - 将 3 乘以 2 ，得到 3 * 2 = 6 。
 - 将 4 乘以 2 ，得到 4 * 2 = 8 。
 其他可能的原数组方案为 [4,3,1] 或者 [3,1,4] 。
 示例 2：

 输入：changed = [6,3,0,1]
 输出：[]
 解释：changed 不是一个双倍数组。
 示例 3：

 输入：changed = [1]
 输出：[]
 解释：changed 不是一个双倍数组。
  

 提示：

 1 <= changed.length <= 105
 0 <= changed[i] <= 105


 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/find-original-array-from-doubled-array
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int[] findOriginalArray(int[] changed) {
        if (changed.length == 0 || changed.length % 2 == 1) {
            return new int[0];
        }
        // 将 changed 按照 升序排序
        Arrays.sort(changed);
        // 遍历 changed 使用一个 queue 记录 要匹配的2倍数
        Queue<Integer> toMatchQueue = new LinkedList<>();
        int[] result = new int[changed.length / 2];
        int index = 0;
        for (int change : changed) {
            // 如果当前 changed i 没有 在 queue 头部 说明是 还没有 匹配的 1倍数 ，否则匹配 将1倍 放在原数组中
            if (toMatchQueue.isEmpty() || change != toMatchQueue.peek()) {
                toMatchQueue.add(change * 2);
            } else {
                // 找到了 计算结果
                result[index] = change / 2;
                toMatchQueue.poll();
                index++;
            }
        }
        if (toMatchQueue.isEmpty()) {
            return result;
        }
        return new int[0];
    }

}

