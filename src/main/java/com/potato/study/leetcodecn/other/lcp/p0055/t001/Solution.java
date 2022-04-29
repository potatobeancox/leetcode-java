package com.potato.study.leetcodecn.other.lcp.p0055.t001;

/**
 * LCP 55. 采集果实
 *
 * 欢迎各位勇者来到力扣新手村，本次训练内容为「采集果实」。
 *
 * 在新手村中，各位勇者需要采集一些果实来制作药剂。time[i] 表示勇者每次采集 1～limit 颗第 i 种类型的果实需要的时间（即每次最多可以采集 limit 颗果实）。
 *
 * 当前勇者需要完成「采集若干批果实」的任务， fruits[j] = [type, num] 表示第 j 批需要采集 num 颗 type 类型的果实。采集规则如下：
 *
 * 按 fruits 给定的顺序依次采集每一批次
 * 采集完当前批次的果实才能开始采集下一批次
 * 勇者完成当前批次的采集后将清空背包（即多余的果实将清空）
 * 请计算并返回勇者完成采集任务最少需要的时间。
 *
 * 示例 1：
 *
 * 输入：time = [2,3,2], fruits = [[0,2],[1,4],[2,1]], limit = 3
 *
 * 输出：10
 *
 * 解释：
 * 由于单次最多采集 3 颗
 * 第 0 批需要采集 2 颗第 0 类型果实，需要采集 1 次，耗时为 2*1=2
 * 第 1 批需要采集 4 颗第 1 类型果实，需要采集 2 次，耗时为 3*2=6
 * 第 2 批需要采集 1 颗第 2 类型果实，需要采集 1 次，耗时为 2*1=2
 * 返回总耗时 2+6+2=10
 *
 * 示例 2：
 *
 * 输入：time = [1], fruits = [[0,3],[0,5]], limit = 2
 *
 * 输出：5
 *
 * 解释：
 * 由于单次最多采集 2 颗
 * 第 0 批需要采集 3 颗第 0 类型果实，需要采集 2 次，耗时为 1*2=2
 * 第 1 批需要采集 5 颗第 0 类型果实，需要采集 3 次，耗时为 1*3=3
 * 需按照顺序依次采集，返回 2+3=5
 *
 * 提示：
 *
 * 1 <= time.length <= 100
 * 1 <= time[i] <= 100
 * 1 <= fruits.length <= 10^3
 * 0 <= fruits[i][0] < time.length
 * 1 <= fruits[i][1] < 10^3
 * 1 <= limit <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/PTXy4P
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int getMinimumTime(int[] time, int[][] fruits, int limit) {
        // 遍历 fruits 看下 这个果实需要几批次弄弄完，计算时间 累计 limit 为每次最多采集多少个果子
        int totalTime = 0;
        for (int[] fruit : fruits) {
            int type = fruit[0];
            int needNum = fruit[1];

            int batchCount = needNum / limit;
            if (needNum % limit != 0) {
                batchCount++;
            }
            totalTime += batchCount * time[type];
        }
        return totalTime;
    }




    public static void main(String[] args) {
//        Solution solution = new Solution();
//        String s = "AB";
//        int res = solution.calculate(s);
//        System.out.println(res);
//        Assert.assertEquals(4, res);
    }


}
