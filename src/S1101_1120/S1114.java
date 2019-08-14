package S1101_1120;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 我们提供了一个类：
 * public class Foo {
 *   public void one() { print("one"); }
 *   public void two() { print("two"); }
 *   public void three() { print("three"); }
 * }
 * 三个不同的线程将会共用一个 Foo 实例。
 * 线程 A 将会调用 one() 方法
 * 线程 B 将会调用 two() 方法
 * 线程 C 将会调用 three() 方法
 * 请设计修改程序，以确保 two() 方法在 one() 方法之后被执行，three() 方法在 two() 方法之后被执行。
 * 示例 1:
 * 输入: [1,2,3]
 * 输出: "onetwothree"
 * 解释:
 * 有三个线程会被异步启动。
 * 输入 [1,2,3] 表示线程 A 将会调用 one() 方法，线程 B 将会调用 two() 方法，线程 C 将会调用 three() 方法。
 * 正确的输出是 "onetwothree"。
 * 示例 2:
 * 输入: [1,3,2]
 * 输出: "onetwothree"
 * 解释:
 * 输入 [1,3,2] 表示线程 A 将会调用 one() 方法，线程 B 将会调用 three() 方法，线程 C 将会调用 two() 方法。
 * 正确的输出是 "onetwothree"。
 * 注意:
 * 尽管输入中的数字似乎暗示了顺序，但是我们并不保证线程在操作系统中的调度顺序。
 * 你看到的输入格式主要是为了确保测试的全面性。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/print-in-order
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S1114 {
    static class Foo {

        CountDownLatch latch1 = new CountDownLatch(1);
        CountDownLatch latch2 = new CountDownLatch(1);

        public Foo() {
        }

        public void first(Runnable printFirst) {
            printFirst.run();
            latch1.countDown();
        }

        public void second(Runnable printSecond) throws InterruptedException {
            latch1.await();
            printSecond.run();
            latch2.countDown();
        }

        public void third(Runnable printThird) throws InterruptedException {
            latch2.await();
            printThird.run();
        }
    }

    static class Foo2 {

        Semaphore one = new Semaphore(1);
        Semaphore two = new Semaphore(0);
        Semaphore three = new Semaphore(0);

        public Foo2() {
        }

        public void first(Runnable printFirst) throws InterruptedException {
            one.acquire();
            printFirst.run();
            two.release();
        }

        public void second(Runnable printSecond) throws InterruptedException {
            two.acquire();
            printSecond.run();
            three.release();
        }

        public void third(Runnable printThird) throws InterruptedException {
            three.acquire();
            printThird.run();
        }
    }

    static class Foo3 {

        SynchronousQueue<Integer> queue1 = new SynchronousQueue<>();
        SynchronousQueue<Integer> queue2 = new SynchronousQueue<>();

        public Foo3() {
        }

        public void first(Runnable printFirst) throws InterruptedException {
            printFirst.run();
            queue1.put(0);
        }

        public void second(Runnable printSecond) throws InterruptedException {
            queue1.take();
            printSecond.run();
            queue2.put(0);
        }

        public void third(Runnable printThird) throws InterruptedException {
            queue2.take();
            printThird.run();
        }
    }

    static class Foo4 {
        ReentrantLock lock = new ReentrantLock();
        Condition firstCondition = lock.newCondition();
        Condition secondCondition = lock.newCondition();
        Condition thirdCondition = lock.newCondition();
        int state = 1;

        public Foo4() {
        }

        public void first(Runnable printFirst) throws InterruptedException {
            lock.lock();
            if (state != 1) {
                firstCondition.await();
            }
            printFirst.run();
            state = 2;
            secondCondition.signal();
            lock.unlock();
        }

        public void second(Runnable printSecond) throws InterruptedException {
            lock.lock();
            if (state != 2) {
                secondCondition.await();
            }
            printSecond.run();
            state = 3;
            thirdCondition.signal();
            lock.unlock();
        }

        public void third(Runnable printThird) throws InterruptedException {
            lock.lock();
            if (state != 3) {
                thirdCondition.await();
            }
            printThird.run();
            lock.unlock();
        }
    }

    static class Foo5 {

        int flag = 0;

        public Foo5() {
        }

        public void first(Runnable printFirst) throws InterruptedException {
            synchronized (this) {
                while (flag != 0)
                    this.wait();
                printFirst.run();
                flag = 1;
                this.notifyAll();
            }
        }

        public void second(Runnable printSecond) throws InterruptedException {
            synchronized (this) {
                while (flag != 1)
                    this.wait();
                printSecond.run();
                flag = 2;
                this.notifyAll();
            }
        }

        public void third(Runnable printThird) throws InterruptedException {
            synchronized (this) {
                while (flag != 2)
                    this.wait();
                printThird.run();
            }
        }
    }

    static class Foo6 {
        // 线程共享变量
        volatile int count = 0;

        public Foo6() {
        }

        public void first(Runnable printFirst) throws InterruptedException {
            printFirst.run();
            count = 1;
        }

        public void second(Runnable printSecond) throws InterruptedException {
            while (count != 1) {
            }
            printSecond.run();
            count = 2;
        }

        public void third(Runnable printThird) throws InterruptedException {
            while (count != 2) {
            }
            printThird.run();
        }
    }

    public static void main(String[] args) {
        Foo3 foo = new Foo3();
        new Thread(() -> {
            try {
                foo.second(() -> System.out.println("second"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                foo.first(() -> System.out.println("first"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                foo.third(() -> System.out.println("third"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
