package com.potato.study.leetcode.p0690;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author liuzhao11
 * 
 * 	690. Employee Importance
 *  
 *        You are given a data structure of employee information, which includes the employee's unique id, his importance value and his direct subordinates' id.

For example, employee 1 is the leader of employee 2, and employee 2 is the leader of employee 3. They have importance value 15, 10 and 5, respectively. Then employee 1 has a data structure like [1, 15, [2]], and employee 2 has [2, 10, [3]], and employee 3 has [3, 5, []]. Note that although employee 3 is also a subordinate of employee 1, the relationship is not direct.

Now given the employee information of a company, and an employee id, you need to return the total importance value of this employee and all his subordinates.

Example 1:

Input: [[1, 5, [2, 3]], [2, 3, []], [3, 3, []]], 1
Output: 11
Explanation:
Employee 1 has importance value 5, and he has two direct subordinates: employee 2 and employee 3. They both have importance value 3. So the total importance value of employee 1 is 5 + 3 + 3 = 11.


Note:

One employee has at most one direct leader and may have several subordinates.
The maximum number of employees won't exceed 2000.
 *
 *
 *         题目解释：
 *
 *
 *         思路：
 *
 *         
 *
 *
 * 
 */
public class Solution {



    public int getImportance(List<Employee> employees, int id) {
        // 聚合employees 到map 中 key：id value：Employee
        Map<Integer, Employee> id2EmployeeMap = new HashMap<>();
        for (Employee employee : employees) {
            id2EmployeeMap.put(employee.id, employee);
        }
        // traverse the list  find the id
        Employee targetEmployee = id2EmployeeMap.get(id);
        return currentEmployeeImportance(targetEmployee, id2EmployeeMap);
    }

    /**
     * 获取当前employee的 importance
     * @param employee
     * @param id2EmployeeMap
     * @return
     */
    private int currentEmployeeImportance(Employee employee, Map<Integer, Employee> id2EmployeeMap){
        if (null == employee) {
            return 0;
        }
        if (employee.subordinates == null || employee.subordinates.size() == 0) {
            return employee.importance;
        }
        int subordinatesImportance = 0;
        for (int subId : employee.subordinates) {
            subordinatesImportance += currentEmployeeImportance(id2EmployeeMap.get(subId), id2EmployeeMap);
        }
        return employee.importance + subordinatesImportance;
    }


	
	public static void main(String[] args) {
		Solution solution = new Solution();
        int importance = solution.getImportance(null, 10);
        System.out.println(importance);
    }
}

class Employee {
    // It's the unique id of each node;
    // unique id of this employee
    public int id;
    // the importance value of this employee
    public int importance;
    // the id of direct subordinates
    public List<Integer> subordinates;
};
