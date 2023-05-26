/**
 * 2690. 无穷方法对象
 *
 * 请你编写一个函数，返回一个 无穷方法对象 。

 无穷方法对象 被定义为一个对象，它允许您调用任何方法，并始终返回方法的名称。

 例如，如果执行 obj.abc123() ，它将返回 "abc123" 。

  

 示例 1：

 输入：method = "abc123"
 输出："abc123"
 解释：
 const obj = createInfiniteObject();
 obj['abc123'](); // "abc123"
 返回的字符串应始终与方法名称匹配。
 示例 2：

 输入：method = ".-qw73n|^2It"
 输出：".-qw73n|^2It"
 解释：返回的字符串应始终与方法名称匹配。
  

 提示：

 0 <= method.length <= 1000


 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/infinite-method-object
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

var createInfiniteObject = function() {

};