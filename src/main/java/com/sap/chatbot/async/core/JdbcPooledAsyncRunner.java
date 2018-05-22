package com.sap.chatbot.async.core;

import com.sap.chatbot.async.AsyncRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.Optional;
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
  public Mono<Void> executeAsync(Runnable task) {
    return Mono.<Void>fromRunnable(task)
        .subscribeOn(jdbcScheduler)
        .publishOn(Schedulers.parallel());
  }

  @Override
  public <T> Mono<T> computeOneOrZeroAsync(Callable<Optional<T>> computation) {
    return Mono.fromCallable(computation)
        .flatMap(Mono::justOrEmpty)
        .subscribeOn(jdbcScheduler)
        .publishOn(Schedulers.parallel());
  }

  @Override
  public <T> Mono<T> computeOneAsync(Callable<T> computation) {
    return Mono.fromCallable(computation)
        .subscribeOn(jdbcScheduler)
        .publishOn(Schedulers.parallel());
  }

  @Override
  public <T, C extends Iterable<T>> Flux<T> computeManyAsync(Callable<C> computation) {
    return Mono.fromCallable(computation)
        .flatMapMany(Flux::fromIterable)
        .subscribeOn(jdbcScheduler)
        .publishOn(Schedulers.parallel());
  }

  @Override
  public <T> Flux<T> computeStreamedAsync(Callable<Stream<T>> computation) {
    return Mono.fromCallable(computation)
        .flatMapMany(Flux::fromStream)
        .subscribeOn(jdbcScheduler)
        .publishOn(Schedulers.parallel());
  }
}
