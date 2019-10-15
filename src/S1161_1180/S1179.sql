/*
部门表 Department：
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| id            | int     |
| revenue       | int     |
| month         | varchar |
+---------------+---------+
(id, month) 是表的联合主键。
这个表格有关于每个部门每月收入的信息。
月份（month）可以取下列值 ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"]。
编写一个 SQL 查询来重新格式化表，使得新的表中有一个部门 id 列和一些对应 每个月 的收入（revenue）列。
查询结果格式如下面的示例所示：
Department 表：
+------+---------+-------+
| id   | revenue | month |
+------+---------+-------+
| 1    | 8000    | Jan   |
| 2    | 9000    | Jan   |
| 3    | 10000   | Feb   |
| 1    | 7000    | Feb   |
| 1    | 6000    | Mar   |
+------+---------+-------+
查询得到的结果表：
+------+-------------+-------------+-------------+-----+-------------+
| id   | Jan_Revenue | Feb_Revenue | Mar_Revenue | ... | Dec_Revenue |
+------+-------------+-------------+-------------+-----+-------------+
| 1    | 8000        | 7000        | 6000        | ... | null        |
| 2    | 9000        | null        | null        | ... | null        |
| 3    | null        | 10000       | null        | ... | null        |
+------+-------------+-------------+-------------+-----+-------------+
注意，结果表有 13 列 (1个部门 id 列 + 12个月份的收入列)。
来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/reformat-department-table
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

-- 聚合函数多进一出，所以用max或min等等
SELECT id,
MAX(IF(month='Jan', revenue, NULL)) AS Jan_Revenue,
MAX(IF(month='Feb', revenue, NULL)) AS Feb_Revenue,
MAX(IF(month='Mar', revenue, NULL)) AS Mar_Revenue,
MAX(IF(month='Apr', revenue, NULL)) AS Apr_Revenue,
MAX(IF(month='May', revenue, NULL)) AS May_Revenue,
MAX(IF(month='Jun', revenue, NULL)) AS Jun_Revenue,
MAX(IF(month='Jul', revenue, NULL)) AS Jul_Revenue,
MAX(IF(month='Aug', revenue, NULL)) AS Aug_Revenue,
MAX(IF(month='Sep', revenue, NULL)) AS Sep_Revenue,
MAX(IF(month='Oct', revenue, NULL)) AS Oct_Revenue,
MAX(IF(month='Nov', revenue, NULL)) AS Nov_Revenue,
MAX(IF(month='Dec', revenue, NULL)) AS Dec_Revenue
FROM Department
GROUP BY id