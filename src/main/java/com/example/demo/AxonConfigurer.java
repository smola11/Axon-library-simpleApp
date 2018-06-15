package com.example.demo;

import org.axonframework.config.EventHandlingConfiguration;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfigurer {

    @Bean
    public EventStore eventBus(EventStorageEngine storageEngine) {
        return new EmbeddedEventStore(storageEngine);
    }

    // Store in memory
    @Bean
    public EventStorageEngine storageEngine() {
        return new InMemoryEventStorageEngine();
    }

    @Autowired
    public void configure(EventHandlingConfiguration eventHandlingConfiguration) {
        // default all processors to tracking mode.
        // https://docs.axonframework.org/part3/spring-boot-autoconfig.html#event-handling-configuration
        // eventHandlingConfiguration.usingTrackingProcessors();
    }


}
