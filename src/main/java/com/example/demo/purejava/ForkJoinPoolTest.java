package com.example.demo.purejava;

import java.util.List;
import java.util.Optional;

public class ForkJoinPoolTest {

    public static void main(String[] args) {

        List<Integer> list = List.of(1,2,3,4,5,6,7,8,9,10);

//        자바에서 ForkJoinPool을 이용해서 스레드를 할당 시켜줌
        Optional<Integer> op = list.parallelStream()
                .filter(integer -> {
                    System.out.println("i: " + integer + " ,thread: " + Thread.currentThread() + ", daemon: "+ Thread.currentThread().isDaemon());
                    boolean b = integer % 2 == 0;
                    return b;
                })
                .findAny();

        System.out.println(op.get());

    }



}
