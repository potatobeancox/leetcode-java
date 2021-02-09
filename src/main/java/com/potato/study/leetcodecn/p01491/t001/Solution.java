package com.potato.study.leetcodecn.p01491.t001;

/**
 * 1491. 去掉最低工资和最高工资后的工资平均值
 *
 * 给你一个整数数组 salary ，数组里每个数都是 唯一 的，其中 salary[i] 是第 i 个员工的工资。

 请你返回去掉最低工资和最高工资以后，剩下员工工资的平均值。

  

 示例 1：

 输入：salary = [4000,3000,1000,2000]
 输出：2500.00000
 解释：最低工资和最高工资分别是 1000 和 4000 。
 去掉最低工资和最高工资以后的平均工资是 (2000+3000)/2= 2500
 示例 2：

 输入：salary = [1000,2000,3000]
 输出：2000.00000
 解释：最低工资和最高工资分别是 1000 和 3000 。
 去掉最低工资和最高工资以后的平均工资是 (2000)/1= 2000
 示例 3：

 输入：salary = [6000,5000,4000,3000,2000,1000]
 输出：3500.00000
 示例 4：

 输入：salary = [8000,9000,2000,3000,6000,1000]
 输出：4750.00000
  

 提示：

 3 <= salary.length <= 100
 10^3 <= salary[i] <= 10^6
 salary[i] 是唯一的。
 与真实值误差在 10^-5 以内的结果都将视为正确答案。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/average-salary-excluding-the-minimum-and-maximum-salary
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 遍历一遍 找到最大最小值
     * @param salary
     * @return
     */
    public double average(int[] salary) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        long sum = 0;
        for (int num : salary) {
            sum += num;
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        sum = sum - max - min;
        return 1.0 * sum / (salary.length - 2);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] salary = new int[] {4000,3000,1000,2000};
        double average = solution.average(salary);
        // 2500.00000
        System.out.println(average);
    }
}
