package com.potato.study.leetcodecn.p01757.t001;

--  1757. 可回收且低脂的产品
--
--  表：Products
--
--  +-------------+---------+
--  | Column Name | Type    |
--  +-------------+---------+
--  | product_id  | int     |
--  | low_fats    | enum    |
--  | recyclable  | enum    |
--  +-------------+---------+
--  product_id 是这个表的主键。
--  low_fats 是枚举类型，取值为以下两种 ('Y', 'N')，其中 'Y' 表示该产品是低脂产品，'N' 表示不是低脂产品。
--  recyclable 是枚举类型，取值为以下两种 ('Y', 'N')，其中 'Y' 表示该产品可回收，而 'N' 表示不可回收。
--   
--
--  写出 SQL 语句，查找既是低脂又是可回收的产品编号。
--
--  返回结果 无顺序要求 。
--
--  查询结果格式如下例所示：
--
--  Products 表：
--  +-------------+----------+------------+
--  | product_id  | low_fats | recyclable |
--  +-------------+----------+------------+
--  | 0           | Y        | N          |
--  | 1           | Y        | Y          |
--  | 2           | N        | Y          |
--  | 3           | Y        | Y          |
--  | 4           | N        | N          |
--  +-------------+----------+------------+
--  Result 表：
--  +-------------+
--  | product_id  |
--  +-------------+
--  | 1           |
--  | 3           |
--  +-------------+
--  只有产品 id 为 1 和 3 的产品，既是低脂又是可回收的产品。
--
--  来源：力扣（LeetCode）
--  链接：https://leetcode-cn.com/problems/recyclable-and-low-fat-products
--  著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
--


SELECT product_id FROM Products WHERE low_fats = 'Y' AND recyclable = 'Y'