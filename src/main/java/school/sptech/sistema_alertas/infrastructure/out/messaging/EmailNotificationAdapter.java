package school.sptech.sistema_alertas.infrastructure.out.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import school.sptech.sistema_alertas.application.dto.EmailMessage;
import school.sptech.sistema_alertas.application.port.out.EmailNotificationPort;
import school.sptech.sistema_alertas.infrastructure.config.RabbitMqProperties;

@Component
@EnableConfigurationProperties(RabbitMqProperties.class)
public class EmailNotificationAdapter implements EmailNotificationPort {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitMqProperties properties;

    public EmailNotificationAdapter(RabbitTemplate rabbitTemplate, RabbitMqProperties properties) {
        this.rabbitTemplate = rabbitTemplate;
        this.properties = properties;
    }

    @Override
    public void enviar(EmailMessage mensagem) {
        rabbitTemplate.convertAndSend(
                properties.exchangeName(),
                properties.emailRoutingKeyName(),
                mensagem);
    }
}
