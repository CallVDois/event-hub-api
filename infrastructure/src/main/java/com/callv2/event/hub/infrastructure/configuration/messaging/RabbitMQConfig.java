package com.callv2.event.hub.infrastructure.configuration.messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    private static final String EVENT_HUB_EXCHANGE_NAME = "eventhub.exchange";
    private static final String EVENT_HUB_DLX_EXCHANGE_NAME = "eventhub.exchange.dlx";

    private static final String EVENT_PUBLISHED_QUEUE_NAME = "eventhub.event.published.queue";
    private static final String EVENT_PUBLISHED_ROUTING_KEY = "#.event";
    private static final String EVENT_PUBLISHED_DLX_ROUTING_KEY = "eventhub.event.published.event.deadletter";
    private static final String EVENT_PUBLISHED_DLQ_QUEUE_NAME = "eventhub.event.published.queue.dlq";

    @Bean
    MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Configuration
    static class Admin {

        private final TopicExchange eventHubExchange = new TopicExchange(EVENT_HUB_EXCHANGE_NAME);
        private final TopicExchange eventHubDlxExchange = new TopicExchange(EVENT_HUB_DLX_EXCHANGE_NAME);

        private final Queue eventPublishedQueue = QueueBuilder
                .durable(EVENT_PUBLISHED_QUEUE_NAME)
                .deadLetterExchange(EVENT_HUB_DLX_EXCHANGE_NAME)
                .deadLetterRoutingKey(EVENT_PUBLISHED_DLX_ROUTING_KEY)
                .build();

        private final Queue eventPublishedDlqQueue = QueueBuilder
                .durable(EVENT_PUBLISHED_DLQ_QUEUE_NAME)
                .build();

        private final Binding eventPublishedBinding = BindingBuilder
                .bind(eventPublishedQueue)
                .to(eventHubExchange)
                .with(EVENT_PUBLISHED_ROUTING_KEY);

        private final Binding eventPublishedDeadLetterBinding = BindingBuilder
                .bind(eventPublishedDlqQueue)
                .to(eventHubDlxExchange)
                .with(EVENT_PUBLISHED_DLX_ROUTING_KEY);

        @Bean
        TopicExchange eventExchange() {
            return eventHubExchange;
        }

        @Bean
        TopicExchange eventDlxExchange() {
            return eventHubDlxExchange;
        }

        @Bean
        Queue eventPublishedQueue() {
            return eventPublishedQueue;
        }

        @Bean
        Queue eventPublishedDlqQueue() {
            return eventPublishedDlqQueue;
        }

        @Bean
        Binding eventPublishedBindings() {
            return eventPublishedBinding;
        }

        @Bean
        Binding eventPublishedDeadLetterBinding() {
            return eventPublishedDeadLetterBinding;
        }

    }

}
