package com.sap.chatbot.config.persistence;

import java.util.concurrent.Executors;
import javax.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.sap.chatbot.domain.entities.DomainEntitiesMarker;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Configuration
@EnableTransactionManagement
@EntityScan(basePackageClasses = DomainEntitiesMarker.class)
public class PersistenceConfig {
  private final Integer maxConnectionPoolSize;

  public PersistenceConfig(
      @Value("${spring.datasource.hikari.maximum-pool-size}") Integer maxConnectionPoolSize) {
    this.maxConnectionPoolSize = maxConnectionPoolSize;
  }

  @Bean
  public Scheduler jdbcScheduler() {
    return Schedulers.fromExecutor(Executors.newFixedThreadPool(maxConnectionPoolSize));
  }

  @Bean
  public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory);
  }
}
