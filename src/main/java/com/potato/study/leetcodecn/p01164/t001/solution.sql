--
-- 1164. 指定日期的产品价格
--
--
-- SQL架构
-- 产品数据表: Products
--
-- +---------------+---------+
-- | Column Name   | Type    |
-- +---------------+---------+
-- | product_id    | int     |
-- | new_price     | int     |
-- | change_date   | date    |
-- +---------------+---------+
-- 这张表的主键是 (product_id, change_date)。
-- 这张表的每一行分别记录了 某产品 在某个日期 更改后 的新价格。
--
--
-- 写一段 SQL来查找在 2019-08-16 时全部产品的价格，假设所有产品在修改前的价格都是 10 。
--
-- 以 任意顺序 返回结果表。
--
-- 查询结果格式如下例所示。
--
--
--
-- 示例 1:
--
-- 输入：
-- Products 表:
-- +------------+-----------+-------------+
-- | product_id | new_price | change_date |
-- +------------+-----------+-------------+
-- | 1          | 20        | 2019-08-14  |
-- | 2          | 50        | 2019-08-14  |
-- | 1          | 30        | 2019-08-15  |
-- | 1          | 35        | 2019-08-16  |
-- | 2          | 65        | 2019-08-17  |
-- | 3          | 20        | 2019-08-18  |
-- +------------+-----------+-------------+
-- 输出：
-- +------------+-------+
-- | product_id | price |
-- +------------+-------+
-- | 2          | 50    |
-- | 1          | 35    |
-- | 3          | 10    |
-- +------------+-------+

-- 最开始都是 10块钱
SELECT
  t.product_id,
  t.price
FROM (
  SELECT
    product_id,
    new_price as price,
    row_number() over(partition BY product_id ORDER BY change_date DESC) as rk
   FROM Products
  WHERE change_date <= '2019-08-16'
) t WHERE t.rk = 1
UNION
SELECT
  product_id,
  10 as price
FROM Products GROUP BY product_id
HAVING min(change_date) > '2019-08-16'