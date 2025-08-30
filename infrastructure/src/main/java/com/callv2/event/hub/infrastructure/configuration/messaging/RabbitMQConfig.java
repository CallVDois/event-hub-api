package com.callv2.event.hub.infrastructure.configuration.messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.callv2.event.hub.infrastructure.event.model.RegisterEventMessage;
import com.callv2.event.hub.infrastructure.messaging.producer.rabbitmq.RabbitMQProducer;

@Configuration
public class RabbitMQConfig {

    private static final String EVENT_HUB_EXCHANGE_NAME = "event.hub.exchange";
    private static final String EVENT_HUB_DLX_EXCHANGE_NAME = "event.hub.exchange.dlx";

    private static final String EVENT_HUB_QUEUE_NAME = "event.hub.queue";
    private static final String EVENT_HUB_DEAD_LETTER_QUEUE_NAME = "event.hub.queue.dlq";

    private static final String EVENT_HUB_ROUTING_KEY = "#";
    private static final String EVENT_HUB_DEAD_LETTER_ROUTING_KEY = "#";

    @Bean
    MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    RabbitMQProducer<RegisterEventMessage> memberCreatedProducer(final RabbitTemplate rabbitTemplate) {
        return new RabbitMQProducer<>(
                EVENT_HUB_EXCHANGE_NAME,
                EVENT_HUB_ROUTING_KEY,
                rabbitTemplate);
    }

    @Configuration
    static class Admin {

        private final TopicExchange eventHubExchange = new TopicExchange(EVENT_HUB_EXCHANGE_NAME);
        private final TopicExchange eventHubDlxExchange = new TopicExchange(EVENT_HUB_DLX_EXCHANGE_NAME);

        private final Queue eventHubQueue = QueueBuilder
                .durable(EVENT_HUB_QUEUE_NAME)
                .deadLetterExchange(EVENT_HUB_DLX_EXCHANGE_NAME)
                .deadLetterRoutingKey(EVENT_HUB_DEAD_LETTER_ROUTING_KEY)
                .build();

        private final Queue eventHubDlxQueue = QueueBuilder
                .durable(EVENT_HUB_DEAD_LETTER_QUEUE_NAME)
                .build();

        private final Binding eventHubBinding = BindingBuilder
                .bind(eventHubQueue)
                .to(eventHubExchange)
                .with(EVENT_HUB_ROUTING_KEY);

        private final Binding eventHubDeadLetterBinding = BindingBuilder
                .bind(eventHubDlxQueue)
                .to(eventHubDlxExchange)
                .with(EVENT_HUB_DEAD_LETTER_ROUTING_KEY);

        @Bean
        TopicExchange eventExchange() {
            return eventHubExchange;
        }

        @Bean
        TopicExchange eventDlxExchange() {
            return eventHubDlxExchange;
        }

        @Bean
        Queue eventHubQueue() {
            return eventHubQueue;
        }

        @Bean
        Queue eventHubDlxQueue() {
            return eventHubDlxQueue;
        }

        @Bean
        Binding eventPublishedBindings() {
            return eventHubBinding;
        }

        @Bean
        Binding eventPublishedDeadLetterBinding() {
            return eventHubDeadLetterBinding;
        }

    }

}
