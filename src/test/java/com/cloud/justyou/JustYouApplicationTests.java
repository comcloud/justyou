package com.cloud.justyou;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
class JustYouApplicationTests {

    @Test
    void contextLoads() {
        List<String> list = new ArrayList<>();
        list.add("123");
        list.add("123");
        list.add("123");
        list.add("456");
        list.add("123");
        Stream<String> stream = list.stream();

//        Stream<String> stringStream = stream.filter(x -> x.equals("456"));
        Stream<String> stringStream = stream.filter(x -> x == "456");
        Stream<String> stringStream1 = stream.map(x -> x + 4);

        stringStream.forEach(System.out::println);
    }

}
