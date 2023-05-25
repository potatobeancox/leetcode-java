/**
 * 2619. 数组原型对象的最后一个元素
 *
 * 请你编写一段代码实现一个数组方法，使任何数组都可以调用 array.last() 方法，这个方法将返回数组最后一个元素。如果数组中没有元素，则返回 -1 。
 *
 *  
 *
 * 示例 1 ：
 *
 * 输入：nums = [1,2,3]
 * 输出：3
 * 解释：调用 nums.last() 后返回最后一个元素： 3。
 * 示例 2 ：
 *
 * 输入：nums = []
 * 输出：-1
 * 解释：因为此数组没有元素，所以应该返回 -1。
 *  
 *
 * 提示：
 *
 * 0 <= arr.length <= 1000
 * 0 <= arr[i] <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/array-prototype-last
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
Array.prototype.last = function() {
    return this[this.length-1] ?? -1;
};
