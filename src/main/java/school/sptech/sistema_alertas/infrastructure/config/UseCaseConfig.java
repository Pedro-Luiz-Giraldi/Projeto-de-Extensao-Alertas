package school.sptech.sistema_alertas.infrastructure.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import school.sptech.sistema_alertas.application.port.in.VerificarEstoqueBaixoUseCase;
import school.sptech.sistema_alertas.application.port.in.VerificarPrazoSolicitacoesUseCase;
import school.sptech.sistema_alertas.application.port.in.VerificarVencimentoMaterialUseCase;
import school.sptech.sistema_alertas.application.port.out.AlertaNotificationPort;
import school.sptech.sistema_alertas.application.port.out.MaterialGatewayPort;
import school.sptech.sistema_alertas.application.port.out.SolicitacaoGatewayPort;
import school.sptech.sistema_alertas.application.port.out.SolicitacaoNotificationPort;
import school.sptech.sistema_alertas.application.service.VerificarEstoqueBaixoInteractor;
import school.sptech.sistema_alertas.application.service.VerificarPrazoSolicitacoesInteractor;
import school.sptech.sistema_alertas.application.service.VerificarVencimentoMaterialInteractor;

@Configuration
@EnableConfigurationProperties(AlertasProperties.class)
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

    @Bean
    public VerificarEstoqueBaixoUseCase verificarEstoqueBaixoUseCase(
            MaterialGatewayPort materialGateway,
            AlertaNotificationPort alertaNotification
    ) {
        return new VerificarEstoqueBaixoInteractor(
                materialGateway,
                alertaNotification);
    }

    @Bean
    public VerificarVencimentoMaterialUseCase verificarVencimentoMaterialUseCase(
            MaterialGatewayPort materialGateway,
            AlertaNotificationPort alertaNotification,
            AlertasProperties alertasProperties
    ) {
        return new VerificarVencimentoMaterialInteractor(
                materialGateway,
                alertaNotification,
                alertasProperties.vencimento().diasAntecedencia());
    }
}
