package com.github.yanglifan.workshop.reactive.reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

/**
 * @author Yang Lifan
 */
public class ReactorTest {
    private static List<String> words = Arrays.asList(
            "the",
            "quick",
            "brown",
            "fox",
            "jumped",
            "over",
            "the",
            "lazy",
            "dog"
    );

    @Test
    public void simpleCreation() {
        Flux<String> wordsFlux = Flux.fromIterable(words);
        wordsFlux.subscribe(System.out::println);
    }
}
