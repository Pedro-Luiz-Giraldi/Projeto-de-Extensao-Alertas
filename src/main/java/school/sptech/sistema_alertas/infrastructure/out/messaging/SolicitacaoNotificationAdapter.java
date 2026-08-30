package school.sptech.sistema_alertas.infrastructure.out.messaging;

import org.springframework.stereotype.Service;

import school.sptech.sistema_alertas.application.port.out.SolicitacaoNotificationPort;

@Service
public class SolicitacaoNotificationAdapter implements SolicitacaoNotificationPort {

    @Override
    public void notificarSolicitacaoExpirada(Integer solicitacaoId) {
        // TODO implementaçao do RabbitMQ
        
    }

}
