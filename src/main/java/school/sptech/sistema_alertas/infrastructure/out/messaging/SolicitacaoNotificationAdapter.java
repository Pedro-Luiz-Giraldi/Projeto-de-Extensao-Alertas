package school.sptech.sistema_alertas.infrastructure.out.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import school.sptech.sistema_alertas.application.port.out.SolicitacaoNotificationPort;
import school.sptech.sistema_alertas.infrastructure.config.RabbitMqProperties;

@Component
@EnableConfigurationProperties(RabbitMqProperties.class)
public class SolicitacaoNotificationAdapter implements SolicitacaoNotificationPort {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitMqProperties properties;

    public SolicitacaoNotificationAdapter(RabbitTemplate rabbitTemplate, RabbitMqProperties properties) {
        this.rabbitTemplate = rabbitTemplate;
        this.properties = properties;
    }

    @Override
    public void notificarSolicitacaoExpirada(Integer solicitacaoId) {
        rabbitTemplate.convertAndSend(
                properties.exchangeName(),
                properties.routingKeyName(),
                solicitacaoId);
    }

}
