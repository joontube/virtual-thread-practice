package com.example.demo.purejava.normal;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class NormalThreadExecutorsCreation {

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

        try (ExecutorService executorService = Executors.newFixedThreadPool(10)) {

            for (int i = 0; i < 10; i++) {
                executorService.execute(runnable);
            }
//        다 끝난 다음에 실행이 됨
//        try 문을 추가하면 자동으로 close가 되므로 주석처리
//        executorService.close();
        }

        log.info("2) main. thread:{}", Thread.currentThread());
    }
}
