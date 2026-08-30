package school.sptech.sistema_alertas.infrastructure.in.scheduler;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import school.sptech.sistema_alertas.application.port.in.VerificarPrazoSolicitacoesUseCase;

@Component
public class AlertaSolicitacaoScheduler {

    private final VerificarPrazoSolicitacoesUseCase verificarPrazoSolicitacoesUseCase;

    public AlertaSolicitacaoScheduler(VerificarPrazoSolicitacoesUseCase verificarPrazoSolicitacoesUseCase) {
        this.verificarPrazoSolicitacoesUseCase = verificarPrazoSolicitacoesUseCase;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void verificarPrazos() {
        verificarPrazoSolicitacoesUseCase.verificar(LocalDateTime.now());
    }
    
}
