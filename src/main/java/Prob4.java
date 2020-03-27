import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


/**
 * @author linxu
 * @date 2020/3/27
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public class Prob4 {
    private Stream stream = Stream.getInstance();
    //只存放两个
    private ArrayBlockingQueue<Long> queue = new ArrayBlockingQueue<>(2);
    private Condition condition = new ReentrantLock().newCondition();
    private AtomicBoolean done1 = new AtomicBoolean(false);

    public void thread1() throws InterruptedException {
        // TODO 请完成实现部分
        for (; ; ) {
            synchronized (stream) {
                queue.offer(stream.get());
            }
            //永远的第一个
            long first = queue.poll();
            condition.signal();//唤醒第二个去拿，保证拿到是有序的
            stream.put(Stream.isPrimeNumber(first));
            //计算完毕，必须执行CAS
            done1.set(true);
        }
    }

    public void thread2() throws InterruptedException {
        for (; ; ) {
            synchronized (stream) {
                queue.offer(stream.get());
            }
            //永远的第二
            condition.await();
            long second = queue.poll();
            //并行计算
            boolean res = Stream.isPrimeNumber(second);
            //1完成了，2才放入
            while (true) {
                if (done1.get()) {
                    stream.put(res);
                    done1.set(false);
                    break;
                }
            }
        }
    }
}

