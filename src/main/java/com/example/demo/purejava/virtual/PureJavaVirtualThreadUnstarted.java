package com.example.demo.purejava.virtual;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PureJavaVirtualThreadUnstarted {


    private static final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            log.info("1) run. thread:{}, class:{}", Thread.currentThread(), Thread.currentThread().getClass());
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e ){
                throw new RuntimeException(e);
            }
            log.info("2) run. thread:{}", Thread.currentThread());
        }
    };

    public static void main(String[] args) throws InterruptedException {



        Thread thread = Thread.ofVirtual().name("myVirtual").unstarted(runnable);
//        Unstarted는 start를 따로 해줘야함
        thread.start();

        thread.join();



    }
}
