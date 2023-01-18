package com.potato.study.leetcodecn.p02408.t001;

import org.junit.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2408. 设计 SQL
 *
 * 给定 n 个表，用两个数组 names 和 columns 表示，其中 names[i] 是第 i 个表的名称，columns[i] 是第 i 个表的列数。
 *
 * 您能够执行以下 操作:
 *
 * 在特定的表中 插入 一行。插入的每一行都有一个 id。id 是使用自动递增方法分配的，其中第一个插入行的 id 为 1，插入到同一个表中的其他行的 id 为最后一个插入行的id (即使它已被删除) 加1。
 * 从指定表中 删除 一行。注意，删除一行不会影响下一个插入行的 id。
 * 从任何表中 查询 一个特定的单元格并返回其值。
 * 实现 SQL 类:
 *
 * SQL(String[] names, int[] columns) 创造 n 个表。
 * void insertRow(String name, String[] row) 向表 name 中添加一行。保证 表存在，并且数组 row 的大小等于表中的列数。
 * void deleteRow(String name, int rowId) 从表 name 中移除行 rowId 。保证 表和行都 存在。
 * String selectCell(String name, int rowId, int columnId) 返回表 name 中 rowId 行和 columnId 列中的单元格值。
 *  
 *
 * 示例 1:
 *
 * 输入
 * ["SQL", "insertRow", "selectCell", "insertRow", "deleteRow", "selectCell"]
 * [[["one", "two", "three"], [2, 3, 1]], ["two", ["first", "second", "third"]], ["two", 1, 3], ["two", ["fourth", "fifth", "sixth"]], ["two", 1], ["two", 2, 2]]
 * 输出
 * [null, null, "third", null, null, "fifth"]
 *
 * 解释
 * SQL sql = new SQL(["one", "two", "three"], [2, 3, 1]); // 创建三个表。
 * sql.insertRow("two", ["first", "second", "third"]); // 向表 "2" 添加一行。id 是 1。
 * sql.selectCell("two", 1, 3); // 返回 "third"，查找表 "two" 中 id 为 1 的行中第三列的值。
 * sql.insertRow("two", ["fourth", "fifth", "sixth"]); // 将另一行添加到表 "2" 中。它的 id 是 2。
 * sql.deleteRow("two", 1); // 删除表 "two" 的第一行。注意，第二行仍然有 id 2。
 * sql.selectCell("two", 2, 2); // 返回 "fifth"，查找表 "two" 中 id 为 2 的行中第二列的值。
 *  
 *
 * 提示:
 *
 * n == names.length == columns.length
 * 1 <= n <= 104
 * 1 <= names[i].length, row[i].length, name.length <= 20
 * names[i], row[i], name 由小写英文字母组成。
 * 1 <= columns[i] <= 100
 * 所有的 names 字符串都是 不同 的。
 * name 存在于 names.
 * row.length 等于所选表中的列数。
 * rowId 和 columnId 是有效的值。
 * 最多 250 次调用 insertRow 和 deleteRow 。
 * 最多 104 次调用 selectCell。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/design-sql
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class SQL {

    private Map<String, Table> tableMap;

    public SQL(List<String> names, List<Integer> columns) {
        // 内部 table接口 记录 name column个数 和具体的id map id 递增
        this.tableMap = new HashMap<>();
        for (int i = 0; i < names.size(); i++) {
            String tableName = names.get(i);
            int colCount = columns.get(i);

            tableMap.put(tableName, new Table(tableName, colCount));
        }
    }

    public void insertRow(String name, List<String> row) {
        Table table = tableMap.get(name);
        if (null == table) {
            return;
        }
        table.content.put(table.autoId++, row);
    }

    public void deleteRow(String name, int rowId) {
        Table table = tableMap.get(name);
        if (null == table) {
            return;
        }
        table.content.remove(rowId);
    }

    public String selectCell(String name, int rowId, int columnId) {
        Table table = tableMap.get(name);
        if (null == table) {
            return null;
        }
        List<String> strings = table.content.get(rowId);
        if (null == strings || strings.size() < columnId) {
            return null;
        }
        return strings.get(columnId - 1);
    }

    class Table {
        public String tableName;
        public int columnCount;
        public int autoId;
        public Map<Integer, List<String>> content;

        public Table(String tableName, int columnCount) {
            this.tableName = tableName;
            this.columnCount = columnCount;
            this.autoId = 1;
            this.content = new HashMap<>();
        }
    }


}
