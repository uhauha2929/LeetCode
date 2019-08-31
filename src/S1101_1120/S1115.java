package S1101_1120;

import java.util.concurrent.*;

public class S1115 {

    // 使用两个信号量相互等待实现同步, 类似可以使用CountDownLatch, 但是每次需要重新new新的
    private static class FooBar {
        private int n;
        private Semaphore one = new Semaphore(1);
        private Semaphore two = new Semaphore(0);

        public FooBar(int n) {
            this.n = n;
        }

        public void foo(Runnable printFoo) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                one.acquire();
                printFoo.run();
                two.release();
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                two.acquire();
                printBar.run();
                one.release();
            }
        }
    }

    public static void main(String[] args) {
        FooBar fooBar = new FooBar(5);
        new Thread(() -> {
            try {
                fooBar.bar(() -> System.out.print("bar"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                fooBar.foo(() -> System.out.print("foo"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
