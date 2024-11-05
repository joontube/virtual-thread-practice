package com.example.demo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PureJavaNormalThreadApplication {


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

        Thread thread = new Thread(runnable);
        thread.start();

    }
}
