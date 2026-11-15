package school.sptech.sistema_alertas.infrastructure.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(RabbitMqProperties.class)
public class RabbitMqConfig {

    private final RabbitMqProperties properties;

    public RabbitMqConfig(RabbitMqProperties properties) {
        this.properties = properties;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.setAutoStartup(true);
        rabbitAdmin.initialize();
        return rabbitAdmin;
    }

    @Bean
    public DirectExchange getDirectExchange() {
        return new DirectExchange(properties.exchangeName());
    }

    @Bean("alertasQueue")
    public Queue getQueue() {
        return QueueBuilder
            .durable(properties.queueName())
            .build();
    }

    @Bean("emailQueue")
    public Queue getEmailQueue() {
        return QueueBuilder
            .durable(properties.emailQueueName())
            .build();
    }

    @Bean
    public Binding getBinding(
            @Qualifier("alertasQueue") Queue queue,
            DirectExchange directExchange
    ) {
        return BindingBuilder
            .bind(queue)
            .to(directExchange)
            .with(properties.routingKeyName());
    }

    @Bean
    public Binding getEmailBinding(
            @Qualifier("emailQueue") Queue emailQueue,
            DirectExchange directExchange
    ) {
        return BindingBuilder
            .bind(emailQueue)
            .to(directExchange)
            .with(properties.emailRoutingKeyName());
    }

    @Bean
    public JacksonJsonMessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }
}
