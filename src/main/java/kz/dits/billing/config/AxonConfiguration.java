package kz.dits.billing.config;

import org.axonframework.eventsourcing.EventCountSnapshotTriggerDefinition;
import org.axonframework.eventsourcing.SnapshotTriggerDefinition;
import org.axonframework.eventsourcing.Snapshotter;
import org.axonframework.spring.eventsourcing.SpringAggregateSnapshotterFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
public class AxonConfiguration {

    @Bean
    public SpringAggregateSnapshotterFactoryBean springAggregateSnapshotterFactoryBean() {
        Executor executor = Executors.newCachedThreadPool();
        SpringAggregateSnapshotterFactoryBean springAggregateSnapshotterFactoryBean = new SpringAggregateSnapshotterFactoryBean();
        springAggregateSnapshotterFactoryBean.setExecutor(executor);
        return springAggregateSnapshotterFactoryBean;
    }

    @Bean
    public SnapshotTriggerDefinition accountSnapshotTriggerDefinition(Snapshotter snapshotter) {
        return new EventCountSnapshotTriggerDefinition(snapshotter, 1000);
    }
}
