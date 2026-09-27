package school.sptech.sistema_alertas.application.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import school.sptech.sistema_alertas.application.dto.AlertaMessage;
import school.sptech.sistema_alertas.application.port.in.VerificarDevolucaoPendenteUseCase;
import school.sptech.sistema_alertas.application.port.out.AlertaNotificationPort;
import school.sptech.sistema_alertas.application.port.out.SolicitacaoGatewayPort;
import school.sptech.sistema_alertas.domain.devolucao.Devolucao;

public class VerificarDevolucaoPendenteInteractor implements VerificarDevolucaoPendenteUseCase {

    private final SolicitacaoGatewayPort solicitacaoGateway;
    private final AlertaNotificationPort alertaNotification;
    private final Long limitePendencia;

    public VerificarDevolucaoPendenteInteractor(SolicitacaoGatewayPort solicitacaoGateway,
            AlertaNotificationPort alertaNotification, Long limitePendencia) {
        this.solicitacaoGateway = solicitacaoGateway;
        this.alertaNotification = alertaNotification;
        this.limitePendencia = limitePendencia;
    }


    @Override
    public void verificar(LocalDate hoje) {
        List<Devolucao> devolucoes = solicitacaoGateway.buscarDevolucoes();

        devolucoes.forEach(devolucao -> {
            if (devolucao.isPendente(hoje, limitePendencia)) {
                alertaNotification.notificar(new AlertaMessage(
                        "DEVOLUCAO_PENDENTE",
                        devolucao.getId(),
                        "",
                        "",
                        LocalDateTime.now()));
            }
        });
    }
}
