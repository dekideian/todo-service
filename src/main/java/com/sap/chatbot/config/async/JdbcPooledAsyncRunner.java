package com.sap.chatbot.config.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.util.concurrent.Callable;
import java.util.stream.Stream;

/**
 * @author Florin-Gabriel Barbuceanu, florin.barbuceanu@sap.com
 * @since 22/05/2018
 */
@Component
public class JdbcPooledAsyncRunner implements AsyncRunner {

  private final Scheduler jdbcScheduler;

  @Autowired
  public JdbcPooledAsyncRunner(Scheduler jdbcScheduler) {
    this.jdbcScheduler = jdbcScheduler;
  }

  @Override
  public <T> Mono<T> computeAsync(Callable<T> computation) {
    return Mono.fromCallable(computation).publishOn(jdbcScheduler);
  }

  @Override
  public <T, C extends Iterable<T>> Flux<T> computeManyAsync(Callable<C> computation) {
    return Mono.fromCallable(computation).flatMapMany(Flux::fromIterable).publishOn(jdbcScheduler);
  }

  @Override
  public <T> Flux<T> computeStreamedAsync(Callable<Stream<T>> computation) {
    return Mono.fromCallable(computation).flatMapMany(Flux::fromStream).publishOn(jdbcScheduler);
  }
}
