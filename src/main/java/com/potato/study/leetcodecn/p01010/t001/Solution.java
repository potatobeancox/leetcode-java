package com.potato.study.leetcodecn.p01010.t001;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

import com.potato.study.leetcode.util.LeetcodeInputUtils;

/**
 * 1010. 总持续时间可被 60 整除的歌曲
 *
 * 在歌曲列表中，第 i 首歌曲的持续时间为 time[i] 秒。
 *
 * 返回其总持续时间（以秒为单位）可被 60 整除的歌曲对的数量。形式上，我们希望下标数字 i 和 j 满足  i < j 且有 (time[i] + time[j]) % 60 == 0。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：time = [30,20,150,100,40]
 * 输出：3
 * 解释：这三对的总持续时间可被 60 整除：
 * (time[0] = 30, time[2] = 150): 总持续时间 180
 * (time[1] = 20, time[3] = 100): 总持续时间 120
 * (time[1] = 20, time[4] = 40): 总持续时间 60
 * 示例 2：
 *
 * 输入：time = [60,60,60]
 * 输出：3
 * 解释：所有三对的总持续时间都是 120，可以被 60 整除。
 *  
 *
 * 提示：
 *
 * 1 <= time.length <= 6 * 104
 * 1 <= time[i] <= 500
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/pairs-of-songs-with-total-durations-divisible-by-60
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 1010
    public int numPairsDivisibleBy60(int[] time) {
        // 使用一个map 记录余数 从 0-30进行遍历
        Map<Integer, Integer> remainderCountMap = new HashMap<>();
        for (int t : time) {
            int key = t % 60;
            Integer count = remainderCountMap.getOrDefault(key, 0);
            count++;
            remainderCountMap.put(key, count);
        }
        // 从 0 - 30计算
        int pairNum = 0;
        for (int i = 0; i <= 30; i++) {
            Integer count = remainderCountMap.get(i);
            if (count == null || count <= 0) {
                continue;
            }
            if (i == 0 || i == 30) {
                pairNum += (count * (count - 1) / 2);
            } else {
                // 查看差值
                int otherKey = 60 - i;
                if (remainderCountMap.containsKey(otherKey)) {
                    pairNum += count * remainderCountMap.get(otherKey);
                }
            }
        }
        return pairNum;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[] {
                30,20,150,100,40
        };
        int i = solution.numPairsDivisibleBy60(arr);
        System.out.println(i);
        Assert.assertEquals(3, i);
    }


}
