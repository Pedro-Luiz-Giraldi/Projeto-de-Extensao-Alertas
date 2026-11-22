package school.sptech.sistema_alertas.infrastructure.out.messaging;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import school.sptech.sistema_alertas.application.dto.AlertaMapper;
import school.sptech.sistema_alertas.application.dto.AlertaMessage;
import school.sptech.sistema_alertas.application.port.out.AlertaNotificationPort;
import school.sptech.sistema_alertas.application.port.out.EmailNotificationPort;
import school.sptech.sistema_alertas.infrastructure.config.RabbitMqProperties;

@Component
@EnableConfigurationProperties(RabbitMqProperties.class)
public class AlertaNotificationAdapter implements AlertaNotificationPort {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitMqProperties properties;
    private final EmailNotificationPort emailNotificationPort;

    public AlertaNotificationAdapter(RabbitTemplate rabbitTemplate,
                                     RabbitMqProperties properties,
                                     EmailNotificationPort emailNotificationPort) {
        this.rabbitTemplate = rabbitTemplate;
        this.properties = properties;
        this.emailNotificationPort = emailNotificationPort;
    }

    @Override
    public void notificar(AlertaMessage mensagem) {
        rabbitTemplate.convertAndSend(
                properties.exchangeName(),
                properties.routingKeyName(),
                mensagem);

        emailNotificationPort.enviar(AlertaMapper.toEmail(mensagem));
    }
}