package com.potato.study.leetcodecn.p00388.t001;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 388. 文件的最长绝对路径
 *
 * 假设有一个同时存储文件和目录的文件系统。下图展示了文件系统的一个示例：



 这里将 dir 作为根目录中的唯一目录。dir 包含两个子目录 subdir1 和 subdir2 。subdir1 包含文件 file1.ext 和子目录 subsubdir1；subdir2 包含子目录 subsubdir2，该子目录下包含文件 file2.ext 。

 在文本格式中，如下所示(⟶表示制表符)：

 dir
 ⟶ subdir1
 ⟶ ⟶ file1.ext
 ⟶ ⟶ subsubdir1
 ⟶ subdir2
 ⟶ ⟶ subsubdir2
 ⟶ ⟶ ⟶ file2.ext
 如果是代码表示，上面的文件系统可以写为 "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" 。'\n' 和 '\t' 分别是换行符和制表符。

 文件系统中的每个文件和文件夹都有一个唯一的 绝对路径 ，即必须打开才能到达文件/目录所在位置的目录顺序，所有路径用 '/' 连接。上面例子中，指向 file2.ext 的 绝对路径 是 "dir/subdir2/subsubdir2/file2.ext" 。每个目录名由字母、数字和/或空格组成，每个文件名遵循 name.extension 的格式，其中 name 和 extension由字母、数字和/或空格组成。

 给定一个以上述格式表示文件系统的字符串 input ，返回文件系统中 指向 文件 的 最长绝对路径 的长度 。 如果系统中没有文件，返回 0。

  

 示例 1：


 输入：input = "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"
 输出：20
 解释：只有一个文件，绝对路径为 "dir/subdir2/file.ext" ，路径长度 20
 示例 2：


 输入：input = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"
 输出：32
 解释：存在两个文件：
 "dir/subdir1/file1.ext" ，路径长度 21
 "dir/subdir2/subsubdir2/file2.ext" ，路径长度 32
 返回 32 ，因为这是最长的路径
 示例 3：

 输入：input = "a"
 输出：0
 解释：不存在任何文件
 示例 4：

 输入：input = "file1.txt\nfile2.txt\nlongfile.txt"
 输出：12
 解释：根目录下有 3 个文件。
 因为根目录中任何东西的绝对路径只是名称本身，所以答案是 "longfile.txt" ，路径长度为 12
  

 提示：

 1 <= input.length <= 104
 input 可能包含小写或大写的英文字母，一个换行符 '\n'，一个制表符 '\t'，一个点 '.'，一个空格 ' '，和数字。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/longest-absolute-file-path
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode-cn.com/problems/longest-absolute-file-path/solution/388-wen-jian-de-zui-chang-jue-dui-lu-jing-zi-fu-ch/
     * @param input
     * @return
     */
    public int lengthLongestPath(String input) {
        // 按照 '\n' 拆分 遍历 拆分之后 split 找到 当前有多少个 \t 判断当前属于第几层，
        String[] split = input.split("\n");
        Stack<Integer> prefixPathLengthStack = new Stack<>();
        int maxLength = 0;
        for (String path : split) {
            // 当前path位于第几层 \t 如果没有出现在 -1
            int index = path.lastIndexOf("\t") + 1;
            // 如果index 所在的层小于 当前栈中的 说明之前有几个栈已经结束了遍历 需要pop
            while (index < prefixPathLengthStack.size() && !prefixPathLengthStack.isEmpty()) {
                prefixPathLengthStack.pop();
            }
            // 如果当前位于的层数 小于 栈中的 个数 说明已经栈需要出栈，修改出栈 ，计算栈中前缀数量与 当前数量的和 同时入栈 计算文件路径max
            int len = 0;
            if (!prefixPathLengthStack.isEmpty()) {
                len = prefixPathLengthStack.peek();
            }
            // 计算当前绝对串大小,需要只保留一个 /t
            if (index == 0) {
                len += path.length();
            } else {
                len += (path.length() - index + 1);
            }
            prefixPathLengthStack.add(len);
            if (path.contains(".")) {
                maxLength = Math.max(maxLength, len);
            }
        }
        return maxLength;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext";
        int i = solution.lengthLongestPath(input);
        System.out.println(i);
        Assert.assertEquals(20, i);


        input = "file1.txt\nfile2.txt\nlongfile.txt";
        i = solution.lengthLongestPath(input);
        System.out.println(i);
        Assert.assertEquals(12, i);
    }
}
