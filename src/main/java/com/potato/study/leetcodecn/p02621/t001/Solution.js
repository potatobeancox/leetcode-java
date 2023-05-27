/**
 * 2621. 睡眠函数
 *
 * 请你编写一个异步函数，它接收一个正整数参数 millis ，并休眠这么多毫秒。要求此函数可以解析任何值。

  

 示例 1：

 输入：millis = 100
 输出：100
 解释：
 在 100ms 后此异步函数执行完时返回一个 Promise 对象
 let t = Date.now();
 sleep(100).then(() => {
  console.log(Date.now() - t); // 100
});
 示例 2：

 输入：millis = 200
 输出：200
 解释：在 200ms 后函数执行完时返回一个 Promise 对象
  

 提示：

 1 <= millis <= 1000

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/sleep
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */


/**
 * @param {number} millis
 * https://leetcode.cn/problems/sleep/solution/js-jing-dian-de-promiseshui-mian-han-shu-togg/
 *
 * promise
 * https://blog.csdn.net/zyf971020/article/details/127015351
 */
async function sleep(millis) {
    return new Promise(fn => setTimeout(fn, millis));
}

/**
 * let t = Date.now()
 * sleep(100).then(() => console.log(Date.now() - t)) // 100
 */

