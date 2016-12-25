package com.github.yanglifan;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class ReactiveController {
    private ReactiveRedisRepository reactiveRedisRepository;

    public ReactiveController(ReactiveRedisRepository reactiveRedisRepository) {
        this.reactiveRedisRepository = reactiveRedisRepository;
    }

    @GetMapping("/people/{name}")
    public Mono<Person> findPerson(@PathVariable("name") String name) {
        return Mono.just(new Person(name));
    }

    @PostMapping
    public Mono<Boolean> createPerson(@RequestBody String name) {
        return reactiveRedisRepository.set(name, name + "-value");
    }
}
