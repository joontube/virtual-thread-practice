package com.example.demo.purejava.virtual;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

@Slf4j
public class VirtualThreadExecutorsCreation {

    private static final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            log.info("1) run. thread:{}", Thread.currentThread());
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e ){
                throw new RuntimeException(e);
            }
            log.info("2) run. thread:{}", Thread.currentThread());
        }
    };

    public static void main(String[] args) {

        log.info("1) main. thread:{}", Thread.currentThread());


//      1. Thread 이름이 Default값으로 설정되어있지 않음
        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {

            for (int i = 0; i < 100; i++) {
                executorService.execute(runnable);
            }
//        다 끝난 다음에 실행이 됨
//        try 문을 추가하면 자동으로 close가 되므로 주석처리
//        executorService.close();
        }


//        2. 이름 설정해주기
        ThreadFactory factory = Thread.ofVirtual().name("myVirtual-",0).factory();
        try (ExecutorService executorService = Executors.newThreadPerTaskExecutor(factory)) {

            for (int i = 0; i < 100; i++) {
                executorService.execute(runnable);
            }

        }

        log.info("2) main. thread:{}", Thread.currentThread());
    }
}
