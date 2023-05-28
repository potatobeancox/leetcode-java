/**
 * 2628. 完全相等的 JSON 字符串
 *
 * 给定两个对象 o1 和 o2 ，请你检查它们是否 完全相等 。

 对于两个 完全相等 的对象，它们必须包含相同的键，并且相关的值也必须 完全相等 。如果两个对象通过了 === 相等性检查，它们也被认为是 完全相等 的。

 你可以假设这两个对象都是 JSON.parse 的输出。换句话说，它们是有效的 JSON 。

 请你在不使用 lodash 的 _.isEqual() 函数的前提下解决这个问题。

  

 示例 1：

 输入：o1 = {"x":1,"y":2}, o2 = {"x":1,"y":2}
 输出：true
 输入：键和值完全匹配。
 示例 2：

 输入：o1 = {"y":2,"x":1}, o2 = {"x":1,"y":2}
 输出：true
 解释：尽管键的顺序不同，但它们仍然完全匹配。
 示例 3：

 输入：o1 = {"x":null,"L":[1,2,3]}, o2 = {"x":null,"L":["1","2","3"]}
 输出：false
 解释：数字数组不同于字符串数组。
 示例 4：

 输入：o1 = true, o2 = false
 输出：false
 解释：true !== false
  

 提示：

 1 <= JSON.stringify(o1).length <= 105
 1 <= JSON.stringify(o2).length <= 105
 maxNestingDepth <= 1000

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/json-deep-equal
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */



/**
 * @param {any} o1
 * @param {any} o2
 * @return {boolean}
 * https://leetcode.cn/problems/json-deep-equal/solution/shou-xie-shen-du-bi-jiao-by-wmorning-oa4m/
 */
var areDeeplyEqual = function(o1, o2) {

    function isObject(obj) {
        return typeof obj === "object" && obj != null;
    }

    if (!isObject(o1) || !isObject(o2)) {
        return o1 === o2;
    }

    if (o1 === o2) {
        return true;
    }

    if (o1 instanceof Array != o2 instanceof Array) {
        return false;
    }

    if (o1 instanceof Array) {
        if (o1.length != o2.length) {
            return false;
        }
    } else {
        const keys1 = Object.keys(o1);
        const keys2 = Object.keys(o2);

        if (keys1.length != keys2.length) {
            return false;
        }
    }

    for (let key in o1) {
        const res = areDeeplyEqual(o1[key], o2[key]);
        if (!res) {
            return false;
        }
    }
    return true;
};
