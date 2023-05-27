/**
 * 2648. 生成斐波那契数列
 *
 * 请你编写一个生成器函数，并返回一个可以生成 斐波那契数列 的生成器对象。

 斐波那契数列 的递推公式为 Xn = Xn-1 + Xn-2 。

 这个数列的前几个数字是 0, 1, 1, 2, 3, 5, 8, 13 。

  

 示例 1：

 输入：callCount = 5
 输出：[0,1,1,2,3]
 解释：
 const gen = fibGenerator();
 gen.next().value; // 0
 gen.next().value; // 1
 gen.next().value; // 1
 gen.next().value; // 2
 gen.next().value; // 3
 示例 2：

 输入：callCount = 0
 输出：[]
 解释：gen.next() 永远不会被调用，所以什么也不会输出
  

 提示：

 0 <= callCount <= 50

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/generate-fibonacci-sequence
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

/**
 * yield
 * https://blog.csdn.net/lan128lan/article/details/108443034
 */
var fibGenerator = function*() {
    let pre = 0;
    let cur = 1;
    yield pre;
    yield cur;
    while (true) {
        [cur, pre] = [pre + cur, cur];
        yield cur;
    };
};