/*
 描述
给定 3 个表： salesperson， company， orders。
输出所有表 salesperson 中，没有向公司 'RED' 销售任何东西的销售员。
解释
输入
表： salesperson
+----------+------+--------+-----------------+-----------+
| sales_id | name | salary | commission_rate | hire_date |
+----------+------+--------+-----------------+-----------+
|   1      | John | 100000 |     6           | 4/1/2006  |
|   2      | Amy  | 120000 |     5           | 5/1/2010  |
|   3      | Mark | 65000  |     12          | 12/25/2008|
|   4      | Pam  | 25000  |     25          | 1/1/2005  |
|   5      | Alex | 50000  |     10          | 2/3/2007  |
+----------+------+--------+-----------------+-----------+
表 salesperson 存储了所有销售员的信息。每个销售员都有一个销售员编号 sales_id 和他的名字 name 。
表： company
+---------+--------+------------+
| com_id  |  name  |    city    |
+---------+--------+------------+
|   1     |  RED   |   Boston   |
|   2     | ORANGE |   New York |
|   3     | YELLOW |   Boston   |
|   4     | GREEN  |   Austin   |
+---------+--------+------------+
表 company 存储了所有公司的信息。每个公司都有一个公司编号 com_id 和它的名字 name 。
表： orders
+----------+------------+---------+----------+--------+
| order_id | order_date | com_id  | sales_id | amount |
+----------+------------+---------+----------+--------+
| 1        |   1/1/2014 |    3    |    4     | 100000 |
| 2        |   2/1/2014 |    4    |    5     | 5000   |
| 3        |   3/1/2014 |    1    |    1     | 50000  |
| 4        |   4/1/2014 |    1    |    4     | 25000  |
+----------+----------+---------+----------+--------+
表 orders 存储了所有的销售数据，包括销售员编号 sales_id 和公司编号 com_id 。
输出
+------+
| name |
+------+
| Amy  |
| Mark |
| Alex |
+------+
解释
根据表 orders 中的订单 '3' 和 '4' ，容易看出只有 'John' 和 'Pam' 两个销售员曾经向公司 'RED' 销售过。
所以我们需要输出表 salesperson 中所有其他人的名字。
来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/sales-person
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
SELECT s.name
FROM salesperson s
WHERE s.sales_id NOT IN (SELECT o.sales_id
                         FROM orders o,
                              company c
                         WHERE c.name = 'RED'
                           AND c.com_id = o.com_id);