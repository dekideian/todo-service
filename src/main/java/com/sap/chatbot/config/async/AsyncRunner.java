package com.sap.chatbot.config.async;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.Callable;
import java.util.stream.Stream;

/**
 * @author Florin-Gabriel Barbuceanu, florin.barbuceanu@sap.com
 * @since 22/05/2018
 */
public interface AsyncRunner {

  <T> Mono<T> computeAsync(Callable<T> computation);

  <T, C extends Iterable<T>> Flux<T> computeManyAsync(Callable<C> computation);

  <T> Flux<T> computeStreamedAsync(Callable<Stream<T>> computation);
}
