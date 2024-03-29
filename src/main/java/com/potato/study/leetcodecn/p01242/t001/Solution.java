package com.potato.study.leetcodecn.p01242.t001;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 1242. 多线程网页爬虫
 *
 * 给你一个初始地址 startUrl 和一个 HTML 解析器接口 HtmlParser，请你实现一个 多线程的网页爬虫，用于获取与 startUrl 有 相同主机名 的所有链接。 

 以 任意 顺序返回爬虫获取的路径。

 爬虫应该遵循：

 从 startUrl 开始
 调用 HtmlParser.getUrls(url) 从指定网页路径获得的所有路径。
 不要抓取相同的链接两次。
 仅浏览与 startUrl 相同主机名 的链接。


 如上图所示，主机名是 example.org 。简单起见，你可以假设所有链接都采用 http 协议，并且没有指定 端口号。举个例子，链接 http://leetcode.com/problems 和链接 http://leetcode.com/contest 属于同一个 主机名， 而 http://example.org/test 与 http://example.com/abc 并不属于同一个 主机名。

 HtmlParser 的接口定义如下：

 interface HtmlParser {
 // Return a list of all urls from a webpage of given url.
 // This is a blocking call, that means it will do HTTP request and return when this request is finished.
 public List<String> getUrls(String url);
 }
 注意一点，getUrls(String url) 模拟执行一个HTTP的请求。 你可以将它当做一个阻塞式的方法，直到请求结束。 getUrls(String url) 保证会在 15ms 内返回所有的路径。 单线程的方案会超过时间限制，你能用多线程方案做的更好吗？

 对于问题所需的功能，下面提供了两个例子。为了方便自定义测试，你可以声明三个变量 urls，edges 和 startUrl。但要注意你只能在代码中访问 startUrl，并不能直接访问 urls 和 edges。

  

 拓展问题：

 假设我们要要抓取 10000 个节点和 10 亿个路径。并且在每个节点部署相同的的软件。软件可以发现所有的节点。我们必须尽可能减少机器之间的通讯，并确保每个节点负载均衡。你将如何设计这个网页爬虫？
 如果有一个节点发生故障不工作该怎么办？
 如何确认爬虫任务已经完成？
  

 示例 1：



 输入：
 urls = [
   "http://news.yahoo.com",
   "http://news.yahoo.com/news",
   "http://news.yahoo.com/news/topics/",
   "http://news.google.com",
   "http://news.yahoo.com/us"
 ]
 edges = [[2,0],[2,1],[3,2],[3,1],[0,4]]
 startUrl = "http://news.yahoo.com/news/topics/"
 输出：[
   "http://news.yahoo.com",
   "http://news.yahoo.com/news",
   "http://news.yahoo.com/news/topics/",
   "http://news.yahoo.com/us"
 ]
 示例 2：



 输入：
 urls = [
   "http://news.yahoo.com",
   "http://news.yahoo.com/news",
   "http://news.yahoo.com/news/topics/",
   "http://news.google.com"
 ]
 edges = [[0,2],[2,1],[3,2],[3,1],[3,0]]
 startUrl = "http://news.google.com"
 输出：["http://news.google.com"]
 解释：startUrl 链接与其他页面不共享一个主机名。
  

 提示：

 1 <= urls.length <= 1000
 1 <= urls[i].length <= 300
 startUrl 是 urls 中的一个。
 主机名的长度必须为 1 到 63 个字符（包括点 . 在内），只能包含从 “a” 到 “z” 的 ASCII 字母和 “0” 到 “9” 的数字，以及中划线 “-”。
 主机名开头和结尾不能是中划线 “-”。
 参考资料：https://en.wikipedia.org/wiki/Hostname#Restrictions_on_valid_hostnames
 你可以假设路径都是不重复的。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/web-crawler-multithreaded
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 一个map 存放已经遍历过的 链接
    private Map<String, Boolean> map;
    private HtmlParser htmlParser;
    // 存开始的域名 之后 用于过滤
    private List<String> visitList;
    private String startUrlDomain;



    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        this.map = new ConcurrentHashMap<>();
        this.htmlParser = htmlParser;
        this.startUrlDomain = getDomain(startUrl);
        // 开启一个线程 线程 任务 分别获取 下一个链接 对于链接进行遍历 任意一个链接开一个子任务，直到所有的任务都执行完成
        map.put(startUrl, true);
        visitList = new ArrayList<>();
        visitList.add(startUrl);

        Task mainTask = new Task(startUrl);

        mainTask.start();
        try {
            mainTask.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 不好的地方就是会级联等待 线程系哦啊红越来越多 需要使用队列 进行优化
        return visitList;
    }


    /**
     * 获取 url对应的 主域名
     * @param url
     * @return
     */
    private String getDomain(String url) {
        // http:// 7个字符
        int i = url.indexOf('/', 7);
        if (i == -1) {
            i = url.length();
        }
        String domain = url.substring(0, i);
        return domain;
    }

    /**
     * // 开启一个线程 线程 任务 分别获取 下一个链接 对于链接进行遍历 任意一个链接开一个子任务，直到所有的任务都执行完成
     */
    class Task extends Thread {

        private String url;

        public Task(String url) {
            this.url = url;
        }

        @Override
        public void run() {
            List<String> urls = htmlParser.getUrls(this.url);
            List<Task> subTaskList = new ArrayList<>();
            for (String nextUrl : urls) {
                // 是不是相同域名的
                String nextDomain = getDomain(nextUrl);
                if (!startUrlDomain.equals(nextDomain)) {
                    continue;
                }
                // 是不是已经访问过了
                if (map.containsKey(nextUrl)) {
                    continue;
                }
                // 没有访问 访问 也就是 开任务继续执行
                map.put(nextUrl, true);
                visitList.add(nextUrl);
                // 创建任务执行
                Task childTask = new Task(nextUrl);
                subTaskList.add(childTask);
                childTask.start();
            }

            // 收集
            for (Task task : subTaskList) {
                try {
                    task.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

interface HtmlParser {
    public List<String> getUrls(String url);
}
