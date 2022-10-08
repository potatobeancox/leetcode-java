--
-- 2112. The Airport With the Most Traffic
--
--
-- SQL架构
-- Table: Flights
--
-- +-------------------+------+
-- | Column Name       | Type |
-- +-------------------+------+
-- | departure_airport | int  |
-- | arrival_airport   | int  |
-- | flights_count     | int  |
-- +-------------------+------+
-- (departure_airport, arrival_airport) is the primary key column for this table.
-- Each row of this table indicates that there were flights_count flights that departed from departure_airport and arrived at arrival_airport.
--
--
-- Write an SQL query to report the ID of the airport with the most traffic. The airport with the most traffic is the airport that has the largest total number of flights that either departed from or arrived at the airport. If there is more than one airport with the most traffic, report them all.
--
-- Return the result table in any order.
--
-- The query result format is in the following example.
--
--
--
-- Example 1:
--
-- Input:
-- Flights table:
-- +-------------------+-----------------+---------------+
-- | departure_airport | arrival_airport | flights_count |
-- +-------------------+-----------------+---------------+
-- | 1                 | 2               | 4             |
-- | 2                 | 1               | 5             |
-- | 2                 | 4               | 5             |
-- +-------------------+-----------------+---------------+
-- Output:
-- +------------+
-- | airport_id |
-- +------------+
-- | 2          |
-- +------------+
-- Explanation:
-- Airport 1 was engaged with 9 flights (4 departures, 5 arrivals).
-- Airport 2 was engaged with 14 flights (10 departures, 4 arrivals).
-- Airport 4 was engaged with 5 flights (5 arrivals).
-- The airport with the most traffic is airport 2.
-- Example 2:
--
-- Input:
-- Flights table:
-- +-------------------+-----------------+---------------+
-- | departure_airport | arrival_airport | flights_count |
-- +-------------------+-----------------+---------------+
-- | 1                 | 2               | 4             |
-- | 2                 | 1               | 5             |
-- | 3                 | 4               | 5             |
-- | 4                 | 3               | 4             |
-- | 5                 | 6               | 7             |
-- +-------------------+-----------------+---------------+
-- Output:
-- +------------+
-- | airport_id |
-- +------------+
-- | 1          |
-- | 2          |
-- | 3          |
-- | 4          |
-- +------------+
-- Explanation:
-- Airport 1 was engaged with 9 flights (4 departures, 5 arrivals).
-- Airport 2 was engaged with 9 flights (5 departures, 4 arrivals).
-- Airport 3 was engaged with 9 flights (5 departures, 4 arrivals).
-- Airport 4 was engaged with 9 flights (4 departures, 5 arrivals).
-- Airport 5 was engaged with 7 flights (7 departures).
-- Airport 6 was engaged with 7 flights (7 arrivals).
-- The airports with the most traffic are airports 1, 2, 3, and 4.

-- 题解
-- https://leetcode.cn/problems/the-airport-with-the-most-traffic/solution/by-humphrey-n-p5o3/

-- 获取排名第一的
SELECT airport_id
FROM (
  -- 统计排名和id
  SELECT
    t.departure_airport as airport_id,
    rank() over(ORDER BY SUM(t.flights_count) DESC) as ran
  FROM (
    -- 通过 并集 union all 将不同行列的组合在一个表里边 便于计数
    SELECT departure_airport, arrival_airport, flights_count FROM Flights
    UNION ALL
    SELECT arrival_airport, departure_airport, flights_count FROM Flights
  ) t
  GROUP BY t.departure_airport
) tt
WHERE tt.ran = 1
ORDER BY airport_id