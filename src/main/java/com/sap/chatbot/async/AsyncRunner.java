package com.sap.chatbot.async;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.stream.Stream;

/**
 * @author Florin-Gabriel Barbuceanu, florin.barbuceanu@sap.com
 * @since 22/05/2018
 */
public interface AsyncRunner {

  Mono<Void> execute(Runnable task);

  <T> Mono<T> oneOrZero(Callable<Optional<T>> computation);

  <T> Mono<T> one(Callable<T> computation);

  <T, C extends Iterable<T>> Flux<T> many(Callable<C> computation);

  <T> Flux<T> stream(Callable<Stream<T>> computation);
}
