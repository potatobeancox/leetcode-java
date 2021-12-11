package com.potato.study.leetcodecn.p00282.t001;


import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * 282. 给表达式添加运算符
 *
 * 给定一个仅包含数字 0-9 的字符串 num 和一个目标值整数 target ，在 num 的数字之间添加 二元 运算符（不是一元）+、- 或 * ，返回所有能够得到目标值的表达式。

  

 示例 1:

 输入: num = "123", target = 6
 输出: ["1+2+3", "1*2*3"]
 示例 2:

 输入: num = "232", target = 8
 输出: ["2*3+2", "2+3*2"]
 示例 3:

 输入: num = "105", target = 5
 输出: ["1*0+5","10-5"]
 示例 4:

 输入: num = "00", target = 0
 输出: ["0+0", "0-0", "0*0"]
 示例 5:

 输入: num = "3456237490", target = 9191
 输出: []
  

 提示：

 1 <= num.length <= 10
 num 仅含数字
 -231 <= target <= 231 - 1

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/expression-add-operators
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {




    public List<String> addOperators(String num, int target) {
        List<String> resultList = new ArrayList<>();
        getOperators(num, 0, target, 0, 0, "", resultList, 0);
        return resultList;
    }


    /**
     *
     * @param num           总的字符串
     * @param index         当前从哪个位置开始获取数组
     * @param target        最终的记过
     * @param currentResult      当前表达式计算得到的值
     * @param lastResult    上一个表达式计算的结果 加减就是 上一个数字 乘法是上一个结果
     * @param resultStr     当前值
     */
    public void getOperators(String num, int index, int target, long currentResult, long lastResult,
                             String resultStr, List<String> resultList, int usedNum) {
        // 如果当前index 到了 最后 看一下是够 currentResult 是够已经等于 target 是的话 增加结果
        if (index == num.length()) {
            if (target == currentResult && usedNum == num.length()) {
                resultList.add(resultStr);
            }
            return;
        }
        // 从index 开始往后 生成 num 使用 +-x 与结果运算 递归往后生成
        long currentNum = 0;
        for (int i = index; i < num.length(); i++) {
            currentNum *= 10;
            currentNum += (num.charAt(i) - '0');
            usedNum++;
            if (index == 0) {
                // 第一个数字 直接往后计数
                getOperators(num, i + 1, target, currentNum, currentNum, String.valueOf(currentNum), resultList, usedNum);

            } else {
                // 使用 + - *
                getOperators(num, i + 1, target, currentResult + currentNum, currentNum,
                        resultStr + '+' + currentNum, resultList, usedNum);


                getOperators(num, i + 1, target, currentResult - currentNum, -1 * currentNum,
                        resultStr + '-' + currentNum, resultList, usedNum);

                getOperators(num, i + 1, target, currentResult - lastResult + lastResult * currentNum,
                        lastResult * currentNum,
                        resultStr + '*' + currentNum, resultList, usedNum);
            }
            // index 开始 到 current 出现前导0 不能继续为数字了
            if (currentNum == 0) {
                break;
            }

        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<String> list = solution.addOperators("105", 5);
        System.out.println(list);

        list = solution.addOperators("2147483648", -2147483648);
        System.out.println(list);
    }


}
