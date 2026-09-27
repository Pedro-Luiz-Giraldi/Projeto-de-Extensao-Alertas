package school.sptech.sistema_alertas.infrastructure.in.scheduler;

import java.time.LocalDate;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import school.sptech.sistema_alertas.application.port.in.VerificarEstoqueBaixoUseCase;
import school.sptech.sistema_alertas.application.port.in.VerificarVencimentoMaterialUseCase;

@Component
public class AlertaMaterialScheduler {

    private final VerificarEstoqueBaixoUseCase verificarEstoqueBaixoUseCase;
    private final VerificarVencimentoMaterialUseCase verificarVencimentoMaterialUseCase;

    public AlertaMaterialScheduler(VerificarEstoqueBaixoUseCase verificarEstoqueBaixoUseCase,
            VerificarVencimentoMaterialUseCase verificarVencimentoMaterialUseCase) {
        this.verificarEstoqueBaixoUseCase = verificarEstoqueBaixoUseCase;
        this.verificarVencimentoMaterialUseCase = verificarVencimentoMaterialUseCase;
    }

    @Scheduled(cron = "0 30 0 * * *")
    public void verificarEstoqueBaixo() {
        verificarEstoqueBaixoUseCase.verificar();
    }

    @Scheduled(cron = "0 0 1 * * *")
    public void verificarVencimentos() {
        verificarVencimentoMaterialUseCase.verificar(LocalDate.now());
    }
}
