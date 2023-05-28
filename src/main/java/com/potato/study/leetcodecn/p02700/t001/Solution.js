/**
 * 2700. 两个对象之间的差异
 *
 * 请你编写一个函数，它接收两个深度嵌套的对象或数组 obj1 和 obj2 ，并返回一个新对象表示它们之间差异。

 该函数应该比较这两个对象的属性，并识别任何变化。返回的对象应仅包含从 obj1 到 obj2 的值不同的键。对于每个变化的键，值应表示为一个数组 [obj1 value, obj2 value] 。不存在于一个对象中但存在于另一个对象中的键不应包含在返回的对象中。在比较两个数组时，数组的索引被视为它们的键。最终结果应是一个深度嵌套的对象，其中每个叶子的值都是一个差异数组。

 你可以假设这两个对象都是 JSON.parse 的输出结果。

  

 示例 1：

 输入：
 obj1 = {}
 obj2 = {
  "a": 1,
  "b": 2
}
 输出：{}
 解释：obj1没有进行任何修改。obj2中出现了新的键 "a" 和 "b" ，但添加或删除的键应该被忽略。
 示例 2：

 输入：
 obj1 = {
  "a": 1,
  "v": 3,
  "x": [],
  "z": {
    "a": null
  }
}
 obj2 = {
  "a": 2,
  "v": 4,
  "x": [],
  "z": {
    "a": 2
  }
}
 输出：
 {
   "a": [1, 2],
   "v": [3, 4],
   "z": {
     "a": [null, 2]
   }
 }
 解释：键 "a"、"v" 和 "z" 都有变化。"a" 从 1 变为 2，"v" 从 3 变为 4 ，"z" 的子对象 "a" 从 null 变为 2。
 示例 3：

 输入：
 obj1 = {
  "a": 5,
  "v": 6,
  "z": [1, 2, 4, [2, 5, 7]]
}
 obj2 = {
  "a": 5,
  "v": 7,
  "z": [1, 2, 3, [1]]
}
 输出：
 {
   "v": [6, 7],
   "z": {
     "2": [4, 3],
     "3": {
       "0": [2, 1]
     }
   }
 }
 解释：在 obj1 和 obj2 中，键 "v" 和 "z" 的值不同。"a" 被忽略，因为值没有变化。在键 "z" 中，有一个嵌套的数组。数组被视为对象，其中索引被视为键。数组发生了两处变化：z[2] 和 z[3][0]。z[0] 和 z[1] 没有变化，因此没有包含在结果中。z[3][1] 和 z[3][2] 被删除，因此也没有包含在结果中。
 示例 4：

 输入：
 obj1 = {
  "a": {"b": 1},
}
 obj2 = {
  "a": [5],
}
 输出：
 {
   "a": [{"b": 1}, [5]]
 }
 解释：键 "a" 在两个对象中都存在。但由于两个相关值具有不同的类型，所以它们被放置在差异数组中。
 示例 5：

 输入：
 obj1 = {
  "a": [1, 2, {}],
  "b": false
}
 obj2 = {  
  "b": false,
  "a": [1, 2, {}]
}
 输出：
 {}
 解释：除了键的顺序不同之外，两个对象是相同的，因此返回一个空对象。
  

 提示：

 2 <= JSON.stringify(obj1).length <= 104
 2 <= JSON.stringify(obj2).length <= 104

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/differences-between-two-objects
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */


/**
 * @param {object} obj1
 * @param {object} obj2
 * @return {object}
 * https://leetcode.cn/problems/differences-between-two-objects/solution/di-gui-huo-di-gui-huan-shi-di-gui-by-l00-ffsp/
 *
 */
function objDiff(obj1, obj2) {
    const diff = {};
    if (obj1 === obj2) {
        return diff;
    }
    if (typeof obj1 !== typeof obj2 || typeof obj1 !== "object"
            || Array.isArray(obj1) ^ Array.isArray(obj2)) {
        return [obj1, obj2];
    }
    const keys = Object.keys(obj1);
    for (let key of keys) {
        if (!obj2.hasOwnProperty(key)) {
            continue
        }
        const change = objDiff(obj1[key], obj2[key]);
        if (Object.keys(change).length) {
            diff[key] = change;
        }
    }
    return diff;
};