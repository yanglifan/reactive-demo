package com.github.yanglifan;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class ReactiveController {
    @GetMapping("/people/{name}")
    public Mono<Person> findPerson(@PathVariable("name") String name) {
        return Mono.just(new Person(name));
    }
}
