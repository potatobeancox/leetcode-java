/**
 * 2796. 重复字符串
 *
 * 编写代码实现字符串方法 string.replicate(x) ，它将返回重复的字符串 x 次。
 *
 * 请尝试在不使用内置方法 string.repeat 的情况下实现它。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：str = "hello", times = 2
 * 输出："hellohello"
 * 解释："hello" 被重复了 2 次
 * 示例 2：
 *
 * 输入：str = "code", times = 3
 * 输出：codecodecode"
 * Explanation: "code" 被重复了 3 次
 * 示例 3：
 *
 * 输入：str = "js", times = 1
 * 输出："js"
 * 解释："js" 被重复了 1 次
 *  
 *
 * 提示：
 *
 * 1 <= str.length <= 1000
 * 1 <= times <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/repeat-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */


String.prototype.replicate = function(times) {
    var res = "";
    for (let i = 0; i < times; i++) {
        res += this;
    }
    return res;
}

