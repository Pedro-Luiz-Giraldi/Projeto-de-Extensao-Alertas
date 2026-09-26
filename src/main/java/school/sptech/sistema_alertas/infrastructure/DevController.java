package school.sptech.sistema_alertas.infrastructure;

import java.time.LocalDateTime;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import school.sptech.sistema_alertas.application.port.in.VerificarPrazoSolicitacoesUseCase;

@Profile("dev")
@RestController
@RequestMapping("/dev")
public class DevController {

    private final VerificarPrazoSolicitacoesUseCase verificarPrazoSolicitacoesUseCase;

    public DevController(VerificarPrazoSolicitacoesUseCase verificarPrazoSolicitacoesUseCase) {
        this.verificarPrazoSolicitacoesUseCase = verificarPrazoSolicitacoesUseCase;
    }

    @PostMapping("/verificar-prazos")
    public void verificarPrazos() {
        verificarPrazoSolicitacoesUseCase.verificar(LocalDateTime.now());
    }
}
