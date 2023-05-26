/**
 * 2695. 包装数组
 *
 * 创建一个名为 ArrayWrapper 的类，它在其构造函数中接受一个整数数组作为参数。该类应具有以下两个特性：

 当使用 + 运算符将两个该类的实例相加时，结果值为两个数组中所有元素的总和。
 当在实例上调用 String() 函数时，它将返回一个由逗号分隔的括在方括号中的字符串。例如，[1,2,3] 。
  

 示例 1：

 输入：nums = [[1,2],[3,4]], operation = "Add"
 输出：10
 解释：
 const obj1 = new ArrayWrapper([1,2]);
 const obj2 = new ArrayWrapper([3,4]);
 obj1 + obj2; // 10
 示例 2：

 输入：nums = [[23,98,42,70]], operation = "String"
 输出："[23,98,42,70]"
 解释：
 const obj = new ArrayWrapper([23,98,42,70]);
 String(obj); // "[23,98,42,70]"
 示例 3：

 输入：nums = [[],[]], operation = "Add"
 输出：0
 解释：
 const obj1 = new ArrayWrapper([]);
 const obj2 = new ArrayWrapper([]);
 obj1 + obj2; // 0
  

 提示：

 0 <= nums.length <= 1000
 0 <= nums[i] <= 1000
 注意：nums 是传递给构造函数的数组。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/array-wrapper
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */


/**
 * @param {number[]} nums
 * https://leetcode.cn/problems/array-wrapper/solution/javascript-de-yin-shi-lei-xing-zhuan-hua-2wm8/
 * =>
 * https://blog.csdn.net/mikewanzi002/article/details/125379524
 * reduce 方法
 * https://blog.csdn.net/qq_38970408/article/details/121018660
 */
var ArrayWrapper = function(nums) {
    this.values = nums;
};

ArrayWrapper.prototype.valueOf = function() {
    return this.values.reduce((p1, p2) => p1+p2, 0);
}

ArrayWrapper.prototype.toString = function() {
    return '[' + this.values.toString() + ']';
}