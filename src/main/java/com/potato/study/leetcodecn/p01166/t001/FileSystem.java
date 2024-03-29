package com.potato.study.leetcodecn.p01166.t001;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 1166. 设计文件系统
 *
 * 你需要设计一个文件系统，你可以创建新的路径并将它们与不同的值关联。
 *
 * 路径的格式是一个或多个连接在一起的字符串，形式为： / ，后面跟着一个或多个小写英文字母。例如， " /leetcode" 和 "/leetcode/problems" 是有效路径，而空字符串 "" 和 "/" 不是。
 *
 * 实现 FileSystem 类:
 *
 * bool createPath(string path, int value) 创建一个新的 path ，并在可能的情况下关联一个 value ，然后返回 true 。如果路径已经存在或其父路径不存在，则返回 false 。
 *  int get(string path) 返回与 path 关联的值，如果路径不存在则返回 -1 。
 *  
 *
 * 示例 1：
 *
 * 输入：
 * ["FileSystem","create","get"]
 * [[],["/a",1],["/a"]]
 * 输出：
 * [null,true,1]
 * 解释：
 * FileSystem fileSystem = new FileSystem();
 *
 * fileSystem.create("/a", 1); // 返回 true
 * fileSystem.get("/a"); // 返回 1
 * 示例 2：
 *
 * 输入：
 * ["FileSystem","createPath","createPath","get","createPath","get"]
 * [[],["/leet",1],["/leet/code",2],["/leet/code"],["/c/d",1],["/c"]]
 * 输出：
 * [null,true,true,2,false,-1]
 * 解释：
 * FileSystem fileSystem = new FileSystem();
 *
 * fileSystem.createPath("/leet", 1); // 返回 true
 * fileSystem.createPath("/leet/code", 2); // 返回 true
 * fileSystem.get("/leet/code"); // 返回 2
 * fileSystem.createPath("/c/d", 1); // 返回 false 因为父路径 "/c" 不存在。
 * fileSystem.get("/c"); // 返回 -1 因为该路径不存在。
 *  
 *
 * 提示：
 *
 * 对两个函数的调用次数加起来小于等于 104 
 * 2 <= path.length <= 100
 * 1 <= value <= 109 
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/design-file-system
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class FileSystem {

    private Map<String, Integer> pathValueMap;

    public FileSystem() {
        this.pathValueMap = new HashMap<>();
    }

    public boolean createPath(String path, int value) {
        if (pathValueMap.containsKey(path)) {
            return false;
        }
        // 父亲不存在返回false
        int index = path.lastIndexOf("/");
        // 输入有问题
        if (index < 0) {
            return false;
        }
        String fatherPath = path.substring(0, index);
        if (fatherPath.length() > 0 && !pathValueMap.containsKey(fatherPath)) {
            return false;
        }
        pathValueMap.put(path, value);
        return true;
    }

    public int get(String path) {
        if (!pathValueMap.containsKey(path)) {
            return -1;
        }
        return pathValueMap.get(path);
    }

    public static void main(String[] args) {
        FileSystem fileSystem = new FileSystem();
        String path = "/a";
        int value = 1;
        boolean res = fileSystem.createPath(path, value);
        System.out.println(res);
        System.out.println(fileSystem.get("/a"));
    }
}
