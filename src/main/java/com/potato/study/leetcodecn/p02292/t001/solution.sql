--
-- 2292. 连续两年有 3 个及以上订单的产品
--
--
-- SQL架构
-- 表: Orders
--
-- +---------------+------+
-- | Column Name   | Type |
-- +---------------+------+
-- | order_id      | int  |
-- | product_id    | int  |
-- | quantity      | int  |
-- | purchase_date | date |
-- +---------------+------+
-- order_id 是该表的主键。
-- 该表中的每一行都包含订单 ID、购买的产品 ID、数量和购买日期。
--
--
-- 编写一个 SQL 查询，获取连续两年订购三次或三次以上的所有产品的 id。
--
-- 以 任意顺序 返回结果表。
--
-- 查询结果格式示例如下。
--
--
--
-- 示例 1:
--
-- 输入:
-- Orders 表:
-- +----------+------------+----------+---------------+
-- | order_id | product_id | quantity | purchase_date |
-- +----------+------------+----------+---------------+
-- | 1        | 1          | 7        | 2020-03-16    |
-- | 2        | 1          | 4        | 2020-12-02    |
-- | 3        | 1          | 7        | 2020-05-10    |
-- | 4        | 1          | 6        | 2021-12-23    |
-- | 5        | 1          | 5        | 2021-05-21    |
-- | 6        | 1          | 6        | 2021-10-11    |
-- | 7        | 2          | 6        | 2022-10-11    |
-- +----------+------------+----------+---------------+
-- 输出:
-- +------------+
-- | product_id |
-- +------------+
-- | 1          |
-- +------------+
-- 解释:
-- 产品 1 在 2020 年和 2021 年都分别订购了三次。由于连续两年订购了三次，所以我们将其包含在答案中。
-- 产品 2 在 2022 年订购了一次。我们不把它包括在答案中。

-- https://leetcode.cn/problems/products-with-three-or-more-orders-in-two-consecutive-years/solution/by-136135736-vdiw/
-- 只要有 diff == 1 说明有2个行连在一起
select
    distinct product_id
from (
    -- LEAD 函数 用上一行的列 值减去当前值
    select
        product_id,
        LEAD(purchase_date, 1) OVER (PARTITION BY product_id ORDER BY purchase_date asc) - purchase_date AS diff
    from (
        -- 3年购买记录的以上的产品
        select
            product_id,
            YEAR(purchase_date) as purchase_date,
            count(product_id) as cnt
        from Orders
        group by 1,2
        having cnt >= 3
    ) as t1
) t2 where t2.diff = 1
