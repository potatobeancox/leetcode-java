package com.potato.study.leetcodecn.p01757.t001;

--  1549. 每件商品的最新订单
--
-- SQL架构
-- 表: Customers
--
-- +---------------+---------+
-- | Column Name   | Type    |
-- +---------------+---------+
-- | customer_id   | int     |
-- | name          | varchar |
-- +---------------+---------+
-- customer_id 是该表主键.
-- 该表包含消费者的信息.
--
--
-- 表: Orders
--
-- +---------------+---------+
-- | Column Name   | Type    |
-- +---------------+---------+
-- | order_id      | int     |
-- | order_date    | date    |
-- | customer_id   | int     |
-- | product_id    | int     |
-- +---------------+---------+
-- order_id 是该表主键.
-- 该表包含消费者customer_id产生的订单.
-- 不会有商品被相同的用户在一天内下单超过一次.
--
--
-- 表: Products
--
-- +---------------+---------+
-- | Column Name   | Type    |
-- +---------------+---------+
-- | product_id    | int     |
-- | product_name  | varchar |
-- | price         | int     |
-- +---------------+---------+
-- product_id 是该表主键.
-- 该表包含所有商品的信息.
--
--
-- 写一个SQL 语句, 找到每件商品的最新订单(可能有多个).
--
-- 返回的结果以 product_name 升序排列, 如果有排序相同, 再以 product_id 升序排列. 如果还有排序相同, 再以 order_id 升序排列.
--
-- 查询结果格式如下例所示。
--
--
--
-- 示例 1：
--
-- 输入:
-- Customers表：
-- +-------------+-----------+
-- | customer_id | name      |
-- +-------------+-----------+
-- | 1           | Winston   |
-- | 2           | Jonathan  |
-- | 3           | Annabelle |
-- | 4           | Marwan    |
-- | 5           | Khaled    |
-- +-------------+-----------+
-- Orders表：
-- +----------+------------+-------------+------------+
-- | order_id | order_date | customer_id | product_id |
-- +----------+------------+-------------+------------+
-- | 1        | 2020-07-31 | 1           | 1          |
-- | 2        | 2020-07-30 | 2           | 2          |
-- | 3        | 2020-08-29 | 3           | 3          |
-- | 4        | 2020-07-29 | 4           | 1          |
-- | 5        | 2020-06-10 | 1           | 2          |
-- | 6        | 2020-08-01 | 2           | 1          |
-- | 7        | 2020-08-01 | 3           | 1          |
-- | 8        | 2020-08-03 | 1           | 2          |
-- | 9        | 2020-08-07 | 2           | 3          |
-- | 10       | 2020-07-15 | 1           | 2          |
-- +----------+------------+-------------+------------+
-- Products 表：
-- +------------+--------------+-------+
-- | product_id | product_name | price |
-- +------------+--------------+-------+
-- | 1          | keyboard     | 120   |
-- | 2          | mouse        | 80    |
-- | 3          | screen       | 600   |
-- | 4          | hard disk    | 450   |
-- +------------+--------------+-------+
-- 输出：
-- +--------------+------------+----------+------------+
-- | product_name | product_id | order_id | order_date |
-- +--------------+------------+----------+------------+
-- | keyboard     | 1          | 6        | 2020-08-01 |
-- | keyboard     | 1          | 7        | 2020-08-01 |
-- | mouse        | 2          | 8        | 2020-08-03 |
-- | screen       | 3          | 3        | 2020-08-29 |
-- +--------------+------------+----------+------------+
-- 解释：
-- keyboard 的最新订单在2020-08-01, 在这天有两次下单.
-- mouse 的最新订单在2020-08-03, 在这天只有一次下单.
-- screen 的最新订单在2020-08-29, 在这天只有一次下单.
-- hard disk 没有被下单, 我们不把它包含在结果表中.

SELECT product_name, product_id, order_id, order_date
FROM (
  SELECT
    Orders.product_id,
    Orders.order_id,
    Orders.order_date,
    rank() over(partition BY Orders.product_id ORDER BY Orders.order_date desc) as rk,
    Products.product_name
  FROM Orders
  INNER JOIN Products USING(product_id)
) as t WHERE rk = 1
ORDER BY product_name, product_id, order_id

