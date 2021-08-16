package com.potato.study.leetcodecn.p01175.t001;


import java.util.concurrent.Semaphore;

/**
 * 1195. 交替打印字符串
 *
 * 编写一个可以从 1 到 n 输出代表这个数字的字符串的程序，但是：

 如果这个数字可以被 3 整除，输出 "fizz"。
 如果这个数字可以被 5 整除，输出 "buzz"。
 如果这个数字可以同时被 3 和 5 整除，输出 "fizzbuzz"。
 例如，当 n = 15，输出： 1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz。

 假设有这么一个类：

 class FizzBuzz {
   public FizzBuzz(int n) { ... }               // constructor
 public void fizz(printFizz) { ... }          // only output "fizz"
 public void buzz(printBuzz) { ... }          // only output "buzz"
 public void fizzbuzz(printFizzBuzz) { ... }  // only output "fizzbuzz"
 public void number(printNumber) { ... }      // only output the numbers
 }
 请你实现一个有四个线程的多线程版  FizzBuzz， 同一个 FizzBuzz 实例会被如下四个线程使用：

 线程A将调用 fizz() 来判断是否能被 3 整除，如果可以，则输出 fizz。
 线程B将调用 buzz() 来判断是否能被 5 整除，如果可以，则输出 buzz。
 线程C将调用 fizzbuzz() 来判断是否同时能被 3 和 5 整除，如果可以，则输出 fizzbuzz。
 线程D将调用 number() 来实现输出既不能被 3 整除也不能被 5 整除的数字。
  

 提示：

 本题已经提供了打印字符串的相关方法，如 printFizz() 等，具体方法名请参考答题模板中的注释部分。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/fizz-buzz-multithreaded
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class FizzBuzz {
    private int n;
    // 处理被3整除
    private Semaphore fizzSemaphore;
    // 处理被5整除
    private Semaphore buzzSemaphore;
    // 处理 3-5 都被整除
    private Semaphore fizzBuzzSemaphore;
    // 处理数字
    private Semaphore numberSemaphore;


    public FizzBuzz(int n) {
        this.n = n;
        this.fizzBuzzSemaphore = new Semaphore(0);
        this.buzzSemaphore = new Semaphore(0);
        this.fizzSemaphore = new Semaphore(0);
        this.numberSemaphore = new Semaphore(1);
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for (int i = 3; i <= n; i+=3) {
            if (i % 5 == 0) {
                continue;
            }
            fizzSemaphore.acquire();
            printFizz.run();
            numberSemaphore.release();
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int i = 5; i <= n; i+=5) {
            if (i % 3 == 0) {
                continue;
            }
            buzzSemaphore.acquire();
            printBuzz.run();
            numberSemaphore.release();
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int i = 15; i <= n; i+=15) {
            fizzBuzzSemaphore.acquire();
            printFizzBuzz.run();
            numberSemaphore.release();
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            // 先获取把保证可以进行
            numberSemaphore.acquire();
            if (i % 15 == 0) {
                fizzBuzzSemaphore.release();
            } else if (i % 5 == 0) {
                buzzSemaphore.release();
            } else if (i % 3 == 0) {
                fizzSemaphore.release();
            } else {
                // 直接打印 可以释放锁
                printNumber.accept(i);
                numberSemaphore.release();
            }
        }
    }
}

class IntConsumer {
    public void accept(int x) {

    }
}
