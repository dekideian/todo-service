package com.sap.chatbot.config.persistence;

import com.sap.chatbot.domain.entities.DomainEntitiesMarker;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Florin-Gabriel Barbuceanu, florin.barbuceanu@sap.com
 * @since 22/05/2018
 */
@Configuration
@EnableTransactionManagement
@EntityScan(basePackageClasses = DomainEntitiesMarker.class)
public class PersistenceConfig {}
