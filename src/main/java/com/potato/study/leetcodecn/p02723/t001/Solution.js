/**
 * 2723. 添加两个 Promise 对象
 *
 * 给定两个 promise 对象 promise1 和 promise2，返回一个新的 promise。promise1 和 promise2 都会被解析为一个数字。返回的 Promise 应该解析为这两个数字的和。
 *  
 *
 * 示例 1：
 *
 * 输入：
 * promise1 = new Promise(resolve => setTimeout(() => resolve(2), 20)),
 * promise2 = new Promise(resolve => setTimeout(() => resolve(5), 60))
 * 输出：7
 * 解释：两个输入的 Promise 分别解析为值 2 和 5。返回的 Promise 应该解析为 2 + 5 = 7。返回的 Promise 解析的时间不作为判断条件。
 * 示例 2：
 *
 * 输入：
 * promise1 = new Promise(resolve => setTimeout(() => resolve(10), 50)),
 * promise2 = new Promise(resolve => setTimeout(() => resolve(-12), 30))
 * 输出：-2
 * 解释：两个输入的 Promise 分别解析为值 10 和 -12。返回的 Promise 应该解析为 10 + -12 = -2。
 *  
 *
 * 提示：
 *
 * promise1 和 promise2 都是被解析为一个数字的 promise 对象
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/add-two-promises
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
/**
 * @param {Promise} promise1
 * @param {Promise} promise2
 * @return {Promise}
 * https://leetcode.cn/problems/add-two-promises/solution/javascript-tian-jia-liang-ge-promisedui-b4s95/
 */
var addTwoPromises = async function(promise1, promise2) {
    let n1 = await promise1;
    let n2 = await promise2;
    // https://www.cnblogs.com/qianxiaox/p/14124551.html
    return Promise.resolve(n1 + n2);
};

/**
 * addTwoPromises(Promise.resolve(2), Promise.resolve(2))
 *   .then(console.log); // 4
 */

