package com.potato.study.leetcodecn.p01757.t001;

--  1777. 每家商店的产品价格
--
-- SQL架构
-- 表：Products
--
-- +-------------+---------+
-- | Column Name | Type    |
-- +-------------+---------+
-- | product_id  | int     |
-- | store       | enum    |
-- | price       | int     |
-- +-------------+---------+
-- (product_id,store) 是这个表的主键。
-- store 字段是枚举类型，它的取值为以下三种 ('store1', 'store2', 'store3') 。
-- price 是该商品在这家商店中的价格。
--
--
-- 写出一个 SQL 查询语句，查找每种产品在各个商店中的价格。
--
-- 可以以 任何顺序 输出结果。
--
-- 查询结果格式如下例所示：
--
-- Products 表：
-- +-------------+--------+-------+
-- | product_id  | store  | price |
-- +-------------+--------+-------+
-- | 0           | store1 | 95    |
-- | 0           | store3 | 105   |
-- | 0           | store2 | 100   |
-- | 1           | store1 | 70    |
-- | 1           | store3 | 80    |
-- +-------------+--------+-------+
-- Result 表：
-- +-------------+--------+--------+--------+
-- | product_id  | store1 | store2 | store3 |
-- +-------------+--------+--------+--------+
-- | 0           | 95     | 100    | 105    |
-- | 1           | 70     | null   | 80     |
-- +-------------+--------+--------+--------+
-- 产品 0 的价格在商店 1 为 95 ，商店 2 为 100 ，商店 3 为 105 。
-- 产品 1 的价格在商店 1 为 70 ，商店 3 的产品 1 价格为 80 ，但在商店 2 中没有销售。

-- group by
-- https://blog.csdn.net/jerrytomcat/article/details/82351605

SELECT product_id,
 SUM(CASE WHEN store = 'store1' THEN price ELSE null END) AS 'store1',
 SUM(CASE WHEN store = 'store2' THEN price ELSE null END) AS 'store2',
 SUM(CASE WHEN store = 'store3' THEN price ELSE null END) AS 'store3'
FROM Products
GROUP BY product_id