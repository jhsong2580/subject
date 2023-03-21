package subject.blog.utils.lock;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CustomLock implements AutoCloseable {

    private static final ConcurrentHashMap<String, Lock> locks = new ConcurrentHashMap<>();

    private String keyName;
    private Lock lock;

    public CustomLock(String key, long waitTime, TimeUnit timeUnit) {
        this.keyName = key;
        this.lock = locks.computeIfAbsent(key, k -> new ReentrantLock());
        boolean lockIsAvailable;

        try {
            this.lock = locks.computeIfAbsent(key, k -> new ReentrantLock());
            lockIsAvailable = lock.tryLock(waitTime, timeUnit);
        } catch (Exception e) {
            throw new RuntimeException("Can not Set Lock : " + key);
        }

        if (!lockIsAvailable) {
            throw new RuntimeException("Can not Set Lock : " + key);
        }
    }

    @Override
    public void close() throws Exception {
        Lock lock = locks.get(keyName);
        if (lock != null) {
            lock.unlock();
        }
    }
}
