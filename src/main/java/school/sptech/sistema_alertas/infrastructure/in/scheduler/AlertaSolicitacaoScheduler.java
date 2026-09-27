package school.sptech.sistema_alertas.infrastructure.in.scheduler;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import school.sptech.sistema_alertas.application.port.in.VerificarDevolucaoPendenteUseCase;
import school.sptech.sistema_alertas.application.port.in.VerificarPrazoSolicitacoesUseCase;

@Component
public class AlertaSolicitacaoScheduler {

    private final VerificarPrazoSolicitacoesUseCase verificarPrazoSolicitacoesUseCase;
    private final VerificarDevolucaoPendenteUseCase verificarDevolucaoPendenteUseCase;

    public AlertaSolicitacaoScheduler(VerificarPrazoSolicitacoesUseCase verificarPrazoSolicitacoesUseCase,
            VerificarDevolucaoPendenteUseCase verificarDevolucaoPendenteUseCase) {
        this.verificarPrazoSolicitacoesUseCase = verificarPrazoSolicitacoesUseCase;
        this.verificarDevolucaoPendenteUseCase = verificarDevolucaoPendenteUseCase;
    }


    @Scheduled(cron = "0 0 0 * * *")
    public void verificarPrazos() {
        verificarPrazoSolicitacoesUseCase.verificar(LocalDateTime.now());
    }
    
    @Scheduled(cron = "0 0 0 * * *")
    public void verificarDevolucoes() {
        verificarDevolucaoPendenteUseCase.verificar(LocalDate.now());
    }
}
