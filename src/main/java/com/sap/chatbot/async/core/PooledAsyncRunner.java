package com.sap.chatbot.async.core;

import com.sap.chatbot.async.AsyncRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
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
public class PooledAsyncRunner implements AsyncRunner {

  private final Scheduler scheduler;

  @Autowired
  public PooledAsyncRunner(Scheduler scheduler) {
    this.scheduler = scheduler;
  }

  @Override
  public Mono<Void> execute(Runnable task) {
    return Mono.<Void>fromRunnable(task)
        .subscribeOn(scheduler)
        .publishOn(Schedulers.parallel());
  }

  @Override
  public <T> Mono<T> oneOrZero(Callable<Optional<T>> computation) {
    return Mono.fromCallable(computation)
        .flatMap(Mono::justOrEmpty)
        .subscribeOn(scheduler)
        .publishOn(Schedulers.parallel());
  }

  @Override
  public <T> Mono<T> one(Callable<T> computation) {
    return Mono.fromCallable(computation)
        .subscribeOn(scheduler)
        .publishOn(Schedulers.parallel());
  }

  @Override
  @Transactional
  public <T, C extends Iterable<T>> Flux<T> many(Callable<C> computation) {
    return Mono.fromCallable(computation)
        .flatMapMany(Flux::fromIterable)
        .subscribeOn(scheduler)
        .publishOn(Schedulers.parallel());
  }

  @Override
  public <T> Flux<T> stream(Callable<Stream<T>> computation) {
    return Mono.fromCallable(computation)
        .flatMapMany(Flux::fromStream)
        .subscribeOn(scheduler)
        .publishOn(Schedulers.parallel());
  }
}
