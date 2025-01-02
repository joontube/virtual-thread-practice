# create virtual thread

```java
Thread thread = Thread.ofVirtual().name("myVirtual").start(runnable);
```

```java
Thread thread = Thread.ofVirtual().name("myVirtual1").unstarted(runnable);
thread.start();
```


# create virtual thread using ExecutorService

```java
ThreadFactory factory = Thread.ofVirtual().name("myVirtual-", 0).factory();
try (ExecutorService executorService = Executors.newThreadPerTaskExecutor(factory)) {
    for (int i = 0; i < 10; i++) {
        executorService.submit(runnable);
    }
}
```

# pinning
```java
private final Runnable runnable = new Runnable() {
    @Override
    public void run() {
        log.info("1) run. thread: " + Thread.currentThread());
        synchronized (this) {  // <--- synchronized block cause pinning
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        log.info("2) run. thread: " + Thread.currentThread());
    }
};
```

# detect pinning
```
-Djdk.tracePinnedThreads=full  or -Djdk.tracePinnedThreads=short
```

# reentrantLock

```java
private final ReentrantLock lock = new ReentrantLock();

private final Runnable runnable = new Runnable() {
    @Override
    public void run() {
        log.info("1) run. thread: " + Thread.currentThread());

        lock.lock();  // <-- use ReentrantLock instead of sycchronized block 
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
        log.info("2) run. thread: " + Thread.currentThread());
    }
};
```
# thread dump
```
jcmd <PID> Thread.dump_to_file -format=text <file>
```

# spring boot

```yaml
spring:
  threads:
    virtual:
      enabled: true
```

### task execution
```yaml
spring:
  task:
    execution:
      thread-name-prefix: myTask-
```

### task scheduling

```yaml
spring:
  task:
    scheduling:
      thread-name-prefix: myScheduler-
```

### keep alive although only daemon thread run

```yaml
spring:
  main:
    keep-alive: true
```

# reference
#### 인프런 무료 강의 링크
https://www.inflearn.com/course/1%EC%8B%9C%EA%B0%84%EB%A7%8C%EC%97%90-%EB%81%9D%EB%82%B4%EB%8A%94-virtual-thread-springboot/dashboard

####  강의 자료
https://github.com/ChunGeun-Yu/java-virtual-thread

#### 그 외 레퍼런스
https://docs.oracle.com/en/java/javase/21/core/virtual-threads.html

https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-3.2-Release-Notes#support-for-virtual-threads

https://d2.naver.com/helloworld/1203723

https://techblog.woowahan.com/15398/