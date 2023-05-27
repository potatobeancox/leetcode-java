/**
 * 2620. 计数器
 *
 * 请你编写并返回一个 计数器 函数，它接收一个整型参数 n 。这个 计数器 函数最初返回 n，每次调用它时返回前一个值加 1 的值 ( n ,  n + 1 ,  n + 2 ，等等)。

  

 示例 1：

 输入：
 n = 10
 ["call","call","call"]
 输出：[10,11,12]
 解释：
 counter() = 10 // 第一次调用 counter()，返回 n。
 counter() = 11 // 返回上次调用的值加 1。
 counter() = 12 // 返回上次调用的值加 1。
 示例 2：

 输入：
 n = -2
 ["call","call","call","call","call"]
 输出：[-2,-1,0,1,2]
 解释：counter() 最初返回 -2。然后在每个后续调用后增加 1。
  

 提示：

 -1000 <= n <= 1000
 最多对 counter() 进行 1000 次调用

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/counter
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
/**
 * @param {number} n
 * @return {Function} counter
 */
var createCounter = function(n) {
    let count = n;
    return function() {
        return count++;
    };
};

/**
 * const counter = createCounter(10)
 * counter() // 10
 * counter() // 11
 * counter() // 12
 */