/**
 * 2703. 返回传递的参数的长度
 *
 * 请你编写一个函数 argumentsLength，返回传递给该函数的参数数量。
 *  
 *
 * 示例 1：
 *
 * 输入：argsArr = [5]
 * 输出：1
 * 解释：
 * argumentsLength(5); // 1
 *
 * 只传递了一个值给函数，因此它应返回 1。
 * 示例 2：
 *
 * 输入：argsArr = [{}, null, "3"]
 * 输出：3
 * 解释：
 * argumentsLength({}, null, "3"); // 3
 *
 * 传递了三个值给函数，因此它应返回 3。
 *  
 *
 * 提示：
 *
 * argsArr 是一个有效的 JSON 数组
 * 0 <= argsArr.length <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/return-length-of-arguments-passed
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

/**
 * @return {number}
 */
var argumentsLength = function(...args) {
    return args.length;
};