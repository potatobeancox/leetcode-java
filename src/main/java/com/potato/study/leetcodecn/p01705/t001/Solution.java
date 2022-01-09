package com.potato.study.leetcodecn.p01705.t001;

import org.junit.Assert;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 1705. 吃苹果的最大数目
 *
 * 有一棵特殊的苹果树，一连 n 天，每天都可以长出若干个苹果。在第 i 天，树上会长出 apples[i] 个苹果，这些苹果将会在 days[i] 天后（也就是说，第 i + days[i] 天时）腐烂，变得无法食用。也可能有那么几天，树上不会长出新的苹果，此时用 apples[i] == 0 且 days[i] == 0 表示。

 你打算每天 最多 吃一个苹果来保证营养均衡。注意，你可以在这 n 天之后继续吃苹果。

 给你两个长度为 n 的整数数组 days 和 apples ，返回你可以吃掉的苹果的最大数目。

  

 示例 1：

 输入：apples = [1,2,3,5,2], days = [3,2,1,4,2]
 输出：7
 解释：你可以吃掉 7 个苹果：
 - 第一天，你吃掉第一天长出来的苹果。
 - 第二天，你吃掉一个第二天长出来的苹果。
 - 第三天，你吃掉一个第二天长出来的苹果。过了这一天，第三天长出来的苹果就已经腐烂了。
 - 第四天到第七天，你吃的都是第四天长出来的苹果。
 示例 2：

 输入：apples = [3,0,0,0,0,2], days = [3,0,0,0,0,2]
 输出：5
 解释：你可以吃掉 5 个苹果：
 - 第一天到第三天，你吃的都是第一天长出来的苹果。
 - 第四天和第五天不吃苹果。
 - 第六天和第七天，你吃的都是第六天长出来的苹果。
  

 提示：

 apples.length == n
 days.length == n
 1 <= n <= 2 * 104
 0 <= apples[i], days[i] <= 2 * 104
 只有在 apples[i] = 0 时，days[i] = 0 才成立

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/maximum-number-of-eaten-apples
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode-cn.com/problems/maximum-number-of-eaten-apples/solution/chi-ping-guo-de-zui-da-shu-mu-by-leetcod-93ka/
     * @param apples
     * @param days
     * @return
     */
    public int eatenApples(int[] apples, int[] days) {
        // 优先级队列 过期日期 剩下的苹果 0 是过期日期， 1是还有多少苹果
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });
        // 终止条件 没有苹果了
        int dayIndex = 0;
        int eatCount = 0;
        while (dayIndex < apples.length || !priorityQueue.isEmpty()) {
            // 记录当前index 先去 加上今天新鲜的苹果
            if (dayIndex < apples.length) {
                priorityQueue.add(new int[] {dayIndex + days[dayIndex], apples[dayIndex]});
            }
            // 然后扔了过期的苹果
            while (!priorityQueue.isEmpty()
                    && (priorityQueue.peek()[0] <= dayIndex || priorityQueue.peek()[1] == 0)) {
                priorityQueue.poll();
            }
            // 还有没有苹果 有的话 吃一个
            if (!priorityQueue.isEmpty()) {
                priorityQueue.peek()[1]--;
                eatCount++;
            }
            dayIndex++;
        }
        return eatCount;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] apples = new int[] {1,2,3,5,2};
        int[] days = new int[] {3,2,1,4,2};
        int i = solution.eatenApples(apples, days);
        System.out.println(i);
        Assert.assertEquals(7, i);



        apples = new int[] {3,0,0,0,0,2};
        days = new int[] {3,0,0,0,0,2};
        i = solution.eatenApples(apples, days);
        System.out.println(i);
        Assert.assertEquals(5, i);
    }



}
