import java.util.concurrent.CountDownLatch;

class Foo {
    private CountDownLatch firstFinished = new CountDownLatch(1);
    private CountDownLatch secondFinished = new CountDownLatch(1);

    public Foo() {
    }

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        firstFinished.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        firstFinished.await();
        printSecond.run();
        secondFinished.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        secondFinished.await();
        printThird.run();
    }
}
