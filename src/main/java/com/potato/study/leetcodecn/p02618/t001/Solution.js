/**
 * 2618. 检查是否是类的对象实例
 *
 * 请你编写一个函数，检查给定的对象是否是给定类或超类的实例。

 可以传递给函数的数据类型没有限制。

  

 示例 1：

 输入：func = () => checkIfInstance(new Date(), Date)
 输出：true
 解释：根据定义，Date 构造函数返回的对象是 Date 的一个实例。
 示例 2：

 输入：func = () => { class Animal {}; class Dog extends Animal {}; return checkIfInstance(new Dog(), Animal); }
 输出：true
 解释：
 class Animal {};
 class Dog extends Animal {};
 checkIfInstance(new Dog(), Animal); // true

 Dog 是 Animal 的子类。因此，Dog 对象同时是 Dog 和 Animal 的实例。
 示例 3：

 输入：func = () => checkIfInstance(Date, Date)
 输出：false
 解释：日期的构造函数在逻辑上不能是其自身的实例。
 示例 4：

 输入：func = () => checkIfInstance(5, Number)
 输出：true
 解释：5 是一个 Number。注意，"instanceof" 关键字将返回 false。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/check-if-object-instance-of-class
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */


/**
 *
 * @param obj
 * @param classFunction
 * https://leetcode.cn/problems/check-if-object-instance-of-class/solution/yuan-xing-lian-by-new-hrz-ixch/
 */
var checkIfInstanceOf = function(obj, classFunction) {
    if (obj == null || obj == undefined) {
        return false;
    }

    while (obj != null) {
        if (obj.constructor == classFunction) {
            return true;
        }
        obj = Object.getPrototypeOf(obj);
    }
    return false;
};
