package school.sptech.sistema_alertas.application.service;

import java.time.LocalDateTime;
import java.util.List;

import school.sptech.sistema_alertas.application.port.out.SolicitacaoGatewayPort;
import school.sptech.sistema_alertas.application.port.out.SolicitacaoNotificationPort;
import school.sptech.sistema_alertas.domain.solicitacao.Solicitacao;
import school.sptech.sistema_alertas.application.port.in.VerificarPrazoSolicitacoesUseCase;

public class VerificarPrazoSolicitacoesInteractor implements VerificarPrazoSolicitacoesUseCase {

    private final SolicitacaoGatewayPort solicitacaoGateway;
    private final SolicitacaoNotificationPort solicitacaoNotification;

    public VerificarPrazoSolicitacoesInteractor(SolicitacaoGatewayPort solicitacaoGateway,
            SolicitacaoNotificationPort solicitacaoNotification) {
        this.solicitacaoGateway = solicitacaoGateway;
        this.solicitacaoNotification = solicitacaoNotification;
    }

    @Override
    public void verificar(LocalDateTime agora) {

        List<Solicitacao> solicitacoes = solicitacaoGateway.buscarSolicitacoes();

        solicitacoes.forEach(solicitacao -> {
            if (solicitacao.isExpirada(agora)) {
                solicitacaoNotification.notificarSolicitacaoExpirada(solicitacao.getId());
            }
        });
        
    }
}
