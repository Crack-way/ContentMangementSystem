package com.example.contentmanagementsystem.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class ReactiveSources {

    public static Flux<String> stringNumbersFlux() {


        return Flux.just("one", "two", "three", "four", "five", "six");
    }

    public static Flux<Integer> intNumbersFlux() {


        return Flux.range(1, 10).delayElements(Duration.ofSeconds(1));
    }

    public static Flux<Integer> intNumbersFluxWithException() {


        return Flux.range(1, 10).delayElements(Duration.ofSeconds(1)).map(e -> {
            if (e == 5) throw new RuntimeException("Error");
            return e;
        });
    }

    public static Mono<Integer> intNumbersMono() {

        return Mono.just(42).delayElement(Duration.ofSeconds(1));
    }


    public static Flux<User> userFlux() {

        return Flux.just(new User(1, "Rupesh", "Sunuwar"),

                new User(2, "Dipesh", "Sunuwar"));

    }


}
