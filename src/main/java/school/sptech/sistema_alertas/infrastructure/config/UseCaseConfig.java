package school.sptech.sistema_alertas.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import school.sptech.sistema_alertas.application.port.in.VerificarPrazoSolicitacoesUseCase;
import school.sptech.sistema_alertas.application.port.out.SolicitacaoGatewayPort;
import school.sptech.sistema_alertas.application.port.out.SolicitacaoNotificationPort;
import school.sptech.sistema_alertas.application.service.VerificarPrazoSolicitacoesInteractor;

@Configuration
public class UseCaseConfig {

    @Bean
    public VerificarPrazoSolicitacoesUseCase verificarPrazoSolicitacoesUseCase(
            SolicitacaoGatewayPort solicitacaoGateway,
            SolicitacaoNotificationPort solicitacaoNotification
    ) {
        return new VerificarPrazoSolicitacoesInteractor(
                solicitacaoGateway, 
                solicitacaoNotification);
    }
}
