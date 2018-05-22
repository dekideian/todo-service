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

  Mono<Void> executeAsync(Runnable task);

  <T> Mono<T> computeOneOrZeroAsync(Callable<Optional<T>> computation);

  <T> Mono<T> computeOneAsync(Callable<T> computation);

  <T, C extends Iterable<T>> Flux<T> computeManyAsync(Callable<C> computation);

  <T> Flux<T> computeStreamedAsync(Callable<Stream<T>> computation);
}
