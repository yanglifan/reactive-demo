package com.github.yanglifan.workshop.reactive.reactor;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

/**
 * The most of following sample methods come from http://www.infoq.com/cn/articles/reactor-by-example
 *
 * @author Yang Lifan
 */
public class ReactorTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReactorTest.class);

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
        wordsFlux.subscribe(LOGGER::info);
    }

    /**
     * zipWith will combine the value in the current stream and the publisher (1st param)
     */
    @Test
    public void findingMissingLetter() {
        Flux<String> letters = Flux
                .fromIterable(words)
                .flatMap(word -> Flux.fromArray(word.split("")))
                .distinct()
                .sort()
                .zipWith(Flux.range(1, Integer.MAX_VALUE),
                        (string, count) -> String.format("%2d. %s", count, string));

        letters.subscribe(LOGGER::info);
    }
}
