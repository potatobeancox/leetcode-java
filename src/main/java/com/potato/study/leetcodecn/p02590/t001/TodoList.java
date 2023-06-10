package com.potato.study.leetcodecn.p02590.t001;


import java.util.*;
import java.util.stream.Collectors;

/**
 * 2590. 设计一个待办事项清单

 设计一个待办事项清单，用户可以添加 任务 ，标记任务为 完成状态 ，或获取待办任务列表。用户还可以为任务添加 标签 ，并可以按照特定标签筛选任务。

 实现 TodoList 类：

 TodoList() 初始化对象。
 int addTask(int userId, String taskDescription, int dueDate, List<String> tags) 为用户 ID 为 userId 的用户添加一个任务，该任务的到期日期为 dueDate ，附带了一个标签列表 tags 。返回值为任务的 ID 。该 ID 从 1 开始，依次 递增。即，第一个任务的ID应为 1 ，第二个任务的 ID 应为 2 ，以此类推。
 List<String> getAllTasks(int userId) 返回未标记为完成状态的 ID 为 userId 的用户的所有任务列表，按照到期日期排序。如果用户没有未完成的任务，则应返回一个空列表。
 List<String> getTasksForTag(int userId, String tag) 返回 ID 为 userId 的用户未标记为完成状态且具有 tag 标签之一的所有任务列表，按照到期日期排序。如果不存在此类任务，则返回一个空列表。
 void completeTask(int userId, int taskId) 仅在任务存在且 ID 为 userId 的用户拥有此任务且它是未完成状态时，将 ID 为 taskId 的任务标记为已完成状态。
  

 示例 1 ：

 输入
 ["TodoList", "addTask", "addTask", "getAllTasks", "getAllTasks", "addTask", "getTasksForTag", "completeTask", "completeTask", "getTasksForTag", "getAllTasks"]
 [[], [1, "Task1", 50, []], [1, "Task2", 100, ["P1"]], [1], [5], [1, "Task3", 30, ["P1"]], [1, "P1"], [5, 1], [1, 2], [1, "P1"], [1]]
 输出
 [null, 1, 2, ["Task1", "Task2"], [], 3, ["Task3", "Task2"], null, null, ["Task3"], ["Task3", "Task1"]]

 解释
 TodoList todoList = new TodoList();
 todoList.addTask(1, "Task1", 50, []); // 返回1。为ID为1的用户添加一个新任务。
 todoList.addTask(1, "Task2", 100, ["P1"]); // 返回2。为ID为1的用户添加另一个任务，并给它添加标签“P1”。
 todoList.getAllTasks(1); // 返回["Task1", "Task2"]。用户1目前有两个未完成的任务。
 todoList.getAllTasks(5); // 返回[]。用户5目前没有任务。
 todoList.addTask(1, "Task3", 30, ["P1"]); // 返回3。为ID为1的用户添加另一个任务，并给它添加标签“P1”。
 todoList.getTasksForTag(1, "P1"); // 返回["Task3", "Task2"]。返回ID为1的用户未完成的带有“P1”标签的任务。
 todoList.completeTask(5, 1); // 不做任何操作，因为任务1不属于用户5。
 todoList.completeTask(1, 2); // 将任务2标记为已完成。
 todoList.getTasksForTag(1, "P1"); // 返回["Task3"]。返回ID为1的用户未完成的带有“P1”标签的任务。
 // 注意，现在不包括 “Task2” ，因为它已经被标记为已完成。
 todoList.getAllTasks(1); // 返回["Task3", "Task1"]。用户1现在有两个未完成的任务。

  

 提示：

 1 <= userId, taskId, dueDate <= 100
 0 <= tags.length <= 100
 1 <= taskDescription.length <= 50
 1 <= tags[i].length, tag.length <= 20
 所有的 dueDate 值都是唯一的。
 所有字符串都由小写和大写英文字母和数字组成。
 每个方法最多被调用 100 次。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/design-a-todo-list
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class TodoList {

    // key:用户id  <key:taskId, task实体（描述 完成时间 tags）>
    private Map<Integer, Map<Integer, TaskInfo>> userTaskMap;
    // 标号
    private int taskId;

    public TodoList() {
        this.userTaskMap = new HashMap<>();
        this.taskId = 1;
    }

    public int addTask(int userId, String taskDescription, int dueDate, List<String> tags) {
        Map<Integer, TaskInfo> integerTaskInfoMap = userTaskMap.get(userId);
        if (null == integerTaskInfoMap) {
            integerTaskInfoMap = new HashMap<>();
            userTaskMap.put(userId, integerTaskInfoMap);
        }
        // 插入任务
        TaskInfo taskInfo = new TaskInfo();
        taskInfo.setUserId(userId);
        taskInfo.setId(taskId);
        taskInfo.setTaskDescription(taskDescription);
        taskInfo.setDueDate(dueDate);
        taskInfo.setTags(new HashSet<>(tags));

        // 设置

        integerTaskInfoMap.put(taskId, taskInfo);
        taskId++;
        return taskId - 1;
    }

    /**
     * 获取用户的所有 task 未完成
     * @param userId
     * @return taskDescription
     */
    public List<String> getAllTasks(int userId) {
        Map<Integer, TaskInfo> integerTaskInfoMap = userTaskMap.get(userId);
        if (null == integerTaskInfoMap || integerTaskInfoMap.size() == 0) {
            return new ArrayList<>();
        }
        // value就是 这个用户所有未完成的任务列表
        return integerTaskInfoMap.values().stream()
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingInt(taskInfo -> taskInfo.getDueDate()))
                .map(TaskInfo::getTaskDescription)
                .collect(Collectors.toList());
    }


    /**
     * 获取用户下面 tag位 执行值的list 未完成
     * 直接搜搜 肯定需要 用户按照 tag分类
     * @param userId
     * @param tag
     * @return
     */
    public List<String> getTasksForTag(int userId, String tag) {
        Map<Integer, TaskInfo> integerTaskInfoMap = userTaskMap.get(userId);
        if (null == integerTaskInfoMap || integerTaskInfoMap.size() == 0) {
            return new ArrayList<>();
        }
        // 按照到期日期排序

        // value就是 这个用户所有未完成的任务列表
        return integerTaskInfoMap.values().stream()
                .filter(Objects::nonNull)
                .filter(taskInfo ->
                     taskInfo.getTags().contains(tag)
                )
                .sorted(Comparator.comparingInt(taskInfo -> taskInfo.getDueDate()))
                .map(TaskInfo::getTaskDescription)
                .collect(Collectors.toList());
    }

    /**
     * 完成 userId 下面对应的 taskid
     * @param userId
     * @param taskId
     */
    public void completeTask(int userId, int taskId) {
        Map<Integer, TaskInfo> integerTaskInfoMap = userTaskMap.get(userId);
        if (null == integerTaskInfoMap || integerTaskInfoMap.size() == 0) {
            return;
        }
        // 将 task id 删除
        integerTaskInfoMap.remove(taskId);
    }

    class TaskInfo {
        private int userId;
        private int id;
        private String taskDescription;
        private int dueDate;
        private Set<String> tags;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTaskDescription() {
            return taskDescription;
        }

        public void setTaskDescription(String taskDescription) {
            this.taskDescription = taskDescription;
        }

        public int getDueDate() {
            return dueDate;
        }

        public void setDueDate(int dueDate) {
            this.dueDate = dueDate;
        }

        public Set<String> getTags() {
            return tags;
        }

        public void setTags(Set<String> tags) {
            this.tags = tags;
        }
    }

    public static void main(String[] args) {
        TodoList todoList = new TodoList();
//        todoList.
    }
}
