/**
 * 2629. 复合函数
 *
 * 请你编写一个函数，它接收一个函数数组 [f1, f2, f3，…， fn] ，并返回一个新的函数 fn ，它是函数数组的 复合函数 。

 [f(x)， g(x)， h(x)] 的 复合函数 为 fn(x) = f(g(h(x))) 。

 一个空函数列表的 复合函数 是 恒等函数 f(x) = x 。

 你可以假设数组中的每个函数接受一个整型参数作为输入，并返回一个整型作为输出。

  

 示例 1：

 输入：functions = [x => x + 1, x => x * x, x => 2 * x], x = 4
 输出：65
 解释：
 从右向左计算......
 Starting with x = 4.
 2 * (4) = 8
 (8) * (8) = 64
 (64) + 1 = 65
 示例 2：

 输出：functions = [x => 10 * x, x => 10 * x, x => 10 * x], x = 1
 输入：1000
 解释：
 从右向左计算......
 10 * (1) = 10
 10 * (10) = 100
 10 * (100) = 1000
 示例 3：

 输入：functions = [], x = 42
 输出：42
 解释：
 空函数列表的复合函数就是恒等函数
  

 提示：

 -1000 <= x <= 1000
 0 <= functions.length <= 1000
 所有函数都接受并返回一个整型

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/function-composition
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */


/**
 * https://leetcode.cn/problems/function-composition/solution/yi-ge-xun-huan-by-wmorning-f0a7/
 * @param functions
 * @returns {Function}
 */
var compose = function(functions) {
    return function(x) {
        let res = x;
        for (let index = functions.length - 1 ; index >= 0 ;index--) {
            res = functions[index](res);
        }
        return res;
    }
};
