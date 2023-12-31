package com.powerledger.powerplant.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String RPC_MESSAGE_QUEUE = "rpc_msg_queue";
    public static final String RPC_REPLY_MESSAGE_QUEUE = "rpc_reply_msg_queue";
    public static final String RPC_EXCHANGE = "rpc_exchange";
    public static final String RPC_REPLY_EXCHANGE = "rpc_reply_exchange";
    /** *
     * Configure the Send Message Queue
     */
    @Bean
    Queue msgQueue() {
        return new Queue(RPC_MESSAGE_QUEUE);
    }
    /** *
     * Return Queue Configuration
     */
    @Bean
    Queue replyQueue() {
        return new Queue(RPC_REPLY_MESSAGE_QUEUE);
    }
    /** *
     * Switch setting
     */
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(RPC_EXCHANGE);
    }

    @Bean
    TopicExchange replyTopicExchange() {
        return new TopicExchange(RPC_REPLY_EXCHANGE);
    }
    /** *
     * Queuing and Switch Link Request
     */
    @Bean
    Binding msgBinding() {
        return BindingBuilder.bind(msgQueue())
                .to(topicExchange())
                .with(RPC_MESSAGE_QUEUE);
    }
    /** *
     * Back to Queue and Switch Link
     */
    @Bean
    Binding replyBinding() {
        return BindingBuilder.bind(replyQueue())
                .to(replyTopicExchange())
                .with(RPC_REPLY_MESSAGE_QUEUE);
    }
}
